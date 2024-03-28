package com.hotelium.limbo.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int statusCode;
    private Object data;
    private String message;

    public Response(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public Response(int statusCode, Object data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }
}
