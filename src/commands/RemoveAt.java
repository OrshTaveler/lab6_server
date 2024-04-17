package commands;

import initials.HumanBeing;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;


/**
 * Команда 'remove_at'. Удаляет элемент с введённым.
 * @author Ubica228
 */
public class RemoveAt extends Command{



    public RemoveAt(){
        super("remove_at","Удаляет людей из списочка по индексу");

    }
    /**
     * Выполняет команду
     * @return  Успешность выполнения команды
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        HumanBeing humanBeing = humanBeings.getByIndex(Integer.parseInt(atribute));
        humanBeings.remove(humanBeing);
        return UDPNetwork.generateResponse(true,null,"Удалили удачно");
    }
}
