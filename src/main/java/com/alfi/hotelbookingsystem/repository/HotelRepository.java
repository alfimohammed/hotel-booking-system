package com.alfi.hotelbookingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.alfi.hotelbookingsystem.model.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String> {

}
