package it.epicode.eShop.auth;

import it.epicode.eShop.services.CartSvc;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CartSvc cartSvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Transactional
    public AppUser registerUser(RegisterRequest registerRequest, Set<Role> roles) {
        if (appUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username già in uso");
        }


        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setEmail(registerRequest.getEmail());
        appUser.setNome(registerRequest.getNome());
        appUser.setCognome(registerRequest.getCognome());
        appUser.setRoles(roles);

        appUserRepository.save(appUser);

        cartSvc.create(appUser);


        return appUser;
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AuthResponse authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            AppUser appUser = loadUserByUsername(username);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtTokenUtil.generateToken(userDetails));

            authResponse.setUser(appUser);

            return authResponse;

        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }


    public AppUser loadUserByUsername(String username)  {
        AppUser appUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utente con username: " + username + "non trovato"));


        return appUser;
    }

    public AppUser updateToSeller(Long idUser) {
        AppUser appUser = appUserRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utente con id: " + idUser + "non trovato"));

        Set<Role> roles = new HashSet<>(appUser.getRoles());
        roles.add(Role.ROLE_SELLER);
        appUser.setRoles(roles);

        return appUserRepository.save(appUser);
    }

    public AppUser updateToAdmin(Long idUser) {
        AppUser appUser = appUserRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utente con id: " + idUser + "non trovato"));

        Set<Role> roles = new HashSet<>(appUser.getRoles());
        roles.add(Role.ROLE_ADMIN);
        appUser.setRoles(roles);

        return appUserRepository.save(appUser);
    }

    public AppUser removeSeller(Long idUser) {
        AppUser appUser = appUserRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utente con id: " + idUser + "non trovato"));

        Set<Role> roles = new HashSet<>(appUser.getRoles());
        roles.remove(Role.ROLE_SELLER);
        appUser.setRoles(roles);

        return appUserRepository.save(appUser);
    }

    public AppUser removeAdmin(Long idUser) {
        AppUser appUser = appUserRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utente con id: " + idUser+ "non trovato"));

        Set<Role> roles = new HashSet<>(appUser.getRoles());
        roles.remove(Role.ROLE_ADMIN);
        appUser.setRoles(roles);

        return appUserRepository.save(appUser);
    }

    public List<AppUser> findAll() {
       return appUserRepository.findAll();
    }
}
