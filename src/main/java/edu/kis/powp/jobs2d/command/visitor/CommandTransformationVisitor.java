package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.drivers.adapter.transformation.TransformationMethod;
import edu.kis.powp.jobs2d.drivers.adapter.transformation.TransformationPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandTransformationVisitor implements CommandVisitor<DriverCommand> {

    private final TransformationMethod transformationMethod;

    public CommandTransformationVisitor(TransformationMethod transformationMethod) {
        this.transformationMethod = transformationMethod;
    }

    @Override
    public DriverCommand visit(SetPositionCommand command) {
        TransformationPoint transformedPoint = transformPoint(command.getPosX(), command.getPosY());
        return new SetPositionCommand(transformedPoint.getX(), transformedPoint.getY());
    }

    @Override
    public DriverCommand visit(OperateToCommand command) {
        TransformationPoint transformedPoint = transformPoint(command.getPosX(), command.getPosY());
        return new OperateToCommand(transformedPoint.getX(), transformedPoint.getY());
    }

    @Override
    public DriverCommand visit(ICompoundCommand command) {
        List<DriverCommand> commandsList = new ArrayList<>();
        Iterator<DriverCommand> iterator = command.iterator();
        while (iterator.hasNext()) {
            DriverCommand subCommand = iterator.next();
            // Recursively transform the sub-command
            DriverCommand transformedSubCommand = subCommand.accept(this);
            commandsList.add(transformedSubCommand);
        }
        return new CompoundCommand(commandsList, command.toString());
    }

    private TransformationPoint transformPoint(int x, int y) {
        TransformationPoint originalPoint = new TransformationPoint(x, y);
        return transformationMethod.transform(originalPoint);
    }
}