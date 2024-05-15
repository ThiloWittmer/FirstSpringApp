package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

public class BenutzerServiceImpl implements BenutzerService{

    private Benutzer benutzer;
    private BenutzerRepository repo;

    @Autowired
    public BenutzerServiceImpl(Benutzer benutzer){
        this.benutzer = benutzer;
    }

    @Override
    public List<Benutzer> holeAlleBenutzer() {
        // TODO Auto-generated method stub
        List<Benutzer> alleBenutzer = repo.findAll(Sort.by("name"));
        return alleBenutzer;

    }

    @Override
    public Optional<Benutzer> holeBenutzerMitId(long id) {
        // TODO Auto-generated method stub
        Optional<Benutzer> benutzer = repo.findById(id);
        return benutzer;
    }

    @Override
    public Benutzer speichereBenutzer(Benutzer b) {
        // TODO Auto-generated method stub
        benutzer = repo.save(b);
        return benutzer;
    }

    @Override
    public void loescheBenutzerMitId(long id) {
        // TODO Auto-generated method stub
        repo.deleteById(id);
    }

    
    
}
