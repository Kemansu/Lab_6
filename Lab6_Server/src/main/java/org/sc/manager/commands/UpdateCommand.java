package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.Flat;
import org.sc.data.generators.FlatGenerator;
import org.sc.manager.CollectionManager;

import java.util.ArrayDeque;

/**
 *  Данная команда обновляет элемент коллекции по заданному id
 *
 * @author Kemansu
 * @since 1.0
 */

public class UpdateCommand implements Command {
    @Override
    public String execute(Request request) throws Exception {
        if (request.getMessage().split(" ").length != 2 || !request.getMessage().split(" ")[1].matches("^-{0,1}\\d{1,}") || CollectionManager.getArrayDeque().size() == 0 || Long.parseLong(request.getMessage().split(" ")[1]) > CollectionManager.getArrayDeque().getLast().getId() || Long.parseLong(request.getMessage().split(" ")[1]) <= 0) {
            return "wrong id";
        } else {
            if (request.getFlat() == null) {
                return "id is good";
            }

            Long neededId = Long.parseLong(request.getMessage().split(" ")[1]);
            Object[] array = CollectionManager.getArrayDeque().toArray();
            Flat newflat = request.getFlat();
            newflat.setId(Long.valueOf(request.getMessage().split(" ")[1]));
            array[(int) (neededId - 1)] = newflat;

            ArrayDeque<Flat> updatedArraydeque = new ArrayDeque<>();

            for (Object flat : array) {
                updatedArraydeque.add((Flat) flat);
            }
            CollectionManager.setArrayDeque(updatedArraydeque);

            return "Flat successfully update!";
        }
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}