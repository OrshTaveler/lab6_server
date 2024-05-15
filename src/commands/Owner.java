package commands;

import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.SQL;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Owner extends Command implements Serializable {
    public Owner() {
        super("owner","");
    }

    @Override
    protected JSONObject execute(String atribute, JSONObject additionalData, HumanBeingDAO humanBeings) throws IOException, SQLException, NoSuchAlgorithmException {
        if (SQL.getOwnerById(Integer.parseInt(atribute)).equals(additionalData.get("login"))) {
            return  UDPNetwork.generateResponse(true,new JSONObject(),null);
        }
        return  UDPNetwork.generateResponse(false,new JSONObject(),null);

    }
}
