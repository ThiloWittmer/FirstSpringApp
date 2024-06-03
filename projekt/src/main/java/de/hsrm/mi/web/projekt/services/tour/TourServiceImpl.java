package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import jakarta.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService{
    private static final Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);
    private TourRepository repo;

    @Autowired BenutzerService benutzerService;
    @Autowired OrtService ortService;

    public TourServiceImpl(TourRepository repo) {
        this.repo = repo;
    }


    @Override
    public List<Tour> holeAlleTouren() {
        List<Tour> alleTouren = repo.findAll(Sort.by("abfahrDateTime"));
        logger.debug("Gefundene Touren: {}", alleTouren);
        return alleTouren;
    }


    @Override
    public Optional<Tour> holeTourMitId(long id) {
        Optional<Tour> tour = repo.findById(id);
        tour.ifPresent(t -> logger.debug("Gefundene Tour: {}", t));
        return tour;
    }


    @Override
    public Tour speichereTour(Tour t) {
        return repo.save(t);
    }


    @Override
    @Transactional
    public void loescheTourMitId(long id) {
        Tour tour = holeTourMitId(id).get();
        Benutzer benutzer = benutzerService.holeBenutzerMitId(tour.getAnbieter().getId()).get();
        benutzer.removeTour(tour);
        repo.deleteById(id);
    }


    @Override
    @Transactional
    public Tour speichereTourangebot(long anbieterId, Tour tour, long startOrtId, long zielOrtId) {

        Benutzer anbieter = benutzerService.holeBenutzerMitId(anbieterId).get();
        Ort startOrt = ortService.holeOrteMitId(startOrtId).get();
        Ort zielOrt = ortService.holeOrteMitId(zielOrtId).get();

        tour.setAnbieter(anbieter);
        tour.setStartOrt(startOrt);
        tour.setZielOrt(zielOrt);
        
        anbieter.addTour(tour);
        speichereTour(tour);

        return tour;
    }


}
