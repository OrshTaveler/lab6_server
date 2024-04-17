package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

public class Clear extends Command{

    public Clear() {
        super("Show", "Выводит всё коллекцию");

    }

    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        humanBeings.clear();
        return UDPNetwork.generateResponse(true,null,"Успешно очищена!");
    }
}
