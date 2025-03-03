package edu.kis.powp.jobs2d.command.visitor.observer;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

import java.util.Map;

public class CounterVisitorSubscriber implements CommandVisitorSubscriber {
    private final CommandVisitor<Map<String, Integer>> countingVisitor = new CommandCounterVisitor();
    private Map<String, Integer> counts = null;

    @Override
    public void update(DriverCommand command) {
        if (command != null) {
            counts = command.accept(countingVisitor);
        } else {
            counts = null;
        }
    }

    @Override
    public String getVisitorMessage() {
        if (counts == null) {
            return "No command loaded";
        }
        return "Counted Visits: " + counts;
    }
}