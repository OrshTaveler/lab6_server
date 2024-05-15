package network;

import commands.Command;
import org.json.simple.JSONObject;
import utilities.SHA;
import utilities.Serialization;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class UDPNetwork  {
    private  int port;
    private String adress;
    private SocketAddress inetSocketAddress;
    private DatagramChannel channel;
    int size = 60000;

    public  UDPNetwork(int port,String adress) throws IOException {
       this.port = port;
       this.adress = adress;
       channel = DatagramChannel.open();
       this.inetSocketAddress = new InetSocketAddress(InetAddress.getByName(adress),port);
       channel.bind(inetSocketAddress);

    }

    public void makeInitialConnect (HashMap<String, byte[]> commands) throws IOException, ClassNotFoundException {
        JSONObject initialResponse = this.generateResponse(true,new JSONObject(commands));
        sendPacket(Serialization.SerializeObject(initialResponse));
        System.out.println("Клиент подключился");

    }



    public JSONObject getPacket(){
        try{
            byte[] bytes = new byte[size];
            this.inetSocketAddress =  channel.receive(ByteBuffer.wrap(bytes));
            return Serialization.DeserializeObject(bytes);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendPacket(byte[] message){
        try{
            channel.send(ByteBuffer.wrap(message),this.inetSocketAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject generateResponse(boolean status,JSONObject data,String responseText){
        JSONObject response = new JSONObject();
        response.put("status",status);
        response.put("data",data);
        response.put("responseText",responseText);
        return response;
    }
    public static JSONObject generateResponse(boolean status,JSONObject data){
        JSONObject response = new JSONObject();
        response.put("status",status);
        response.put("data",data);
        response.put("responseText","");
        return response;
    }
    public static String generateLiveToken(String login, String password) throws NoSuchAlgorithmException {
        return SHA.encodeInSHA(login+"_"+password+"_"+ Calendar.getInstance().getTimeInMillis());
    }
    public static String generateLiveToken(String login, String password,long timestamp) throws NoSuchAlgorithmException {
        return SHA.encodeInSHA(login+"_"+password+"_"+ timestamp);
    }
    public void close() throws IOException {
        this.channel.close();
    }




}
