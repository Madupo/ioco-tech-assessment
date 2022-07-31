package com.ioco.iocoassessmentbackend;

import com.ioco.iocoassessmentbackend.service.SurvivorService;
import com.ioco.iocoassessmentbackend.model.Survivor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class IocoAssessmentBackendApplication {

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//
//        return builder
//                .setConnectTimeout(Duration.ofMillis(3000))
//                .setReadTimeout(Duration.ofMillis(3000))
//                .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(IocoAssessmentBackendApplication.class, args);
    }



//    @Bean
//    @Profile("dev")
//    public CommandLineRunner setup(SurvivorService survivorService) {
//        return (args) -> {
//            survivorService.saveSurvivor(new Survivor(1L,"Emmanuel Madupo",29,"9208035482082",
//                    "Male","5241564", "3223132",false,0));
//        };
//    }
}
