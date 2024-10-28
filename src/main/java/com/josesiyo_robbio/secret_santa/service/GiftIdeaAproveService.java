package com.josesiyo_robbio.secret_santa.service;


import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.repository.GiftExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class GiftIdeaAproveService {

    @Autowired
    private GiftExchangeRepository giftExchangeRepository;

    public String updateGiftIdeaApprove(String exchangeId, String email) {
        try {
            // Buscar el intercambio por ID
            Optional<GiftExchange> optionalExchange = giftExchangeRepository.findById(exchangeId);
            if (optionalExchange.isEmpty()) {
                return "Exchange Not Found";
            }
            GiftExchange exchange = optionalExchange.get();

            // Buscar la idea de regalo del participante por correo electrónico
            int giftIdeaIndex = IntStream.range(0, exchange.getGiftIdeas().size())
                    .filter(i -> exchange.getGiftIdeas().get(i).getParticipant().getEmail().equals(email))
                    .findFirst()
                    .orElse(-1);

            if (giftIdeaIndex == -1) {
                return "Gift Idea Not Found for this Participant";
            }

            // Aprobar la idea de regalo
            exchange.getGiftIdeas().get(giftIdeaIndex).setApproved(true);

            // Guardar los cambios
            giftExchangeRepository.save(exchange);

            return "Gift idea approved successfully";

        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error approving gift idea: " + e.getMessage());
            return "Error approving gift idea";
        }
    }
}