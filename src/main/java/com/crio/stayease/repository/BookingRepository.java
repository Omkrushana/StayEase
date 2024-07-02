package com.crio.stayease.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.stayease.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
