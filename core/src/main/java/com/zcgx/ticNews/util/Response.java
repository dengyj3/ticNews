package com.zcgx.ticNews.util;

public class Response<T> {
    public static final int SC_OK = 200;
    public static final int SC_INTERNAL_SERVER_ERROR = 500;
    private T data;
    private String message;
    private int code;
    private Throwable error;

    public Response(){

    }
    public static <T> Response<T> of(T data, String message, int code){
        return (new Response()).withData(data).withMessage(message).withCode(code);
    }

    public static <T> Response<T> error(){
        return of((T)null, "Internal Server Error", 500);
    }

    public static <T> Response<T> error(String message){
        return of((T)null, message, 500);
    }
    public static <T> Response<T> error(String message, int code){
        return of((T)null, message, code);
    }
    public static <T> Response<T> ok(T data){
        return of((T)data, "OK", 200);
    }
    public static <T> Response<T> ok(){
        return of((T)null, "OK", 200);
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Response<T> withData(T data){
        this.setData(data);
        return this;
    }

    public Response<T> withMessage(String message){
        this.setMessage(message);
        return this;
    }

    public Response<T> withCode(int code){
        this.setCode(code);
        return this;
    }
}
