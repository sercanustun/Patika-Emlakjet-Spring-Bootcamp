package com.emlakjet.rabbitmqproducer.repository;

import com.emlakjet.rabbitmqproducer.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


    List<Advertisement> findAdvertisementByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Advertisement> findAdvertisementByTitleContainingIgnoreCase(String title);

    List<Advertisement> findAdvertisementByDescriptionContainingIgnoreCase(String description);

    List<Advertisement> findAdvertisementByPriceBetween(long priceStart, long priceEnd);

    List<Advertisement> findTop10AdvertisementOrOrderByCreatedAt(LocalDateTime createdAt);


}