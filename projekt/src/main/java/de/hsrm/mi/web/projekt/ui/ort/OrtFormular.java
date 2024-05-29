package de.hsrm.mi.web.projekt.ui.ort;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrtFormular {
    private String name;
    private double geobreite;
    private double geolaenge;

    @NotNull
    @Size(min = 3, max = 80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGeobreite() {
        return geobreite;
    }

    public void setGeobreite(double geobreite) {
        this.geobreite = geobreite;
    }

    public double getGeolaenge() {
        return geolaenge;
    }

    public void setGeolaenge(double geolaenge) {
        this.geolaenge = geolaenge;
    }

    public void fromOrt(Ort o) {
        this.name = o.getName();
        this.geobreite = o.getGeobreite();
        this.geolaenge = o.getGeolaenge();
    }

    public void toOrt(Ort o) {
        o.setName(this.name);
        o.setGeobreite(this.geobreite);
        o.setGeolaenge(this.geolaenge);
    }

}
