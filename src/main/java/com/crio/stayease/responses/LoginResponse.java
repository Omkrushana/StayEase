package com.crio.stayease.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

}