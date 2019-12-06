package com.zone.toyrobot.command;

import com.zone.toyrobot.Heading.Compass;
import com.zone.toyrobot.InvalidPositionException;
import com.zone.toyrobot.Robot;
import com.zone.toyrobot.Table;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A CommandParser for parsing Strings into executable {@link Command} objects
 */
public class CommandParser {

    private static final String PLACE_COMMAND_STRING = "place ";
    private final Map<String, Command> noParamCommandMap;

    /**
     * Creates a new CommandParser that understands following commands:
     * 'place','left','right','move' and 'report'
     *
     * @param printStream PrintStream that report command writes to
     */
    public CommandParser(PrintStream printStream) {
        this.noParamCommandMap = new HashMap<>();
        noParamCommandMap.put("report", table -> table.getRobot().ifPresent(
                robot -> printStream.println(robot.getStatus())));
        noParamCommandMap.put("left", table -> table.getRobot().ifPresent(Robot::rotateLeft));
        noParamCommandMap.put("right", table -> table.getRobot().ifPresent(Robot::rotateRight));
        noParamCommandMap.put("move", table -> table.getRobot().ifPresent(Robot::moveForward));
    }

    /**
     * Attempts to parse the commandString into an executable {@link Command}
     * If the string cannot be parsed or matched to a Command an empty Optional is returned
     *
     * @param commandString String to parse
     * @return Optional containing a Command
     */
    public Optional<Command> parse(String commandString) {
        return Optional.ofNullable(findCommand(commandString));
    }

    private Command findCommand(String commandString) {
        Command command = noParamCommandMap.get(commandString);
        //we only currently have one command that takes params
        if (command == null) {
            command = parsePlaceCommand(commandString);
        }
        return command;
    }

    private Command parsePlaceCommand(String commandString) {
        if (commandString.startsWith(PLACE_COMMAND_STRING)) {
            String[] params = commandString.substring(PLACE_COMMAND_STRING.length()).split(",");
            if (params.length != 3) {
                return null;
            }
            try {
                return new PlaceCommand(parseInt(params[0]), parseInt(params[1]), parseCompass(params[2]));
            } catch (IllegalArgumentException ie) {
                //do nothing
            }
        }
        return null;
    }

    private Compass parseCompass(String param) {
        return Compass.valueOf(param.trim());
    }

    private int parseInt(String param) {
        return Integer.parseInt(param.trim());
    }

    static class PlaceCommand implements Command {
        private final int x;
        private final int y;
        private final Compass direction;

        PlaceCommand(int x, int y, Compass direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public void execute(Table table) {
            try {
                table.place(x, y, direction);
            } catch (InvalidPositionException e) {
                //do nothing
            }
        }
    }
}
