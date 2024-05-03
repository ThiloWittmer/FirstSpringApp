package de.hsrm.mi.web.projekt.ui.benutzer;

public class Vorliebe implements Comparable<Vorliebe> {
    
    private String wort;

    public String getWort() {
        return wort;
    }
    public void setWort(String wort) {
        this.wort = wort;
    }

    @Override
    public int compareTo(Vorliebe andereVorliebe) {
        return this.wort.compareTo(andereVorliebe.getWort());
    }
}
