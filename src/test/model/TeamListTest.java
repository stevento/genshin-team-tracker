package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamListTest {
    TeamList testTeamList;
    List<Team> testTeams;

    @BeforeEach
    public void setup() {
        testTeamList = new TeamList();
        testTeams = testTeamList.getTeams();
    }

    @Test
    public void testAddTeamSingle() {
        testTeamList.addTeam();

        assertEquals(1, testTeams.size());
    }

    @Test
    public void testAddTeamMultiple() {
        testTeamList.addTeam();
        testTeamList.addTeam();

        assertEquals(2, testTeams.size());
    }

    @Test
    public void testRemoveTeamSuccess() {
        testTeamList.addTeam();
        testTeamList.addTeam();
        assertEquals(2, testTeams.size());

        assertTrue(testTeamList.removeTeam(0));

        assertEquals(1, testTeams.size());
    }

    @Test
    public void testRemoveTeamFailure() {
        testTeamList.addTeam();
        testTeamList.addTeam();
        assertEquals(2, testTeams.size());

        assertFalse(testTeamList.removeTeam(2));
        assertFalse(testTeamList.removeTeam(-1));

        assertEquals(2, testTeams.size());
    }
}
