package de.hsrm.mi.web.projekt.ui.tour;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.tour.Tour;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import de.hsrm.mi.web.projekt.services.tour.TourService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/tour")
@SessionAttributes({"tourNr", "tourForm", "tour", "benutzerListe", "ortListe", "ort"})
public class TourController {

    @Autowired
    private BenutzerService benutzerService;

    @Autowired
    private TourService tourService;

    @Autowired
    private OrtService ortService;
    
    @ModelAttribute
    public void createForm(Model m) {
        m.addAttribute("tourForm", new TourFormular());
    }

    @GetMapping
    public String getAllTours(Model m) {
        m.addAttribute("tourListe", tourService.holeAlleTouren());
        return "tour/tourliste";
    }

    @GetMapping("/{tourNr}")
    public String tourForm(@PathVariable("tourNr") long tourNr, Model m, 
        @ModelAttribute("tourForm") TourFormular form, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("tourNr", tourNr);
        m.addAttribute("benutzerListe", benutzerService.holeAlleBenutzer());
        m.addAttribute("ortListe", ortService.holeAlleOrte());

        
        if(tourNr == 0) {
            Tour tour = new Tour();
            TourFormular tourForm = new TourFormular();

            m.addAttribute("tour", tour);
            m.addAttribute("tourForm", tourForm);
        } else {
            Tour tour;
            tour = tourService.holeTourMitId(tourNr).get();
            form.fromBenutzer(tour);

            m.addAttribute("tour", tour);
            m.addAttribute("tourForm", form);
        }
        
        return "tour/tourbearbeiten";
    }

    
    @PostMapping("{tourNr}")
    public String postMethodName(@Valid @ModelAttribute("tourForm") TourFormular tourForm, BindingResult formErrors,
        @ModelAttribute("tourNr") long tourNr, Model m, @ModelAttribute("tour") Tour tour) {

        tourForm.toTour(tour);
        tourService.speichereTour(tour);
         
        return "tour/tourbearbeiten";
    }
    
}
