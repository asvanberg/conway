package io.github.asvanberg.conway;

public sealed interface Cell {
    Cell ALIVE = new Alive();
    Cell DEAD = new Dead();

    Cell evolve(long aliveNeighbours);

    record Alive() implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            return aliveNeighbours == 2 || aliveNeighbours == 3 ? ALIVE : DEAD;
        }
    }
    record Dead() implements Cell {
        @Override
        public Cell evolve(final long aliveNeighbours) {
            return aliveNeighbours == 3 ? ALIVE : DEAD;
        }
    }
}
