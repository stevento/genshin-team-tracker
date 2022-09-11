package ui;

import exceptions.IllegalCharacterException;
import jdk.nashorn.internal.scripts.JO;
import model.Character;
import model.Team;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

// Note: many ideas and inspirations for the GUI were taken from sources linked below:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html
// https://www.youtube.com/watch?v=Kmgo00avvEw
// http://www.seasite.niu.edu/cs580java/JList_Basics.htm
// https://www.youtube.com/watch?v=SyZQVJiARTQ
// ============================================================================================

// Represents the main window in which the Genshin Impact Team Tracker app is displayed
public class GenshinTrackerAppFrame extends JFrame {
    private static final String JSON_STORE = "./data/TeamList.json";
    private static final String SOUND_NAME = "./data/cymbals.wav";

    private TeamList teams;
    private JList teamList;
    private DefaultListModel teamListModel;
    private JList characterList;
    private DefaultListModel characterListModel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Clip clip;
    private JButton addTeamButton;
    private JButton deleteTeamButton;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton viewTeamButton;
    private JButton addCharButton;
    private JMenuBar menuBar;
    private JPanel teamPanel;
    private JPanel viewPanel;

    // Constructs main window
    // EFFECTS: sets up window in which Genshin Impact Team Tracker app will be used
    public GenshinTrackerAppFrame() {

        // Create and set up the window
        super("Genshin Impact Team Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,600);
        setLayout(null);

        // Instantiate JsonReader and JsonWriter
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        // Instantiate teams
        teams = new TeamList();

        // Create lists
        makeTeamJList();
        makeViewList();

        // Setup audio
        setupAudio();

        // Create scroll panes
        JScrollPane teamListScrollPane = new JScrollPane(teamList);
        JScrollPane characterListScrollPane = new JScrollPane(characterList);

        // Create buttons
        setupButtons();

        // Create a menu bar and add buttons to it
        setupMenuBar();

        // Create a panel to display teams and add components to panel
        setupTeamPanel(teamListScrollPane);

        // Create a panel for viewing team details
        setupViewPanel(characterListScrollPane);

        // Add components to frame
        add(viewPanel);
        add(teamPanel);
        add(menuBar);

        // Display the window
        setVisible(true);
    }

    // EFFECTS: Sets up a JPanel to display team details
    private void setupViewPanel(JScrollPane characterListScrollPane) {
        viewPanel = new JPanel();
        viewPanel.setBounds(350, 50, 300, 500);
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.LINE_AXIS));
        viewPanel.add(characterListScrollPane, BorderLayout.CENTER);
        viewPanel.add(new JSeparator(SwingConstants.VERTICAL));
    }

    // MODIFIES: this
    // EFFECTS: Sets up a JPanel to display teams
    private void setupTeamPanel(JScrollPane teamListScrollPane) {
        teamPanel = new JPanel();
        teamPanel.setBounds(0,50,300,500);
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.LINE_AXIS));
        teamPanel.add(teamListScrollPane, BorderLayout.CENTER);
        teamPanel.add(new JSeparator(SwingConstants.VERTICAL));
    }

    // MODIFIES: this
    // EFFECTS: Sets up a menu bar with buttons
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 700, 50);
        menuBar.add(addTeamButton);
        menuBar.add(deleteTeamButton);
        menuBar.add(loadDataButton);
        menuBar.add(saveDataButton);
        menuBar.add(viewTeamButton);
        menuBar.add(addCharButton);
        menuBar.setBackground(Color.GRAY);
    }

    // MODIFIES: this
    // EFFECTS: Instantiates buttons
    private void setupButtons() {
        addTeamButton = new AddTeamButton();
        deleteTeamButton = new DeleteTeamButton();
        loadDataButton = new LoadDataButton();
        saveDataButton = new SaveDataButton();
        viewTeamButton = new ViewTeamButton();
        addCharButton = new AddCharButton();
    }

    // MODIFIES: this
    // EFFECTS: Sets up audio clip
    private void setupAudio() {
        File file = new File(SOUND_NAME);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.err.println("Unexpected exception");
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets up team lists
    private void makeTeamJList() {
        teamListModel = new DefaultListModel<>();
        for (Team t : teams.getTeams()) {
            teamListModel.addElement(t);
        }
        teamList = new JList(teamListModel);
        teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teamList.setSelectedIndex(0);
        teamList.setVisibleRowCount(-1);
    }

    // MODIFIES: this
    // EFFECTS: Sets up view panel list
    private void makeViewList() {
        characterListModel = new DefaultListModel();
        characterList = new JList(characterListModel);
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        characterList.setSelectedIndex(0);
        characterList.setVisibleRowCount(-1);
    }

    // MODIFIES: this
    // EFFECTS: Updates the teams within listModel
    public void updateTeams() {
        teamListModel.clear();
        for (Team t : teams.getTeams()) {
            teamListModel.addElement(t);
        }
    }

    // Represents the "Create new empty team" button
    public class AddTeamButton extends JButton implements ActionListener {

        // EFFECTS: Constructs the "Create new empty team" button
        public AddTeamButton() {
            super("Create new empty team");
            addActionListener(this);
            setFocusable(false);
        }

        // EFFECTS: On button press, create a new team
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {
                String name = JOptionPane.showInputDialog("Enter team name");
                teams.newTeam(name);
                updateTeams();
            }
        }
    }

    // Represents the "Delete team" button
    public class DeleteTeamButton extends JButton implements ActionListener {

        // EFFECTS: Constructs the "Delete team" button
        public DeleteTeamButton() {
            super("Delete team");
            addActionListener(this);
            setFocusable(false);
        }

        // EFFECTS: On button press, delete the currently highlighted team
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {
                int index = teamList.getSelectedIndex();
                teams.removeTeam(index);
                updateTeams();
            }
        }
    }

    // Represents the "Load data" button
    public class LoadDataButton extends JButton implements ActionListener {

        // EFFECTS: Constructs the "Load data" button
        public LoadDataButton() {
            super("Load data");
            addActionListener(this);
            setFocusable(false);
        }

        // EFFECTS: On button press, load a saved state of teams from file
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {
                loadTeamList();
                updateTeams();
                clip.start();
                clip.setMicrosecondPosition(0);
            }
        }

        // Copied from JsonSerializationDemo from UBC CPSC 210. Link below:
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
        // ===================================================================
        // MODIFIES: this
        // EFFECTS: loads team list from file
        private void loadTeamList() {
            try {
                teams = jsonReader.read();
            } catch (IOException e) {
                System.err.println("Unable to read from file: " + JSON_STORE);
            } catch (IllegalCharacterException e) {
                System.err.println("Invalid character was read from file: " + JSON_STORE);
            }
        }
    }

    // Represents the "Save data" button
    public class SaveDataButton extends JButton implements ActionListener {

        // EFFECTS: Constructs the "Load data" button
        public SaveDataButton() {
            super("Save data");
            addActionListener(this);
            setFocusable(false);
        }

        // EFFECTS: On button press, save the current state of teams
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this) {
                saveTeamList();
                clip.start();
                clip.setMicrosecondPosition(0);
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
            } catch (FileNotFoundException e) {
                System.err.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    public class ViewTeamButton extends JButton implements ActionListener {

        // EFFECTS: Constructs the "View team" button
        public ViewTeamButton() {
            super("View team");
            addActionListener(this);
            setFocusable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int index = teamList.getSelectedIndex();
            characterListModel.clear();
            for (Character c : teams.getTeams().get(index).getCharacters()) {
                characterListModel.addElement(c);
            }

        }
    }

    public class AddCharButton extends JButton implements ActionListener {

        public AddCharButton() {
            super("Add Character");
            addActionListener(this);
            setFocusable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<Character> comboBox = new JComboBox(Character.values());

            JOptionPane.showMessageDialog(null, comboBox, "Character", JOptionPane.QUESTION_MESSAGE);

            Team currentTeam = teams.getTeams().get(teamList.getSelectedIndex());
            currentTeam.addCharacter(comboBox.getItemAt(comboBox.getSelectedIndex()));

            int index = teamList.getSelectedIndex();
            characterListModel.clear();
            for (Character c : teams.getTeams().get(index).getCharacters()) {
                characterListModel.addElement(c);
            }
        }
    }

}
