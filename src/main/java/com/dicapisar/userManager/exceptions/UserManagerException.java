package com.dicapisar.userManager.exceptions;

import com.dicapisar.userManager.dtos.ErrorDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserManagerException extends Exception{
    private final ErrorDTO error;
    private final HttpStatus status;

    public UserManagerException(String message, HttpStatus status) {
        this.error = new ErrorDTO();
        this.error.setMessage(message);
        this.error.setErrorName(this.getClass().getName());
        this.status = status;
    }
}
