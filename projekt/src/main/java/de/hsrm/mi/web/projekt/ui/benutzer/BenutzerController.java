package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/benutzer")
@SessionAttributes({ "formular", "ueberschrift", "benNr", "maxWunsch" })
public class BenutzerController {

    @ModelAttribute("formular")
    public void creatForm(Model m) {
        m.addAttribute("formular", new BenutzerFormular());
    }

    @GetMapping("/{benutzerNr}")
    public String benutzerProfil(@PathVariable("benutzerNr") long benNr, Model m,
            @ModelAttribute("formular") BenutzerFormular form, Locale locale) {
        int maxWunsch = 5;
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("maxWunsch", "(max. " + maxWunsch + ")");
        m.addAttribute("benNr", (benNr));
        return "benutzerbearbeiten";
    }

    @PostMapping("{benNr}")
    public String postForm(@Valid @ModelAttribute("formular") BenutzerFormular form,
    BindingResult formErrors, Model m) {
        return "benutzerbearbeiten";
    }

}
