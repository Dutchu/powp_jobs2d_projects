package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface CommandVisitor<R> {
    R visit(SetPositionCommand command);
    R visit(OperateToCommand command);
    R visit(ICompoundCommand command);
}