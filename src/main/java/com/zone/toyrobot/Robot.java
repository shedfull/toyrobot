package com.zone.toyrobot;

import com.zone.toyrobot.Heading.Compass;

import java.util.Objects;

public class Robot {

    private Position position;
    private final Heading heading;
    private static final String OUTPUT_FORMAT = "%s, %s, %s";
    private final Table table;

    Robot(int x, int y, Compass direction, Table table) {
        Objects.requireNonNull(direction);
        Objects.requireNonNull(table);
        this.position = new Position(x, y);
        this.heading = new Heading(direction);
        this.table = table;
    }

    /**
     * Moves this Robot forward 1 square in the direction it is currently facing
     * If the move will make the Robot's position invalid in terms of the table
     * it has been placed on then no move will occur
     *
     * @see Table#isInvalidPosition(Position)
     */
    public void moveForward() {
        Position newPosition = createForwardPosition();
        if (!table.isInvalidPosition(newPosition)) {
            position = newPosition;
        }

    }

    private Position createForwardPosition() {
        switch (heading.getDirection()) {
            case EAST:
                return new Position(position.getX() + 1, position.getY());
            case WEST:
                return new Position(position.getX() - 1, position.getY());
            case NORTH:
                return new Position(position.getX(), position.getY() + 1);
            case SOUTH:
                return new Position(position.getX(), position.getY() - 1);
            default:
                return position;
        }
    }

    /**
     * @return the position and compass heading of the robot
     */
    public String getStatus() {
        return String.format(OUTPUT_FORMAT, position.getX(), position.getY(), heading.getDirection());
    }

    public void rotateLeft() {
        heading.left();
    }

    public void rotateRight() {
        heading.right();
    }

    Position getPosition() {
        return new Position(position.getX(), position.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return position.equals(robot.position) &&
                heading.equals(robot.heading) &&
                Objects.equals(table, robot.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, heading, table);
    }
}
