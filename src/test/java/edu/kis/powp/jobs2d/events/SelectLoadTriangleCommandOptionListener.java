package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.ShapeCommandFactory;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandBoundaryVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadTriangleCommandOptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CompoundCommand triangleCommand = new ShapeCommandFactory().createTriangleShape();
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(triangleCommand);;
    }
}