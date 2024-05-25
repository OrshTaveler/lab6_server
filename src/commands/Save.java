package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.FileManager;
import utilities.HumanBeingDAO;
import utilities.XML;

import java.io.IOException;

public class Save extends Command{
    public Save(){
        super("save","save");

    }

    /**
     * Выполняет команду
     * @return  Json
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException {
        FileManager.save(XML.toXML(humanBeings));
        System.out.println("Сохранили успешно");
        return UDPNetwork.generateResponse(true,new JSONObject(),"Сохранили успешно");
    }
}
