package edu.kis.powp.jobs2d.command.visitor.observer;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandBoundaryVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

public class BoundaryVisitorSubscriber implements CommandVisitorSubscriber {
    private final CommandVisitor<Boolean> boundaryVisitor = new CommandBoundaryVisitor();
    private Boolean isWithinBoundaries = null;

    @Override
    public void update(DriverCommand command) {
        if (command != null) {
            isWithinBoundaries = command.accept(boundaryVisitor);
        } else {
            isWithinBoundaries = null;
        }
    }

    @Override
    public String getVisitorMessage() {
        if (isWithinBoundaries == null) {
            return "No command loaded";
        }
        return "Is Command within Canvas boundaries: " + isWithinBoundaries;
    }
}

