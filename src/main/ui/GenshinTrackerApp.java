package ui;

import exceptions.IllegalCharacterException;
import model.*;
import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// UI Functionality and methods are implemented/inspired from Teller App from UBC CPSC 210. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// ===================================================
// Genshin Team Tracker Application
public class GenshinTrackerApp {
    private static final String JSON_STORE = "./data/TeamList.json";
    private TeamList teams;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public GenshinTrackerApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runGenshinTrackerApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGenshinTrackerApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayTeamListMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processTeamListCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at the team list level
    private void processTeamListCommand(String command) {
        if (command.equals("at")) {
            teams.newTeam();
            System.out.println("New team was added at index " + (teams.getTeams().size() - 1));
        } else if (command.equals("rt")) {
            askUserForIndex();
            command = input.next();
            command = command.toLowerCase();
            teams.removeTeam(Integer.parseInt(command));
        } else if (command.equals("dt")) {
            displayTeams();
        } else if (command.equals("de")) {
            displayAllTeamElements();
        } else if (command.equals("dr")) {
            displayAllTeamElementalResonance();
        } else if (command.equals("mt")) {
            manageTeams();
        } else if (command.equals("l")) {
            loadTeamList();
        } else if (command.equals("s")) {
            saveTeamList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays team management menu and asks user for desired team index
    private void manageTeams() {
        String command;
        displayTeams();
        askUserForIndex();
        command = input.next();
        command = command.toLowerCase();
        manageTeam(Integer.parseInt(command), command);
    }

    // MODIFIES: this
    // EFFECTS: initializes team list
    private void init() {
        teams = new TeamList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user for team list management
    private void displayTeamListMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tat -> create and add an empty team");
        System.out.println("\trt -> remove a team");
        System.out.println("\tdt -> display teams");
        System.out.println("\tde -> display all teams' elements");
        System.out.println("\tdr -> display all teams' resonance(s)");
        System.out.println("\tmt -> manage a team");
        System.out.println("\tl -> load teams from file");
        System.out.println("\ts -> save teams to file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: asks user for index of team
    private void askUserForIndex() {
        System.out.println("\nPlease enter the desired team index: ");
    }

    // EFFECTS: displays a list of all teams in the team list
    private void displayTeams() {
        int count = 0;
        for (Team t : teams.getTeams()) {
            System.out.println("Team at Index " + count + ": ");
            for (Character c : t.getCharacters()) {
                System.out.println(c.name());
            }
            count++;
            System.out.println("\n");
        }
    }

    // EFFECTS: displays a list of elements for every team
    public void displayAllTeamElements() {
        for (Team t : teams.getTeams()) {
            displayTeamElements(t);
            System.out.println("\n");
        }
    }

    // EFFECTS: displays a list of elemental resonances for each team
    public void displayAllTeamElementalResonance() {
        for (Team t : teams.getTeams()) {
            displayTeamElementalResonance(t);
            System.out.println("\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at the team level
    private void manageTeam(int index, String command) {
        displayTeamMenu();
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("q")) {
            return;
        } else {
            processTeamCommand(command, index);
        }
    }

    // EFFECTS: displays menu of options to user for team management
    private void displayTeamMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tac -> add a character");
        System.out.println("\trc -> remove a character");
        System.out.println("\tde -> display team elements");
        System.out.println("\tdr -> display team resonance(s)");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at the team level
    private void processTeamCommand(String command, int index) {
        Team currentTeam = teams.getTeams().get(index);
        if (command.equals("ac")) {
            askUserForCharacter();
            command = input.next();
            displayAddCharacterResult(command, currentTeam);
        } else if (command.equals("rc")) {
            askUserForCharacter();
            command = input.next();
            displayRemoveCharacterResult(command, currentTeam);
        } else if (command.equals("de")) {
            displayTeamElements(currentTeam);
        } else if (command.equals("dr")) {
            displayTeamElementalResonance(currentTeam);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: currentTeam
    // EFFECTS: attempts to remove character from currentTeam and prints success or failure message
    private void displayRemoveCharacterResult(String command, Team currentTeam) {
        if (currentTeam.removeCharacter(Character.valueOf(command))) {
            System.out.println("Character was removed");
        } else {
            System.out.println("Character was unable to be removed");
        }
    }

    // MODIFIES: currentTeam
    // EFFECTS: attempts to add character to currentTeam and prints success or failure message
    private void displayAddCharacterResult(String command, Team currentTeam) {
        if (currentTeam.addCharacter(Character.valueOf(command))) {
            System.out.println("\nCharacter was added at index " + (currentTeam.getCharacters().size() - 1));
        } else {
            System.out.println("\nCharacter was not added");
        }
    }

    // EFFECTS: asks user for character
    private void askUserForCharacter() {
        for (Character c : Character.values()) {
            System.out.println(c.toString());
        }
        System.out.println("\nPlease enter the desired character from the above list: ");
    }

    // MODIFIES: team
    // EFFECTS: displays single team's elements
    private void displayTeamElements(Team team) {
        team.updateElements();
        System.out.println("Team at Index " + teams.getTeams().indexOf(team) + ":");
        for (Element e : team.getElements()) {
            System.out.println(e.name());
        }
    }

    // MODIFIES: team
    // EFFECTS: displays single team's resonances
    private void displayTeamElementalResonance(Team team) {
        team.updateElementalResonances();
        List<ElementalResonance> elementalResonances = team.getElementalResonances();
        System.out.println("Team at Index" + teams.getTeams().indexOf(team) + ":");
        if (elementalResonances.isEmpty()) {
            System.out.println("No elemental resonance");
        } else {
            for (ElementalResonance er : elementalResonances) {
                System.out.println(er.name());
            }
        }
        System.out.println("\n");
    }

    // Copied from JsonSerializationDemo from UBC CPSC 210. Link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // ===================================================================
    // MODIFIES: this
    // EFFECTS: loads team list from file
    private void loadTeamList() {
        try {
            teams = jsonReader.read();
            System.out.println("Loaded teams from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (IllegalCharacterException e) {
            System.out.println("Invalid character was read from file: " + JSON_STORE);
        }
    }

    // Copied from JsonSerializationDemo from UBC CPSC 210. Link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // ===================================================================
    // EFFECTS: saves team list to file
    private void saveTeamList() {
        try {
            jsonWriter.open();
            jsonWriter.write(teams);
            jsonWriter.close();
            System.out.println("Saved teams to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
