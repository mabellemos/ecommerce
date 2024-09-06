package com.compasso.ecommerce_app.core.interfaces.services.controller;

import java.util.List;

import com.compasso.ecommerce_app.app.dto.category.CategoryDTO;
import com.compasso.ecommerce_app.app.dto.category.CategoryDisplayDTO;
import com.compasso.ecommerce_app.app.service.CategoryService;
import com.compasso.ecommerce_app.core.exception.category.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.save(categoryDTO));

    }
    @PostMapping("/save-list-categorys")
    public ResponseEntity<String>saveAll(@RequestBody List<CategoryDTO> listCatDto){
        return ResponseEntity.ok(categoryService.saveAll(listCatDto));

    }
    @GetMapping("/list-category")
    public ResponseEntity<List<CategoryDTO>> listCategory() {
        return ResponseEntity.ok(categoryService.listCategory());
    }

    @GetMapping("/get-category/{idCategory}")
    public ResponseEntity<CategoryDisplayDTO> getById(@PathVariable Integer id) throws CategoryException {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/update-category/{idCategory}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) throws CategoryException {
        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/delete-category/{idCategory}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }
}
