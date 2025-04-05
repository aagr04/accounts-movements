package com.accounts.movements.exception.handler;

import com.accounts.movements.dto.MessageExceptionDto;
import com.accounts.movements.exception.UserManagementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserManagementExceptionHandler {

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<MessageExceptionDto> handleUserManagementException(UserManagementException ex) {
        MessageExceptionDto body = new MessageExceptionDto(
                ex.getMessage(),
                ex.getStatus()
        );
        return new ResponseEntity<>(body, ex.getStatus());
    }

}
