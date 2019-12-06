package com.zone.toyrobot.command;

import com.zone.toyrobot.Table;

@FunctionalInterface
public interface Command {
    void execute(Table table);
}
