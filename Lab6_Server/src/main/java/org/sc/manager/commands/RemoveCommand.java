package org.sc.manager.commands;

import org.sc.Request;
import org.sc.data.Flat;
import org.sc.manager.CollectionManager;

import java.util.ArrayDeque;

/**
 *  Данная команда удаляет из коллекции элемент, по заданному id
 *
 * @author Kemansu
 * @since 1.0
 */
public class RemoveCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {
        if (request.getMessage().split(" ").length != 2 || !request.getMessage().split(" ")[1].matches("^-{0,1}\\d{1,}") || CollectionManager.getArrayDeque().size() == 0 || Long.parseLong(request.getMessage().split(" ")[1]) > CollectionManager.getArrayDeque().getLast().getId() || Long.parseLong(request.getMessage().split(" ")[1]) <= 0) {
            return  "wrong id";
        } else {
            Long neededId = Long.parseLong(request.getMessage().split(" ")[1]);
            Object[] array = CollectionManager.getArrayDeque().toArray();

            // заносим все в обновленный массив
            ArrayDeque<Flat> updatedArraydeque = new ArrayDeque<>();

            for (Object object : array) {
                Flat flat = (Flat) object;
                if (flat.getId() != neededId) {
                    updatedArraydeque.add(flat);
                }
            }
            CollectionManager.setArrayDeque(updatedArraydeque);
            return "Flat successfully remove!";
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
}
