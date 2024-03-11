package com.osk.recruitmentsystemapi.service.impl;

import com.osk.recruitmentsystemapi.model.Application;
import com.osk.recruitmentsystemapi.repository.ApplicationRepository;
import com.osk.recruitmentsystemapi.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Application addApplication(Application app, MultipartFile file) {
        try {
            Application newApp = applicationRepository.save(app);

            Path root = Paths.get(uploadPath + "/" + newApp.getId());
            System.out.println("Creating directory: " + root);
            Files.createDirectory(root);
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));

            return newApp;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not create upload folder!");
        }
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
        try {
            Application application = applicationRepository.findById(id).get();
            Path folderToDelete = Paths.get(uploadPath + "/" + application.getId());

            System.out.println(folderToDelete);
            Files.walk(folderToDelete)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete file or directory: " + path, e);
                        }
                    });

            applicationRepository.delete(application);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not delete application.");
        }
    }
}
