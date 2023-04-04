package io.github.asvanberg.conway;

import java.io.IOException;
import java.nio.file.Path;

public class Conway {
    public static void main(String[] args) throws IOException {
        Universe universe = getUniverse(args);
        do {
            show(universe);
            universe = universe.evolve();
        } while (System.in.read() != 'q');
    }

    private static Universe getUniverse(final String[] args) throws IOException {
        if (args.length == 1) {
            return UniverseLoader.fromFile(Path.of(args[0]));
        }
        else {
            return UniverseLoader.fromString("""
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
        }
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
