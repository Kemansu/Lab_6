package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.Flat;
import org.sc.data.comparators.FlatsComparator;
import org.sc.data.generators.FlatGenerator;
import org.sc.manager.CollectionManager;

import java.util.ArrayDeque;
import java.util.List;

/**
 *  Данная команда удаляет из коллекции все элементы, меньшие, чем заданный
 *
 *
 * @author Kemansu
 * @since 1.0
 */

public class RemoveLowerCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {
        // Заданный параметр
        Flat flat_param = request.getFlat();

        Integer size = CollectionManager.getArrayDeque().size();
        FlatsComparator c = new FlatsComparator();
        Object[] array = CollectionManager.getArrayDeque().toArray();
        Integer counter = 0;

        List<Flat> list = CollectionManager.getArrayDeque().stream().filter(x -> c.compare(x, flat_param) >= 0).toList();
        counter = (size - list.size());
        CollectionManager.clear();
        list.forEach(CollectionManager::add);


        return  "Collection was successfully changed!\nFlats deleted - " + counter;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
