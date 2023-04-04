package io.github.asvanberg.conway;

import java.io.IOException;

public class Conway {
    public static void main(String[] args) throws IOException {
        Universe universe = UniverseLoader.fromString("""
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

        do {
            show(universe);
            universe = universe.evolve();
        } while (System.in.read() != 'q');
    }

    private static void show(final Universe universe) {
        for (int y = 0; y < universe.height(); y++) {
            for (int x = 0; x < universe.width(); x++) {
                if (universe.cellLivesAt(new Position(x, y))) {
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
