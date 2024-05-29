package de.hsrm.mi.web.projekt.configuration.ort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.hsrm.mi.web.projekt.entities.ort.Ort;

@Configuration
public class OrtConfiguration {
    @Bean
    public Ort ort() {
        Ort o = new Ort();
        return o;
    }
}
