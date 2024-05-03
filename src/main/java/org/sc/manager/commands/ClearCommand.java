package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.generators.IdGenerator;
import org.sc.manager.CollectionManager;
/**
 *  Данная команда очищает коллекцию
 *
 * @author Kemansu
 * @since 1.0
 */
public class ClearCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {
        CollectionManager.clear();
        IdGenerator.Fuel();
        return "Collection successfully cleared!";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}
