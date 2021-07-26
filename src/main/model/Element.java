package model;

// Represents an element from Genshin Impact
public enum Element {
    ANEMO, GEO, PYRO, CRYO, HYDRO, ELECTRO, NONE;

    // REQUIRES: Element e is valid and exists
    // EFFECTS: returns the ElementalResonance corresponding to this
    public ElementalResonance toElementalResonance() {
        for (ElementalResonance er : ElementalResonance.values()) {
            if (this == er.getElement()) {
                return er;
            }
        }
        return null;
    }
}
