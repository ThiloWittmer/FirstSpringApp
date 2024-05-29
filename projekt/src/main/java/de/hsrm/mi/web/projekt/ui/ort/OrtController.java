package de.hsrm.mi.web.projekt.ui.ort;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.ort.Ort;
import de.hsrm.mi.web.projekt.services.ort.OrtService;
import de.hsrm.mi.web.projekt.ui.ort.OrtFormular;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/ort")
@SessionAttributes({ "formular", "ueberschrift", "oNr", "ort" })
public class OrtController {

    @ModelAttribute("formular")
    public void creatForm(Model m) {
        m.addAttribute("formular", new OrtFormular());
    }

    @Autowired
    private OrtService ortService;

    @GetMapping
    public String getAllOrte(Model m) {
       List<Ort> ortsListe = ortService.holeAlleOrte();
        m.addAttribute("ortsListe", ortsListe);
        return "ort/ortliste";
    }

    @GetMapping("/{oNr}")
    public String ortsVerwaltung(@PathVariable("oNr") long oNr, Model m,
            @ModelAttribute("formular") OrtFormular form, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("oNr", (oNr));

        if (oNr == 0) {
            Ort ort = new Ort();
            OrtFormular oForm = new OrtFormular();

            m.addAttribute("ort", ort);
            m.addAttribute("formular", oForm);
        } else {
            Ort ort;
            ort = ortService.holeOrteMitId(oNr).get();
            form.fromOrt(ort);

            m.addAttribute("ort", ort);
            m.addAttribute("formular", form);
        }

        return "ort/ortbearbeiten";
    }

    @PostMapping("{oNr}")
    public String postForm(@Valid @ModelAttribute("formular") OrtFormular form, BindingResult formErrors,
            @ModelAttribute("oNr") long oNr, Model m, @ModelAttribute("ort") Ort ort) {

        if (formErrors.hasErrors()) {
            return "ortbearbeiten";
        }

        if (oNr > 0) {
            return "ortbearbeiten";
        }

        return "redirect:/ort/" + ort.getId();
    }

}
