package com.alfi.hotelbookingsystem.controller;

import com.alfi.hotelbookingsystem.dto.HotelRequestDTO;
import com.alfi.hotelbookingsystem.dto.HotelResponseDTO;
import com.alfi.hotelbookingsystem.model.Hotel;
import com.alfi.hotelbookingsystem.service.HotelService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public HotelResponseDTO addHotel(@Valid @RequestBody HotelRequestDTO request) {
        return hotelService.addHotel(request);
    }

    @PostMapping("/test")
public String testPost() {
    return "POST is working";
}

    @GetMapping
    public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {

    Optional<Hotel> hotel = hotelService.getHotelById(id);

    if (hotel.isPresent()) {
        return ResponseEntity.ok(hotel.get());
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
public ResponseEntity<Hotel> updateHotel(@PathVariable String id,
                                         @RequestBody Hotel hotel) {

    Optional<Hotel> updatedHotel = hotelService.updateHotel(id, hotel);

    if (updatedHotel.isPresent()) {
        return ResponseEntity.ok(updatedHotel.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String id) {

    boolean deleted = hotelService.deleteHotel(id);

    if (deleted) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
  }
}