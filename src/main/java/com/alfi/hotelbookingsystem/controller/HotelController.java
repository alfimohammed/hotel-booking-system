package com.alfi.hotelbookingsystem.controller;

import com.alfi.hotelbookingsystem.dto.HotelRequestDTO;
import com.alfi.hotelbookingsystem.dto.HotelResponseDTO;
import com.alfi.hotelbookingsystem.model.Hotel;
import com.alfi.hotelbookingsystem.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Hotel Management",
    description = "APIs for managing hotels"
)
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(
    summary = "Create a hotel",
    description = "Creates a new hotel in the database"
    )
    @PostMapping
public ResponseEntity<HotelResponseDTO> addHotel(
        @Valid @RequestBody HotelRequestDTO request) {

    HotelResponseDTO response = hotelService.addHotel(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

    @Operation(
    summary = "Get all hotels",
    description = "Returns a list of all available hotels"
    )
    @GetMapping
    public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {

    Hotel hotel = hotelService.getHotelById(id);

    return ResponseEntity.ok(hotel);
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