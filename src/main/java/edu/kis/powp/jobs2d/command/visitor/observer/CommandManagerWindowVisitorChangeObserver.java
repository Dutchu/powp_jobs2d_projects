package edu.kis.powp.jobs2d.command.visitor.observer;

import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindowVisitorChangeObserver implements Subscriber {
    private final CommandManagerWindow commandManagerWindow;

    public CommandManagerWindowVisitorChangeObserver(CommandManagerWindow commandManagerWindow) {
        this.commandManagerWindow = commandManagerWindow;
    }

    @Override
    public void update() {
        commandManagerWindow.updateVisitorFields();
    }
}
