package commands;

import initials.Car;
import initials.Coordinates;
import initials.HumanBeing;
import initials.WeaponType;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;


/**
 * Команда 'insert_at'. Добавляет элемент в коллекцию по заданому индексу.
 * @author Ubica228
 */
public class InsertAt extends Command{



    public InsertAt(){
        super("insert_at","Добавляет людей в списочек по индексу");

    }
    /**
     * Выполняет команду
     * @return  Успешность выполнения команды
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        JSONObject coordinatesJson = (JSONObject) additionalData.get("coordinates");
        Coordinates coordinates = new Coordinates((double) coordinatesJson.get("x"),(long) coordinatesJson.get("y") );
        JSONObject carJson = (JSONObject) additionalData.get("car");
        Car car = new Car((String) carJson.get("name"));
        HumanBeing humanBeing = new HumanBeing(
                humanBeings.idPointer+1,(String) additionalData.get("name"),coordinates,
                (boolean) additionalData.get("realHero"),(boolean) additionalData.get("hasToothpick"),(float) additionalData.get("impactSpeed"),
                (String) additionalData.get("soundtrackName"),(long) additionalData.get("minutesOfWaiting"),(WeaponType) additionalData.get("weaponType"),
                car
        );
        humanBeings.add(Integer.parseInt(atribute),humanBeing);
        return UDPNetwork.generateResponse(true,null,"Успешно добавили");
    }
}
