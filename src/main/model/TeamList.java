package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of all teams created so far
public class TeamList implements Writable {
    private List<Team> teams;

    // EFFECTS: constructs a new empty team list
    public TeamList() {
        this.teams = new ArrayList<Team>();
    }

    // GETTERS
    public List<Team> getTeams() {
        return teams;
    }

    // MODIFIES: this
    // EFFECTS: create a new empty team and add it to the team list
    public void newTeam() {
        teams.add(new Team());
    }

    // MODIFIES: this
    // EFFECTS: adds team to team list
    public void addTeam(Team t) {
        teams.add(t);
    }

    // MODIFIES: this
    // EFFECTS: removes team at index; return true for success; false otherwise
    public boolean removeTeam(int index) {
        if (index > teams.size() - 1 || index < 0) {
            return false;
        } else {
            teams.remove(index);
            return true;
        }
    }

    // EFFECTS: returns number of teams
    public int numTeams() {
        return teams.size();
    }

    // EFFECTS: returns JSON representation of this
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns teams in team list as a JSON array
    public JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
