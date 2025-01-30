package it.epicode.eShop.services;


import it.epicode.eShop.cloudinary.CloudinarySvc;
import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.repo.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductSvc {
    private final ProductRepository productRepository;
    private final CategorySvc categorySvc;
    private final CloudinarySvc cloudinarySvc;
    private final CartItemSvc cartItemSvc;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAllPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
           .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));
    }

    public Product create(Long idCategory,ProductDTO productDTO) {

        if (productRepository.existsByName(productDTO.getName())) {
            throw new EntityExistsException("Prodotto esistente o nome prodotto già in uso");
        }


        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        product.setCreatedAt(LocalDate.now());
        product.getPriceHistory().put(LocalDate.now(),product.getPrice());
        product.setCategory(categorySvc.findById(idCategory));

        return productRepository.save(product);
    }


    public Product updateImg(Long id, List<MultipartFile> imageFiles){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));


        for (MultipartFile imageFile : imageFiles) {
            Map<String, Object> uploadResult = cloudinarySvc.uploader(imageFile, "product-images");
            String imageUrl = (String) uploadResult.get("secure_url");

            product.getImageUrls().add(imageUrl);
        }


        return productRepository.save(product);


    }



    public Product update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));

        BigDecimal currentPrice = product.getPrice();
        BigDecimal newPrice = productDTO.getPrice();


        BeanUtils.copyProperties(productDTO, product);

        if (!currentPrice.equals(newPrice)){
            product.getPriceHistory().put(LocalDate.now(),product.getPrice());
        }

        return productRepository.save(product);
    }

    public void delete(Long id) {
        if(!productRepository.existsById(id)){
            throw new EntityNotFoundException("Prodotto non trovato");
        }
        productRepository.deleteById(id);
    }

//    fare i filtri

}
