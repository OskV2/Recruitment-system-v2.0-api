package com.osk.recruitmentsystemapi.service.impl;

import com.osk.recruitmentsystemapi.model.Application;
import com.osk.recruitmentsystemapi.model.Offer;
import com.osk.recruitmentsystemapi.repository.ApplicationRepository;
import com.osk.recruitmentsystemapi.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application addApplication(Application app) {
        Application newApp = new Application();
        BeanUtils.copyProperties(app, newApp);
        applicationRepository.save(newApp);
        return newApp;
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications;
    }


    @Override
    public List<Application> getApplicationsForSpecificOffer(Long offer_id) {
        List<Application> applications = applicationRepository.getApplicationsForSpecificOffer(offer_id);
        return applications;
    }

    @Override
    @Transactional
    public boolean acceptApplication(Long id) {
        applicationRepository.acceptApplication(id);
        return true;
    }

    @Override
    @Transactional
    public boolean rejectApplication(Long id) {
        applicationRepository.rejectApplication(id);
        return true;
    }

    @Override
    @Transactional
    public boolean restoreApplication(Long id) {
        applicationRepository.restoreApplication(id);
        return true;
    }

    @Override
    public Application editApplication(Long id, Application app) {
        Optional<Application> optionalApp = applicationRepository.findById(id);

        if (optionalApp.isPresent()) {
            Application existingApp = optionalApp.get();
            existingApp.setInterviewDate(app.getInterviewDate());
            return applicationRepository.save(existingApp);
        } else {
            throw new RuntimeException("Application with ID " + id + " not found");
        }
    }

    @Override
    public boolean deleteApplication(Long id) {
        Application application = applicationRepository.findById(id).get();
        applicationRepository.delete(application);
        return true;
    }
}
