import commands.*;
import controllers.MainLoop;
import network.UDPNetwork;
import utilities.FileManager;
import utilities.HumanBeingDAO;
import utilities.Serialization;
import utilities.XML;

import java.io.IOException;
import java.nio.channels.Selector;

import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, Command> commands = new HashMap<>();
        HumanBeingDAO humanBeings = XML.XMLToHuman(FileManager.readCollection().toArray(new String[0]));


        commands.put("add", new Add());
        commands.put("show", new Show());
        commands.put("update",new Update());
        commands.put("clear",new Clear());
        commands.put("remove_by_id", new RemoveById());
        commands.put("insert_at", new InsertAt());
        commands.put("remove_at", new RemoveAt());
        commands.put("remove_first", new RemoveFirst());
        commands.put("remove_any_by_weapon_type", new RemoveAnyByWeaponType());
        commands.put("filter_starts_with_name", new FilterStartsWithName());



        try {
            UDPNetwork test = new UDPNetwork(4555,"localhost");
            MainLoop mainLoop = new MainLoop(commands,test,humanBeings);
            System.out.println("Ждём подключения");
            mainLoop.start();

        } catch (IOException e){
                System.out.println(e.getMessage());
        }


    }
}