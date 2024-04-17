package commands;

import initials.Car;
import initials.Coordinates;
import initials.HumanBeing;
import initials.WeaponType;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

public class Update extends Command {
    public Update(){
        super("update","Обновляет списочек");
    }

    /**
     * Выполняет команду
     * @return  Json
     * */
    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) {
        HumanBeing initialHumanBeing = humanBeings.getById(Integer.parseInt(atribute));
        int idpointer = initialHumanBeing.getId();
        int indx = humanBeings.getIndexById(idpointer);

        JSONObject coordinatesJson = (JSONObject) additionalData.get("coordinates");
        Coordinates coordinates = new Coordinates((double) coordinatesJson.get("x"),(long) coordinatesJson.get("y") );

        JSONObject carJson = (JSONObject) additionalData.get("car");
        Car car = new Car((String) carJson.get("name"));
        humanBeings.remove(initialHumanBeing);

        HumanBeing humanBeing = new HumanBeing(
                idpointer,(String) additionalData.get("name"),coordinates,
                (boolean) additionalData.get("realHero"),(boolean) additionalData.get("hasToothpick"),(float) additionalData.get("impactSpeed"),
                (String) additionalData.get("soundtrackName"),(long) additionalData.get("minutesOfWaiting"),(WeaponType) additionalData.get("weaponType"),
                car
        );
        humanBeings.add(indx,humanBeing);
        return UDPNetwork.generateResponse(true,new JSONObject(),"Добавили успешно!");
    }
}
