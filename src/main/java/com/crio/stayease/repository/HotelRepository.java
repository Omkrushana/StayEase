package com.crio.stayease.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.stayease.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

