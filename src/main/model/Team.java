package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Represents a team of up to four characters and has elemental resonance(s) and elemental reactions
public class Team {
    private List<Character> data;
    private List<ElementalResonance> elementalResonance;
    private List<ElementalReaction> elementalReactions;

    // EFFECTS: constructs an empty team with no resonance and no reactions
    public Team() {
        this.data = new ArrayList<Character>();
        this.elementalResonance = new ArrayList<ElementalResonance>();
        this.elementalReactions = new ArrayList<ElementalReaction>();
    }

    // GETTERS
    public List<Character> getCharacterList() {
        return this.data;
    }

    public List<ElementalResonance> getElementalResonance() {
        return this.elementalResonance;
    }

    public List<ElementalReaction> getElementalReactions() {
        return this.elementalReactions;
    }


    // REQUIRES: character is valid and exists
    // MODIFIES: this
    // EFFECTS: adds character to team
    public boolean addCharacter(Character character) {
        // STUB
        data.add(character);
        return false;
    }

    // REQUIRES: character is valid and exists
    // MODIFIES: this
    // EFFECTS: removes character from the team
    public boolean removeCharacter(Character character) {
        // STUB
        data.remove(character);
        return false;
    }

    // MODIFIES: this
    // EFFECTS: updates the team's elemental resonance(s) based on current characters on team
    public void updateElementalResonance() {
        // STUB
    }
}
