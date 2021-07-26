package model;

// Represents an elemental resonance and its effects in Genshin Impact
public enum ElementalResonance {
    FERVENT_FLAMES(Element.PYRO, "Affected by Cryo for 40% less time. Increases ATK by 25%"),
    SOOTHING_WATER(Element.HYDRO, "Affected by Pyro for 40% less time. Increases incoming healing by 30%"),
    HIGH_VOLTAGE(Element.ELECTRO, "Affected by Hydro for 40% less time. Electro reactions have a 100% chance to "
            + "generate an Electro Elemental Particle (CD: 5s)"),
    SHATTERING_ICE(Element.CRYO, "Affected by Electro for 40% less time. Increases CRIT Rate against enemies "
            + "that are Frozen or affected by Cyro by 15%"),
    IMPETUOUS_WINDS(Element.ANEMO, "Decreases Stamina consumption by 15%. Increases Movement SPD by 10%. "
            + "Shortens Skill CD by 5%"),
    ENDURING_ROCK(Element.GEO, "Increases shield strength by 15%. Additionally, characters protected by a "
            + "shield will have the following special characteristics: DMG dealt increased by 15%, dealing DMG to "
            + "enemies will decrease their Geo RES by 20% for 15s."),
    PROTECTIVE_CANOPY(Element.NULL,"All Elemental RES +15%, Physical RES +15%.");

    private Element element;
    private String effect;

    // EFFECTS: constructs an elemental resonance including its effect
    ElementalResonance(Element element, String effect) {
        this.element = element;
        this.effect = effect;
    }

    // GETTERS
    public Element getElement() {
        return this.element;
    }
}
