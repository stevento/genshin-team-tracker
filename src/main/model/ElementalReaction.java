package model;

// Represents an elemental reaction and its effects in Genshin Impact
public enum ElementalReaction {
    // ANEMO && (ELECTRO || PYRO || CRYO || HYDRO)
    SWIRL("Deals anemo area of effect damage that spreads the swirled element"),
    // GEO && (ELECTRO || PYRO || CRYO || HYDRO)
    CRYSTALLIZE("Creates a shield of the crystallized element"),
    // ELECTRO && CRYO
    SUPERCONDUCT("Deals electro area of effect damage and lowers physical resistance"),
    // ELECTRO && HYDRO
    ELECTROCHARGED("Deals electro damage over time and causes staggering"),
    // ELECTRO && PYRO
    OVERLOADED("Deals electro area of effect damage and launches enemies"),
    // PYRO && CRYO
    MELT("Amplifies damage of the melting attack"),
    // PYRO && HYDRO
    VAPORIZE("Amplifies damage of the vaporizing attack"),
    // HYDRO && CYRO
    FROZEN("Freezes enemies");

    private String effect;

    // constructs an elemental reaction with its effect
    ElementalReaction(String effect) {
        this.effect = effect;
    }

    // GETTERS
    public String getEffect() {
        return this.effect;
    }

}
