package com.pratap.model.request;

import lombok.Data;

@Data
public class UserSignInRequestModel {

    private String email;
    private String password;
}
