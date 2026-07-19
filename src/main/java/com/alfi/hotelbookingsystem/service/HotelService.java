package com.alfi.hotelbookingsystem.service;

import com.alfi.hotelbookingsystem.dto.HotelRequestDTO;
import com.alfi.hotelbookingsystem.dto.HotelResponseDTO;

import com.alfi.hotelbookingsystem.model.Hotel;
import com.alfi.hotelbookingsystem.repository.HotelRepository;
import com.alfi.hotelbookingsystem.exception.HotelNotFoundException;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private static final Logger logger =
        LoggerFactory.getLogger(HotelService.class);

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponseDTO addHotel(HotelRequestDTO request) {

    Hotel hotel = new Hotel();

    hotel.setName(request.getName());
    hotel.setCity(request.getCity());
    hotel.setAddress(request.getAddress());
    hotel.setDescription(request.getDescription());
    hotel.setRating(request.getRating());
    hotel.setPricePerNight(request.getPricePerNight());
    hotel.setContactNumber(request.getContactNumber());
    hotel.setEmail(request.getEmail());
    hotel.setAvailable(request.getAvailable());

    logger.info("Creating hotel: {}", request.getName());

    Hotel savedHotel = hotelRepository.save(hotel);

    logger.info("Hotel created with ID: {}", savedHotel.getId());

    HotelResponseDTO response = new HotelResponseDTO();

    response.setId(savedHotel.getId());
    response.setName(savedHotel.getName());
    response.setCity(savedHotel.getCity());
    response.setAddress(savedHotel.getAddress());
    response.setDescription(savedHotel.getDescription());
    response.setRating(savedHotel.getRating());
    response.setPricePerNight(savedHotel.getPricePerNight());
    response.setContactNumber(savedHotel.getContactNumber());
    response.setEmail(savedHotel.getEmail());
    response.setAvailable(savedHotel.getAvailable());

    return response;
}

    public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
    }

    public Hotel getHotelById(String id) {

    return hotelRepository.findById(id)
            .orElseThrow(() -> new HotelNotFoundException(id));
}

    public Optional<Hotel> updateHotel(String id, Hotel updatedHotel) {

    Optional<Hotel> existingHotel = hotelRepository.findById(id);

    if (existingHotel.isPresent()) {

        updatedHotel.setId(id);

        return Optional.of(hotelRepository.save(updatedHotel));
    }

    return Optional.empty();
}
    public boolean deleteHotel(String id) {

    if (hotelRepository.existsById(id)) {
        hotelRepository.deleteById(id);
        return true;
    }

    return false;
}
}