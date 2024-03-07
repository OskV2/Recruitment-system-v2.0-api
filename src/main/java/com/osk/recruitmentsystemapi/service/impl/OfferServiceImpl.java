package com.osk.recruitmentsystemapi.service.impl;

import com.osk.recruitmentsystemapi.repository.OfferRepository;
import com.osk.recruitmentsystemapi.model.Offer;
import com.osk.recruitmentsystemapi.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public List<Offer> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers;
    }

    @Override
    public Offer saveOffer(Offer offer) {
        Offer newOffer = new Offer();
        BeanUtils.copyProperties(offer, newOffer);
        offerRepository.save(newOffer);
        return newOffer;
    }

    @Override
    public Offer editOffer(Long id, Offer offer) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);

        if (optionalOffer.isPresent()) {
            Offer existingOffer = optionalOffer.get();

            existingOffer.setName(offer.getName());
            existingOffer.setDescription(offer.getDescription());
            existingOffer.setRequirements(offer.getRequirements());
            existingOffer.setBenefits(offer.getBenefits());

            return offerRepository.save(existingOffer);
        } else {
            throw new RuntimeException("Offer with ID " + id + " not found");
        }
    }

    @Override
    public boolean deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).get();
        offerRepository.delete(offer);
        return true;
    }
}
