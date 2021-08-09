package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    Team testTeam;
    List <Character> characterList;
    List <Element> elementList;
    List <ElementalResonance> resonanceList;

    @BeforeEach
    public void setup() {
        testTeam = new Team();
        characterList = testTeam.getCharacters();
        elementList = testTeam.getElements();
        resonanceList = testTeam.getElementalResonances();
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
    public void testUpdateElementsEmptyTeam() {
        testTeam.updateElements();

        assertEquals(0, elementList.size());
    }

    @Test
    public void testUpdateElementsEmptyInitial() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));

        testTeam.updateElements();

        assertEquals(2, elementList.size());
        assertEquals(Element.PYRO, elementList.get(0));
        assertEquals(Element.CRYO, elementList.get(1));
    }

    @Test
    public void testUpdateElementsFullInitial() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertTrue(testTeam.addCharacter(Character.JEAN));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.VENTI, characterList.get(2));
        assertEquals(Character.JEAN, characterList.get(3));

        testTeam.updateElements();

        assertEquals(4, elementList.size());
        assertEquals(Element.PYRO, elementList.get(0));
        assertEquals(Element.CRYO, elementList.get(1));
        assertEquals(Element.ANEMO, elementList.get(2));
        assertEquals(Element.ANEMO, elementList.get(3));

        assertTrue(testTeam.removeCharacter(Character.AMBER));
        assertTrue(testTeam.removeCharacter(Character.CHONGYUN));
        assertTrue(testTeam.removeCharacter(Character.JEAN));
        assertTrue(testTeam.removeCharacter(Character.VENTI));
        assertEquals(0, characterList.size());

        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.FISCHL));
        assertEquals(2, characterList.size());
        assertEquals(Character.KEQING, characterList.get(0));
        assertEquals(Character.FISCHL, characterList.get(1));

        testTeam.updateElements();

        assertEquals(2, elementList.size());
        assertEquals(Element.ELECTRO, elementList.get(0));
        assertEquals(Element.ELECTRO, elementList.get(1));
    }

    @Test
    public void testUpdateElementalResonancesEmptyTeam() {
        testTeam.updateElementalResonances();

        assertEquals(0, resonanceList.size());
    }

    @Test
    public void testUpdateElementalResonancesOneCharacter() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertEquals(1, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));

        testTeam.updateElementalResonances();

        assertEquals(0, resonanceList.size());
    }

    @Test
    public void testUpdateElementalResonancesTwoCharactersNoResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));

        testTeam.updateElementalResonances();

        assertEquals(0, resonanceList.size());
    }

    @Test
    public void testUpdateElementalResonancesTwoCharactersResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.DILUC));
        assertEquals(2, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.DILUC, characterList.get(1));

        testTeam.updateElementalResonances();

        assertEquals(1, resonanceList.size());
        assertEquals(ElementalResonance.FERVENT_FLAMES, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonancesFourCharactersProtectiveCanopy() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElementalResonances();

        assertEquals(1, resonanceList.size());
        assertEquals(ElementalResonance.PROTECTIVE_CANOPY, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonancesFourCharactersOneResonance() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.JEAN));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.JEAN, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElementalResonances();

        assertEquals(1, resonanceList.size());
        assertEquals(ElementalResonance.IMPETUOUS_WINDS, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonancesFourCharactersTwoResonance() {
        assertTrue(testTeam.addCharacter(Character.GANYU));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.FISCHL));
        assertEquals(4, characterList.size());
        assertEquals(Character.GANYU, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.FISCHL, characterList.get(3));

        testTeam.updateElementalResonances();

        assertEquals(2, resonanceList.size());
        assertEquals(ElementalResonance.SHATTERING_ICE, resonanceList.get(0));
        assertEquals(ElementalResonance.HIGH_VOLTAGE, resonanceList.get(1));
    }

    @Test
    public void testUpdateElementalResonanceProtectiveCanopyMoreThanOnce() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElementalResonances();

        assertEquals(1, resonanceList.size());
        assertEquals(ElementalResonance.PROTECTIVE_CANOPY, resonanceList.get(0));

        testTeam.updateElementalResonances();
        assertEquals(1, resonanceList.size());
        assertEquals(ElementalResonance.PROTECTIVE_CANOPY, resonanceList.get(0));
    }

    @Test
    public void testUpdateElementalResonanceMoreThanOnce() {
        assertTrue(testTeam.addCharacter(Character.GANYU));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.FISCHL));
        assertEquals(4, characterList.size());
        assertEquals(Character.GANYU, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.FISCHL, characterList.get(3));

        testTeam.updateElementalResonances();

        assertEquals(2, resonanceList.size());
        assertEquals(ElementalResonance.SHATTERING_ICE, resonanceList.get(0));
        assertEquals(ElementalResonance.HIGH_VOLTAGE, resonanceList.get(1));

        testTeam.updateElementalResonances();
        assertEquals(2, resonanceList.size());
        assertEquals(ElementalResonance.SHATTERING_ICE, resonanceList.get(0));
        assertEquals(ElementalResonance.HIGH_VOLTAGE, resonanceList.get(1));
    }

    @Test
    public void testHasDuplicateElementsFalse() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.KEQING));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.KEQING, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElements();

        assertFalse(testTeam.hasDuplicateElements());
    }

    @Test
    public void testHasDuplicateElementsTrueTwoDuplicates() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.CHONGYUN));
        assertTrue(testTeam.addCharacter(Character.JEAN));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.CHONGYUN, characterList.get(1));
        assertEquals(Character.JEAN, characterList.get(2));

        testTeam.updateElements();

        assertTrue(testTeam.hasDuplicateElements());
    }

    @Test
    public void testHasDuplicateElementsTrueMoreThanTwoDuplicates() {
        assertTrue(testTeam.addCharacter(Character.AMBER));
        assertTrue(testTeam.addCharacter(Character.SUCROSE));
        assertTrue(testTeam.addCharacter(Character.JEAN));
        assertTrue(testTeam.addCharacter(Character.VENTI));
        assertEquals(4, characterList.size());
        assertEquals(Character.AMBER, characterList.get(0));
        assertEquals(Character.SUCROSE, characterList.get(1));
        assertEquals(Character.JEAN, characterList.get(2));
        assertEquals(Character.VENTI, characterList.get(3));

        testTeam.updateElements();

        assertTrue(testTeam.hasDuplicateElements());
    }

    @Test
    public void testToString() {
        assertEquals("Team", testTeam.toString());
    }
}
