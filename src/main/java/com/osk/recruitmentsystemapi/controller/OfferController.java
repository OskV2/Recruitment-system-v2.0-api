package com.osk.recruitmentsystemapi.controller;

import com.osk.recruitmentsystemapi.service.OfferService;
import com.osk.recruitmentsystemapi.model.Offer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @PostMapping("/offers")
    public Offer saveOffer( @RequestBody Offer offer) {
        return offerService.saveOffer(offer);
    }

    @PutMapping("/offers/{id}")
    public Offer editOffer(@PathVariable("id") Long id, @RequestBody Offer offer) {
        return offerService.editOffer(id, offer);
    }

    @DeleteMapping("/offers/{id}")
    public boolean deleteOffer(@PathVariable("id") Long id) {
        return offerService.deleteOffer(id);
    }

}
