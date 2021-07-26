package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of all teams created so far
public class TeamList {
    List<Team> teams;
    List<List<ElementalResonance>> resonances;
    List<List<Element>> elements;

    public TeamList() {
        this.teams = new ArrayList<Team>();
    }

    // GETTERS
    public Team getTeam(int index) {
        //STUB
        return new Team();
    }

    // MODIFIES: this
    // EFFECTS: create a new empty team and add it to the team list
    public void addTeam() {
        //STUB
    }

    // REQUIRES: team exists already
    // MODIFIES: this
    // EFFECTS: removes team at index
    public void removeTeam(int index) {
        //STUB
    }
}
