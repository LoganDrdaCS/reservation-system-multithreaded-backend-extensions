package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.service.WelcomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The @RestController annotation marks this class as a controller for handling REST API requests
@RestController
@RequestMapping("/api/welcome")  // Base path for welcome message-related API endpoints
@CrossOrigin(origins = "http://localhost:4200")  // Allows cross-origin requests from frontend (Angular app)
public class WelcomeMessageController {

    private final WelcomeMessageService welcomeMessageService;  // Service to fetch welcome messages

    @Autowired  // Constructor injection for the WelcomeMessageService
    public WelcomeMessageController(WelcomeMessageService welcomeMessageService) {
        this.welcomeMessageService = welcomeMessageService;
    }

    // This endpoint handles GET requests to "/api/welcome/messages"
    @GetMapping("/messages")
    public String[] getWelcomeMessages() {
        return welcomeMessageService.getWelcomeMessageList();  // Delegates fetching messages to the service
    }
}