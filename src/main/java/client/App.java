package client;

import client.service.communication.Send;
import client.service.communication.UserEcho;
import client.service.communication.EchoClient;
import client.view.UI;
import settings.Setting;

public class App {
    public static EchoClient client = new EchoClient();

    public static void main(String[] args) {

        client.startConnection(Setting.ip, Setting.port);
        UI.showMenu();
        UI.choseOption();
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
