package com.josesiyo_robbio.secret_santa.controller;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.service.GiftExchangeService;
import com.josesiyo_robbio.secret_santa.service.GiftIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/exchanges")
public  class GiftExchangeController
{
    private final GiftIdeaService giftIdeaService;
    private GiftExchangeService giftExchangeService;

    @Autowired
    public GiftExchangeController(GiftExchangeService giftExchangeService, GiftIdeaService giftIdeaService)
    {
        this.giftExchangeService = giftExchangeService;
        this.giftIdeaService = giftIdeaService;
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


    @PostMapping("/addGiftIdea")
    public ResponseEntity<String> addGiftIdea(@RequestBody GiftExchange.GiftIdea giftIdea) {
        String response = giftIdeaService.insertNewIdeaGift(
                giftIdea.getId(), // Asegúrate de agregar este método en tu clase GiftIdea
                giftIdea.getDescription(),
                giftIdea.getPrice(),
                giftIdea.getUrl(),
                giftIdea.getParticipant().getEmail() // Asegúrate de que el participante esté definido en giftIdea
        );
        return ResponseEntity.ok(response);
    }




}

