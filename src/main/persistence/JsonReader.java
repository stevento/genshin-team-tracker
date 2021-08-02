package persistence;

import exceptions.IllegalCharacterException;
import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import model.Character;
import org.json.*;

// Copied from JsonSerializationDemo from UBC CPSC 210. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// ===================================================================
// Represents a reader that reads team list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TeamList read() throws IOException, IllegalCharacterException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeamList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team list from JSON object and returns it
    private TeamList parseTeamList(JSONObject jsonObject) throws IllegalCharacterException {
        TeamList tl = new TeamList();
        addTeams(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses teams from JSON object and adds them to team list
    private void addTeams(TeamList tl, JSONObject jsonObject) throws IllegalCharacterException {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(tl, nextTeam);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses team from JSON object and adds it to team list
    private void addTeam(TeamList tl, JSONObject jsonObject) throws IllegalCharacterException {
        Team team = new Team();
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            String nextCharacter = json.toString();
            addCharacter(team, nextCharacter);
        }
        team.updateElements();
        team.updateElementalResonances();
        tl.addTeam(team);
    }

    // MODIFIES: team
    // EFFECTS: parses characters from JSON object and adds them to team
    private void addCharacter(Team team, String character) throws IllegalCharacterException {
        Character toAdd;
        toAdd = Character.find(character);
        team.addCharacter(toAdd);
    }

}

