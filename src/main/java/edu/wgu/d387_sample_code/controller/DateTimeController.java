package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The @RestController annotation marks this class as a controller for handling REST API requests
@RestController
@RequestMapping("/api/datetime")  // Base path for datetime-related API endpoints
@CrossOrigin(origins = "http://localhost:4200")  // Allows cross-origin requests from frontend (Angular app)
public class DateTimeController {

    private final DateTimeService dateTimeService;  // Service to handle datetime logic

    @Autowired  // Constructor injection for the DateTimeService
    public DateTimeController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    // This endpoint handles GET requests to "/api/datetime/results"
    @GetMapping("/results")
    public String[] getConvertedTimes() {
        return dateTimeService.convertTimes();  // Delegates the conversion to the DateTimeService
    }
}