package com.pratap.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserServiceExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
}
