package io.github.asvanberg.conway;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Universe {
    private static final Position DUMMY = new Position(-1, -1);

    private final int width;
    private final int height;

    private final Cell[][] cells;

    /**
     * @param aliveCells (0,0) is top left and positive x is right while positive y is down
     */
    public Universe(final int width, final int height, Set<Position> aliveCells) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                Position o = new Position(x, y);
                boolean alive = aliveCells.contains(o);
                cells[y][x] = Cell.at(o, alive);
            }
        }
    }

    private Universe(final int width, final int height, final Cell[][] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public Universe evolve() {
        Cell[][] evolved = new Cell[height][width];
        for (int y = 0; y < evolved.length; y++) {
            for (int x = 0; x < evolved[y].length; x++) {
                evolved[y][x] = cells[y][x].evolve(neighbours(cells[y][x]));
            }
        }
        return new Universe(width, height, evolved);
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public boolean cellLivesAt(Position position) {
        return cells[position.y()][position.x()].alive();
    }

    private Set<Cell> neighbours(Cell cell) {
        Position above = cell.position().above();
        Position below = cell.position().below();
        return Set.of(
                cellAt(above),
                cellAt(above.left()),
                cellAt(above.right()),
                cellAt(below),
                cellAt(below.right()),
                cellAt(below.left()),
                cellAt(cell.position().left()),
                cellAt(cell.position().right()));
    }

    private Cell cellAt(final Position position) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height()) {
            // a dead cell is as good as any
            return Cell.at(position, false);
        }
        return cells[position.y()][position.x()];
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        return o instanceof Universe universe
               && width == universe.width
               && height == universe.height
               && Arrays.deepEquals(cells, universe.cells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
    }
}
