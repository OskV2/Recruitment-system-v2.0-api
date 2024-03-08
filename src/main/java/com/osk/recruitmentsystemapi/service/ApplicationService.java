package com.osk.recruitmentsystemapi.service;

import com.osk.recruitmentsystemapi.model.Application;

import java.util.List;


public interface ApplicationService {

    Application addApplication(Application app);

    List<Application> getAllApplications();

    List<Application> getApplicationsForSpecificOffer(Long id);  //  Function that allows for filtering Applications based on Offer that candidate applied.

    boolean acceptApplication(Long id); // Function that allows Admin to accept application

    boolean rejectApplication(Long id);  // This function sets application status to "rejected" but doesn't delete it from database

    boolean restoreApplication(Long id);  //  Function for Admin to restore App to status = 1 which means that Application is new.
                                          //  Can be used when Admin rejects application by mistake or something.

    Application editApplication(Long id, Application app);  // Function for Admin to add interview date if application was accepted before

    boolean deleteApplication(Long id);  //  This function deletes application from database. Admin can use it only if app was rejected before

}
