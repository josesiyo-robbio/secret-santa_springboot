package com.josesiyo_robbio.secret_santa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "exchanges")
public class GiftExchange {
    @Id
    private String id;
    private String name;
    private int numberParticipants;
    private double minBudget;
    private double maxBudget;
    private Date date;
    private boolean active;
    private int validateGifts;

    private List<Participant> participants;
    private List<Assignment> assignments;
    private List<ReturnedGift> returnedGifts;
    private List<GiftIdea> giftIdeas;

    // Constructor
    public GiftExchange() {
        this.date = new Date(); // Asigna la fecha actual al momento de la creación
        this.validateGifts = 0;
        this.active = true;

    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberParticipants() {
        return numberParticipants;
    }

    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }

    public double getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(double minBudget) {
        this.minBudget = minBudget;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getValidateGifts() {
        return validateGifts;
    }

    public void setValidateGifts(int validateGifts) {
        this.validateGifts = validateGifts;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<ReturnedGift> getReturnedGifts() {
        return returnedGifts;
    }

    public void setReturnedGifts(List<ReturnedGift> returnedGifts) {
        this.returnedGifts = returnedGifts;
    }

    public List<GiftIdea> getGiftIdeas() {
        return giftIdeas;
    }

    public void setGiftIdeas(List<GiftIdea> giftIdeas) {
        this.giftIdeas = giftIdeas;
    }

    public static class Participant {
        private String name;
        private String email;
        private String exchangeId;

        // Getters y Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }
    }

    public static class Assignment {
        private String sender;
        private String recipient;

        public Assignment(String sender, String recipient) {
            this.sender = sender;
            this.recipient = recipient;
        }

        // Getters y Setters
        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }
    }

    public static class ReturnedGift {
        @Id
        private String id; // MongoDB ObjectId
        private String originalRecipient;
        private String description;

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOriginalRecipient() {
            return originalRecipient;
        }

        public void setOriginalRecipient(String originalRecipient) {
            this.originalRecipient = originalRecipient;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class GiftIdea { // Cambiado a GiftIdea
        @Id
        private String id; // MongoDB ObjectId
        private String description;
        private double price;
        private String url;
        private boolean approved;
        private Participant participant;

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isApproved() {
            return approved;
        }

        public void setApproved(boolean approved) {
            this.approved = approved;
        }

        public Participant getParticipant() {
            return participant;
        }

        public void setParticipant(Participant participant) {
            this.participant = participant;
        }
    }
}
