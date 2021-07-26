package model;

import org.junit.jupiter.api.Test;

import static model.ElementalResonance.FERVENT_FLAMES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ElementTest {
    @Test
    public void testToElementalResonance() {
        assertEquals(FERVENT_FLAMES, Element.PYRO.toElementalResonance());
    }

    @Test
    public void testToElementalResonanceNull() {
        assertNull(Element.NONE.toElementalResonance());
    }
}
