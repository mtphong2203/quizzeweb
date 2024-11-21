package com.maiphong.quizzeweb.response;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
    private String message;

    private HttpStatus status;
}
