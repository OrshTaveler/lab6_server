package controllers;

import commands.Command;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.Serialization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInputLoop  extends ControlLoop{

    Scanner scanner;
    HumanBeingDAO humanBeings;
    public void printIndication(){
        System.out.print(">");
    }
    public ConsoleInputLoop(HashMap<String, Command> commands, HumanBeingDAO humanBeings) {
        super(commands);
        scanner = new Scanner(System.in);
        this.humanBeings =humanBeings;
        printIndication();
    }

    @Override
    public void run() {
        while (true) {
            String command_name = scanner.nextLine();
            try {
                Command command = commands.get(command_name);
                command.execute(null,null,humanBeings);
                printIndication();

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                if (!command_name.isEmpty())
                    System.out.println("Введена не существующая команда. help чтобы узнать команды");
                printIndication();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
