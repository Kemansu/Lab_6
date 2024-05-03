package org.sc;

import org.sc.data.Flat;
import org.sc.data.House;
import org.sc.data.generators.FlatGenerator;
import org.sc.data.generators.HouseGenerator;
import org.sc.exceptions.WrongInputException;
import org.sc.manager.ExecuteScriptCommand;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Console {
    private Request request;
    public void start(InputStream inputStream) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(inputStream);
            String input;
            try {
                input = scanner.nextLine();
                request = new Request(input);
                Server client = new Server();

                if (input.equals("add") || input.equals("add_if_min") || input.equals("remove_greater") || input.equals("remove_lower")) {
                    Flat flat = FlatGenerator.createFlat(0L);
                    request.setFlat(flat);

                } else if (input.split(" ")[0].equals("update")) {
                    String anwser = client.sendEcho(request);

                    if (anwser.equals("wrong id")) {
                        System.out.println(anwser);
                        continue;
                    } else {
                        System.out.println("Let's get a new flat");
                        request.setFlat(FlatGenerator.createFlat(0L));
                    }

                } else if (input.equals("exit")) {
                    System.exit(1);
                } else if (input.equals("count_greater_than_house")) {
                    House house = HouseGenerator.createHouse();
                    request.setHouse(house);
                }
                if (input.split(" ")[0].equals("execute_script_command")) {
                    ExecuteScriptCommand.execute(request.getMessage());
                } else {
                    String echo = client.sendEcho(request);
                    System.out.println("Echo from server: \n" + echo);
                    client.close();
                }


            } catch (SocketException e) {
                System.out.println("SocketException: \n" + e.getMessage());
            } catch (UnknownHostException e) {
                System.out.println("UnknownHostException: \n" + e.getMessage());
            }
        }

    }

}
