package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.Flat;
import org.sc.data.comparators.*;
import org.sc.data.generators.FlatGenerator;
import org.sc.data.generators.IdGenerator;
import org.sc.manager.CollectionManager;
import org.sc.manager.CommandManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Данная команда добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 *
 * @author Kemansu
 * @since 1.0
 */
public class AddIfMinCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {
        // Преобразуем коллекцию в лист
        List<Flat> arrayDeque_list = CollectionManager.getArrayDeque().stream().toList();

        // Проверка на минимум (через сортировку)
        FlatsComparator c = new FlatsComparator();
        if (CollectionManager.getArrayDeque().size() == 0) {
            return "Flat did not suit :(";
        }

        Flat min_flat = arrayDeque_list.stream().min(c).get();

        Flat flat = request.getFlat();

        if (c.compare(flat, min_flat) < 0) {
            flat.setId(IdGenerator.getId());
            CollectionManager.add(flat);
            return "Flat successfully added!";
        } else {
            return "Flat did not suit :(";
        }

    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
