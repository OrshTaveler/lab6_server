package commands;

import initials.HumanBeing;
import initials.WeaponType;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
/**
 * Команда 'remove_first'. Удаляет первый элемент из коллекции.
 * @author Ubica228
 */
public class RemoveFirst extends Command{



    public RemoveFirst(){
        super("remove_first","Удаляет первого человека из списочка ");

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
