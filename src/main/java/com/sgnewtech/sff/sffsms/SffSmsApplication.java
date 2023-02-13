package com.sgnewtech.sff.sffsms;

import com.sgnewtech.sff.sffsms.controllers.SffSmsController;
import com.sgnewtech.sff.sffsms.services.SffSmsServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { SffSmsController.class, SffSmsServices.class, SffSmsApplication.class})
public class SffSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SffSmsApplication.class, args);
    }
}