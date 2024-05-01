package de.hsrm.mi.web.projekt.ui.benutzer;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BenutzerFormular {
    private String name;
    private String mail;
    private String password;
    @DateTimeFormat (iso = ISO.DATE)
    private LocalDate bday;

    List<Vorlieben> liste = new ArrayList<>();

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

    public List<Vorlieben> getListe() {
        return liste;
    }

    public void setListe(List<Vorlieben> liste) {
        this.liste = liste;
    }

    
}
