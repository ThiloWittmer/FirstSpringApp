package de.hsrm.mi.web.projekt.entities.benutzer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Benutzer {
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotNull
    private String name;
    @NotNull
    private String mail;

    @NotNull
    @GutesPasswort(wort = "siebzehn", ziffer = "17")
    private String password;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate geburtstag;

    @ElementCollection
    private Set<String> magListe = new HashSet<String>();

    @ElementCollection
    private Set<String> magNichtListe = new HashSet<String>();

    @OneToMany
    private List<Tour> angeboteneTouren;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(LocalDate geburtstag) {
        this.geburtstag = geburtstag;
    }

    public Set<String> getMagListe() {
        return magListe;
    }

    public void setMagListe(Set<String> magListe) {
        this.magListe = magListe;
    }

    public Set<String> getMagNichtListe() {
        return magNichtListe;
    }

    public void setMagNichtListe(Set<String> magNichtListe) {
        this.magNichtListe = magNichtListe;
    }

    public List<Tour> getAngeboteneTouren() {
        return angeboteneTouren;
    }

    public void setAngeboteneTouren(List<Tour> angeboteneTouren) {
        this.angeboteneTouren = angeboteneTouren;
    }

}
