package de.hsrm.mi.web.projekt.ui.tour;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TourFormular {

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime abfahrDateTime;

    @PositiveOrZero
    private int preis;

    @Min(1)
    private int plaetze;

    @Size(max = 400)
    private String info;

    @NotNull
    private Benutzer anbieter;

    @NotNull
    private Ort startOrt;

    @NotNull
    private Ort zielOrt;

    public LocalDateTime getAbfahrDateTime() {
        return abfahrDateTime;
    }

    public void setAbfahrDateTime(LocalDateTime abfahrDateTime) {
        this.abfahrDateTime = abfahrDateTime;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

    public Ort getStartOrt() {
        return startOrt;
    }

    public void setStartOrt(Ort startOrt) {
        this.startOrt = startOrt;
    }

    public Ort getZielOrt() {
        return zielOrt;
    }

    public void setZielOrt(Ort zielOrt) {
        this.zielOrt = zielOrt;
    }


    public void toTour(Tour t) {
        t.setAbfahrDateTime(this.abfahrDateTime);
        t.setPreis(this.preis);
        t.setPlaetze(this.plaetze);
        t.setInfo(this.info);
        t.setAnbieter(this.anbieter);
        t.setStartOrt(this.startOrt);
        t.setZielOrt(this.zielOrt);
    }

    public void fromBenutzer(Tour t) {
        this.abfahrDateTime = t.getAbfahrDateTime();
        this.preis = t.getPreis();
        this.plaetze = t.getPlaetze();
        this.info = t.getInfo();
        this.anbieter = t.getAnbieter();
        this.startOrt = t.getStartOrt();
        this.zielOrt = t.getZielOrt();
    }
    
}
