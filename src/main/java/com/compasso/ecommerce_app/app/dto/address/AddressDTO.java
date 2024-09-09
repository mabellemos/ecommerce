package com.compasso.ecommerce_app.app.dto.address;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String cep;
    private Integer num;
    private String complement;

    public AddressDTO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

}