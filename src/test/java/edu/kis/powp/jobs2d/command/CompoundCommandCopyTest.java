package edu.kis.powp.jobs2d.command;

import static org.junit.jupiter.api.Assertions.*;

import edu.kis.powp.jobs2d.command.visitor.CompoundCommandCopyVisitor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundCommandCopyTest {
    @Test
    void copy() {
        CompoundCommand compoundCommand2 = getCompoundCommand();

        CompoundCommandCopyVisitor visitor = new CompoundCommandCopyVisitor();
        CompoundCommand result = (CompoundCommand) compoundCommand2.accept(visitor);

        assertNotSame(compoundCommand2, result);

        Iterator<DriverCommand> originalIterator = compoundCommand2.iterator();
        Iterator<DriverCommand> copiedIterator = result.iterator();

        while (originalIterator.hasNext() && copiedIterator.hasNext()) {
            DriverCommand originalCommand = originalIterator.next();
            DriverCommand copiedCommand = copiedIterator.next();
            assertTrue(originalCommand.hashCode() != copiedCommand.hashCode());
            assertNotSame(originalCommand, copiedCommand);
        }

        assertFalse(originalIterator.hasNext());
        assertFalse(copiedIterator.hasNext());

    }

    private static CompoundCommand getCompoundCommand() {
        OperateToCommand opCmd1 = new OperateToCommand(2, 5);
        SetPositionCommand stCmd1 = new SetPositionCommand(4, 7);

        List<DriverCommand> list1 = new ArrayList<>();
        list1.add(opCmd1);
        list1.add(stCmd1);

        CompoundCommand compoundCommand1 = new CompoundCommand(list1, "test1");

        OperateToCommand opCmd2 = new OperateToCommand(8, 10);
        SetPositionCommand stCmd2 = new SetPositionCommand(12, 15);

        List<DriverCommand> list2 = new ArrayList<>();
        list2.add(opCmd2);
        list2.add(stCmd2);
        list2.add(compoundCommand1);

        CompoundCommand compoundCommand2 = new CompoundCommand(list2, "test2");
        return compoundCommand2;
    }
}