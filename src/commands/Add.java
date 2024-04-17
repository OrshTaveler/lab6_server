package commands;
import initials.Car;
import initials.Coordinates;
import initials.HumanBeing;
import initials.WeaponType;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;


import java.io.IOException;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 * @author Ubica228
 */

public class Add extends Command{



    public Add(){
        super("add","Добавляет людей в списочек");
    }

    /**
     * Выполняет команду
     * @return  Json
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData,HumanBeingDAO humanBeings) {
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
        humanBeings.add(humanBeing);
        System.out.println(humanBeing.toString());
        return UDPNetwork.generateResponse(true,new JSONObject(),"Добавили успешно!");
    }
}
