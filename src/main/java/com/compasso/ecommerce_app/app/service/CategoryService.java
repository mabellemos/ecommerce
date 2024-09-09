package com.compasso.ecommerce_app.app.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.ecommerce_app.app.dto.category.CategoryDTO;
import com.compasso.ecommerce_app.app.dto.category.CategoryDisplayDTO;
import com.compasso.ecommerce_app.core.exception.category.CategoryException;
import com.compasso.ecommerce_app.core.model.Category;
import com.compasso.ecommerce_app.core.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category dtoModel(CategoryDTO categoryDTO, Category category) {

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }

    public CategoryDTO modelDto(Category category, CategoryDTO categoryDTO) {

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return categoryDTO;
    }

    public CategoryDisplayDTO modelToDisplay(Category category, CategoryDisplayDTO categoryDisplayDTO) {

        categoryDisplayDTO.setId(category.getId());
        categoryDisplayDTO.setName(category.getName());
        categoryDisplayDTO.setDescription(category.getDescription());
        categoryDisplayDTO.setListProduct(category.getListProduct());

        return categoryDisplayDTO;
    }

    public String save(CategoryDTO categoryDTO) {

        Category category = new Category();
        dtoModel(categoryDTO, category);
        categoryRepository.save(category);

        return "Categoria salva com sucesso!";
    }

    public List<CategoryDTO> listCategory() {

        List<Category> listCat = categoryRepository.findAll();
        List<CategoryDTO> listCatDto = new ArrayList<>();

        for(Category category : listCat) {
            CategoryDTO catDto = new CategoryDTO();
            modelDto(category, catDto);
            listCatDto.add(catDto);
        }

        return listCatDto;

    }

    public CategoryDisplayDTO getById(Integer id) throws CategoryException {
        Optional<Category> category = categoryRepository.findById(id);
        Category ct = new Category();
        CategoryDisplayDTO  ctDto = new CategoryDisplayDTO();

        if(category.isPresent()) {
            ct = category.get();
            modelToDisplay(ct, ctDto);

            return ctDto;
        }

        throw new CategoryException("Categoria não encontrada com id informado!");
    }

    public String update(Integer id, CategoryDTO categoryDTO) throws CategoryException {
        Optional<Category> category = categoryRepository.findById(id);
        Category ct = new Category();

        if(category.isPresent()) {
            ct = category.get();

            if(categoryDTO.getName() != null) {
                ct.setName(categoryDTO.getName());
            }

            if(categoryDTO.getDescription() !=null) {
                ct.setDescription(categoryDTO.getDescription());
            }

            categoryRepository.save(ct);

            return "Categoria atualizada!";
        }

        throw new CategoryException("Categoria não atualizada!");

    }

    public String delete(Integer id) {
        categoryRepository.deleteById(id);

        return "Categoria excluida com sucesso!";
    }

    public String saveAll(List<CategoryDTO> listCatDto) {
        List<Category> listCategory = new ArrayList<>();

        for(CategoryDTO categoryDTO : listCatDto) {
            Category category = new Category();
            dtoModel(categoryDTO, category);
            listCategory.add(category);
        }

        categoryRepository.saveAll(listCategory);

        return "Categorias salvas com sucesso!";
    }
}