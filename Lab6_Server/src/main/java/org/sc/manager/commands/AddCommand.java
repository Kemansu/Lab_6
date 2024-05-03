package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.Flat;
import org.sc.data.generators.FlatGenerator;
import org.sc.data.generators.IdGenerator;
import org.sc.manager.CollectionManager;

/**
 * Данная команда добавляет новый элемент в коллекцию
 *
 * @author Kemansu
 * @since 1.0
 */

public class AddCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {

        Flat flat = request.getFlat();
        flat.setId(IdGenerator.getId());
        CollectionManager.add(flat);

        return "flat added!";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
