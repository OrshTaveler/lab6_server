package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

public class Exit extends Command{
    public Exit(){
        super("exit","exit");

    }

    /**
     * Выполняет команду
     * @return  Json
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData,HumanBeingDAO humanBeings) {
        System.exit(0);
        return UDPNetwork.generateResponse(true,new JSONObject(),"Всё, отключились");
    }
}
