package multithread;

import commands.Command;
import commands.Exit;
import commands.Save;
import controllers.ConsoleInputLoop;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.Serialization;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

public class MainTask implements Runnable{
    HumanBeingDAO humanBeings;
    UDPNetwork udp;
    HashMap<String, Command> commands;

    private int threadNumber;

    public MainTask(HashMap<String,Command> commands, UDPNetwork udp, HumanBeingDAO humanBeings, int threadNumber){
        this.humanBeings =humanBeings;
        this.udp = udp;
        this.commands = commands;
        this.threadNumber = threadNumber;
    }
    public void printIndication(){
        System.out.print(">");
    }
    @Override
    public void run() {
       while (true){
        try{
            JSONObject commandJson =  udp.getPacket();
            if ((boolean) commandJson.get("initialConnect")){
                udp.makeInitialConnect(Serialization.serializeWholeMap(commands));
                continue;
            }

            Command command = Serialization.DeserializeObject((byte[]) commandJson.get("command"));
            System.out.println("Пришла команда - "+ command.getName());
            printIndication();
            JSONObject additionalData = (JSONObject) commandJson.get("additionalData");
            JSONObject execResult = command.exec((String) commandJson.get("atribute"),additionalData,humanBeings);
            Response.sendResponse(udp,Serialization.SerializeObject(execResult));
            System.out.println("Команда - "+ command.getName() + " обработана потоком " + this.threadNumber);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        }

    }
}
