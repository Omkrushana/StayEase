package com.crio.stayease.service;

import com.crio.stayease.model.Booking;
import com.crio.stayease.model.Hotel;
import com.crio.stayease.model.User;
import com.crio.stayease.repository.BookingRepository;
import com.crio.stayease.repository.HotelRepository;
import com.crio.stayease.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Booking> getAllBookings() {
        log.info("Fetching all bookings");
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        log.info("Fetching booking with id: {}", id);
        return bookingRepository.findById(id).orElseThrow(() -> {
            log.error("Booking not found with id: {}", id);
            return new RuntimeException("Booking not found");
        });
    }

    public Booking createBooking(Long hotelId, String userEmail) {
        log.info("Creating booking for user: {} at hotel: {}", userEmail, hotelId);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> {
            log.error("User not found with email: {}", userEmail);
            return new RuntimeException("User not found");
        });
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> {
            log.error("Hotel not found with id: {}", hotelId);
            return new RuntimeException("Hotel not found");
        });
        if (hotel.getNumberOfAvailableRooms() <= 0) {
            log.error("No available rooms at hotel: {}", hotelId);
            throw new RuntimeException("No available rooms");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setBookingDate(LocalDateTime.now());

        hotel.setNumberOfAvailableRooms(hotel.getNumberOfAvailableRooms() - 1);
        hotelRepository.save(hotel);
        log.info("Booking created successfully for user: {} at hotel: {}", userEmail, hotelId);

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId, String userEmail) {
        log.info("Cancelling booking with id: {} for user: {}", bookingId, userEmail);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> {
                    log.error("Booking not found with id: {}", bookingId);
                    return new RuntimeException("Booking not found");
                });
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        if (!booking.getUser().equals(user)) {
            log.error("Unauthorized action by user: {} for booking id: {}", userEmail, bookingId);

            throw new RuntimeException("Unauthorized action");
        }

        Hotel hotel = booking.getHotel();
        hotel.setNumberOfAvailableRooms(hotel.getNumberOfAvailableRooms() + 1);
        hotelRepository.save(hotel);

        bookingRepository.delete(booking);
        log.info("Booking cancelled successfully with id: {} for user: {}", bookingId, userEmail);

    }
}
