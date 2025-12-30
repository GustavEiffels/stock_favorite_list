package com.bookmark.stock.common;

import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.common.exceptions.ExceptionInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        int status,
        T data,
        Error error
) {
    private record Error(
            String errorCode,
            String message
    ){
        private static Error create(String errorCode, String message){
            return new Error(errorCode,message);
        }
        private static Error create(ExceptionInterface exceptionInterface){
            return create(exceptionInterface.getCode(),exceptionInterface.getMessage());
        }
        private static Error create(BaseException baseException){
            return create(baseException.getCode(), baseException.getMessage());
        }
    }


    public static <T> ApiResponse<T> ok(){
        return new ApiResponse<>(HttpStatus.OK.value(),null,null);
    }
    public static <T> ApiResponse<T> created(){
        return new ApiResponse<>(HttpStatus.CREATED.value(),null,null);
    }
    public static <T> ApiResponse<T> ok(T data){
        return new ApiResponse<>(HttpStatus.OK.value(), data,null);
    }
    public static <T> ApiResponse<T> created(T data){
        return new ApiResponse<>(HttpStatus.CREATED.value(), data,null);
    }

    public static <T> ApiResponse<T> fail(HttpStatus httpStatus, String errorCode, String message){
        return new ApiResponse<>(httpStatus.value(),null,new Error(errorCode,message));
    }

    public static <T> ApiResponse<T> fail(BaseException e){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null,Error.create(e));
    }
}
