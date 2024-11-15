/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld.apiwebfronted;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 *
 * @author mejia
 */
@Configuration
public class Config implements WebMvcConfigurer{
     @Override
     //Consumir backend en fronted
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/tipoCambioDia") //CORS para todos los endpoints
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
