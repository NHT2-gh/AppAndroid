package com.example.testapp.api;

public class ApiResponse {
    private String message;
    private boolean status;
    private int stt;

    public ApiResponse(String message, boolean status,int stt) {
        super();
        this.message = message;
        this.status = status;
        this.stt = stt;
    }

    public ApiResponse() {

    }

    //getter and setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

}
