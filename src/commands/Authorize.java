package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.SQL;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Authorize extends Command implements Serializable {
    public Authorize() {
        super("auth","Авторизирует пользователя");
    }

    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException, SQLException, NoSuchAlgorithmException {
         boolean isDataCorrect = SQL.isPasswordRight((String) additionalData.get("login"),(String) additionalData.get("password"));
         String token = UDPNetwork.generateLiveToken((String) additionalData.get("login"),(String) additionalData.get("password"),(Long) additionalData.get("timestamp"));
         JSONObject authData = new JSONObject();
         authData.put("token",token);
         if (isDataCorrect) return UDPNetwork.generateResponse(true,authData,"Авторизация успешна!");
         else  return  UDPNetwork.generateResponse(false,new JSONObject(),"Такого юзера нет!");
    }
}
