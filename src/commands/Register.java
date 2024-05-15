package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.SQL;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register extends  Command implements Serializable {
    public Register() {
        super("reg","Регистрирует юзера");
    }

    @Override
    public JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException, SQLException, NoSuchAlgorithmException {
        boolean res = SQL.register((String) additionalData.get("login"), (String) additionalData.get("password"));
        String token = UDPNetwork.generateLiveToken((String) additionalData.get("login"),(String) additionalData.get("password"),(Long) additionalData.get("timestamp"));
        JSONObject authData = new JSONObject();
        authData.put("token",token);
        if(res) return UDPNetwork.generateResponse(true,authData,"Зарегистрировали!");
        else return UDPNetwork.generateResponse(false,authData,"Ошибка регистрации!");
    }
}
