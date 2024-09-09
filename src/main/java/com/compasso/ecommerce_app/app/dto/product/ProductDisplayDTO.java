package com.compasso.ecommerce_app.app.dto.product;

import com.compasso.ecommerce_app.core.model.Sale;

import java.time.LocalDate;
import java.util.List;

public class ProductDisplayDTO {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private LocalDate dateExpiration;
    private Integer amount;
    private Integer idCategory;
    private List<Sale> listSale;

    public ProductDisplayDTO() {

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public List<Sale> getListSale() {
        return listSale;
    }

    public void setListSale(List<Sale> listSale) {
        this.listSale = listSale;
    }
}
