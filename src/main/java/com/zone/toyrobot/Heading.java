package com.zone.toyrobot;

import java.util.Objects;

public class Heading {

    public enum Compass {
        NORTH, EAST, SOUTH, WEST
    }

    private Compass direction;

    public Heading(Compass direction) {
        Objects.requireNonNull(direction);
        this.direction = direction;
    }

    public Compass getDirection() {
        return direction;
    }

    public void left() {
        int ordinal = direction.ordinal() - 1;
        if (ordinal == -1) {
            ordinal = Compass.values().length - 1;
        }
        direction = Compass.values()[ordinal];

    }

    public void right() {
        int ordinal = direction.ordinal() + 1;
        if (ordinal == Compass.values().length) {
            ordinal = 0;
        }
        direction = Compass.values()[ordinal];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heading heading = (Heading) o;
        return direction == heading.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }
}
