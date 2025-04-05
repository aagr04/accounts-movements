package com.accounts.movements.exception;


import com.accounts.movements.dto.MessageExceptionDto;
import org.springframework.http.HttpStatus;

public class UserManagementException extends RuntimeException {

    private final HttpStatus status;

    public UserManagementException(
            MessageExceptionDto messageExceptionDto, HttpStatus status
    ) {
        super(messageExceptionDto.message());
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public HttpStatus getStatusCode() {
        return status;
    }

}
