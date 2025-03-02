package edu.kis.powp.jobs2d.command.manager;

import java.util.*;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandBoundaryVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
    private DriverCommand currentCommand = null;
    private Publisher changePublisher = new Publisher();
    private final CommandVisitor<Map<String, Integer>> countingVisitor = new CommandCounterVisitor();
    private final CommandVisitor<Boolean> boundaryVisitor = new CommandBoundaryVisitor();
    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        setCurrentCommand(new CompoundCommand(commandList, name));
    }

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

//    public synchronized void addVisitor(CommandVisitor<StringBuilder> visitor) {
//        this.loggingVisitors.add(visitor);
//    }

    public synchronized String getVisitorMessages() {
        StringBuilder result = new StringBuilder();
        if (getCurrentCommand() == null) {
            result.append("No command loaded");
        } else {
            result.append("Counted Visits: ").append(getCurrentCommand().accept(countingVisitor)).append("\n");
            result.append("Is Command within Canvas boundaries").append(": ").append(getCurrentCommand().accept(boundaryVisitor)).append("\n");
        }
        return result.toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }
}
