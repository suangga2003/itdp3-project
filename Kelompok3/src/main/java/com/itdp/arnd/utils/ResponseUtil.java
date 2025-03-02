package com.itdp.arnd.utils;

import com.itdp.arnd.dto.ApiResponse;

public class ResponseUtil {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>("success", message, data);
    }
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<T>("error", message, data);
    }
}
