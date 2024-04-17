package commands;

import initials.HumanBeing;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;


/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по полю id класса HumanBeing.
 * @author Ubica228
 */
public class RemoveById extends Command{
    public RemoveById(){
        super("remove_by_id","Удаляет людей по ID");
    }
    /**
     * Выполняет команду
     * @return  Успешность выполнения команды
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        HumanBeing humanBeing = humanBeings.getByIndex(0);
        humanBeings.remove(humanBeing);
        return UDPNetwork.generateResponse(true,null,"Удалили удачно");
    }
}
