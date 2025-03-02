package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandCounterVisitor implements CommandVisitor<Map<String, Integer>> {

    // Helper method to merge two count maps.
    private Map<String, Integer> merge(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> merged = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            merged.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        return merged;
    }

    @Override
    public Map<String, Integer> visit(SetPositionCommand command) {
        Map<String, Integer> result = new HashMap<>();
        result.put(command.getClass().getSimpleName(), 1);
        return result;
    }

    @Override
    public Map<String, Integer> visit(OperateToCommand command) {
        Map<String, Integer> result = new HashMap<>();
        result.put(command.getClass().getSimpleName(), 1);
        return result;
    }

    @Override
    public Map<String, Integer> visit(ICompoundCommand command) {
        // Start with an empty map.
        Map<String, Integer> result = new HashMap<>();

        // Optionally, if you want to count the compound command itself, you could uncomment the following:
        // result.put(command.getClass().getSimpleName(), 1);
        Iterator<DriverCommand> commandListIterator = command.iterator();
        while(commandListIterator.hasNext())
        {
            DriverCommand cmd = commandListIterator.next();
            Map<String, Integer> subResult = cmd.accept(this);
            result = merge(result, subResult);
        }
        return result;
    }
}