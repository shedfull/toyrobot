package com.zone.toyrobot;

import com.zone.toyrobot.Heading.Compass;

import java.util.Optional;

/**
 * Table represents the table the toy robot is designed to moveForward about on
 */
public class Table {

    private final int dimensionX;
    private final int dimensionY;

    private Robot robot;

    public Table(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    /**
     * @return Returns an Optional containing the Robot that has been placed on this Table
     */
    public Optional<Robot> getRobot() {
        return Optional.ofNullable(robot);
    }

    /**
     * Places a Robot on the table, if a Robot has already been
     * placed on the table a new one will be created and overwrite the
     * existing one
     *
     * @throws InvalidPositionException if  position is off the table
     */

    public void place(int x, int y, Compass direction) throws InvalidPositionException {
        Robot r = new Robot(x, y, direction, this);
        checkPositionOnTable(r.getPosition());
        this.robot = r;
    }

    private void checkPositionOnTable(Position position) throws InvalidPositionException {
        if (isInvalidPosition(position)) {
            throw new InvalidPositionException();
        }
    }

    /**
     * @param position Position to check
     * @return Returns true if the Position does not represent a square on the table
     */
    boolean isInvalidPosition(Position position) {
        return position.getX() < 0 || position.getX() > dimensionX ||
                position.getY() < 0 || position.getY() > dimensionY;
    }
}
