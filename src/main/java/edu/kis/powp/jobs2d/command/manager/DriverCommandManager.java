package edu.kis.powp.jobs2d.command.manager;

import java.util.*;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandBoundaryVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;
import edu.kis.powp.jobs2d.command.visitor.observer.BoundaryVisitorSubscriber;
import edu.kis.powp.jobs2d.command.visitor.observer.CommandVisitorSubscriber;
import edu.kis.powp.jobs2d.command.visitor.observer.CounterVisitorSubscriber;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
    private DriverCommand currentCommand = null;
    private Publisher changePublisher = new Publisher();
    private final List<CommandVisitorSubscriber> visitorSubscribers = new ArrayList<>();
    // Constructor to add default subscribers
    public DriverCommandManager() {
        visitorSubscribers.add(new BoundaryVisitorSubscriber());
        visitorSubscribers.add(new CounterVisitorSubscriber());
    }
    
    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        updateVisitorSubscribers();
        changePublisher.notifyObservers();
    }

    private void updateVisitorSubscribers() {
        for (CommandVisitorSubscriber subscriber : visitorSubscribers) {
            subscriber.update(currentCommand);
        }
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
        updateVisitorSubscribers();
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public synchronized void addVisitor(CommandVisitorSubscriber subscriber) {
        visitorSubscribers.add(subscriber);
        subscriber.update(currentCommand);
    }

    public synchronized String getVisitorMessages() {
        StringBuilder result = new StringBuilder();
        if (getCurrentCommand() == null) {
            result.append("No command loaded");
        } else {
            for (CommandVisitorSubscriber subscriber : visitorSubscribers) {
                result.append(subscriber.getVisitorMessage()).append("\n");
            }
        }
        return result.toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }
}
