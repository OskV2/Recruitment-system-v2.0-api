package com.osk.recruitmentsystemapi.repository;

import com.osk.recruitmentsystemapi.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM application WHERE offer_id = :offer_id", nativeQuery=true)
    List<Application> getApplicationsForSpecificOffer(@Param("offer_id") Long offer_id);

    @Modifying
    @Query(value="UPDATE application SET status = status + 1 WHERE id = :id", nativeQuery = true)
    void acceptApplication(@Param("id") Long id);

    @Modifying
    @Query(value="UPDATE application SET status = 5 WHERE id = :id", nativeQuery = true)
    void rejectApplication(@Param("id") Long id);

    @Modifying
    @Query(value="UPDATE application SET status = 1 WHERE id = :id", nativeQuery = true)
    void restoreApplication(@Param("id") Long id);
}