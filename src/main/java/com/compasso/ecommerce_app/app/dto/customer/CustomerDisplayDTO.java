package com.compasso.ecommerce_app.app.dto.customer;

import com.compasso.ecommerce_app.core.model.Address;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CustomerDisplayDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    //private String clienteUsuario;
    private String email;
    private String cpf;
    private LocalDate dateNasc;
    private String telephone;
    //private List<Movimentacao> listaPedidos;
    private List<Address> listAddress;

    // Constructor
    public CustomerDisplayDTO() {}

    // Getters and Setters
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

    /*public String getUserCustomer() {
        return userCustomer;
    }*/

    /*public void setClienteUsuario(String clienteUsuario) {
        this.clienteUsuario = clienteUsuario;
    }*/

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

    /*public List<Movimentacao> getListaPedidos() {
        return listaPedidos;
    }*/

    /*public void setListaPedidos(List<Movimentacao> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }*/

    public List<Address> getListAddress() {
        return listAddress;
    }

    public void setListAddress(List<Address> listAddress) {
        this.listAddress = listAddress;
    }

}