package io.github.asvanberg.conway;

public sealed interface Cell {
    Cell ALIVE = new Alive();
    Cell DEAD = new Dead();

    Cell evolve(long aliveNeighbours);

    record Alive() implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return this;
            }
            else {
                return DEAD;
            }
        }
    }
    record Dead() implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            if (aliveNeighbours == 3) {
                return ALIVE;
            }
            else {
                return this;
            }
        }
    }
}
