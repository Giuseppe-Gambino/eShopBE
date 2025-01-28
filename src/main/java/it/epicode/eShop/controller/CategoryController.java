package it.epicode.eShop.controller;

import it.epicode.eShop.dto.CategoryDTO;
import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Category;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.services.CategorySvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategorySvc categorySvc;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> products = categorySvc.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categorySvc.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO) {
        Category createdCategory = categorySvc.create(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categorySvc.update(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categorySvc.delete(id);
        return new ResponseEntity<>("Categoria eliminata correttamente!", HttpStatus.NO_CONTENT);
    }
}
