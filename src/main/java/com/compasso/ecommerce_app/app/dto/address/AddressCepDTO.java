package com.compasso.ecommerce_app.app.dto.address;

public class AddressCepDTO {

    private String logradouro;
    private String district;
    private String localidade;
    private String state;

    public AddressCepDTO() {

    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String bairro) {
        this.district = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getState() {
        return state;
    }

    public void setUf(String state) {
        this.state = state;
    }
}