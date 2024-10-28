package com.josesiyo_robbio.secret_santa.dto;

public class GiftReturnRequest {
    private String exchangeId;
    private String email;
    private String idGiftReturned;
    private String idGiftTaken;

    // Constructor vacío necesario para Jackson
    public GiftReturnRequest() {}

    // Getters y setters
    public String getExchangeId() { return exchangeId; }
    public void setExchangeId(String exchangeId) { this.exchangeId = exchangeId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIdGiftReturned() { return idGiftReturned; }
    public void setIdGiftReturned(String idGiftReturned) { this.idGiftReturned = idGiftReturned; }

    public String getIdGiftTaken() { return idGiftTaken; }
    public void setIdGiftTaken(String idGiftTaken) { this.idGiftTaken = idGiftTaken; }
}