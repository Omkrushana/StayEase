package com.crio.stayease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.stayease.model.Hotel;
import com.crio.stayease.repository.HotelRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        log.info("Fetching all hotels");
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        log.info("Fetching hotel with id: {}", id);
        return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public Hotel createHotel(Hotel hotel) {

        log.info("Creating hotel with name: {}", hotel.getName());
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) {

        log.info("Updating hotel with id: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> {
            log.error("Hotel not found with id: {}", id);
            return new RuntimeException("Hotel not found");
        });
        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setDescription(hotelDetails.getDescription());
        hotel.setNumberOfAvailableRooms(hotelDetails.getNumberOfAvailableRooms());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        log.info("Deleting hotel with id: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> {
            log.error("Hotel not found with id: {}", id);
            return new RuntimeException("Hotel not found");
        });
        hotelRepository.delete(hotel);
    }
}
