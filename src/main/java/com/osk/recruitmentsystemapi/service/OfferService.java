package com.osk.recruitmentsystemapi.service;

import com.osk.recruitmentsystemapi.model.Offer;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOffers(); //  get all offers, so candidate can pick one

    Offer saveOffer(Offer offer); //  add new offer

    Offer editOffer(Long id, Offer offer); //  edit existing offer

    boolean deleteOffer(Long id); //  delete offer
}
