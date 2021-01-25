package server;

import common.Setting;
import server.service.communication.EchoMultiServer;

public class App {
    public static void main(String[] args) {
        new EchoMultiServer(Setting.port);
        Thread communicationThread = new Thread(EchoMultiServer::run);
        communicationThread.setDaemon(true);
        communicationThread.start();
        System.out.printf("stop!!!");
        int i = 0;
        while (true){
            System.out.printf(i+"");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
