package com.osk.recruitmentsystemapi.repository;

import com.osk.recruitmentsystemapi.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}