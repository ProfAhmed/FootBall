package com.pro.ahmed.football.data.models;

public class RequestType {
    private String id;
    private String requestType;

    public RequestType(String id, String requestType) {
        this.id = id;
        this.requestType = requestType;
    }

    public String getId() {
        return id;
    }

    public String getRequestType() {
        return requestType;
    }
}
