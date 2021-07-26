package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    Team testTeam;
    List <Character> characterList;
    List <ElementalResonance> resonanceList;

    @BeforeEach
    public void setup() {
        testTeam = new Team();
        characterList = testTeam.getCharacterList();
        resonanceList = testTeam.getElementalResonance();
    }

    @Test
    public void testAddCharacterToEmptyTeam() {
        assertTrue(testTeam.addCharacter(Character.KEQING));

        assertEquals(1, characterList.size());
        assertEquals(Character.KEQING, characterList.get(0));
    }

    @Test
    public void testAddCharacterToNonEmptyNonFullTeamNoDuplicate() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.AYAKA));

        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.AYAKA, characterList.get(1));
    }

    @Test
    public void testAddCharacterToNonEmptyNonFullTeamDuplicate() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertFalse(testTeam.addCharacter((Character.AMBER)));

        assertEquals(1, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
    }

    @Test
    public void testAddCharacterFullTeam() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.AYAKA));
        assertTrue(testTeam.addCharacter(Character.ALBEDO));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertFalse(testTeam.addCharacter(Character.ZHONGLI));

        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.AYAKA, characterList.get(1));
        assertEquals(Character.ALBEDO, characterList.get(2));
        assertEquals(Character.KEQING, characterList.get(3));
    }

    @Test
    public void testRemoveCharacterEmptyTeam() {
        assertFalse(testTeam.removeCharacter(Character.AMBER));
        assertEquals(0, characterList.size());
    }

    @Test
    public void testRemoveCharacterNonEmptyTeamTrue() {
        assertTrue(testTeam.addCharacter(Character.BEIDOU));
        assertEquals(1, characterList.size());
        assertEquals(Character.BEIDOU, characterList.get(0));

        assertTrue(testTeam.removeCharacter(Character.BEIDOU));
        assertEquals(0, characterList.size());
    }

    @Test
    public void testRemoveCharacterNonEmptyTeamFalse() {
        assertTrue(testTeam.addCharacter(Character.BEIDOU));
        assertEquals(1, characterList.size());
        assertEquals(Character.BEIDOU, characterList.get(0));

        assertFalse(testTeam.removeCharacter(Character.FISCHL));
        assertEquals(1, characterList.size());
        assertEquals(Character.BEIDOU, characterList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceEmptyTeam() {
        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.NONE, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceOneCharacter() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertEquals(1, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.NONE, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceTwoCharactersNoResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.NONE, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceTwoCharactersResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.DILUC));
        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.DILUC, characterList.get(1));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.FERVENT_FLAMES, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceFourCharactersProtectiveCanopy() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.PROTECTIVE_CANOPY, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceFourCharactersOneResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.JEAN));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.JEAN, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.IMPETUOUS_WINDS, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceFourCharactersTwoResonance() {
        assertTrue(testTeam.addCharacter(Character.GANYU));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.FISCHL));
        assertEquals(4, characterList.size());
        assertEquals(Character.GANYU, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.FISCHL, characterList.get(3));

        testTeam.updateElementalResonance();

        assertEquals(ElementalResonance.IMPETUOUS_WINDS, resonanceList.get(0));
        assertEquals(ElementalResonance.HIGH_VOLTAGE, resonanceList.get(1));
    }
}
