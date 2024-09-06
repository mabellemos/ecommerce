package com.compasso.ecommerce_app.core.security.users;

public class UsersAuthenticationResponse {

    private final String token;

    public String getToken() {
        return token;
    }

    public UsersAuthenticationResponse(String token) {
        super();
        this.token = token;
    }

}