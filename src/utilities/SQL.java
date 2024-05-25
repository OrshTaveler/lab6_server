package utilities;
import initials.Car;
import initials.Coordinates;
import initials.HumanBeing;
import initials.WeaponType;
import org.postgresql.util.PSQLException;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SQL {
    static Connection connection;
    public static void makeConnection(String url,String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url,username,password);
    }

    public static boolean isUserRegistered(String login) throws SQLException {
        var prepared = connection.prepareStatement("SELECT COUNT(*) FROM users " + "WHERE (login = ?)");
        prepared.setString(1,login);
        ResultSet resultSet = prepared.executeQuery();
        resultSet.next();
        return !(resultSet.getInt(1) == 0);
    }




    public static boolean isPasswordRight(String login,String password) throws SQLException, NoSuchAlgorithmException {
        if (!isUserRegistered(login)) return false;
        var prepared = connection.prepareStatement("SELECT password FROM users " + "WHERE (login = ?)");
        prepared.setString(1,login);
        ResultSet resultSet = prepared.executeQuery();
        resultSet.next();
        return SHA.encodeInSHA(password).equals(resultSet.getString(1));

    }

    public static boolean register(String login,String password) throws SQLException {
        try{
         var prepared = connection.prepareStatement("INSERT INTO users(login,password,active) " + "VALUES (?,?,true)");
         prepared.setString(1,login);
         prepared.setString(2,SHA.encodeInSHA(password));
         prepared.execute();
         return true;
        }
        catch (PSQLException | NoSuchAlgorithmException e){
            return false;
        }
    }


    private static int getLastID() throws SQLException {
        var prepared = connection.prepareStatement("SELECT MAX(id) FROM humans");
        ResultSet resultSet = prepared.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static boolean addHuman(HumanBeing humanBeing,String owner) throws SQLException {
       try{
        var prepared = connection.prepareStatement("INSERT INTO humans(name,x,y,realhero,hastoothpick,carname,minutesofwaiting,weapontype,owner) " + "VALUES (?,?,?,?,?,?,?,?,?)");
        prepared.setString(1,humanBeing.getName());
        prepared.setDouble(2,humanBeing.getCoordinates().getX());
        prepared.setLong(3,humanBeing.getCoordinates().getY());
        prepared.setBoolean(4,humanBeing.getRealHero());
        prepared.setBoolean(5,humanBeing.getHasToothpick());
        prepared.setString(6,humanBeing.getCar().getName());
        prepared.setLong(7,humanBeing.getMinutesOfWaiting());
        prepared.setString(8,humanBeing.getWeaponType().toString());
        prepared.setString(9,owner);
        prepared.execute();
        humanBeing.setId(getLastID());
        return true;
    }
        catch (PSQLException e){
        return false;
    }
    }
    public static boolean updateHuman(HumanBeing humanBeing,String owner){
        try{
            var prepared = connection.prepareStatement("INSERT INTO humans(name,x,y,realhero,hastoothpick,carname,minutesofwaiting,weapontype,owner) " + "VALUES (?,?,?,?,?,?,?,?,?)");
            prepared.setString(1,humanBeing.getName());
            prepared.setDouble(2,humanBeing.getCoordinates().getX());
            prepared.setLong(3,humanBeing.getCoordinates().getY());
            prepared.setBoolean(4,humanBeing.getRealHero());
            prepared.setBoolean(5,humanBeing.getHasToothpick());
            prepared.setString(6,humanBeing.getCar().getName());
            prepared.setLong(7,humanBeing.getMinutesOfWaiting());
            prepared.setString(8,humanBeing.getWeaponType().toString());
            prepared.setString(9,owner);
            prepared.execute();
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }

    public static String getOwnerById(int id) throws SQLException {

        var prepared = connection.prepareStatement("SELECT owner FROM humans " + "WHERE (id = ?)");
        prepared.setInt(1,id);
        ResultSet resultSet = prepared.executeQuery();
        if(!resultSet.isBeforeFirst()) return null;
        resultSet.next();
        return resultSet.getString(1);
    }
    public static HumanBeingDAO sqlToCollection() throws SQLException {
        HumanBeingDAO humanBeings = new HumanBeingDAO();
        var prepared = connection.prepareStatement("SELECT * FROM humans");
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            double x = resultSet.getDouble(3);
            long y = resultSet.getLong(4);
            boolean realHero = resultSet.getBoolean(5);
            boolean hasToothpick = resultSet.getBoolean(6);
            long minutesOfWaiting = resultSet.getLong(8);
            WeaponType weaponType = WeaponType.valueOf(resultSet.getString(9));
            String carName = resultSet.getString(7);
            humanBeings.add(new HumanBeing(id,name,new Coordinates(x,y),realHero,hasToothpick,minutesOfWaiting,weaponType,new Car(carName)));
        }
        return humanBeings;
    }

}
