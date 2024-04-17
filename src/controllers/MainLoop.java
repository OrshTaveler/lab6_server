package controllers;

import commands.Command;
import commands.Exit;
import commands.Save;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.Serialization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс наследник ControlLoop. Получает команды из стандартного потока ввода
 * @author Ubica228
 */
public class MainLoop extends ControlLoop{
    HumanBeingDAO humanBeings;
    UDPNetwork udp;


    public MainLoop(HashMap<String,Command> commands, UDPNetwork udp, HumanBeingDAO humanBeings){
        super(commands);
        this.humanBeings =humanBeings;
        this.udp = udp;
    }
    public void printIndication(){
        System.out.print(">");
    }
    @Override
    public void run(){

        while (true) {
           try{
            JSONObject commandJson =  udp.getPacket();
            if ((boolean) commandJson.get("initialConnect")){
                udp.makeInitialConnect(Serialization.serializeWholeMap((HashMap) commands));

                HashMap<String, Command> server_commands = new HashMap<>();
                server_commands.put("exit",new Exit());
                server_commands.put("save",new Save());

                ConsoleInputLoop consoleInputLoop = new ConsoleInputLoop(server_commands,humanBeings);
                consoleInputLoop.start();
                continue;
            }

            Command command = Serialization.DeserializeObject((byte[]) commandJson.get("command"));
            System.out.println("Пришла команда - "+ command.getName());
            printIndication();
            JSONObject execResult = command.execute((String) commandJson.get("atribute"),(JSONObject) commandJson.get("additionalData"),humanBeings);
            udp.sendPacket(Serialization.SerializeObject(execResult));
           }
           catch (IOException e){
               System.out.println(e.getMessage());
           } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
           }

        }
   }
}
