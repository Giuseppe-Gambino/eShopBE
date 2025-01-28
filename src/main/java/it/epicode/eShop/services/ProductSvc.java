package it.epicode.eShop.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductSvc {
    private final ProductRepository productRepository;

    public Product save(ProductDTO productDTO) {
        Product product = new Product();
        return product;
    }


}
