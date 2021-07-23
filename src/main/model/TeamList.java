package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of all teams created so far
public class TeamList {
    List<Team> data;

    public TeamList() {
        this.data = new ArrayList<Team>();
    }

    // MODIFIES: this
    // EFFECTS: create a new empty team and add it to the team list
    public void createTeam() {
        //STUB
    }

    // REQUIRES: team exists already
    // MODIFIES: this
    // EFFECTS: removes team at index
    public void removeTeam(int index) {
        //STUB
    }
}
