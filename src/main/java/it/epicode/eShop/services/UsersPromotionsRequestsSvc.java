package it.epicode.eShop.services;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.auth.AppUserService;
import it.epicode.eShop.dto.UsersPromotionsRequestsDTO;
import it.epicode.eShop.entity.UsersPromotionsRequests;
import it.epicode.eShop.repo.UsersPromotionsRequestsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersPromotionsRequestsSvc {
    private final UsersPromotionsRequestsRepository usersPromotionsRequestsRepository;
    private final AppUserService appUserService;

    public UsersPromotionsRequests create(UsersPromotionsRequestsDTO usersPromotionsRequestsDTO, String username) {

        AppUser appUser = appUserService.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException("Utente con username: " + username + "non trovato"));

        UsersPromotionsRequests usersPromotionsRequests = new UsersPromotionsRequests();
        BeanUtils.copyProperties(usersPromotionsRequestsDTO, usersPromotionsRequests);
        usersPromotionsRequests.setAppUser(appUser);
        usersPromotionsRequests.setCreatedAt(LocalDate.now());
        usersPromotionsRequests.setMark(false);

        return usersPromotionsRequestsRepository.save(usersPromotionsRequests);
    }

    public List<UsersPromotionsRequests> findAll() {
        return usersPromotionsRequestsRepository.findAll();
    }

    public List<UsersPromotionsRequests> findByUsername(String username) {
        List<UsersPromotionsRequests> lista = usersPromotionsRequestsRepository.findByAppUser_Username(username)
                .orElseThrow(()->new EntityNotFoundException("non esistono richieste dall'utente:" + username));
        return lista;
    }

    public void delete(Long id) {
       UsersPromotionsRequests usersPromotionsRequests = usersPromotionsRequestsRepository.findById(id)
               .orElseThrow(()->new EntityNotFoundException("Richiesta con id:" + id + "non trovata"));

       usersPromotionsRequestsRepository.delete(usersPromotionsRequests);
    }

    public UsersPromotionsRequests editMark(Long id,Boolean mark) {
        UsersPromotionsRequests usersPromotionsRequests = usersPromotionsRequestsRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Richiesta con id:" + id + "non trovata"));

        usersPromotionsRequests.setMark(mark);

        return usersPromotionsRequestsRepository.save(usersPromotionsRequests);
    }

}
