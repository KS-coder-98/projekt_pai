package client;

import client.service.communication.EchoClient;
import client.view.UI;
import settings.Setting;

public class AppClient {
    public static EchoClient client = new EchoClient();

    public static void main(String[] args) {

        client.startConnection(Setting.ip, Setting.port);
        UI.showMenu();
        UI.choseOption();
        int i = 0;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
