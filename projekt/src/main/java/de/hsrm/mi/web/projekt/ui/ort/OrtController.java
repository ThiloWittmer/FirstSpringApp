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
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/ort")
@SessionAttributes({ "ortForm", "ueberschrift", "oNr", "ort" })
public class OrtController {

    @Autowired
    private OrtService ortService;

    @ModelAttribute("ortForm")
    public void creatForm(Model m) {
        m.addAttribute("ortForm", new OrtFormular());
    }

    @GetMapping
    public String getAllOrte(Model m) {
        List<Ort> ortsListe = ortService.holeAlleOrte();
        m.addAttribute("ortsListe", ortsListe);
        return "ort/ortliste";
    }

    @GetMapping("/{id}/del")
    public String deleteBenutzer(@PathVariable("id") Long id) {
        ortService.loescheOrtMitId(id);
        return "redirect:/ort";
    }

    @GetMapping("/{oNr}")
    public String ortProfil(@PathVariable("oNr") long oNr, Model m, @ModelAttribute("ortForm") OrtFormular form, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("langCode", locale.getLanguage());
        m.addAttribute("oNr", (oNr));

        if (oNr == 0) {
            Ort ort = new Ort();
            OrtFormular oForm = new OrtFormular();

            m.addAttribute("ort", ort);
            m.addAttribute("ortForm", oForm);
        } else {
            Ort ort;
            ort = ortService.holeOrteMitId(oNr).get();
            form.fromOrt(ort);

            m.addAttribute("ort", ort);
            m.addAttribute("ortForm", form);
        }

        return "ort/ortbearbeiten";
    }
  

    @PostMapping("{oNr}")
    public String postForm(@Valid @ModelAttribute("ortForm") OrtFormular form, BindingResult formErrors,
            @ModelAttribute("oNr") long oNr, Model m, @ModelAttribute("ort") Ort ort) {

        if (formErrors.hasErrors()) {
            return "ort/ortbearbeiten";
        }
        try {

            form.toOrt(ort);
            ortService.speichereOrt(ort);
        } catch (Exception e) {
            String excMsg = e.getLocalizedMessage();
            m.addAttribute("info", excMsg);
            return "ort/ortbearbeiten";
        }

        if (oNr > 0) {
            return "ort/ortbearbeiten";
        }

        return "redirect:/ort/" + ort.getId();
    }

}
