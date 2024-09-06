package com.compasso.ecommerce_app.app.dto.category;

import com.compasso.ecommerce_app.core.model.Product;

import java.util.List;

public class CategoryDisplayDTO {

    private Integer id;
    private String name;
    private String description;
    private List<Product> listProduct;

    public CategoryDisplayDTO() {}


    public List<Product> getListProduct() {
        return listProduct;

    }
    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
