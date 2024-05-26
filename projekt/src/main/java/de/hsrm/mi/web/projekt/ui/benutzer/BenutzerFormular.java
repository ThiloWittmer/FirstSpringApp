package de.hsrm.mi.web.projekt.ui.benutzer;

import java.time.*;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.validators.GutesPasswort;
import jakarta.validation.constraints.*;

public class BenutzerFormular {
    private String name;
    private String mail;

    @GutesPasswort(wort="siebzehn", ziffer="17", message="{gutespasswort.fehler}")
    private String passwort;
    
    @DateTimeFormat (iso = ISO.DATE)
    private LocalDate bday;

    private Set<String> magListe = new HashSet<String>();
    private Set<String> magNichtListe = new HashSet<String>();


    @Email
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @NotNull
    @Size(min=3, max=80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Past
    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public Set<String> getMagListe() {
        return magListe;
    }

    public void setMagListe(String wort) {
        if(wort.length() > 0) {
            this.magListe.add(wort);
        }
    }

    public Set<String> getMagNichtListe() {
        return magNichtListe;
    }

    public void setMagNichtListe(String wort) {
        if(wort.length() > 0) {
            this.magNichtListe.add(wort);
        }
    }

    public void toBenutzer(Benutzer b) {
        b.setName(this.name);
        b.setMail(this.mail);
        b.setBday(this.bday);
        b.setMagListe(this.magListe);
        b.setMagNichtListe(this.magNichtListe);
    }
    
    public void fromBenutzer(Benutzer b) {
        this.name = b.getName();
        this.mail = b.getMail();
        this.bday = b.getBday();
        this.magListe = b.getMagListe();
        this.magNichtListe = b.getMagNichtListe();
    }
}
