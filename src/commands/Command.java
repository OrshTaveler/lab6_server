package commands;

import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;

import java.io.IOException;
import java.io.Serializable;

/**
 * Абстрактный класс для реализации команд.
 * @author Ubica228
 */
public abstract class Command implements Serializable {
    private final String name;
    private final String description;
    public Command(String name, String description){
        this.description =description;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }
    public abstract JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException;


}
