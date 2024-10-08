package com.compasso.ecommerce_app.app.dto.sale;

import java.util.List;

public class SaleDisplayDTO {

    private Integer id;
    private String invoice;
    private List<SaleProductDTO> listProduct;

    public SaleDisplayDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
