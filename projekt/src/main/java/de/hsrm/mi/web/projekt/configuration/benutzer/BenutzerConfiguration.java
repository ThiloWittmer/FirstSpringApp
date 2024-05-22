package de.hsrm.mi.web.projekt.configuration.benutzer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

@Configuration
public class BenutzerConfiguration {
    @Bean public Benutzer benutzer() {

        Benutzer b = new Benutzer();

        return b;
    }

}
