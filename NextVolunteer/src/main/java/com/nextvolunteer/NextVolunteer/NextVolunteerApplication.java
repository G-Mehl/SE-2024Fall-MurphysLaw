package com.nextvolunteer.NextVolunteer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NextVolunteerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NextVolunteerApplication.class, args);

        // Launch JavaFX Application
       JavaFXApp.launch(JavaFXApp.class, args);

    }
}