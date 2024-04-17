package commands;

import initials.HumanBeing;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;


/**
 * Команда 'filter_starts_with_name'. Выводит элементы коллекции у которых поле name начинается с заданой подстроки.
 * @author Ubica228
 */
public class FilterStartsWithName extends Command {

    public FilterStartsWithName(){
        super("filter_starts_with_name","Находит людей у которых имя начинается на");

    }
    /**
     * Выполняет команду
     * @return  Успешность выполнения команды
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        String subString = atribute;
        String response = "";
        for(HumanBeing humanBeing:humanBeings.startsWithName(subString)){
            response += humanBeing.toString() + "\n";
        }
        return UDPNetwork.generateResponse(true,null,response);
    }
}
