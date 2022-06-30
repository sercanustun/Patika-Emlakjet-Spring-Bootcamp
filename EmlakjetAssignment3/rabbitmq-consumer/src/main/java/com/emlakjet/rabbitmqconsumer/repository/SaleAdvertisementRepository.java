package com.emlakjet.rabbitmqconsumer.repository;

import com.emlakjet.rabbitmqconsumer.model.SaleAdvertisement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleAdvertisementRepository extends JpaRepository<SaleAdvertisement, Long> {

}
