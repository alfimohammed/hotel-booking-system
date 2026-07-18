package com.alfi.hotelbookingsystem.service;

import com.alfi.hotelbookingsystem.model.Hotel;
import com.alfi.hotelbookingsystem.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(String id) {
    return hotelRepository.findById(id);
}
}