package com.zone.toyrobot;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ToyRobotRunnerTest {
    /*
    place 0,0,NORTH
    move
    report => Output:0, 1, NORTH
    place 0, 0, NORTH
    left
    report => Output:0, 0, WEST
    place 1,2,EAST
    move
    move
    left
    move
    report => Output:3, 3, NORTH
     */
    @Test
    void execute() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bos);
        ToyRobotRunner runner = new ToyRobotRunner(5, out);
        runner.execute("place 0,0,NORTH");
        runner.execute("move");
        runner.execute("report");
        assertThat(bos.toString("UTF-8"), equalTo("0, 1, NORTH\n"));
        bos.reset();
        runner.execute("place 0, 0, NORTH");
        runner.execute("left");
        runner.execute("report");
        assertThat(bos.toString("UTF-8"), equalTo("0, 0, WEST\n"));
        bos.reset();
        runner.execute("place 1,2,EAST");
        runner.execute("move");
        runner.execute("move");
        runner.execute("left");
        runner.execute("move");
        runner.execute("report");
        assertThat(bos.toString("UTF-8"), equalTo("3, 3, NORTH\n"));

    }
}
