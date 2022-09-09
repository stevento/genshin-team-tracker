//package persistence;
//
//import exceptions.IllegalCharacterException;
//import model.*;
//import model.Character;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//import static model.Character.KEQING;
//import static model.Element.ELECTRO;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//// Tests copied from JsonSerializationDemo from UBC CPSC 210. Link below:
//// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//// ===================================================================
//public class JsonWriterTest {
//
//    @Test
//    void testWriterInvalidFile() {
//        try {
//            TeamList tl = new TeamList();
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:filename.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // all good
//        }
//    }
//
//    @Test
//    void testWriterEmptyTeamList() {
//        try {
//            TeamList tl = new TeamList();
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeamList.json");
//            writer.open();
//            writer.write(tl);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyTeamList.json");
//            tl = reader.read();
//            assertEquals(0, tl.numTeams());
//        } catch (IOException | IllegalCharacterException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//
//    @Test
//    void testWriterGeneralTeamList() {
//        try {
//            TeamList tl = new TeamList();
//            Team testTeam = new Team();
//            testTeam.addCharacter(KEQING);
//            tl.newTeam();
//            tl.addTeam(testTeam);
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeamList.json");
//            writer.open();
//            writer.write(tl);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralTeamList.json");
//            tl = reader.read();
//            List<Team> teams = tl.getTeams();
//            assertEquals(2, teams.size());
//
//            Team team0 = teams.get(0);
//            List<Character> characters0 = team0.getCharacters();
//            List<Element> elements0 = team0.getElements();
//            List<ElementalResonance> er0 = team0.getElementalResonances();
//            assertEquals(0, characters0.size());
//            assertEquals(0, elements0.size());
//            assertEquals(0, er0.size());
//
//            Team team1 = teams.get(1);
//            List<Character> characters1 = team1.getCharacters();
//            List<Element> elements1 = team1.getElements();
//            List<ElementalResonance> er1 = team1.getElementalResonances();
//            assertEquals(1, characters1.size());
//            assertEquals(1, elements1.size());
//            assertEquals(0, er1.size());
//            assertEquals(KEQING, characters1.get(0));
//            assertEquals(ELECTRO, elements1.get(0));
//
//        } catch (IOException | IllegalCharacterException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//}
