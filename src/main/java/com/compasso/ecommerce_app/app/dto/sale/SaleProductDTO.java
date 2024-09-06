package com.compasso.ecommerce_app.app.dto.sale;

public class SaleProductDTO {
    private Integer amount;
    private Double value;
    private Integer idProduct;

    public SaleProductDTO() {

    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}