package it.epicode.eShop.runner;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.auth.AppUserService;
import it.epicode.eShop.dto.CategoryDTO;
import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.repo.ProductRepository;
import it.epicode.eShop.services.CategorySvc;
import it.epicode.eShop.services.ProductSvc;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Order(10)
@RequiredArgsConstructor
@Component
public class Runner implements ApplicationRunner {
    private final CategorySvc categorySvc;
    private final ProductSvc productSvc;
    private final ProductRepository productRepository;
    private final AppUserService appUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> categorie = List.of(
                // Tastiere
                "tastiere",
                "meccaniche",
                "membrana",
                "wireless",
                "ergonomiche",
                "TKL",
                "60%",
                "80%",

                // Mouse
                "mouse",
                "gaming",
                "ergonomici",
                "wireless",
                "ottico",
                "laser",

                // Cuffie e Audio
                "cuffie",
                "cancellazione del rumore",
                "wireless",
                "open-back",
                "closed-back",
                "microfoni",
                "audio",

                // Monitor
                "monitor",
                "144Hz",
                "240Hz",
                "4K",
                "ultrawide",
                "curvi",
                "IPS",
                "TN",
                "VA",
                "G-Sync",
                "FreeSync",

                // Mousepad e Superfici
                "mousepad",
                "tessuto",
                "rigidi",
                "ibrido",
                "XXL",
                "RGB",

                // Controller e Joystick
                "controller",
                "volanti",


                // Sedie e Scrivanie Gaming
                "ergonomico"


        );


        for (String nome : categorie) {
            if (!categorySvc.existsByNome(nome)) { // Evita duplicati
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(nome);
                categorySvc.create(categoryDTO);
            }
        }

        AppUser appUser = appUserService.findByUsername("admin").orElseThrow(()->new EntityNotFoundException("Utente non trovato"));

        // Creazione della lista di prodotti
        List<Product> products = new ArrayList<>();

        // Prodotto 1
        Product product1 = new Product();
        product1.setName("Logitech G Pro X");
        product1.setDescription("Tastiera meccanica da gaming con switch intercambiabili");
        product1.setPrice(new BigDecimal("149.99"));
        product1.setImageUrls(Arrays.asList("https://example.com/logitech-g-pro-x.jpg"));
        product1.setReseller(appUser);
        product1.setCategory(categorySvc.findById(9L));
        product1.setCreatedAt(LocalDate.now());
        product1.getPriceHistory().put(LocalDate.now(), new BigDecimal("149.99"));
        products.add(product1);

        // Prodotto 2
        Product product2 = new Product();
        product2.setName("Logitech G502 HERO");
        product2.setDescription("Mouse da gaming con DPI fino a 25.600");
        product2.setPrice(new BigDecimal("79.99"));
        product2.setImageUrls(Arrays.asList("https://example.com/logitech-g502-hero.jpg"));
        product2.setReseller(appUser);
        product2.setCategory(categorySvc.findById(9L));
        product2.setCreatedAt(LocalDate.now());
        product2.getPriceHistory().put(LocalDate.now(), new BigDecimal("79.99"));
        products.add(product2);

        // Prodotto 3
        Product product3 = new Product();
        product3.setName("Pulsar Xlite V2");
        product3.setDescription("Mouse ultraleggero con sensore PAW3370");
        product3.setPrice(new BigDecimal("89.99"));
        product3.setImageUrls(Arrays.asList("https://example.com/pulsar-xlite-v2.jpg"));
        product3.setReseller(appUser);
        product3.setCategory(categorySvc.findById(9L));
        product3.setCreatedAt(LocalDate.now());
        product3.getPriceHistory().put(LocalDate.now(), new BigDecimal("89.99"));
        products.add(product3);

        // Prodotto 4
        Product product4 = new Product();
        product4.setName("Wooting One");
        product4.setDescription("Tastiera analogica per gaming con tecnologia a pressione");
        product4.setPrice(new BigDecimal("199.99"));
        product4.setImageUrls(Arrays.asList("https://example.com/wooting-one.jpg"));
        product4.setReseller(appUser);
        product4.setCategory(categorySvc.findById(9L));
        product4.setCreatedAt(LocalDate.now());
        product4.getPriceHistory().put(LocalDate.now(), new BigDecimal("199.99"));
        products.add(product4);

        // Prodotto 5
        Product product5 = new Product();
        product5.setName("X-Raypad Gamma");
        product5.setDescription("Mousepad in tessuto di alta qualità per un'ottima scorrevolezza");
        product5.setPrice(new BigDecimal("29.99"));
        product5.setImageUrls(Arrays.asList("https://example.com/x-raypad-gamma.jpg"));
        product5.setReseller(appUser);
        product5.setCategory(categorySvc.findById(32L));
        product5.setCreatedAt(LocalDate.now());
        product5.getPriceHistory().put(LocalDate.now(), new BigDecimal("29.99"));
        products.add(product5);

        // Prodotto 6
        Product product6 = new Product();
        product6.setName("Artisan Hien");
        product6.setDescription("Mousepad premium con superficie fine per movimenti rapidi");
        product6.setPrice(new BigDecimal("59.99"));
        product6.setImageUrls(Arrays.asList("https://example.com/artisan-hien.jpg"));
        product6.setReseller(appUser);
        product6.setCategory(categorySvc.findById(32L));
        product6.setCreatedAt(LocalDate.now());
        product6.getPriceHistory().put(LocalDate.now(), new BigDecimal("59.99"));
        products.add(product6);

        // Prodotto 7
        Product product7 = new Product();
        product7.setName("Logitech G733");
        product7.setDescription("Cuffie wireless con audio surround e illuminazione RGB");
        product7.setPrice(new BigDecimal("129.99"));
        product7.setImageUrls(Arrays.asList("https://example.com/logitech-g733.jpg"));
        product7.setReseller(appUser);
        product7.setCategory(categorySvc.findById(9L));
        product7.setCreatedAt(LocalDate.now());
        product7.getPriceHistory().put(LocalDate.now(), new BigDecimal("129.99"));
        products.add(product7);

        // Prodotto 8
        Product product8 = new Product();
        product8.setName("Saturn Keyboard");
        product8.setDescription("Tastiera meccanica con retroilluminazione RGB e tasti programmabili");
        product8.setPrice(new BigDecimal("129.99"));
        product8.setImageUrls(Arrays.asList("https://example.com/saturn-keyboard.jpg"));
        product8.setReseller(appUser);
        product8.setCategory(categorySvc.findById(9L));
        product8.setCreatedAt(LocalDate.now());
        product8.getPriceHistory().put(LocalDate.now(), new BigDecimal("129.99"));
        products.add(product8);

        // Prodotto 9
        Product product9 = new Product();
        product9.setName("WLMouse Gaming");
        product9.setDescription("Mouse da gaming wireless con prestazioni elevate e design ergonomico");
        product9.setPrice(new BigDecimal("69.99"));
        product9.setImageUrls(Arrays.asList("https://example.com/wlmouse-gaming.jpg"));
        product9.setReseller(appUser);
        product9.setCategory(categorySvc.findById(32L));
        product9.setCreatedAt(LocalDate.now());
        product9.getPriceHistory().put(LocalDate.now(), new BigDecimal("69.99"));
        products.add(product9);

        // Prodotto 10
        Product product10 = new Product();
        product10.setName("Sennheiser GSP 300");
        product10.setDescription("Cuffie gaming con audio immersivo e comfort prolungato");
        product10.setPrice(new BigDecimal("99.99"));
        product10.setImageUrls(Arrays.asList("https://example.com/sennheiser-gsp-300.jpg"));
        product10.setReseller(appUser);
        product10.setCategory(categorySvc.findById(9L));
        product10.setCreatedAt(LocalDate.now());
        product10.getPriceHistory().put(LocalDate.now(), new BigDecimal("99.99"));
        products.add(product10);

        // Salva i prodotti nel database
        productRepository.saveAll(products);
        System.out.println("Database popolato con prodotti iniziali.");

    }
}
