package commands;

import initials.HumanBeing;
import initials.WeaponType;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

import java.util.NoSuchElementException;

/**
 * Команда 'remove_any_by_weapon_type'. Удаляет первый элемент коллекции у которого поле weaponType совпадает с заданым.
 * @author Ubica228
 */
public class RemoveAnyByWeaponType extends Command{



    public RemoveAnyByWeaponType(){
        super("remove_any_by_weapon_type","Удаляет людей по типу оружия");

    }
    /**
     * Выполняет команду
     * @return  Успешность выполнения команды
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        HumanBeing humanBeing = humanBeings.getByWeaponType(WeaponType.valueOf(atribute));
        humanBeings.remove(humanBeing);
        return UDPNetwork.generateResponse(true,null,"Удалили удачно");


    }
}
