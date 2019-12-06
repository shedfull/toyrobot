package com.zone.toyrobot.command;

import com.zone.toyrobot.InvalidPositionException;
import com.zone.toyrobot.Robot;
import com.zone.toyrobot.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.zone.toyrobot.Heading.Compass.SOUTH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CommandParserTest {

    @Mock
    private Table table;

    @Mock
    private Robot robot;

    private final CommandParser commandParser = new CommandParser(System.out);

    @Test
    @DisplayName("executing parse 'left' command should invoke Robot.rotateLeft")
    void leftCommand() {
        given(table.getRobot()).willReturn(Optional.of(robot));
        commandParser.parse("left").ifPresent(command -> command.execute(table));
        then(robot).should().rotateLeft();
    }

    @Test
    @DisplayName("executing parse 'right' command should invoke Robot.rotateRight")
    void rightCommand() {
        given(table.getRobot()).willReturn(Optional.of(robot));
        commandParser.parse("right").ifPresent(command -> command.execute(table));
        then(robot).should().rotateRight();
    }

    @Test
    @DisplayName("executing parse 'move' command should invoke Robot.moveForward()")
    void moveCommand() {
        given(table.getRobot()).willReturn(Optional.of(robot));
        commandParser.parse("move").ifPresent(command -> command.execute(table));
        then(robot).should().moveForward();
    }

    @Test
    @DisplayName("executing parse 'report' command should invoke Robot.getStatus()")
    void reportCommand() {
        given(table.getRobot()).willReturn(Optional.of(robot));
        commandParser.parse("report").ifPresent(command -> command.execute(table));
        then(robot).should().getStatus();
    }

    @Test
    @DisplayName("executing parse 'place 2,2, SOUTH' command should invoke Table.place")
    void placeCommand() throws InvalidPositionException {
        commandParser.parse("place 2,2,SOUTH").ifPresent(command -> command.execute(table));
        then(table).should().place(2, 2, SOUTH);
    }

    @ParameterizedTest
    @DisplayName("parse should return Optional.empty with for invalid input'")
    @ValueSource(strings = {"", "ASDAS", "REPORT", "report 1", "place A,1,SOUTH"})
    void parseInvalidCommands(String commandString) {
        Optional<Command> actual = commandParser.parse(commandString);
        assertThat(actual, equalTo(Optional.empty()));
    }

}
