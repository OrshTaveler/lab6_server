import commands.*;
import controllers.MainLoop;
import network.UDPNetwork;
import utilities.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, Command> commands = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("url:");
        String url = scanner.nextLine();
        System.out.print("user:");
        String user = scanner.nextLine();
        System.out.print("password:");
        String password = scanner.nextLine();


        commands.put("add", new Add());
        commands.put("show", new Show());
        commands.put("update",new Update());
        commands.put("clear",new Clear());
        commands.put("remove_by_id", new RemoveById());
        commands.put("insert_at", new InsertAt());
        commands.put("remove_at", new RemoveAt());
        commands.put("remove_first", new RemoveFirst());
        commands.put("remove_any_by_weapon_type", new RemoveAnyByWeaponType());
        commands.put("filter_starts_with_name", new FilterStartsWithName());
        commands.put("reg",new Register());
        commands.put("auth",new Authorize());
        commands.put("owner",new Owner());

        try {
            SQL.makeConnection(url,user,password);
            HumanBeingDAO humanBeings = SQL.sqlToCollection();
            UDPNetwork test = new UDPNetwork(4555,"localhost");
            MainLoop mainLoop = new MainLoop(commands,test,humanBeings);
            System.out.println("Ждём подключения");
            mainLoop.start();


        } catch (IOException e){
                System.out.println(e.getMessage());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}