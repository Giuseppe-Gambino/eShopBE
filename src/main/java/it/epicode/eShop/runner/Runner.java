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
        product1.setDimensioni("Formato compatto, circa 18x6 cm");
        product1.setFeature("Tecnologia analogica a portata completa per una risposta immediata");
        product1.setDettagli("Costruita con materiali di alta qualità e design ergonomico");

        product1.setTitleSeconda("INPUT ANALOGICO a portata completa.");
        product1.setDescriptionSeconda("Il wooting 60HE rileva il movimento completo dell'interruttore con precisione di 0,1 mm dall'inizio alla fine. Ogni singola chiave emette un segnale analogico che può essere utilizzato per numerose funzionalità che migliorano l'esperienza di battitura e di gioco.");
        product1.setTitleTerza("RAPID TRIGGER");
        product1.setDescriptionTerza("riduci il tempo di risposta con la funzione Rapid Trigger. Questa funzione ti permette di impostare il punto di attivazione dell'interruttore in qualsiasi punto della corsa dell'interruttore.");

        product1.setPrice(new BigDecimal("189.99"));
        product1.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/MAJl103M9bI/maxresdefault.jpg","https://wooting-web-files.ams3.digitaloceanspaces.com/public/open-graph.png","https://www.techpowerup.com/img/4VsnYJTnz4ZctaW7.jpg","https://preview.redd.it/heads-up-regarding-wootings-new-rappy-snappy-feature-v0-0mo7izf4fboc1.gif?width=800&auto=webp&s=de31883061f2b59c6826852128f3144d3d870f12","https://tpucdn.com/review/wooting-80he-hall-effect-analog-gaming-keyboard/images/gif-1.gif","https://tpucdn.com/review/wooting-80he-hall-effect-analog-gaming-keyboard/images/gif-2.gif"));
        product1.setReseller(appUser);
        product1.setCategory(categorySvc.findById(1L));
        product1.setCreatedAt(LocalDate.now());
        product1.getPriceHistory().putAll(Map.of(
                LocalDate.of(2023, 10, 3), new BigDecimal("209.90"),
                LocalDate.of(2024, 9, 5), new BigDecimal("199.95"),
                LocalDate.now(), new BigDecimal("189.99")
        ));
        products.add(product1);

        // Prodotto 2
        Product product2 = new Product();
        product2.setName("G Pro X Superlight 2");
        product2.setDescription("Mouse simmetrico da 60 g con sensore HERO 2, robusto wireless LIGHTSPEED e interruttori LIGHTFORCE.");
        product2.setDimensioni("Circa 12x7x4 cm");
        product2.setFeature("Sensore HERO 2 per un tracciamento di precisione elevata");
        product2.setDettagli("Design leggero e confortevole per sessioni di gioco prolungate");

        product2.setTitleSeconda("SENSORE HERO 2");
        product2.setDescriptionSeconda("Precisione di livello professionale con il sensore più avanzato per il gaming. Tracciamento affidabile con oltre 888+ IPS e fino a 44.000 DPI.");
        product2.setTitleTerza("LIGHTSPEED WIRELESS");
        product2.setDescriptionTerza("Prova le prestazioni e l'affidabilità senza pari del wireless LIGHTSPEED con aggiornamento fino a 8 kHz. Progettato per i tornei, garantisce un'affidabilità totale anche in ambienti RF estremi, permettendoti di concentrarti completamente sul tuo gioco.");
        product2.setPrice(new BigDecimal("139.00"));
        product2.setImageUrls(Arrays.asList("https://www.club386.com/wp-content/uploads/2023/08/G-Pro-X-Superlight-2-Design-and-Weight-696x514.jpg","https://hd2.tudocdn.net/1219122?w=824&h=494","https://tpucdn.com/review/logitech-g-pro-x-superlight-2/images/title.jpg","https://media.ldlc.com/r1600/ld/products/00/06/06/12/LD0006061290.jpg","https://tlggaming.com/image/cache/catalog/products/gaming_mouse/logitech/g_pro_x_superlight_2_all_sku/superlight_2_black_tlg_3-320x320.webp","https://static.cazasouq.com/image/cache/catalog/001-products/Gaming%20Accessories/Mouse/Logitech%20Mouse/Logitech%20G%20pro%20X%20superlight%202%20lightspeed%20White/Logitech%20G%20pro%20X%20superlight%202%20lightspeed%20White1-550x550h.jpg"));
        product2.setReseller(appUser);
        product2.setCategory(categorySvc.findById(9L));
        product2.setCreatedAt(LocalDate.now());
        product2.getPriceHistory().putAll(Map.of(
                LocalDate.of(2023, 10, 3), new BigDecimal("165.95"),
                LocalDate.of(2024, 9, 5), new BigDecimal("160.00"),
                LocalDate.of(2024, 12, 1), new BigDecimal("144.95"),
                LocalDate.of(2025, 2, 9), new BigDecimal("114.95"),
                LocalDate.now(), new BigDecimal("139.00")
        ));
        products.add(product2);

        // Prodotto 3
        Product product3 = new Product();
        product3.setName("Pulsar Xlite V4 Mini");
        product3.setDescription("Mouse ultraleggero con sensore Pulsar XS-1 Sensor");
        product3.setDimensioni("Le dimensioni del prodotto sono 30x20x10 cm.");
        product3.setFeature("Il mouse è dotato di un sensore Pulsar XS-1 con risoluzione di 32.000 dpi, include funzionalità avanzate come il controllo tramite software e la connettività Wireless.");
        product3.setDettagli("Realizzato con materiali di alta qualità, il prodotto è resistente e durevole.");
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
        product4.setDimensioni("Il mousepad ha dimensioni di 30x20 cm.");
        product4.setFeature("Super scorrevole e resistente grazie alla superficie in vetro di alluminosilicato.");
        product4.setDettagli("Materiale di alta qualità, resistente e durevole. Superficie liscia e resistente, progettata per durare nel tempo e offrire prestazioni costanti.");
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
        product7.setCategory(categorySvc.findById(14L));
        product7.setCreatedAt(LocalDate.of(2024, 9, 12));
        product7.getPriceHistory().putAll(Map.of(
                LocalDate.of(2024, 9, 12), new BigDecimal("199.95"),
                LocalDate.of(2024, 12, 1), new BigDecimal("189.95"),
                LocalDate.of(2025, 2, 1), new BigDecimal("169.95"),
                LocalDate.of(2025, 2, 9), new BigDecimal("114.95"),
                LocalDate.now(), new BigDecimal("149.99")
        ));
        products.add(product7);

        Product product8 = new Product();
        product8.setName("Corsair MM700 RGB");
        product8.setDescription("Mousepad esteso con illuminazione RGB e superficie in tessuto.");
        product8.setTitleSeconda("Superficie in Tessuto");
        product8.setDescriptionSeconda("Fornisce un'ottima precisione di tracciamento e scorrevolezza.");
        product8.setTitleTerza("Retroilluminazione RGB");
        product8.setDescriptionTerza("Illuminazione personalizzabile con effetti dinamici multicolore.");
        product8.setPrice(new BigDecimal("59.99"));
        product8.setImageUrls(Arrays.asList("https://assets.corsair.com/image/upload/c_pad,q_auto,h_1024,w_1024,f_auto/products/Gaming-Mousepads/base-mm700-rgb-config/Gallery/MM700_RGB_02.webp","https://img.evetech.co.za/repository/ProductImages/corsair-mm700-rgb-cloth-mouse-pad-extended-1000px-v0012.webp",
                "https://assets.corsair.com/image/upload/c_pad,q_auto,h_1024,w_1024,f_auto/products/Gaming-Mousepads/base-mm700-rgb-config/Gallery/MM700_RGB_25.webp","https://m.media-amazon.com/images/I/912R9CFMclL._AC_UF350,350_QL80_.jpg"));
        product8.setReseller(appUser);
        product8.setCategory(categorySvc.findById(33L)); // Mousepad
        product8.setCreatedAt(LocalDate.now());
        product8.getPriceHistory().put(LocalDate.now(), new BigDecimal("59.99"));
        products.add(product8);

        // Prodotto 9
        Product product9 = new Product();
        product9.setName("WLMouse Beast X 8k");
        product9.setDescription("Mouse da gaming wireless con prestazioni elevate e design ergonomico");
        product9.setTitleSeconda("Prestazioni Elevate");
        product9.setDescriptionSeconda("Progettato per offrire prestazioni elevate durante il gioco.");
        product9.setTitleTerza("Design Ergonomico");
        product9.setDescriptionTerza("Il design ergonomico garantisce comfort durante lunghe sessioni di gioco.");
        product9.setPrice(new BigDecimal("69.99"));
        product9.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/HfgjiwFnfrc/maxresdefault.jpg","https://external-preview.redd.it/wlmouse-beastx-8k-collab-has-arrived-v0-kChSsB7EcRTiMbbmD7eO8DNtU73i_2DIQlp-muNz4oM.jpg?auto=webp&s=1b9da5607a5d75a89202a4845c74dc8ec965376d","https://ausmodshop.com/cdn/shop/files/M-8K.webp?v=1722850004&width=1008"));
        product9.setReseller(appUser);
        product9.setCategory(categorySvc.findById(9L));
        product9.setCreatedAt(LocalDate.now());
        product9.getPriceHistory().put(LocalDate.now(), new BigDecimal("69.99"));
        products.add(product9);

        // Prodotto 10
        Product product10 = new Product();
        product10.setName("Sennheiser HD660S");
        product10.setDescription("Cuffie gaming con audio immersivo e comfort prolungato");
        product10.setTitleSeconda("Audio Immersivo");
        product10.setDescriptionSeconda("L'audio immersivo offre un'esperienza di gioco coinvolgente.");
        product10.setTitleTerza("Comfort Prolungato");
        product10.setDescriptionTerza("Progettate per garantire comfort anche durante lunghe sessioni di gioco.");
        product10.setPrice(new BigDecimal("99.99"));
        product10.setImageUrls(Arrays.asList("https://www.avmagazine.it/immagini/sennheiser_hd660s2.jpg","https://videosell.it/cdn/shop/products/x1_desktop_HD_660S_Product_shot_detail_02_web_1024x.jpg?v=1648800823","https://www.techpowerup.com/review/sennheiser-hd-660-s/images/lifestyle-3.jpg"));
        product10.setReseller(appUser);
        product10.setCategory(categorySvc.findById(21L));
        product10.setCreatedAt(LocalDate.now());
        product10.getPriceHistory().put(LocalDate.now(), new BigDecimal("99.99"));
        products.add(product10);

        Product product11 = new Product();
        product11.setName("HyperX Alloy FPS Pro");
        product11.setDescription("Compact mechanical keyboard with red switches.");
        product11.setTitleSeconda("Mechanical Switches");
        product11.setDescriptionSeconda("Offers linear keypress for fast gaming.");
        product11.setTitleTerza("Compact Layout");
        product11.setDescriptionTerza("Tenkeyless design saves space on the desk.");
        product11.setPrice(new BigDecimal("89.99"));
        product11.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/zkEXQr4UMvk/maxresdefault.jpg","https://cdn.panacompu.com/cdn-img/pv/hyperx-alloy-fps-pro-mechanical-keyboard-red-switches.jpg?width=550&height=400&fixedwidthheight=false","https://i0.wp.com/play3r.net/wp-content/uploads/2017/03/cherry-red-key-switch.jpg?ssl=1"));
        product11.setReseller(appUser);
        product11.setCategory(categorySvc.findById(1L));
        product11.setCreatedAt(LocalDate.now());
        product11.getPriceHistory().put(LocalDate.now(), new BigDecimal("89.99"));
        products.add(product11);

        Product product12 = new Product();
        product12.setName("SteelSeries Rival 600");
        product12.setDescription("Dual sensor mouse with adjustable weight system.");
        product12.setTitleSeconda("Dual Sensor System");
        product12.setDescriptionSeconda("Combines true 1-to-1 tracking with depth sensing.");
        product12.setTitleTerza("Adjustable Weights");
        product12.setDescriptionTerza("Customize the weight for a tailored feel.");
        product12.setPrice(new BigDecimal("79.95"));
        product12.setImageUrls(Arrays.asList("https://www.dhresource.com/webp/m/0x0/f2/albu/g13/M01/20/33/rBVak19J_Q2AHdbGAAL14SSqGmY198.jpg",
                "https://cdn.mos.cms.futurecdn.net/aB9JoChCuhTjFAa2j8482k-1200-80.jpg"));
        product12.setReseller(appUser);
        product12.setCategory(categorySvc.findById(9L));
        product12.setCreatedAt(LocalDate.now());
        product12.getPriceHistory().put(LocalDate.now(), new BigDecimal("79.95"));
        products.add(product12);

        Product product13 = new Product();
        product13.setName("Razer Kraken Edition");
        product13.setDescription("RGB headset with cat ear design.");
        product13.setTitleSeconda("Chroma RGB");
        product13.setDescriptionSeconda("Customizable RGB ear lighting.");
        product13.setTitleTerza("Comfortable Fit");
        product13.setDescriptionTerza("Soft ear cushions for long gaming sessions.");
        product13.setPrice(new BigDecimal("129.00"));
        product13.setImageUrls(Arrays.asList("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmj-aGtGd-rXKTRcRoWIQ4VyW_D4BTwuv2DA&s",
                "https://assets2.razerzone.com/images/pnx.assets/cdb5cdc59332c751db1b05b9fd38f613/razer-kraken-v3-triforce-mobile-768x460.jpg"));
        product13.setReseller(appUser);
        product13.setCategory(categorySvc.findById(14L));
        product13.setCreatedAt(LocalDate.now());
        product13.getPriceHistory().put(LocalDate.now(), new BigDecimal("129.00"));
        products.add(product13);

        // Prodotto 14: Microfono
        Product product14 = new Product();
        product14.setName("Rode NT-USB Mini");
        product14.setDescription("Microfono USB compatto con audio di alta qualit\\`a.");
        product14.setTitleSeconda("Compatto e Versatile");
        product14.setDescriptionSeconda("Progettato per streaming e podcast con uscita USB.");
        product14.setTitleTerza("Filtro Pop Integrato");
        product14.setDescriptionTerza("Riduce i rumori di fondo e migliora la chiarezza vocale.");
        product14.setPrice(new BigDecimal("119.99"));
        product14.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/lM020uMNeJs/maxresdefault.jpg","https://cdn.studio-microphone.com/wp-content/uploads/2022/07/test-du-micro-Rode-NT-USB-mini.png","https://cdn.studio-microphone.com/wp-content/uploads/2022/07/test-et-avis-du-Rode-NT-USB-mini.png"));
        product14.setReseller(appUser);
        product14.setCategory(categorySvc.findById(16L)); // Adatta l'ID in base alla tua tabella categorie
        product14.setCreatedAt(LocalDate.now());
        product14.getPriceHistory().put(LocalDate.now(), new BigDecimal("119.99"));
        products.add(product14);

// Product 15: Tastiera
        Product product15 = new Product();
        product15.setName("Razer Huntsman V2");
        product15.setDescription("Tastiera gaming con switch ottici e tempi di risposta ultrarapidi.");
        product15.setTitleSeconda("Switch Ottici Razer");
        product15.setDescriptionSeconda("Offrono un'attuazione fulminea e una maggiore durata.");
        product15.setTitleTerza("Retroilluminazione Chroma");
        product15.setDescriptionTerza("Personalizza l'illuminazione con milioni di combinazioni di colore.");
        product15.setPrice(new BigDecimal("169.99"));
        product15.setImageUrls(Arrays.asList("https://assets2.razerzone.com/images/og-image/razer-huntsman-v2-analog-OGimage-1200x630.jpg",
                "https://assets2.razerzone.com/images/pnx.assets/13b1fd279c9e51bc3fb9ca44364615c1/razer-huntsman-v2-refresh-polling-rate-desktop.webp"));
        product15.setReseller(appUser);
        product15.setCategory(categorySvc.findById(1L)); // Tastiere
        product15.setCreatedAt(LocalDate.now());
        product15.getPriceHistory().put(LocalDate.now(), new BigDecimal("169.99"));
        products.add(product15);

// Prodotto 16: Switch Meccanici
        Product product16 = new Product();
        product16.setName("Cherry MX Red Switch");
        product16.setDescription("Set di switch lineari con bassa forza di attuazione.");
        product16.setTitleSeconda("Linea Pura");
        product16.setDescriptionSeconda("Movimento privo di scatti per una digitazione fluida.");
        product16.setTitleTerza("Lunga Durata");
        product16.setDescriptionTerza("Garantiti per milioni di pressioni senza perdita di qualit\\`a.");
        product16.setPrice(new BigDecimal("39.99"));
        product16.setImageUrls(Arrays.asList("https://www.cherry.de/fileadmin/_processed_/8/9/csm_MX2A-L1NA_MX-RED-hero_006c8970a4.jpg","https://keyspace.store/cdn/shop/files/cherry_mx_red.gif?v=1661288474&width=700"));
        product16.setReseller(appUser);
        product16.setCategory(categorySvc.findById(2L)); // Adatta l'ID in base alla tua tabella categorie
        product16.setCreatedAt(LocalDate.now());
        product16.getPriceHistory().put(LocalDate.now(), new BigDecimal("39.99"));
        products.add(product16);

        Product product17 = new Product();
        product17.setName("Elgato Wave:3");
        product17.setDescription("Microfono a condensatore USB con tecnologia proprietaria Clipguard.");
        product17.setTitleSeconda("Qualità Studio");
        product17.setDescriptionSeconda("Registra podcast e streaming con resa audio professionale.");
        product17.setTitleTerza("Clipguard");
        product17.setDescriptionTerza("Evita distorsioni e clipping con tecnologia avanzata.");
        product17.setPrice(new BigDecimal("159.99"));
        product17.setImageUrls(Arrays.asList("https://res.cloudinary.com/elgato-pwa/image/upload/v1709024706/Products/10MAB9911/above-the-fold/desktop/wave-3-white-01_desktop_web_v2.jpg","https://www.gamesvillage.it/wp-content/uploads/2021/06/Elgato-wave-3-01.jpg","https://www.soundguys.com/wp-content/uploads/2020/06/Elgato-Wave-3-controls-detail.jpg"));
        product17.setReseller(appUser);
        product17.setCategory(categorySvc.findById(16L));
        product17.setCreatedAt(LocalDate.now());
        product17.getPriceHistory().put(LocalDate.now(), new BigDecimal("159.99"));
        products.add(product17);

        Product product18 = new Product();
        product18.setName("Logitech G733 Lightspeed");
        product18.setDescription("Cuffie wireless leggere, RGB e con tecnologia Lightspeed.");
        product18.setTitleSeconda("Wireless Lightspeed");
        product18.setDescriptionSeconda("Connessione stabile a 2,4 GHz per bassa latenza.");
        product18.setTitleTerza("Comfort e Stile");
        product18.setDescriptionTerza("Design personalizzabile con fasce intercambiabili.");
        product18.setPrice(new BigDecimal("129.90"));
        product18.setImageUrls(Arrays.asList("https://resource.logitechg.com/w_695,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/g733/g733-feature4-3.png?v=1","https://ae01.alicdn.com/kf/Hbdb961fcde57485a932f184e429656c96.jpg"));
        product18.setReseller(appUser);
        product18.setCategory(categorySvc.findById(14L));
        product18.setCreatedAt(LocalDate.now());
        product18.getPriceHistory().put(LocalDate.now(), new BigDecimal("129.90"));
        products.add(product18);

        Product product19 = new Product();
        product19.setName("Gateron Yellow Switch");
        product19.setDescription("Switch meccanici lineari, ideali per una digitazione fluida.");
        product19.setTitleSeconda("Corsa Lineare");
        product19.setDescriptionSeconda("Movimento continuo e senza feedback tattile.");
        product19.setTitleTerza("Durata Estesa");
        product19.setDescriptionTerza("Testati per milioni di pressioni garantite.");
        product19.setPrice(new BigDecimal("42.99"));
        product19.setImageUrls(Arrays.asList("https://i.ytimg.com/vi/CNCF1B3V48c/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA7rjvn402owHpnq66gu8LGKO9AjQ","https://clickeys.nl/cdn/shop/products/gateron-ks9-yellow-switch-3-pin-10st-clickeys-nl-2.gif?v=1712998896","https://42keebs.eu/wp-content/uploads/2021/01/IMG_20210210_142317.jpg"));
        product19.setReseller(appUser);
        product19.setCategory(categorySvc.findById(2L));
        product19.setCreatedAt(LocalDate.now());
        product19.getPriceHistory().put(LocalDate.now(), new BigDecimal("42.99"));
        products.add(product19);

        Product product20 = new Product();
        product20.setName("ASUS ROG Swift PG279Q");
        product20.setDescription("Monitor gaming 27\" con risoluzione 2560x1440, pannello IPS e refresh rate fino a 165Hz.");
        product20.setTitleSeconda("IPS e 165Hz");
        product20.setDescriptionSeconda("Offre colori vividi, ampio angolo di visione, e un alto refresh rate per gaming fluido.");
        product20.setTitleTerza("G-Sync Compatibile");
        product20.setDescriptionTerza("Riduce il tearing dello schermo per sessioni di gioco senza interruzioni.");
        product20.setPrice(new BigDecimal("599.99"));
        product20.setImageUrls(Arrays.asList("https://dlcdnwebimgs.asus.com/gain/42BD1450-5437-42FD-8F5B-1777841C8EC3/w717/h525",
                "https://cdn.cs.1worldsync.com/syndication/mediaserverredirect/0fdc4b48370ffd4907c4965347e0688e/original.png"));
        product20.setReseller(appUser);
        product20.setCategory(categorySvc.findById(22L)); // Monitor
        product20.setCreatedAt(LocalDate.now());
        product20.getPriceHistory().put(LocalDate.now(), new BigDecimal("599.99"));
        products.add(product20);





        // Salva i prodotti nel database
        productRepository.saveAll(products);
        System.out.println("Database popolato con prodotti iniziali.");
    }
}
