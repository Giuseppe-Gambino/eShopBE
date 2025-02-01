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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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
    public ResponseEntity<Product> create(@PathVariable Long idCategory, @RequestBody ProductDTO productDTO, @AuthenticationPrincipal UserDetails user) {
        Product createdProduct = productSvc.create(idCategory,productDTO,user.getUsername());
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/products/{id}/images", consumes = {"multipart/form-data"})
    public ResponseEntity<Product> updateProductImages(@PathVariable Long id, @RequestParam List<MultipartFile> images) {
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

    @GetMapping("/fitraProdotti")
    public ResponseEntity<Page<Product>> getFilteredProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            Pageable pageable) {

        Page<Product> products = productSvc.getFilteredProducts(name, category, minPrice, maxPrice, pageable);
        return ResponseEntity.ok(products);
    }

}
