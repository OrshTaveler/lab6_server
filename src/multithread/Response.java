package multithread;

import network.UDPNetwork;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Response {
    static class ResponseThread extends Thread{
        UDPNetwork udp;
        byte[] message;
        public ResponseThread(UDPNetwork udp,byte[] message){
            this.udp = udp;
            this.message = message;
        }
        public void run(){
            udp.sendPacket(message);
        }
    }
    public static void sendResponse(UDPNetwork udp,byte[] message){
        Thread responseThread = new ResponseThread(udp,message);
        responseThread.start();
    }
}
