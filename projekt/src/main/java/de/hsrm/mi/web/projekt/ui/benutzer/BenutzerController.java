package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/benutzer")
@SessionAttributes({ "formular", "ueberschrift", "benNr", "maxWunsch", "benutzer" })
public class BenutzerController {

    @Autowired
    private BenutzerService benutzerService;

    @ModelAttribute("formular")
    public void creatForm(Model m) {
        m.addAttribute("formular", new BenutzerFormular());
    }

    @GetMapping
    public String getAllBenutzer(Model m) {
        List<Benutzer> benutzerListe = benutzerService.holeAlleBenutzer();
        m.addAttribute("benutzerListe", benutzerListe);
        return "benutzerliste";
    }

    @GetMapping("/{id}/del")
    public String deleteBenutzer(@PathVariable("id") Long id) {
        benutzerService.loescheBenutzerMitId(id);
        return "redirect:/benutzer";
    }

    @GetMapping("/{benutzerNr}")
    public String benutzerProfil(@PathVariable("benutzerNr") long benNr, Model m,
            @ModelAttribute("formular") BenutzerFormular form, Locale locale) {
        int maxWunsch = 5;
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("maxWunsch", "(max. " + maxWunsch + ")");
        m.addAttribute("benNr", (benNr));

        if (benNr == 0) {
            Benutzer benutzer = new Benutzer();
            BenutzerFormular bForm = new BenutzerFormular();

            m.addAttribute("benutzer", benutzer);
            m.addAttribute("formular", bForm);
        } else {
            Benutzer benutzer;
            benutzer = benutzerService.holeBenutzerMitId(benNr).get();
            form.fromBenutzer(benutzer);

            m.addAttribute("benutzer", benutzer);
            m.addAttribute("formular", form);
        }
        return "benutzerbearbeiten";
    }

    @PostMapping("{benNr}")
    public String postForm(@Valid @ModelAttribute("formular") BenutzerFormular form, BindingResult formErrors,
            @ModelAttribute("benNr") long benNr, Model m, @ModelAttribute("benutzer") Benutzer benutzer) {

        String pw = form.getPasswort();

        if (formErrors.hasErrors()) {
            return "benutzerbearbeiten";
        }

        // Benutzer existiert noch nicht und kein gueltiges Passwort
        if (pw.length() == 0 && benutzerService.holeBenutzerMitId(benNr).isEmpty()) {
            formErrors.rejectValue("passwort", "benutzer.passwort.ungesetzt", "Passwort wurde noch nicht gesetzt");
            return "benutzerbearbeiten";
        }

        // Wenn der Benutzer nicht schon existiert und das Passwortfeld leer ist
        if (!(benutzerService.holeBenutzerMitId(benNr).isPresent() && pw.length() == 0)) {
            benutzer.setPassword(pw);
        }

        try {
            form.toBenutzer(benutzer);
            benutzerService.speichereBenutzer(benutzer);
        } catch (Exception e) {
            String excMsg = e.getLocalizedMessage();
            m.addAttribute("info", excMsg);
            return "benutzerbearbeiten";
        }

        if (benNr > 0) {
            return "benutzerbearbeiten";
        }

        return "redirect:/benutzer/" + benutzer.getId();
    }

}
