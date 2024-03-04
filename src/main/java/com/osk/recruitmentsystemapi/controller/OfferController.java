package com.osk.recruitmentsystemapi.controller;

import com.osk.recruitmentsystemapi.repository.OfferRepository;
import com.osk.recruitmentsystemapi.model.Offer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class OfferController {

    private final OfferRepository offerRepository;

    @GetMapping("/test")
    public int test() {
        return 1;
    }

    @GetMapping("/offers")
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }
}
