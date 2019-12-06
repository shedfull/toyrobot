package com.zone.toyrobot;

import com.zone.toyrobot.Heading.Compass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.zone.toyrobot.Heading.Compass.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeadingTest {

    private final Heading heading = new Heading(NORTH);

    @Test
    @DisplayName("direction cannot be null")
    void directionCannotBeNull() {
        assertThrows(NullPointerException.class, () -> new Heading(null));
    }

    @Test
    @DisplayName("invoking rotateLeft 4 times should return all points of compass in anti-clockwise order")
    void left() {
        Compass[] order = {WEST, SOUTH, EAST, NORTH};
        for (Compass expected : order) {
            heading.left();
            assertThat(heading.getDirection(), equalTo(expected));
        }
    }

    @Test
    @DisplayName("invoking rotateRight 4 times should return all points of compass in clockwise order")
    void right() {
        Compass[] order = {EAST, SOUTH, WEST, NORTH};
        for (Compass expected : order) {
            heading.right();
            assertThat(heading.getDirection(), equalTo(expected));
        }
    }
}