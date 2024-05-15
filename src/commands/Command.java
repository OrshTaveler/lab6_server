package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.SQL;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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


    protected abstract JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException, SQLException, NoSuchAlgorithmException;

    public JSONObject exec(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException, SQLException, NoSuchAlgorithmException{
         String token = (String) additionalData.get("token");
         if (token.equals(UDPNetwork.generateLiveToken((String) additionalData.get("login"),(String) additionalData.get("password"),(Long) additionalData.get("timestamp")))| this.getName().equals("auth") | this.getName().equals("reg") ) { return execute(atribute,additionalData,humanBeings);}
         return new JSONObject();
    }



}
