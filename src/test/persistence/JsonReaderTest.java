package persistence;

import exceptions.IllegalCharacterException;
import model.*;
import model.Character;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static model.Character.*;
import static org.junit.jupiter.api.Assertions.*;

// Tests copied from JsonSerializationDemo from UBC CPSC 210. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// ===================================================================
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TeamList tl = reader.read();
        } catch (IOException e) {
            // pass
        } catch (IllegalCharacterException e) {
            fail("Did not expect IllegalCharacterException");
        }
    }

    @Test
    void testReaderEmptyTeamList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeamList.json");
        try {
            TeamList tl = reader.read();
            assertEquals(0, tl.numTeams());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (IllegalCharacterException e) {
            fail("Did not expect IllegalCharacterException");
        }
    }

    @Test
    void testReaderGeneralTeamList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeamList.json");
        try {
            TeamList tl = reader.read();
            List<Team> teams = tl.getTeams();
            assertEquals(2, teams.size());

            Team team0 = teams.get(0);
            List<Character> characters0 = team0.getCharacters();
            List<Element> elements0 = team0.getElements();
            List<ElementalResonance> er0 = team0.getElementalResonances();
            assertEquals(4, characters0.size());
            assertEquals(4, elements0.size());
            assertEquals(1, er0.size());
            for (Character c : characters0) {
                assertEquals(c, Character.valueOf(c.toString()));
            }
            for (Element e : elements0) {
                assertEquals(e, Element.valueOf(e.toString()));
            }
            for (ElementalResonance er : er0) {
                assertEquals(er, ElementalResonance.valueOf(er.toString()));
            }

            Team team1 = teams.get(1);
            List<Character> characters1 = team1.getCharacters();
            List<Element> elements1 = team1.getElements();
            List<ElementalResonance> er1 = team1.getElementalResonances();
            assertEquals(4, characters1.size());
            assertEquals(4, elements1.size());
            assertEquals(2, er1.size());
            for (Character c : characters1) {
                assertEquals(c, Character.valueOf(c.toString()));
            }
            for (Element e : elements1) {
                assertEquals(e, Element.valueOf(e.toString()));
            }
            for (ElementalResonance er : er1) {
                assertEquals(er, ElementalResonance.valueOf(er.toString()));
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (IllegalCharacterException e) {
            fail("Did not expect IllegalCharacterException");
        }
    }

    @Test
    void testReaderIllegalCharacter() {
        JsonReader jsonReader = new JsonReader("./data/testReaderIllegalCharacter.json");
        try {
            jsonReader.read();
            fail("Expected IllegalCharacterException");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (IllegalCharacterException e) {
            // all good
        }
    }
}
