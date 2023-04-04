package io.github.asvanberg.conway;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Universe {
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
                Position position = new Position(x, y);
                boolean alive = aliveCells.contains(position);
                cells[y][x] = alive ? Cell.ALIVE : Cell.DEAD;
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
                Position position = new Position(x, y);
                evolved[y][x] = evolve(cells[y][x], position);
            }
        }
        return new Universe(width, height, evolved);
    }

    private Cell evolve(final Cell cell, final Position position) {
        long aliveNeighbours = neighbours(position)
                .stream()
                .filter(Cell.Alive.class::isInstance)
                .count();
        return cell.evolve(aliveNeighbours);
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public boolean cellLivesAt(Position position) {
        return cells[position.y()][position.x()] instanceof Cell.Alive;
    }

    private Collection<Cell> neighbours(Position position) {
        Position above = position.above();
        Position below = position.below();
        return List.of(
                cellAt(above),
                cellAt(above.left()),
                cellAt(above.right()),
                cellAt(below),
                cellAt(below.right()),
                cellAt(below.left()),
                cellAt(position.left()),
                cellAt(position.right()));
    }

    private Cell cellAt(final Position position) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height()) {
            // a dead cell is as good as any
            return Cell.DEAD;
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
