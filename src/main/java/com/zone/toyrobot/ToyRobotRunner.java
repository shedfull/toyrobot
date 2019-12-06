package com.zone.toyrobot;

import com.zone.toyrobot.command.CommandParser;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Entry point for Toy Robot Simulation
 * Running this class will read commands from std.in and output to std.out
 */
public class ToyRobotRunner {

    private final Table table;
    private final CommandParser commandParser;

    public ToyRobotRunner(int gridSize, PrintStream outputStream) {
        table = new Table(gridSize - 1, gridSize - 1);
        commandParser = new CommandParser(outputStream);
    }

    public static void main(String[] args) {

        ToyRobotRunner toyRobotRunner = new ToyRobotRunner(5, System.out);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            toyRobotRunner.execute(sc.nextLine());
        }
    }

    void execute(String commandString) {
        commandParser.parse(commandString).ifPresent(cmd -> cmd.execute(table));
    }
}
