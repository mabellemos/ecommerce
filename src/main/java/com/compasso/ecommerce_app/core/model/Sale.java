package com.compasso.ecommerce_app.core.model;

import jakarta.persistence.*;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id", referencedColumnName="id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="id", referencedColumnName="id")
    @JsonIgnore
    private Product product;

    @Column(name="value")
    @NotNull
    private Double value;

    @Column(name="invoice")
    @NotNull
    private String invoice;

    @Column(name="amount")
    @NotNull
    private Integer amount;

    public Sale() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduto(Product product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}