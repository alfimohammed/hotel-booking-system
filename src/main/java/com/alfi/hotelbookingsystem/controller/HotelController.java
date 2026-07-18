package com.alfi.hotelbookingsystem.controller;

import com.alfi.hotelbookingsystem.model.Hotel;
import com.alfi.hotelbookingsystem.service.HotelService;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
    }
}