//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TeamListTest {
//    TeamList testTeamList;
//    List<Team> testTeams;
//
//    @BeforeEach
//    public void setup() {
//        testTeamList = new TeamList();
//        testTeams = testTeamList.getTeams();
//    }
//
//    @Test
//    public void testNewTeamSingle() {
//        testTeamList.newTeam();
//
//        assertEquals(1, testTeams.size());
//    }
//
//    @Test
//    public void testNewTeamMultiple() {
//        testTeamList.newTeam();
//        testTeamList.newTeam();
//
//        assertEquals(2, testTeams.size());
//    }
//
//    @Test
//    public void testRemoveTeamSuccess() {
//        testTeamList.newTeam();
//        testTeamList.newTeam();
//        assertEquals(2, testTeams.size());
//
//        assertTrue(testTeamList.removeTeam(0));
//
//        assertEquals(1, testTeams.size());
//    }
//
//    @Test
//    public void testRemoveTeamFailure() {
//        testTeamList.newTeam();
//        testTeamList.newTeam();
//        assertEquals(2, testTeams.size());
//
//        assertFalse(testTeamList.removeTeam(2));
//        assertFalse(testTeamList.removeTeam(-1));
//
//        assertEquals(2, testTeams.size());
//    }
//
//    @Test
//    public void testAddTeam() {
//        Team testTeam = new Team();
//        assertEquals(0, testTeams.size());
//        testTeamList.addTeam(testTeam);
//        assertEquals(1, testTeams.size());
//    }
//
//    @Test
//    public void testNumTeams() {
//        assertEquals(0, testTeamList.numTeams());
//        testTeamList.newTeam();
//        assertEquals(1, testTeamList.numTeams());
//        testTeamList.newTeam();
//        assertEquals(2, testTeamList.numTeams());
//    }
//}
