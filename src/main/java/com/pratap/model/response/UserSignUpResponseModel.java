package com.pratap.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
