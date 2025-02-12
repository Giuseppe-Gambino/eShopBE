package it.epicode.eShop.controller;

import it.epicode.eShop.dto.CategoryDTO;
import it.epicode.eShop.entity.Category;
import it.epicode.eShop.services.CategorySvc;
import jakarta.validation.Valid;
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
    public ResponseEntity<Category> create(@Valid @RequestBody String name) {
        Category createdCategory = categorySvc.create(name);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categorySvc.update(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categorySvc.delete(id);
        return new ResponseEntity<>("Categoria eliminata correttamente!", HttpStatus.NO_CONTENT);
    }
}
