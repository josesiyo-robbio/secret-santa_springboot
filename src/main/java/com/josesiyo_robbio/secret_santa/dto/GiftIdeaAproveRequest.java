package com.josesiyo_robbio.secret_santa.dto;

public class GiftIdeaAproveRequest {
    private String exchangeId;
    private String email;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}