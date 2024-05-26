package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerServiceImpl implements BenutzerService{
    private static final Logger logger = LoggerFactory.getLogger(BenutzerServiceImpl.class);


    private BenutzerRepository repo;

    
    public BenutzerServiceImpl(BenutzerRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Benutzer> holeAlleBenutzer() {
        List<Benutzer> alleBenutzer = repo.findAll(Sort.by("name"));
        logger.debug("Gefundene Benutzer: {}", alleBenutzer);
        return alleBenutzer;
    }

    @Override
    public Optional<Benutzer> holeBenutzerMitId(long id) {
        Optional<Benutzer> benutzer = repo.findById(id);
        benutzer.ifPresent(b -> logger.debug("Gefundener Benutzer: {}", b));
        return benutzer;
    }

    @Override
    public Benutzer speichereBenutzer(Benutzer b) {
        return repo.save(b);
    }

    @Override
    public void loescheBenutzerMitId(long id) {
        repo.deleteById(id);
    }
    
}
