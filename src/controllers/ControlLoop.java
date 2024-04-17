package controllers;

import commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Класс предназначенный для управления программой в зависимости от полученого ввода
 * @author Ubica228
 */
public abstract class ControlLoop  extends  Thread{
    protected Map<String, Command> commands;



    public ControlLoop(HashMap<String, Command> commands){
        this.commands = commands;
    }
    public abstract void run();
}
