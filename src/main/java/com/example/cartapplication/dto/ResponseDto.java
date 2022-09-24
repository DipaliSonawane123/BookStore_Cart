package com.example.cartapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String s, String response) {
        this.message = s;
        this.object = response;
    }



    public ResponseDto(String s, Object response) {
        this.message = s;
        this.object = response;
    }
}



