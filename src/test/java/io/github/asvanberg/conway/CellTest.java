package io.github.asvanberg.conway;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    public void alive_cell_lives_with_2_neighbours() {
        Cell alive = Cell.ALIVE;

        Cell cell = alive.evolve(2);

        assertInstanceOf(Cell.Alive.class, cell);
    }

    @Test
    public void alive_cell_lives_with_3_neighbours() {
        Cell alive = Cell.ALIVE;

        Cell cell = alive.evolve(3);

        assertInstanceOf(Cell.Alive.class, cell);
    }

    @Property
    public void alive_cell_dies_with_above_3_neighbours(@ForAll @IntRange(min = 4) int neighbours) {
        Cell alive = Cell.ALIVE;

        Cell cell = alive.evolve(neighbours);

        assertInstanceOf(Cell.Dead.class, cell);
    }

    @Property
    public void alive_cell_dies_with_below_2_neighbours(@ForAll @IntRange(max = 1) int neighbours) {
        Cell alive = Cell.DEAD;

        Cell cell = alive.evolve(neighbours);

        assertInstanceOf(Cell.Dead.class, cell);
    }

    @Test
    public void dead_cell_starts_living_with_3_neighbours() {
        Cell dead = Cell.DEAD;

        Cell cell = dead.evolve(3);

        assertInstanceOf(Cell.Alive.class, cell);
    }

    @Property
    public void dead_cell_stays_dead_with_more_than_3_neighbours(@ForAll @IntRange(min = 4) int neighbours) {
        Cell dead = Cell.DEAD;

        Cell cell = dead.evolve(neighbours);

        assertInstanceOf(Cell.Dead.class, cell);
    }

    @Property
    public void dead_cell_stays_dead_with_less_than_3_neighbours(@ForAll @IntRange(max = 2) int neighbours) {
        Cell dead = Cell.DEAD;

        Cell cell = dead.evolve(neighbours);

        assertInstanceOf(Cell.Dead.class, cell);
    }
}