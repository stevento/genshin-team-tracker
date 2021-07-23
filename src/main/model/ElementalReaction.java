package model;

// Represents an elemental reaction and its effect
public enum ElementalReaction {
    SWIRL("Deals anemo area of effect damage that spreads the swirled element"),
    CRYSTALLIZE("Creates a shield of the crystallized element"),
    SUPERCONDUCT("Deals electro area of effect damage and lowers physical resistance"),
    ELECTROCHARGED("Deals electro damage over time and causes staggering"),
    OVERLOADED("Deals electro area of effect damage and launches enemies"),
    MELT("Amplifies damage of the melting attack"),
    VAPORIZE("Amplifies damage of the vaporizing attack"),
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
