package org.sc;

import org.sc.data.generators.IdGenerator;
import org.sc.manager.CollectionManager;
import org.sc.manager.CommandManager;
import org.sc.manager.ReaderXML;

import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        ReaderXML.read();
        IdGenerator idGenerator = new IdGenerator();
        idGenerator.Fuel();
        int port = 1234; // Здесь используйте порт, который вам нужен
        try {
            Server server = new Server(port);
            server.listen();
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
