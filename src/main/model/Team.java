package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Represents a team of up to four characters and has elemental resonance(s) and elemental reactions
public class Team {
    private Set<Character> data;
    private ElementalResonance elementalResonance;
    private List<ElementalReaction> elementalReactions;

    // EFFECTS: constructs an empty team with no resonance and no reactions
    public Team() {
        this.data = new HashSet<Character>();
        this.elementalResonance = null;
        this.elementalReactions = new ArrayList<ElementalReaction>();
    }

    // GETTERS
    public Set<Character> getCharacters() {
        return this.data;
    }

    public ElementalResonance getElementalResonance() {
        return this.elementalResonance;
    }

    public List<ElementalReaction> getElementalReactions() {
        return this.elementalReactions;
    }


    // REQUIRES: character is valid and exists
    // MODIFIES: this
    // EFFECTS: adds character to team
    public void addCharacter(Character character) {
        data.add(character);
    }

    // REQUIRES: character is valid and exists and is already in the team
    // MODIFIES: this
    // EFFECTS: removes character from the team
    public void removeCharacter(Character character) {
        data.remove(character);
    }

    // MODIFIES: this
    // EFFECTS: updates the team's elemental resonance(s) based on current characters on team
    public void updateElementalResonance() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: updates the team's elemental reactions based on current characters on team
    public void updateElementalReactions() {
        //stub
    }
}
