package com.emlakjet.rabbitmqconsumer.repository;

import com.emlakjet.rabbitmqconsumer.model.Advertisement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
