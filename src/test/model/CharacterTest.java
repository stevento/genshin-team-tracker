package model;

import exceptions.IllegalCharacterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class CharacterTest {
    Character testChar;

    @Test
    void testFindValidName() {
        try {
            testChar = Character.find("KEQING");
        } catch (IllegalCharacterException e) {
            fail("Did not expect IllegalCharacterException");
        }
    }

    @Test
    void testFindInvalidName() {
        try {
            testChar = Character.find("SUPERMAN");
            fail("Expected IllegalCharacterException");
        } catch (IllegalCharacterException e) {
            // all good
        }
    }
}
