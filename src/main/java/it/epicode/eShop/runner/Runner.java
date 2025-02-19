package it.epicode.eShop.runner;

import it.epicode.eShop.auth.AppUser;
import it.epicode.eShop.auth.AppUserService;
import it.epicode.eShop.dto.CategoryDTO;
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
import java.util.Map;

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
                categorySvc.create(nome);
            }
        }

        AppUser appUser = appUserService.findByUsername("admin").orElseThrow(()->new EntityNotFoundException("Utente non trovato"));

        // Creazione della lista di prodotti
        List<Product> products = new ArrayList<>();

        // Prodotto 1
        Product product1 = new Product();
        product1.setName("Wooting 60HE");
        product1.setDescription("Tastiera analogica da gaming con layout del 60% e switch hotswap");
        product1.setPrice(new BigDecimal("189.99"));
        product1.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/MAJl103M9bI/maxresdefault.jpg","https://wooting-web-files.ams3.digitaloceanspaces.com/public/open-graph.png","https://www.techpowerup.com/img/4VsnYJTnz4ZctaW7.jpg"));
        product1.setReseller(appUser);
        product1.setCategory(categorySvc.findById(1L));
        product1.setCreatedAt(LocalDate.now());
        product1.getPriceHistory().put(LocalDate.now(), new BigDecimal("189.99"));
        products.add(product1);

        // Prodotto 2
        Product product2 = new Product();
        product2.setName("G Pro X Superlight 2");
        product2.setDescription("Mouse simmetrico da 60 g con sensore HERO 2, robusto wireless LIGHTSPEED e interruttori LIGHTFORCE.");
        product2.setPrice(new BigDecimal("139.00"));
        product2.setImageUrls(Arrays.asList("https://www.club386.com/wp-content/uploads/2023/08/G-Pro-X-Superlight-2-Design-and-Weight-696x514.jpg","https://hd2.tudocdn.net/1219122?w=824&h=494","https://tpucdn.com/review/logitech-g-pro-x-superlight-2/images/title.jpg"));
        product2.setReseller(appUser);
        product2.setCategory(categorySvc.findById(9L));
        product2.setCreatedAt(LocalDate.now());
        product2.getPriceHistory().put(LocalDate.now(), new BigDecimal("139.00"));
        products.add(product2);

        // Prodotto 3
        Product product3 = new Product();
        product3.setName("Pulsar Xlite V4 Mini");
        product3.setDescription("Mouse ultraleggero con sensore Pulsar XS-1 Sensor");
        product3.setTitleSeconda("Sensore Pulsar XS-1");
        product3.setDescriptionSeconda("Il sensore XS-1, esclusivo di Pulsar, vanta un'impressionante risoluzione di 32.000 dpi, una velocità di tracciamento di 750 IPS e un'accelerazione 50G, che fornisce precisione e velocità senza pari.");
        product3.setTitleTerza("ULTRA LIGHT WEIGHT");
        product3.setDescriptionTerza("'Simple but not simpler.' Abbiamo tenuto sempre a mente questo principio quando abbiamo progettato la struttura Xlite. Abbiamo realizzato una struttura il più semplice possibile, senza però comprometterne la resistenza. Il risultato? Un mouse da gaming ad alte prestazioni, più leggero di un uovo che tieni in mano.");
        product3.setPrice(new BigDecimal("99.95"));
        product3.setImageUrls(Arrays.asList("https://www.pulsar.gg/cdn/shop/files/Xlite_v4_Black_Galleryimage_002_G.jpg?v=1721002495","https://www.pulsar.gg/cdn/shop/files/Pulsar-Xlite-v4-Gaming-Mouse_size1-Black_001_large.png?v=1736756231","https://www.pulsar.gg/cdn/shop/files/Pulsar-Xlite-v4-Gaming-Mouse_size1-Black_002_large.png?v=1736756231","https://m.media-amazon.com/images/I/71+hpx-ITqL._AC_SL1500_.jpg","https://m.media-amazon.com/images/I/71+S0UBFV9L._AC_SL1500_.jpg"));
        product3.setReseller(appUser);
        product3.setCategory(categorySvc.findById(9L));
        product3.setCreatedAt(LocalDate.of(2024, 10, 1));
        product3.getPriceHistory().putAll(Map.of(
                LocalDate.of(2024, 10, 1), new BigDecimal("79.95"),
                LocalDate.of(2024, 12, 1), new BigDecimal("119.95"),
                LocalDate.of(2025, 2, 1), new BigDecimal("99.95"),
                LocalDate.of(2025, 2, 9), new BigDecimal("114.95"),
                LocalDate.now(), new BigDecimal("99.95")
        ));

        products.add(product3);

        // Prodotto 4
        Product product4 = new Product();
        product4.setName("Superglide Glass");
        product4.setDescription("Mousepad di vetro premium. Il massimo delle prestazioni e della durata, con uno strato superiore di vetro di alluminosilicato super forte.");
        product4.setTitleSeconda("Vetro di Alluminosilicato");
        product4.setDescriptionSeconda("Il vetro di alluminosilicato offre una superficie liscia e resistente.");
        product4.setTitleTerza("Prestazioni e Durata");
        product4.setDescriptionTerza("Progettato per durare nel tempo e offrire prestazioni costanti.");
        product4.setPrice(new BigDecimal("149.95"));
        product4.setImageUrls(Arrays.asList("https://m.media-amazon.com/images/S/aplus-media-library-service-media/11daf5b3-b0de-453e-a72f-ec9bd6f083c3.__CR0,0,970,600_PT0_SX970_V1___.jpg", "https://m.media-amazon.com/images/S/aplus-media-library-service-media/20e2bf92-136e-410f-9d1d-701e28e962cd.__CR0,0,970,300_PT0_SX970_V1___.jpg"));
        product4.setReseller(appUser);
        product4.setCategory(categorySvc.findById(31L));
        product4.setCreatedAt(LocalDate.now());
        product4.getPriceHistory().put(LocalDate.now(), new BigDecimal("149.95"));
        products.add(product4);

        // Prodotto 5
        Product product5 = new Product();
        product5.setName("Aqua Control+");
        product5.setDescription("X-Raypad Mousepad in tessuto di alta qualità per un'ottima scorrevolezza e controllo");
        product5.setTitleSeconda("Tessuto di Alta Qualità");
        product5.setDescriptionSeconda("Il tessuto di alta qualità offre una scorrevolezza eccezionale.");
        product5.setTitleTerza("Controllo Preciso");
        product5.setDescriptionTerza("Progettato per offrire un controllo preciso durante il gioco.");
        product5.setPrice(new BigDecimal("48.99"));
        product5.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/d_i50gv8NFc/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBwJnGGEp2BbBPBpOaZ0tob0u6LCA", "https://shop.x-raypad.com/wp-content/uploads/2017/02/aqua-control-plus-white-version.jpg"));
        product5.setReseller(appUser);
        product5.setCategory(categorySvc.findById(31L));
        product5.setCreatedAt(LocalDate.now());
        product5.getPriceHistory().put(LocalDate.now(), new BigDecimal("48.99"));
        products.add(product5);

        // Prodotto 6
        Product product6 = new Product();
        product6.setName("Artisan Hien X-Soft");
        product6.setDescription("Mousepad premium con superficie fine e morbida per movimenti controllati e precisi");
        product6.setTitleSeconda("Superficie In Tessuto");
        product6.setDescriptionSeconda("La superficie offre un controllo preciso e una scorrevolezza uniforme, grazie alla trama fine e la gomma morbida.");
        product6.setTitleTerza("Materiali Premium");
        product6.setDescriptionTerza("Realizzato artigianalmente in Giappone con materiali di alta qualità per una lunga durata e prestazioni costanti.");
        product6.setPrice(new BigDecimal("79.99"));
        product6.setImageUrls(Arrays.asList("https://ennuii.com/wp-content/uploads/2024/07/hien-black-1.jpg","https://ausmodshop.com/cdn/shop/collections/FSn1aBEaMAAthwn.jpg?v=1712655318","https://i.ytimg.com/vi/indGaiEFpJ0/maxresdefault.jpg","https://pbs.twimg.com/media/EsTqr2iUwAAhgi2.jpg:large","https://preview.redd.it/review-of-artisan-hien-xl-soft-after-2-years-of-hardcore-use-v0-th73ez1dwyha1.jpg?width=2961&format=pjpg&auto=webp&s=102738d73ca954b4c31ee14ba0ad98912cd0700b","https://preview.redd.it/review-of-artisan-hien-xl-soft-after-2-years-of-hardcore-use-v0-7mp3s7slrzha1.jpg?width=2592&format=pjpg&auto=webp&s=af579394249729406c765d84f2d828d75ba87bdc"));
        product6.setReseller(appUser);
        product6.setCategory(categorySvc.findById(31L));
        product6.setCreatedAt(LocalDate.now());
        product6.getPriceHistory().put(LocalDate.now(), new BigDecimal("79.99"));
        products.add(product6);

        // Prodotto 7
        Product product7 = new Product();
        product7.setName("HS80 MAX WIRELESS");
        product7.setDescription("Cuffie wireless con audio surround e illuminazione RGB");
        product7.setTitleSeconda("AUDIO SPAZIALE IMMERSIVO DOLBY ATMOS");
        product7.setDescriptionSeconda("Non perderti nemmeno la minima sfumatura acustica mentre giochi e riproduci contenuti multimediali con i driver in neodimio ad alta densita da 50 mm con tuning di precisione, con tecnologia Dolby Atmos e un microfono omnidirezionale a prestazioni elevate.");
        product7.setTitleTerza("CONNETTIVITA MULTIPIAITAFORMA");
        product7.setDescriptionTerza("Sfrutta una qualità audio eccezionale con la connessione wireless a 2,4 GHz e il Bluetooth, non perderti nulla grazie alla compatibilita multipiattaforma con PC, Mac,PlayStation, dispositivi mobili e altro ancora..");
        product7.setPrice(new BigDecimal("149.99"));
        product7.setImageUrls(Arrays.asList("https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_02.webp","https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_14.webp","https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_01.webp","https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_06.webp","https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_05.webp","https://assets.corsair.com/image/upload/c_pad,q_85,h_1100,w_1100,f_auto/products/Gaming-Headsets/hs80-max-wireless/steel-grey/HS80_MAX_WIRELESS_STEEL_GRAY_03.webp"));
        product7.setReseller(appUser);
        product7.setCategory(categorySvc.findById(9L));
        product7.setCreatedAt(LocalDate.of(2024, 9, 12));
        product7.getPriceHistory().putAll(Map.of(
                LocalDate.of(2024, 9, 12), new BigDecimal("199.95"),
                LocalDate.of(2024, 12, 1), new BigDecimal("189.95"),
                LocalDate.of(2025, 2, 1), new BigDecimal("169.95"),
                LocalDate.of(2025, 2, 9), new BigDecimal("114.95"),
                LocalDate.now(), new BigDecimal("149.99")
        ));
        products.add(product7);

        // Prodotto 8
        Product product8 = new Product();
        product8.setName("Saturn Keyboard");
        product8.setDescription("Tastiera meccanica con retroilluminazione RGB e tasti programmabili");
        product8.setTitleSeconda("Retroilluminazione RGB");
        product8.setDescriptionSeconda("La retroilluminazione RGB offre un'illuminazione personalizzabile.");
        product8.setTitleTerza("Tasti Programmabili");
        product8.setDescriptionTerza("I tasti programmabili permettono di personalizzare le funzioni della tastiera.");
        product8.setPrice(new BigDecimal("129.99"));
        product8.setImageUrls(Arrays.asList("https://example.com/saturn-keyboard.jpg"));
        product8.setReseller(appUser);
        product8.setCategory(categorySvc.findById(1L));
        product8.setCreatedAt(LocalDate.now());
        product8.getPriceHistory().put(LocalDate.now(), new BigDecimal("129.99"));
        products.add(product8);

        // Prodotto 9
        Product product9 = new Product();
        product9.setName("WLMouse Gaming");
        product9.setDescription("Mouse da gaming wireless con prestazioni elevate e design ergonomico");
        product9.setTitleSeconda("Prestazioni Elevate");
        product9.setDescriptionSeconda("Progettato per offrire prestazioni elevate durante il gioco.");
        product9.setTitleTerza("Design Ergonomico");
        product9.setDescriptionTerza("Il design ergonomico garantisce comfort durante lunghe sessioni di gioco.");
        product9.setPrice(new BigDecimal("69.99"));
        product9.setImageUrls(Arrays.asList("https://example.com/wlmouse-gaming.jpg"));
        product9.setReseller(appUser);
        product9.setCategory(categorySvc.findById(9L));
        product9.setCreatedAt(LocalDate.now());
        product9.getPriceHistory().put(LocalDate.now(), new BigDecimal("69.99"));
        products.add(product9);

        // Prodotto 10
        Product product10 = new Product();
        product10.setName("Sennheiser GSP 300");
        product10.setDescription("Cuffie gaming con audio immersivo e comfort prolungato");
        product10.setTitleSeconda("Audio Immersivo");
        product10.setDescriptionSeconda("L'audio immersivo offre un'esperienza di gioco coinvolgente.");
        product10.setTitleTerza("Comfort Prolungato");
        product10.setDescriptionTerza("Progettate per garantire comfort anche durante lunghe sessioni di gioco.");
        product10.setPrice(new BigDecimal("99.99"));
        product10.setImageUrls(Arrays.asList("https://example.com/sennheiser-gsp-300.jpg"));
        product10.setReseller(appUser);
        product10.setCategory(categorySvc.findById(21L));
        product10.setCreatedAt(LocalDate.now());
        product10.getPriceHistory().put(LocalDate.now(), new BigDecimal("99.99"));
        products.add(product10);

        // Salva i prodotti nel database
        productRepository.saveAll(products);
        System.out.println("Database popolato con prodotti iniziali.");
    }
}
