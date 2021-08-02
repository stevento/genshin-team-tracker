package model;

import java.util.ArrayList;
import java.util.List;

// Represents a team of up to four characters and has elemental resonance(s) and elemental reactions
public class Team {
    private List<Character> characters;
    private List<Element> elements;
    private List<ElementalResonance> elementalResonances;

    // EFFECTS: constructs an empty team with no resonance and no reactions
    public Team() {
        this.characters = new ArrayList<Character>();
        this.elements = new ArrayList<Element>();
        this.elementalResonances = new ArrayList<ElementalResonance>();
    }

    // GETTERS
    public List<Character> getCharacters() {
        return this.characters;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public List<ElementalResonance> getElementalResonances() {
        return this.elementalResonances;
    }

    // REQUIRES: character is valid and exists
    // MODIFIES: this
    // EFFECTS: adds character to team; returns true on success; false otherwise
    //          teams cannot have duplicate characters and have a max size of 4
    public boolean addCharacter(Character character) {
        if (!(characters.contains(character)) && characters.size() < 4) {
            characters.add(character);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: character is valid and exists
    // MODIFIES: this
    // EFFECTS: removes character from the team; returns true on success; false otherwise
    public boolean removeCharacter(Character character) {
        if (characters.size() == 0 || !(characters.contains(character))) {
            return false;
        } else {
            characters.remove(character);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the team's element list
    public void updateElements() {
        elements.clear();
        for (Character c : characters) {
            elements.add(c.getElement());
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the team's elemental resonance(s) based on current characters on team
    public void updateElementalResonances() {
        updateElements();
        boolean hasDuplicateElements = hasDuplicateElements();
        if (!hasDuplicateElements) {
            if (characters.size() < 4) {
                return;
            } else {
                elementalResonances.add(ElementalResonance.PROTECTIVE_CANOPY);
            }
        } else {
            for (Element e : Element.values()) {
                int count = 0;
                for (Element currentElement : elements) {
                    if (e == currentElement) {
                        count++;
                    }
                }
                if (count >= 2) {
                    elementalResonances.add(e.toElementalResonance());
                }
            }
        }
    }

    // EFFECTS: returns true if this.elements contains duplicates; false otherwise
    public boolean hasDuplicateElements() {
        for (Element e : Element.values()) {
            int count = 0;
            for (Element currentElement : elements) {
                if (e == currentElement) {
                    count++;
                }
            }
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }
}
