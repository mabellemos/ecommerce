package com.compasso.ecommerce_app.core.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, length=50, nullable=false)
    private String name;

    @Column(name = "description", columnDefinition="TEXT", nullable=true)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Column(name = "amount", nullable=true)
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(Integer id, String name,  String description, double price, LocalDate dateExpiration, Integer amount) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateExpiration = dateExpiration;
        this.amount = amount;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}