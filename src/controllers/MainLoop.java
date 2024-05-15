package controllers;

import commands.Command;
import commands.Exit;
import commands.Save;
import multithread.MainTask;
import network.UDPNetwork;
import org.json.simple.JSONObject;
import utilities.HumanBeingDAO;
import utilities.Serialization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс наследник ControlLoop. Получает команды из стандартного потока ввода
 * @author Ubica228
 */
public class MainLoop extends ControlLoop{
    HumanBeingDAO humanBeings;
    UDPNetwork udp;

    ExecutorService executorService;





    public MainLoop(HashMap<String,Command> commands, UDPNetwork udp, HumanBeingDAO humanBeings){
        super(commands);
        this.humanBeings =humanBeings;
        this.udp = udp;

        this.executorService = Executors.newCachedThreadPool();


    }
    public void printIndication(){
        System.out.print(">");
    }
    @Override
    public void run(){
        for (int i = 0; i < 3; i++){
         executorService.submit(new MainTask((HashMap<String, Command>) commands,udp,humanBeings,i+1));
        }
    }
}
