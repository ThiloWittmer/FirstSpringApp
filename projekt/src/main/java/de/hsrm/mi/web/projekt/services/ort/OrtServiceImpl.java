package de.hsrm.mi.web.projekt.services.ort;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.ort.OrtRepository;
import de.hsrm.mi.web.projekt.services.ort.OrtServiceImpl;

@Service
public class OrtServiceImpl implements OrtService {
    private static final Logger logger = LoggerFactory.getLogger(OrtServiceImpl.class);

    private OrtRepository repo;

    public OrtServiceImpl(OrtRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Ort> holeAlleOrte() {
        List<Ort> alleOrte = repo.findAll(Sort.by("name"));
        logger.debug("Gefundene Orte: {}", alleOrte);
        return alleOrte;
    }

    @Override
    public Optional<Ort> holeOrteMitId(long id) {
        Optional<Ort> orte = repo.findById(id);
        orte.ifPresent(o -> logger.debug("Gefundener Orte: {}", o));
        return orte;
    }

    @Override
    public Ort speichereOrt(Ort o) {
        return repo.save(o);
    }

    @Override
    public void loescheOrtMitId(long id) {
        repo.deleteById(id);
    }

}
