package com.todo.www.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String status;
    private HttpStatus statusCode;
    private Object data;
}
