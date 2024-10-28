package com.josesiyo_robbio.secret_santa.service;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.repository.GiftExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class GiftReturnService {

    private final GiftExchangeRepository giftExchangeRepository;

    @Autowired
    public GiftReturnService(GiftExchangeRepository giftExchangeRepository) {
        this.giftExchangeRepository = giftExchangeRepository;
    }

    public Map<String, String> updateReturnGift(String exchangeId, String email, String idGiftReturned, String idGiftTaken) {
        Map<String, String> response = new HashMap<>();

        try {
            // Buscar el intercambio
            Optional<GiftExchange> optionalExchange = giftExchangeRepository.findById(exchangeId);
            if (optionalExchange.isEmpty()) {
                response.put("error", "Exchange Not Found");
                return response;
            }

            GiftExchange exchange = optionalExchange.get();

            // Buscar el participante
            GiftExchange.Participant participant = exchange.getParticipants().stream()
                    .filter(member -> member.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);

            if (participant == null) {
                response.put("error", "Participant Not Found in this Exchange");
                return response;
            }

            // Buscar el regalo a devolver en las ideas de regalo
            int returnedGiftIndex = -1;
            for (int i = 0; i < exchange.getGiftIdeas().size(); i++) {
                if (exchange.getGiftIdeas().get(i).getId().equals(idGiftReturned)) {
                    returnedGiftIndex = i;
                    break;
                }
            }

            if (returnedGiftIndex == -1) {
                response.put("error", "Returned Gift Not Found in gift ideas");
                return response;
            }

            // Obtener el regalo a devolver
            GiftExchange.GiftIdea returnedGift = exchange.getGiftIdeas().get(returnedGiftIndex);

            // Si no hay regalos en la lista de devueltos, simplemente agregar el regalo devuelto
            if (exchange.getReturnedGifts() == null || exchange.getReturnedGifts().isEmpty()) {
                GiftExchange.ReturnedGift newReturnedGift = new GiftExchange.ReturnedGift();
                newReturnedGift.setId(returnedGift.getId());
                newReturnedGift.setOriginalRecipient(email);
                newReturnedGift.setDescription(returnedGift.getDescription());

                if (exchange.getReturnedGifts() == null) {
                    exchange.setReturnedGifts(new ArrayList<>());
                }
                exchange.getReturnedGifts().add(newReturnedGift);

                giftExchangeRepository.save(exchange);
                response.put("message", "Your gift was left on the table. Wait to take another.");
                return response;
            } else {
                // Buscar el regalo a tomar en la lista de regalos devueltos
                int takenGiftIndex = -1;
                for (int i = 0; i < exchange.getReturnedGifts().size(); i++) {
                    if (exchange.getReturnedGifts().get(i).getId().equals(idGiftTaken)) {
                        takenGiftIndex = i;
                        break;
                    }
                }

                if (takenGiftIndex == -1) {
                    response.put("error", "Taken Gift Not Found in returned gifts");
                    return response;
                }

                // Eliminar el regalo tomado de la lista de regalos devueltos
                exchange.getReturnedGifts().remove(takenGiftIndex);

                // Agregar el regalo devuelto a la lista
                GiftExchange.ReturnedGift newReturnedGift = new GiftExchange.ReturnedGift();
                newReturnedGift.setId(returnedGift.getId());
                newReturnedGift.setOriginalRecipient(email);
                newReturnedGift.setDescription(returnedGift.getDescription());

                exchange.getReturnedGifts().add(newReturnedGift);

                giftExchangeRepository.save(exchange);
                response.put("message", "Gift returned successfully");
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Error updating return gift");
            return response;
        }
    }
}