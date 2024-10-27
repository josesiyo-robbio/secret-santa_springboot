package com.josesiyo_robbio.secret_santa.repository;

import com.josesiyo_robbio.secret_santa.model.GiftExchange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftExchangeRepository extends MongoRepository<GiftExchange, String>
{

}
