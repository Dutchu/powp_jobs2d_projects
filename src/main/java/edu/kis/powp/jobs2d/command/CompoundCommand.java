package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

public final class CompoundCommand implements ICompoundCommand {

    private final List<DriverCommand> commandList;
    private final String name;

    public CompoundCommand(List<DriverCommand> commandList, String name) {
        this.commandList = commandList.stream().map(DriverCommand::copy).collect(Collectors.toList());
        this.name = new String(name);
    }

    @Override
    public void execute(Job2dDriver driver) {
        commandList.forEach((c) -> c.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commandList.iterator();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public CompoundCommand copy() {
        return new CompoundCommand(commandList, name);
    }

    @Override
    public <R> R accept(CommandVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
