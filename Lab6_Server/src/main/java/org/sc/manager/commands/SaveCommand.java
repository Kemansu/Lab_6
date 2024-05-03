package org.sc.manager.commands;

import org.sc.manager.WritterXML;
import org.sc.Request;

/**
 *  Данная команда сохраняет коллекицю в файл, в формате xml
 *
 * @author Kemansu
 * @since 1.0
 */
public class SaveCommand implements Command{
    @Override
    public String execute(Request request) throws Exception {
        WritterXML.write();
        return "collection was successfully saved!";
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
