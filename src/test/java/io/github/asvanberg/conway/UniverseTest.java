package io.github.asvanberg.conway;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UniverseTest {
    @Property
    public void stable_block(@ForAll @IntRange(max = 100) int evolutions) {
        Set<Position> block = Set.of(
                new Position(1, 1), new Position(2, 1),
                new Position(1, 2), new Position(2, 2));

        Universe universe = new Universe(4, 4, block);
        for (int i = 0; i < evolutions; i++) {
            universe = universe.evolve();
        }

        for (Position position : block) {
            assertTrue(universe.cellLivesAt(position));
        }
    }

    @Test
    public void oscillator_step_2_goes_back_after_two_evolutions() {
        Universe origin = UniverseLoader.fromString("""
                .....
                .....
                .###.
                .....
                .....
                """);

        Universe evolved = origin
                .evolve()
                .evolve();

        assertEquals(origin, evolved);
    }

    @Test
    public void oscillator_step_2() {
        Universe origin = UniverseLoader.fromString("""
                .....
                .....
                .###.
                .....
                .....
                """);

        Universe evolved = origin.evolve();

        assertTrue(evolved.cellLivesAt(new Position(2, 1)));
        assertTrue(evolved.cellLivesAt(new Position(2, 2)));
        assertTrue(evolved.cellLivesAt(new Position(2, 3)));
        assertFalse(evolved.cellLivesAt(new Position(1, 3)));
        assertFalse(evolved.cellLivesAt(new Position(3, 3)));
    }


    @Test
    public void oscillator_step_3() {
        Universe origin = UniverseLoader.fromString("""
                .................
                .................
                ....###...###....
                .................
                ..#....#.#....#..
                ..#....#.#....#..
                ..#....#.#....#..
                ....###...###....
                .................
                ....###...###....
                ..#....#.#....#..
                ..#....#.#....#..
                ..#....#.#....#..
                .................
                ....###...###....
                .................
                .................
                """);

        Universe evolved = origin
                .evolve()
                .evolve()
                .evolve();

        assertEquals(origin, evolved);
    }
}
