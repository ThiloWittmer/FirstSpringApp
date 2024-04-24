package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@SessionAttributes(names = {"formular"})
@RequestMapping("/benutzer")
public class BenutzerController {
    
    @GetMapping("/{benutzerNr}")
    public String benutzerProfil(@PathVariable("benutzerNr") long benNr, Model m) {
        m.addAttribute("ueberschrift", ("Benutzerprofil " + benNr + " bearbeiten"));
        m.addAttribute("formular", new BenutzerFormular());
        return "benutzerbearbeiten";
    }

    @PostMapping("{benNr}")
    public String postForm(@ModelAttribute("formular") BenutzerFormular form, Model m) {
        String n = form.getName();
        return "benutzerbearbeiten";
    }
}
