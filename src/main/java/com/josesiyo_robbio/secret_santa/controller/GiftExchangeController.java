package com.josesiyo_robbio.secret_santa.controller;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.service.GiftExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/exchanges")
public  class GiftExchangeController
{
    private GiftExchangeService giftExchangeService;

    @Autowired
    public GiftExchangeController(GiftExchangeService giftExchangeService)
    {
        this.giftExchangeService = giftExchangeService;
    }

    @PostMapping
    public ResponseEntity<GiftExchange> createExchange(@RequestBody GiftExchange exchangeRequest)
    {
        GiftExchange createdExchange = giftExchangeService.insertNewSecretSanta(
                exchangeRequest.getName(),
                exchangeRequest.getNumberParticipants(),
                exchangeRequest.getMinBudget(),
                exchangeRequest.getMaxBudget(),
                exchangeRequest.getParticipants()
        );

        if (createdExchange != null) 
        {
            return new ResponseEntity<>(createdExchange, HttpStatus.CREATED);
        } 
        else 
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

