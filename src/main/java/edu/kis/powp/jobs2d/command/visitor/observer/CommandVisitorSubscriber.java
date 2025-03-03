package edu.kis.powp.jobs2d.command.visitor.observer;

import edu.kis.powp.jobs2d.command.DriverCommand;

public interface CommandVisitorSubscriber {
    void update(DriverCommand command);
    String getVisitorMessage();
}
