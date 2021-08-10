package model;

import exceptions.IllegalCharacterException;
import org.junit.jupiter.api.Test;

import static model.Character.KEQING;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    Character testChar;

    @Test
    void testFindValidName() {
        try {
            testChar = Character.find("KEQING");
        } catch (IllegalCharacterException e) {
            fail("Did not expect IllegalCharacterException");
        }
        assertEquals(KEQING, testChar);
    }

    @Test
    void testFindInvalidName() {
        try {
            testChar = Character.find("SUPERMAN");
            fail("Expected IllegalCharacterException");
        } catch (IllegalCharacterException e) {
            // all good
        }
        assertNull(testChar);
    }
}
