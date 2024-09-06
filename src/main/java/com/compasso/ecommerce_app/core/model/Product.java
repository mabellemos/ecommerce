package com.compasso.ecommerce_app.core.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", unique = true, length=50, nullable=false)
    private String name;

    @NotNull
    @Column(name = "description", columnDefinition="TEXT", nullable=true)
    private String description;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @NotNull
    @Column(name = "amount", nullable=true)
    private Integer amount;

    @OneToOne(mappedBy = "product")
    private Order order;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(Integer id, @NotNull String name, @NotNull String description,
                   @NotNull Double price, @NotNull LocalDate dataExpiration, @NotNull Integer amount) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}