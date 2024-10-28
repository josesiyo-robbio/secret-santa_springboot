package com.josesiyo_robbio.secret_santa.service;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import com.josesiyo_robbio.secret_santa.repository.GiftExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class GiftExchangeService
{
    private GiftExchangeRepository giftExchangeRepository;

    @Autowired
    public GiftExchangeService(GiftExchangeRepository giftExchangeRepository)
    {
        this.giftExchangeRepository = giftExchangeRepository;
    }

    public GiftExchange insertNewSecretSanta(String name, int numberParticipants,
                                                double minBudget, double maxBudget, List<GiftExchange.Participant> participants)
    {
        try
        {
            String exchangeId = UUID.randomUUID().toString();

            List<GiftExchange.Assignment> assignments = generateAssignments(participants);

            GiftExchange newExchange = new GiftExchange();
            newExchange.setId(exchangeId);
            newExchange.setName(name);
            newExchange.setNumberParticipants(numberParticipants);
            newExchange.setMinBudget(minBudget);
            newExchange.setMaxBudget(maxBudget);
            newExchange.setParticipants(participants);
            newExchange.setAssignments(assignments);

            return giftExchangeRepository.save(newExchange);
        }
        catch (Exception e) 
        {
            System.err.println("Error inserting new Secret Santa: " + e.getMessage());
            return null; 
        }
    }



    public List<GiftExchange.Assignment> generateAssignments(List<GiftExchange.Participant> participants) 
    {
        List<GiftExchange.Assignment> assignments = new ArrayList<>();
        List<GiftExchange.Participant> availableParticipants = new ArrayList<>(participants);
        Random random = new Random();

        if (participants.size() % 2 != 0) 
        {
            int randomIndex = random.nextInt(participants.size());
            GiftExchange.Participant luckyParticipant = participants.get(randomIndex);

            assignments.add(new GiftExchange.Assignment(luckyParticipant.getEmail(), luckyParticipant.getEmail()));
        }


        for (GiftExchange.Participant sender : participants) 
        {
            GiftExchange.Participant recipient;

            do {
                int randomIndex = random.nextInt(availableParticipants.size());
                recipient = availableParticipants.get(randomIndex);
            } 
            while (recipient.getEmail().equals(sender.getEmail()));

            assignments.add(new GiftExchange.Assignment(sender.getEmail(), recipient.getEmail()));
            availableParticipants.remove(recipient);
        }

        return assignments;
    }
}
