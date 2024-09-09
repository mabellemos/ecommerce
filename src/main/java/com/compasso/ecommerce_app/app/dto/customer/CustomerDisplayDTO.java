package com.compasso.ecommerce_app.app.dto.customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.compasso.ecommerce_app.core.model.Address;
import com.compasso.ecommerce_app.core.model.Sale;

public class CustomerDisplayDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String userCustomer;
    private String email;
    private String cpf;
    private LocalDate dateNasc;
    private String telephone;
    private List<Sale> listOrder;
    private List<Address> listAddress;

    public CustomerDisplayDTO() {}

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

    public String getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(String userCustomer) {
        this.userCustomer = userCustomer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateNasc() {
        return dateNasc;
    }

    public void setDateNasc(LocalDate dateNasc) {
        this.dateNasc = dateNasc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Sale> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Sale> listOrder) {
        this.listOrder = listOrder;
    }

    public List<Address> getListAddress() {
        return listAddress;
    }

    public void setListAddress(List<Address> listAddress) {
        this.listAddress = listAddress;
    }

}