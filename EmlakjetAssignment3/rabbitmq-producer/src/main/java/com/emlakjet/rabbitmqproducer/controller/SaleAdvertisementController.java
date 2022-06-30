package com.emlakjet.rabbitmqproducer.controller;

import com.emlakjet.rabbitmqproducer.dto.SaleAdvertisementDto;
import com.emlakjet.rabbitmqproducer.model.SaleAdvertisement;
import com.emlakjet.rabbitmqproducer.service.SaleAdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// we use all args constructor because we defined SaleAdvertisementService with final.
@AllArgsConstructor
@RestController
@RequestMapping("/sale-advertisement")
public class SaleAdvertisementController {

    // we dont need autowired anotation because spring does that for us with AllArgsConstructor
    private final SaleAdvertisementService saleAdvertisementService;

    // we Published sale advertisement from this endpoint.
    @PostMapping
    public void createSaleAdvertisement(@RequestBody SaleAdvertisementDto saleAdvertisementDto){
        saleAdvertisementService.createSaleAdvertisement(saleAdvertisementDto);
    }

}