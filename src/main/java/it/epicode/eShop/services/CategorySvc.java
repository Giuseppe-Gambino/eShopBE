package it.epicode.eShop.services;

import it.epicode.eShop.dto.CategoryDTO;
import it.epicode.eShop.dto.ProductDTO;
import it.epicode.eShop.entity.Category;
import it.epicode.eShop.entity.Product;
import it.epicode.eShop.repo.CartItemRepository;
import it.epicode.eShop.repo.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategorySvc {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria non trovata"));
    }

    public Category create(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    public Category update(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria non trovata"));

        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new EntityNotFoundException("Categoria non trovata");
        }
        categoryRepository.deleteById(id);
    }
}
