package io.github.asvanberg.conway;

public sealed interface Cell {
    Cell evolve(long aliveNeighbours);
    Position position();

    record Alive(Position position) implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return this;
            }
            else {
                return new Dead(position);
            }
        }
    }
    record Dead(Position position) implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            if (aliveNeighbours == 3) {
                return new Alive(position);
            }
            else {
                return this;
            }
        }
    }
}
