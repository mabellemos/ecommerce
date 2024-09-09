package com.compasso.ecommerce_app.app.dto.address;

public class AddressCepDTO {

    private String id;
    private String city;
    private String district;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressCepDTO() {

    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String bairro) {
        this.district = bairro;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}