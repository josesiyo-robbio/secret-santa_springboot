package com.josesiyo_robbio.secret_santa.service;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.repository.GiftExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiftIdeaService
{

    private final GiftExchangeRepository giftExchangeRepository;

    @Autowired
    public GiftIdeaService(GiftExchangeRepository giftExchangeRepository) {
        this.giftExchangeRepository = giftExchangeRepository;
    }

    public String insertNewIdeaGift(String exchangeId, String description, double price,String url, String participantEmail)
    {
       Optional<GiftExchange> optionalExchange = giftExchangeRepository.findById(exchangeId);

       if(optionalExchange.isEmpty())
       {
           return "Exchange Not Found";
       }

       GiftExchange exchange = optionalExchange.get();

       //find the participant
        GiftExchange.Participant participant = exchange.getParticipants().stream()
                .filter(member -> member.getEmail().equals(participantEmail))
                .findFirst().orElse(null);

        if(participant == null)
        {
            return "Participant Not Found";
        }

        //search if idea gift exists or not¡
        int existingGiftIdeaIndex = -1;
        for (int i = 0; i < exchange.getGiftIdeas().size(); i++)
        {
            if (exchange.getGiftIdeas().get(i).getParticipant().getEmail().equals(participantEmail))
            {
                existingGiftIdeaIndex = i;
                break;
            }
        }

        GiftExchange.GiftIdea newGiftIdea = new GiftExchange.GiftIdea();
        newGiftIdea.setDescription(description);
        newGiftIdea.setPrice(price);
        newGiftIdea.setUrl(url);
        newGiftIdea.setParticipant(participant);

        if(existingGiftIdeaIndex != -1)
        {
            GiftExchange.GiftIdea existingGiftIdea = exchange.getGiftIdeas().get(existingGiftIdeaIndex);

            if(existingGiftIdea.isApproved())
            {
                return "Exchange Already Approved";
            }
            else
            {
                exchange.getGiftIdeas().set(existingGiftIdeaIndex, newGiftIdea);
            }

        }
        else
        {
            exchange.getGiftIdeas().add(newGiftIdea);
        }
        giftExchangeRepository.save(exchange);
        return "New gift idea added successfully";
    }
}
