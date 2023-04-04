package io.github.asvanberg.conway;

public record Position(int x, int y) {
    public Position above() {
        return new Position(x, y - 1);
    }

    public Position below() {
        return new Position(x, y + 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }
}
