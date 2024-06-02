package de.hsrm.mi.web.projekt.services.ort;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.ort.*;

public interface OrtService {

    List<Ort> holeAlleOrte();

    Optional<Ort> holeOrteMitId(long id);

    Ort speichereOrt(Ort o);

    void loescheOrtMitId(long id);
}
