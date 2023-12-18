package com.pratap.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestModel {

    @NotEmpty(message = "cannot be empty")
    @Size(min = 3, max = 10, message = "should have at least 3 characters")
    private String firstName;

    @NotEmpty(message = "cannot be empty")
    @Size(min = 4, max = 10, message = "should have at least 4 characters")
    private String lastName;

    @Email(message = "Not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "cannot be empty")
    private String email;

    @NotEmpty
    private String password;
}
