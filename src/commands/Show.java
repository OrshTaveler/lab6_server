package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

public class Show extends Command{
    public Show( ) {
        super("Show", "Выводит всё коллекцию");

    }

    @Override
    public JSONObject execute(String atribute, JSONObject additionalData,HumanBeingDAO humanBeings) {
        return UDPNetwork.generateResponse(true,null,humanBeings.toString());
    }
}
