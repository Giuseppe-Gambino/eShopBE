package it.epicode.eShop.controller;

import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.services.ProductSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductSvc productSvc;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productSvc.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAll/page")
    public ResponseEntity<Page<Product>> findAllPage(Pageable pageable) {
        Page<Product> products = productSvc.findAllPage(pageable);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productSvc.findById(id);
        return ResponseEntity.ok(product);
    }


    @PostMapping("/{idCategory}")
    public ResponseEntity<Product> create(@PathVariable Long idCategory, @RequestBody ProductDTO productDTO) {
        Product createdProduct = productSvc.create(idCategory,productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping( name = "/products/{id}/images", consumes = {"multipart/form-data"})
    public ResponseEntity<Product> updateProductImages(@RequestParam Long id, @RequestParam List<MultipartFile> images) {
        Product product = productSvc.updateImg(id, images);
        return ResponseEntity.ok(product); // Restituisci il prodotto aggiornato
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
            Product updatedProduct = productSvc.update(id, productDTO);
            return ResponseEntity.ok(updatedProduct);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productSvc.delete(id);
        return new ResponseEntity<>("Cliente eliminato correttamente!",HttpStatus.NO_CONTENT);
    }

}
