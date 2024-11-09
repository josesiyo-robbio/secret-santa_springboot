# Secret Santa Exchange API

## Overview
An advanced Secret Santa gift exchange API that allows users to create exchanges, manage gift ideas, and handle gift returns/exchanges. Built with Spring Boot and MongoDB, this API includes features like gift approval, return management, and dynamic gift reassignment.

## Key Features
- Create and manage gift exchanges
- Add and approve gift ideas
- Return and exchange gifts
- Budget range enforcement
- Participant management
- Gift idea URL tracking
- Email-based participant identification



## API Documentation
The project includes a complete Insomnia collection file (`api_collection.InsomniaV4.json`) with all endpoints. To use:

1. Open Insomnia
2. Go to Application -> Preferences -> Data -> Import Data
3. Select the `api_collection.InsomniaV4.json` file
4. All endpoints will be imported with example requests

## API Endpoints

### 1. Create Gift Exchange
```
POST /api/exchanges
```
Creates a new Secret Santa exchange with participants and budget constraints.

### 2. Add Gift Idea
```
POST /api/exchanges/addGiftIdea
```
Allows participants to suggest gift ideas with URLs and prices.

### 3. Return Gift
```
POST /api/exchanges/returnGift
```
Handles gift returns and exchanges between participants.

### 4. Approve Gift Idea
```
POST /api/exchanges/gift-ideas/approve
```
Approves suggested gift ideas for the exchange.

## Technical Stack
- **Framework**: Spring Boot
- **Build Tool**: Gradle
- **Java Version**: 17
- **Database**: MongoDB
- **Containerization**: Docker
- **Architecture**: REST API
- **Design Pattern**: MVC

## Docker Setup
The project includes a Docker Compose file for easy deployment:

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mongodb
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/secretsanta
  
  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db

volumes:
  mongodb_data:
```

## Installation & Running

### Using Docker
```bash
# Start the application and database
docker compose up -d

# Stop the application
docker compose down
```

### Manual Setup
```bash
# Clone the repository
git clone [repository-url]

# Navigate to project directory
cd secret-santa-api

# Build the project
gradle clean build

# Run the application
gradle bootRun
```

## Environment Configuration
```yaml
# application.yml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/secretsanta
```

## Example Requests

### Create Exchange
```json
{
  "name": "Office Secret Santa 2024",
  "numberParticipants": 10,
  "minBudget": 20.0,
  "maxBudget": 50.0,
  "participants": [
    {
      "name": "John Doe",
      "email": "john@example.com"
    }
  ]
}
```

### Add Gift Idea
```json
{
  "id": "exchangeId",
  "description": "Bluetooth Headphones",
  "price": 45.99,
  "url": "http://example.com/headphones",
  "participant": {
    "email": "john@example.com"
  }
}
```

## Error Handling
- Validation errors for budget constraints
- Participant verification
- Gift availability checking
- Email validation
- Duplicate entry prevention

## Development Setup
1. Install Java 17
2. Install Docker and Docker Compose
3. Install MongoDB (if not using Docker)
4. Import Insomnia collection for testing

## Testing
```bash
# Run tests
gradle test
```

## Future Enhancements
- Real-time notifications
- Gift tracking system
- Wishlist management
- Anonymous messaging
- Event scheduling
- Gift suggestion AI

## Contributing
Feel free to submit issues and enhancement requests.