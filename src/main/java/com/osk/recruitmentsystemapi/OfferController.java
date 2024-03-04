package com.osk.recruitmentsystemapi;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
