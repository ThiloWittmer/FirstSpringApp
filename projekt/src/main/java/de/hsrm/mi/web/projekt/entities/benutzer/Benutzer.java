package de.hsrm.mi.web.projekt.entities.benutzer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.ui.benutzer.Vorliebe;
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
    @GutesPasswort(wort="siebzehn", ziffer="17")
    private String password;
    
    @NotNull
    @DateTimeFormat (iso = ISO.DATE)
    private LocalDate bday;
    
    @ElementCollection
    private Set<Vorliebe> magListe = new HashSet<Vorliebe>();
    
    @ElementCollection
    private Set<Vorliebe> magNichtListe = new HashSet<Vorliebe>();
    
    public long getId() {
        return id;
    }
    
    public long getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public void setMagListe(Set<Vorliebe> magListe) {
        this.magListe = magListe;
    }

    public void setMagNichtListe(Set<Vorliebe> magNichtListe) {
        this.magNichtListe = magNichtListe;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBday() {
        return bday;
    }

    public Set<Vorliebe> getMagListe() {
        return magListe;
    }

    public Set<Vorliebe> getMagNichtListe() {
        return magNichtListe;
    }

    
}
