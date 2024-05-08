package de.hsrm.mi.web.projekt.validators;

import java.lang.annotation.*;
import jakarta.validation.*;

@Target({ ElementType.FIELD }) // Worauf ist Annotation anwendbar?
@Retention(RetentionPolicy.RUNTIME) // zur Laufzeit vorhanden
// Verweis auf Validator-Implementierung
@Constraint(validatedBy=GutesPasswortValidator.class)
@Documented // z.B. in JavaDoc des Zielelements aufnehmen
public @interface GutesPasswort {
    String message() default "Passwort muss {ziffer} oder {wort} enthalten.";
    // optionale Zusatzinfos
    Class<? extends Payload>[] payload() default { };
    // Zuordnung zu Validierungsgruppen?
    Class<?>[] groups() default { };
    String wort();
    String ziffer();
}