package com.josesiyo_robbio.secret_santa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;



@Document(collection = "exchanges")
public class GiftExchange 
{
    @Id
    private String      id;
    private String      name;
    private int         numberParticipants;
    private int         validateGifts;
    private double      minBudget;
    private double      maxBudget;
    private Date        date;
    private boolean     active;


    private List<Participant> participants;
    private List<Assignment> assignments;
    private List<ReturnedGift> returnedGifts;
    private List<GiftIdea> giftIdeas;

    // Constructor
    public GiftExchange() 
    {
        this.date = new Date(); 
        this.validateGifts = 0;
        this.active = true;
    }

    // GETTERS
    public int                  getNumberParticipants()     { return numberParticipants;    }
    public int                  getValidateGifts()          { return validateGifts;         }
    public String               getId()                     { return id;                    }
    public String               getName()                   { return name;                  }
    public double               getMinBudget()              { return minBudget;             }
    public double               getMaxBudget()              { return maxBudget;             }
    public void                 setDate(Date date)          { this.date = date;             }
    public Date                 getDate()                   { return date;                  }
    public List<Participant>    getParticipants()           { return participants;          }
    public List<Assignment>     getAssignments()            { return assignments;           }
    public List<ReturnedGift>   getReturnedGifts()          { return returnedGifts;         }
    public List<GiftIdea>       getGiftIdeas()              { return giftIdeas;             }

    //SETTERS
    public void setId(String id)                                    { this.id = id;                                 }
    public void setName(String name)                                { this.name = name;                             }
    public void setNumberParticipants(int numberParticipants)       { this.numberParticipants = numberParticipants; }
    public void setMinBudget(double minBudget)                      { this.minBudget = minBudget;                   }
    public void setMaxBudget(double maxBudget)                      { this.maxBudget = maxBudget;                   }
    public void setValidateGifts(int validateGifts)                 { this.validateGifts = validateGifts;           }
    public void setParticipants(List<Participant> participants)     { this.participants = participants;             }
    public void setAssignments(List<Assignment> assignments)        { this.assignments = assignments;               }
    public void setReturnedGifts(List<ReturnedGift> returnedGifts)  { this.returnedGifts = returnedGifts;           }
    public void setGiftIdeas(List<GiftIdea> giftIdeas)              { this.giftIdeas = giftIdeas;                   }
    public void setActive(boolean active)                           { this.active = active;                         }

    public boolean isActive() { return active; }



    public static class Participant 
    {
        private String name;
        private String email;
        private String exchangeId;

        // GETTERS
        public String getName()         { return name;          }
        public String getExchangeId()   { return exchangeId;    }
        public String getEmail()        { return email;         }

        //SETTERS
        public void setName(String name)                { this.name = name;             }
        public void setEmail(String email)              { this.email = email;           }
        public void setExchangeId(String exchangeId)    { this.exchangeId = exchangeId; }
    }



    public static class Assignment 
    {
        private String sender;
        private String recipient;

        public Assignment(String sender, String recipient) 
        {
            this.sender = sender;
            this.recipient = recipient;
        }

        // GETTERS
        public String getSender()       { return sender;    }
        public String getRecipient()    { return recipient; }

        //SETTERS
        public void setSender(String sender)        { this.sender = sender;         }
        public void setRecipient(String recipient)  { this.recipient = recipient;   }
    }



    public static class ReturnedGift 
    {
        @Id
        private String id; // MongoDB ObjectId
        private String originalRecipient;
        private String description;

        // GETTERS
        public String getId()                   { return id;                }
        public String getDescription()          { return description;       }
        public String getOriginalRecipient()    { return originalRecipient; }

        //SETTERS
        public void setId(String id)                                { this.id = id;                                 }
        public void setOriginalRecipient(String originalRecipient)  { this.originalRecipient = originalRecipient;   }
        public void setDescription(String description)              { this.description = description;               }
    }



    public static class GiftIdea 
    {
        @Id
        private     String          id; // MongoDB ObjectId
        private     String          description;
        private     String          url;
        private     double          price;
        private     boolean         approved;
        private     Participant     participant;

        // GETTERS 
        public String       getId()             { return id;            }
        public String       getDescription()    { return description;   }
        public String       getUrl()            { return url;           }
        public double       getPrice()          { return price;         }
        public Participant  getParticipant()    { return participant;   }


        //SETTERS
        public void setId(String id)                            { this.id = id;                     }
        public void setDescription(String description)          { this.description = description;   }
        public void setPrice(double price)                      { this.price = price;               }
        public void setUrl(String url)                          { this.url = url;                   }
        public void setApproved(boolean approved)               { this.approved = approved;         }
        public void setParticipant(Participant participant)     { this.participant = participant;   }

        public boolean isApproved() { return approved; }
    }
}
