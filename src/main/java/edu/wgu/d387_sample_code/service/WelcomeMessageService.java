package edu.wgu.d387_sample_code.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service  // Marks this class as a service, so Spring manages it as a bean
public class WelcomeMessageService {

    // Method to retrieve welcome messages from property files
    public String[] getWelcomeMessageList() {
        Properties properties = new Properties();  // Properties object to load messages
        String[] welcomeMessageArray = {"FirstMessage", "SecondMessage"};  // Array to store the messages

        // Executor to handle multi-threaded loading of properties files
        ExecutorService welcomeMessageExecutor = Executors.newFixedThreadPool(2);

        // First thread to load the English message
        welcomeMessageExecutor.execute(() -> {
            try {
                InputStream stream = new ClassPathResource("welcome_message_en_US.properties").getInputStream();
                properties.load(stream);

                // Since multithreading, ensure correct assignment of the message to the array index
                if (Objects.equals(welcomeMessageArray[0], "FirstMessage")) {
                    welcomeMessageArray[0] = properties.getProperty("welcomeMessage");
                }
                else if (Objects.equals(welcomeMessageArray[1], "SecondMessage")) {
                    welcomeMessageArray[1] = properties.getProperty("welcomeMessage");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Second thread to load the French message
        welcomeMessageExecutor.execute(() -> {
            try {
                InputStream stream = new ClassPathResource("welcome_message_fr_CA.properties").getInputStream();
                properties.load(stream);

                // Since multithreading, ensure correct assignment of the message to the array index
                if (Objects.equals(welcomeMessageArray[0], "FirstMessage")) {
                    welcomeMessageArray[0] = properties.getProperty("welcomeMessage");
                }
                else if (Objects.equals(welcomeMessageArray[1], "SecondMessage")) {
                    welcomeMessageArray[1] = properties.getProperty("welcomeMessage");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        welcomeMessageExecutor.shutdown();  // Shut down the executor after starting both threads

        // Sleep for 1.5 seconds to allow both threads to complete their work
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted while sleeping.");
        }
        welcomeMessageExecutor.shutdownNow();  // Forcefully terminate any remaining threads

        return welcomeMessageArray;  // Return the array containing the loaded welcome messages
    }
}