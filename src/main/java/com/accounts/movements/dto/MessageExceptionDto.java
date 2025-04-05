package com.accounts.movements.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public record MessageExceptionDto(
    @JsonProperty("message") String message,
    @JsonProperty("cause") HttpStatus httpStatus
) {

}
