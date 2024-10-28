package com.josesiyo_robbio.secret_santa.controller;

import com.josesiyo_robbio.secret_santa.dto.GiftIdeaAproveRequest;
import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.service.GiftExchangeService;
import com.josesiyo_robbio.secret_santa.service.GiftIdeaService;
import com.josesiyo_robbio.secret_santa.service.GiftReturnService;
import com.josesiyo_robbio.secret_santa.service.GiftIdeaAproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.josesiyo_robbio.secret_santa.dto.GiftReturnRequest;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/exchanges")
public  class GiftExchangeController
{
    private final GiftIdeaService giftIdeaService;
    private GiftExchangeService giftExchangeService;
    private final GiftReturnService giftReturnService;
    private final GiftIdeaAproveService giftIdeaAproveService;

    @Autowired
    public GiftExchangeController(GiftExchangeService giftExchangeService, GiftIdeaService giftIdeaService, GiftReturnService giftReturnService,GiftIdeaAproveService giftIdeaAproveService)
    {
        this.giftExchangeService = giftExchangeService;
        this.giftIdeaService = giftIdeaService;
        this.giftReturnService = giftReturnService;
        this.giftIdeaAproveService = giftIdeaAproveService;
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
    public ResponseEntity<Map<String, String>> addGiftIdea(@RequestBody GiftExchange.GiftIdea giftIdea) {
        String response = giftIdeaService.insertNewIdeaGift(
                giftIdea.getId(),
                giftIdea.getDescription(),
                giftIdea.getPrice(),
                giftIdea.getUrl(),
                giftIdea.getParticipant().getEmail()
        );

        Map<String, String> result = new HashMap<>();
        if (response.startsWith("New gift idea added successfully")) {
            result.put("message", response);
            return ResponseEntity.ok(result);
        } else {
            result.put("error", response);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/returnGift")
    public ResponseEntity<Map<String, String>> returnGift(@RequestBody GiftReturnRequest request) {
        Map<String, String> result = giftReturnService.updateReturnGift(
                request.getExchangeId(),
                request.getEmail(),
                request.getIdGiftReturned(),
                request.getIdGiftTaken()
        );

        if (result.containsKey("error")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }



    @PostMapping("/gift-ideas/approve") // Endpoint POST
    public ResponseEntity<Map<String, String>> approveGiftIdea(@RequestBody GiftIdeaAproveRequest request) {
        // Usamos @RequestBody y un DTO para recibir el JSON
        String response = giftIdeaAproveService.updateGiftIdeaApprove(request.getExchangeId(), request.getEmail());

        Map<String, String> result = new HashMap<>();
        if (response.equals("Gift idea approved successfully")) {
            result.put("message", response);
            return ResponseEntity.ok(result);
        } else {
            result.put("error", response);
            return ResponseEntity.badRequest().body(result);
        }
    }


}

