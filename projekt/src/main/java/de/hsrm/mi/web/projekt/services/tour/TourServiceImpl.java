package de.hsrm.mi.web.projekt.services.tour;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.entities.tour.TourRepository;

@Service
public class TourServiceImpl implements TourService{
    private static final Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);
    private TourRepository repo;


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
    public void loescheTourMitId(long id) {
        repo.deleteById(id);
    }


}
