package client;

import client.service.communication.UserEcho;
import client.service.communication.EchoClient;
import settings.Setting;

public class App {
    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        client.startConnection(Setting.ip, Setting.port);
        UserEcho.setEchoClient(client);
        int i = 0;
        while (true){
            System.out.println("start" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
