package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.drivers.adapter.transformation.TransformationMethod;
import edu.kis.powp.jobs2d.exceptions.CommandNotFoundException;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCommandTransformVisitorListener implements ActionListener {
    private final TransformationMethod transformationMethod;

    public SelectCommandTransformVisitorListener(TransformationMethod transformationMethod) {
        this.transformationMethod = transformationMethod;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        DriverCommand command = manager.getCurrentCommand();

        if (command == null) {
            throw new CommandNotFoundException("No command has been selected, cannot apply transformation.");
        }

        CommandTransformationVisitor commandVisitor = new CommandTransformationVisitor(this.transformationMethod);
        DriverCommand transformedCommand = command.accept(commandVisitor);
        manager.setCurrentCommand(transformedCommand);
    }
}
