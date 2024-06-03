package de.hsrm.mi.web.projekt.services.geo;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.geo.GeoAdresse;

public interface GeoService {
    List<GeoAdresse> findeAdressen(String ort);
}