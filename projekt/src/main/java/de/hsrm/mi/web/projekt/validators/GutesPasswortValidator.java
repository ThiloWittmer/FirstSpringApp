package de.hsrm.mi.web.projekt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GutesPasswortValidator implements ConstraintValidator<GutesPasswort, String>{
    protected String wort;
    protected String ziffer;

    @Override
    public void initialize(GutesPasswort annotationGutesPasswort) {
        this.wort = annotationGutesPasswort.wort();
        this.ziffer = annotationGutesPasswort.ziffer();
    }
    
    @Override
    public boolean isValid(String pw, ConstraintValidatorContext ctx) {
        if (pw.isEmpty()|| pw ==null) return true;
        return (pw.contains(this.wort) || pw.contains(this.ziffer));
    }

}
