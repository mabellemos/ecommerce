package com.compasso.ecommerce_app.app.dto.sale;

import java.util.List;

public class SaleDTO {

    private Integer id;
    private String invoice;
    private Integer idCustomer;
    private List<SaleProductDTO> listProduct;

    public SaleDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdCustomer() {
        return id;
    }

    public void setId(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }


    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public List<SaleProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<SaleProductDTO> listProduct) {
        this.listProduct = listProduct;
    }
}