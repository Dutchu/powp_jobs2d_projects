package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundCommandCopyVisitor implements CommandVisitor<DriverCommand> {
    private DriverCommand copiedCommand;

    @Override
    public DriverCommand visit(OperateToCommand command) {
        return new OperateToCommand(command.getPosX(), command.getPosY());
    }

    @Override
    public DriverCommand visit(SetPositionCommand command) {
        return new SetPositionCommand(command.getPosX(), command.getPosY());
    }

    @Override
    public DriverCommand visit(ICompoundCommand command) {
        List<DriverCommand> copiedCommandList = new ArrayList<>();
        Iterator<DriverCommand> iterator = command.iterator();
        while (iterator.hasNext()) {
            DriverCommand subCommand = iterator.next();
            // Recursively copy each sub-command
            DriverCommand copiedSubCommand = subCommand.accept(this);
            copiedCommandList.add(copiedSubCommand);
        }
        return new CompoundCommand(copiedCommandList, command.toString());
    }
}
