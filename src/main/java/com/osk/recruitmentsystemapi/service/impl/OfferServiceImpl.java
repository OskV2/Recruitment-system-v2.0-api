package com.osk.recruitmentsystemapi.service.impl;

import com.osk.recruitmentsystemapi.repository.ApplicationRepository;
import com.osk.recruitmentsystemapi.repository.OfferRepository;
import com.osk.recruitmentsystemapi.model.Offer;
import com.osk.recruitmentsystemapi.model.Application;
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
    private final ApplicationRepository applicationRepository;

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
            existingOffer.setSalary(offer.getSalary());
            existingOffer.setContract(offer.getContract());
            existingOffer.setJobType(offer.getJobType());
            existingOffer.setLocation(offer.getLocation());

            return offerRepository.save(existingOffer);
        } else {
            throw new RuntimeException("Offer with ID " + id + " not found");
        }
    }

    @Override
    public boolean deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).get();
        List<Application> applications =  applicationRepository.getApplicationsForSpecificOffer(id);  //  This line will find all applications for this specific offer
                                                                                                      //  So those applications will be deleted with offer, because there is no point of keeping them on database, when offer doesn't exist
        applications.forEach(application -> applicationRepository.delete(application));
        offerRepository.delete(offer);
        return true;
    }
}
