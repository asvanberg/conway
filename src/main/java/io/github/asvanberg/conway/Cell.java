package io.github.asvanberg.conway;

import java.util.Set;

public sealed abstract class Cell {
    protected final Position position;

    protected Cell(final Position position) {
        this.position = position;
    }

    public static Cell at(final Position position, final boolean alive) {
        if (alive) {
            return new Alive(position);
        }
        else {
            return new Dead(position);
        }
    }

    public Cell evolve(final Set<Cell> neighbours) {
        long aliveNeighbours = neighbours
                .stream()
                .filter(Cell::alive)
                .count();
        return evolveInternal(aliveNeighbours);
    }

    protected abstract Cell evolveInternal(final long aliveNeighbours);

    abstract boolean alive();

    public Position position() {
        return position;
    }

    private static final class Alive extends Cell {
        Alive(final Position position) {
            super(position);
        }

        @Override
        protected Cell evolveInternal(final long aliveNeighbours) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return this;
            }
            else {
                return new Dead(position);
            }
        }

        @Override
        boolean alive() {
            return true;
        }

        @Override
        public int hashCode() {
            return position().hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Alive other && position.equals(other.position);
        }
    }

    private static final class Dead extends Cell {
        Dead(final Position position) {
            super(position);
        }

        @Override
        protected Cell evolveInternal(final long aliveNeighbours) {
            if (aliveNeighbours == 3) {
                return new Alive(position);
            }
            else {
                return this;
            }
        }

        @Override
        boolean alive() {
            return false;
        }

        @Override
        public int hashCode() {
            return position().hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Dead other && position.equals(other.position);
        }
    }
}
