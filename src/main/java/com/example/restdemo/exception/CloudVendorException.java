package com.example.restdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class CloudVendorException {
    private final String message;
    private final Throwable cause;
    private final HttpStatus httpStatus;


}
