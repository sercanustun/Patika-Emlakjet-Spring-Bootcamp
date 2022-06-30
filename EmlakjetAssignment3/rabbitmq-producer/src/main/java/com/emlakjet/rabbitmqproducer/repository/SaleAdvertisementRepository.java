package com.emlakjet.rabbitmqproducer.repository;

import com.emlakjet.rabbitmqproducer.model.SaleAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleAdvertisementRepository extends JpaRepository<SaleAdvertisement, Long> {

}
