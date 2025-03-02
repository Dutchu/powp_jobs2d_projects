package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.canvas.ICanvas;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.CanvasFeature;

import java.util.Iterator;

public class CommandBoundaryVisitor implements CommandVisitor<Boolean> {

    @Override
    public Boolean visit(SetPositionCommand command) {
        return isWithinBounds(command.getPosX(), command.getPosY());
    }

    @Override
    public Boolean visit(OperateToCommand command) {
        return isWithinBounds(command.getPosX(), command.getPosY()); // stop further checks
    }

    @Override
    public Boolean visit(ICompoundCommand command) {
        boolean isWithinBounds = true;
        Iterator<DriverCommand> commandIterator = command.iterator();
        while (commandIterator.hasNext()) {
            DriverCommand subCommand = commandIterator.next();
            isWithinBounds = subCommand.accept(this);
            if (!isWithinBounds) {
                break;
            }
        }
        return isWithinBounds;
    }

    private boolean isWithinBounds(int x, int y) {
        ICanvas currentCanvas = CanvasFeature.getCurrentCanvas();
        return currentCanvas.checkIfPointInside(x, y);
    }
}
