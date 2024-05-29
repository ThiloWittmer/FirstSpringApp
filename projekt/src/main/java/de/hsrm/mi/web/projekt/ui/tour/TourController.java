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
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/tour")
@SessionAttributes({"tourNr", "tourForm"})
public class TourController {

    @Autowired
    private BenutzerService benutzerService;
    
    @ModelAttribute
    public void createForm(Model m) {
        m.addAttribute("tourForm", new TourFormular());
    }

    @GetMapping("/{tourNr}")
    public String tourForm(@PathVariable("tourNr") long tourNr, Model m, 
        @ModelAttribute("tourForm") TourFormular form, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("tourNr", tourNr);
        m.addAttribute("benutzerListe", benutzerService.holeAlleBenutzer());

        if(tourNr == 0) {
            Tour tour = new Tour();
            TourFormular tourForm = new TourFormular();

            m.addAttribute("tour", tour);
            m.addAttribute("tourForm", tourForm);
        }
        
            
        return "tour/tourbearbeiten";
    }

    
    @PostMapping("{tourNr}")
    public String postMethodName(@Valid @ModelAttribute("tourForm") TourFormular tourForm, BindingResult formErrors,
        @ModelAttribute("tourNr") long tourNr, Model m) {

        
        return "redirect:/tour/";
    }
    
}
