package com.osk.recruitmentsystemapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osk.recruitmentsystemapi.model.Application;
import com.osk.recruitmentsystemapi.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/applications")
    public Application addApplication(@RequestParam("file") MultipartFile file, @RequestParam("app") String applicationJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        Application application;
        try {
            application = objectMapper.readValue(applicationJson, Application.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid JSON format for application data", e);
        }
        return applicationService.addApplication(application, file);
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/applications/{id}")
    public List<Application> getApplicationsForSpecificOffer(@PathVariable("id") Long offer_id) {
        return applicationService.getApplicationsForSpecificOffer(offer_id);
    }

    @PutMapping("/applications/accept/{id}")
    public boolean acceptApplication(@PathVariable("id") Long id) {
        return applicationService.acceptApplication(id);
    }

    @PutMapping("/applications/reject/{id}")
    public boolean rejectApplication(@PathVariable("id") Long id) {
        return applicationService.rejectApplication(id);
    }

    @PutMapping("/applications/restore/{id}")
    public boolean restoreApplication(@PathVariable("id") Long id) {
        return applicationService.restoreApplication(id);
    }

    @PutMapping("/applications/edit/{id}")
    public Application editApplication(@PathVariable("id") Long id, @RequestBody Application app) {
        return applicationService.editApplication(id, app);
    }

    @DeleteMapping("/applications/delete/{id}")
    public boolean deleteApplication(@PathVariable("id") Long id) {
        return applicationService.deleteApplication(id);
    }
}
