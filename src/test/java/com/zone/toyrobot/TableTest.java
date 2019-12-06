package com.zone.toyrobot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static com.zone.toyrobot.Heading.Compass.NORTH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableTest {

    private final Table table = new Table(5, 5);

    @Test
    @DisplayName("getRobot should return empty if no robot has been place on the table")
    void getRobotShouldReturnEmpty() {
        assertThat(table.getRobot(), equalTo(Optional.empty()));
    }

    @Test
    @DisplayName("place should place a robot on the table")
    void place() throws Exception {
        Robot expected = new Robot(0, 0, NORTH, table);
        table.place(0, 0, NORTH);

        assertThat(table.getRobot(), equalTo(Optional.of(expected)));
    }

    @ParameterizedTest
    @DisplayName("place should throw InvalidPositionException if the robot's position off the table")
    @CsvSource({"-1,0", "6,0", "-1,-1", "6,6", "0,-1", "6,6"})
    void putWithInvalidRobotPositionForTable(int x, int y) {
        assertThrows(InvalidPositionException.class, () -> {
            table.place(x, y, NORTH);
        });
    }
}