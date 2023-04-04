package io.github.asvanberg.conway;

import java.util.HashSet;
import java.util.Set;

public class UniverseLoader {
    public static Universe fromString(String textualUniverse) {
        String[] rows = textualUniverse.split("\n");
        int width = rows[0].length();
        int height = rows.length;

        Set<Position> aliveCells = new HashSet<>();
        for (int y = 0; y <rows.length; y++) {
            String row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                if (row.charAt(x) == '#') {
                    aliveCells.add(new Position(x, y));
                }
            }
        }
        return new Universe(width, height, aliveCells);
    }
}
