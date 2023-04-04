package io.github.asvanberg.conway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniverseLoaderTest {
    @Test
    public void load_from_string() {
        Universe universe = UniverseLoader.fromString("....\n.##.\n.##.\n....\n");

        assertEquals(4, universe.width());
        assertEquals(4, universe.height());

        assertFalse(universe.cellLivesAt(new Position(0, 0)));
        assertFalse(universe.cellLivesAt(new Position(0, 1)));
        assertFalse(universe.cellLivesAt(new Position(0, 2)));
        assertFalse(universe.cellLivesAt(new Position(0, 3)));

        assertFalse(universe.cellLivesAt(new Position(1, 0)));
        assertTrue(universe.cellLivesAt(new Position(1, 1)));
        assertTrue(universe.cellLivesAt(new Position(1, 2)));
        assertFalse(universe.cellLivesAt(new Position(1, 3)));

        assertFalse(universe.cellLivesAt(new Position(2, 0)));
        assertTrue(universe.cellLivesAt(new Position(2, 1)));
        assertTrue(universe.cellLivesAt(new Position(2, 2)));
        assertFalse(universe.cellLivesAt(new Position(2, 3)));

        assertFalse(universe.cellLivesAt(new Position(3, 0)));
        assertFalse(universe.cellLivesAt(new Position(3, 1)));
        assertFalse(universe.cellLivesAt(new Position(3, 2)));
        assertFalse(universe.cellLivesAt(new Position(3, 3)));
    }
}