package com.zone.toyrobot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.zone.toyrobot.Heading.Compass.NORTH;
import static com.zone.toyrobot.Heading.Compass.WEST;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class RobotTest {

    private final Table table = new Table(5, 5);
    private final Robot robot = new Robot(1, 2, NORTH, table);


    @Test
    @DisplayName("moveForward should move robot 1 square north")
    void moveNorth() {

        robot.moveForward();
        assertThat(robot.getPosition(), equalTo(new Position(1, 3)));
    }

    @Test
    @DisplayName("moveForward should move robot 1 square south")
    void moveSouth() {
        robot.rotateRight();
        robot.rotateRight();
        robot.moveForward();
        assertThat(robot.getPosition(), equalTo(new Position(1, 1)));
    }

    @Test
    @DisplayName("moveForward should move robot 1 square east")
    void moveEast() {

        robot.rotateLeft();
        robot.moveForward();
        assertThat(robot.getPosition(), equalTo(new Position(0, 2)));
    }

    @Test
    @DisplayName("moveForward should move robot 1 square west")
    void moveWest() {

        robot.rotateRight();
        robot.moveForward();
        assertThat(robot.getPosition(), equalTo(new Position(2, 2)));
    }

    @Test
    @DisplayName("moveForward should not move robot off the table")
    void illegalMove() {
        Robot robotOnEdge = new Robot(0, 0, WEST, table);

        robotOnEdge.moveForward();
        assertThat(robotOnEdge.getPosition(), equalTo(new Position(0, 0)));
    }

    @Test
    @DisplayName("getStatus should display in format {x}, {y}, {direction}")
    void getStatus() {
        assertThat(robot.getStatus(), equalTo("1, 2, NORTH"));
    }

}