package de.hsrm.mi.web.projekt.ui.benutzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/benutzer")
public class BenutzerController {
    
    @GetMapping("/{benutzerNr}")
    public String benutzerProfil(@PathVariable("benutzerNr") int benNr, Model m) {
        m.addAttribute("ueberschrift", ("Benutzerprofil " + benNr + " bearbeiten"));
        return "benutzerbearbeiten";
    }
}
