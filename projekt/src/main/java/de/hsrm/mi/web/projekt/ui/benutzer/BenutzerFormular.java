package de.hsrm.mi.web.projekt.ui.benutzer;

import java.time.*;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BenutzerFormular {
    private String name;
    private String mail;
    private String password;
    @DateTimeFormat (iso = ISO.DATE)
    private LocalDate bday;

    private Set<Vorliebe> magListe = new HashSet<Vorliebe>();
    private Set<Vorliebe> magNichtListe = new HashSet<Vorliebe>();


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public Set<Vorliebe> getMagListe() {
        return magListe;
    }

    public void setMagListe(String wort) {
        if(wort.length() > 0) {
            Vorliebe vorl = new Vorliebe();
            vorl.setWort(wort);
            this.magListe.add(vorl);
        }
    }

    public Set<Vorliebe> getMagNichtListe() {
        return magNichtListe;
    }

    public void setMagNichtListe(String wort) {
        if(wort.length() > 0) {
            Vorliebe vorl = new Vorliebe();
            vorl.setWort(wort);
            this.magNichtListe.add(vorl);
        }
    }

    
}
