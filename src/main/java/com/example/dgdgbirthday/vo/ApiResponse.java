package com.example.dgdgbirthday.vo;

public record ApiResponse(
        String status,
        String message,
        Object data
) {
    public static ApiResponse success(Object data) {
        return new ApiResponse("SUCCESS", null, data);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse("ERROR", message, null);
    }
}