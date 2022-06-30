package com.emlakjet.rabbitmqproducer.controller;


import com.emlakjet.rabbitmqproducer.dto.AdvertisementDto;
import com.emlakjet.rabbitmqproducer.model.Advertisement;
import com.emlakjet.rabbitmqproducer.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
// we dont need autowired anotation because spring does that for us with AllArgsConstructor

@AllArgsConstructor
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    // we Published sale advertisement from this endpoint.

    @PostMapping
    public void createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.createAdvertisement(advertisementDto);
    }

    // filter with date
    @GetMapping
    public List<Advertisement> findAdvertisementByCreatedAtBetween(@RequestParam(name = "startDate") LocalDateTime startDate, @RequestParam("endDate") LocalDateTime endDate) {
        return advertisementService.findAdvertisementByCreatedAtBetween(startDate, endDate);
    }

    // filter with title
    @GetMapping
    public List<Advertisement> findAdvertisementByTitleContainingIgnoreCase(String title) {
        return advertisementService.findAdvertisementByTitleContainingIgnoreCase(title);
    }

    // filter with description
    @GetMapping
    public List<Advertisement> findAdvertisementByDescriptionContainingIgnoreCase(String description) {
        return advertisementService.findAdvertisementByDescriptionContainingIgnoreCase(description);
    }


    @GetMapping
    public List<Advertisement> findAdvertisementByPriceBetween(long priceStart, long priceEnd) {
        return advertisementService.findAdvertisementByPriceBetween(priceStart, priceEnd);
    }

    @GetMapping
    public List<Advertisement> findTop10AdvertisementOrOrderByCreatedAt(LocalDateTime createdAt) {
        return advertisementService.findTop10AdvertisementOrOrderByCreatedAt(createdAt);
    }

}