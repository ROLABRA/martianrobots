package com.guidesmiths.martianrobots.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Configuration
public class ScannerConfiguration {
    @Bean(name = "scanner")
    public Scanner createScanner(){
        return new Scanner(System.in);
    }
}
