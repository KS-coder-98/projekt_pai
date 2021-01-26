package server;

import server.model.User;
import server.model.parser.UserParsers;
import settings.Setting;
import server.service.communication.EchoMultiServer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        UserParsers userParser = new UserParsers();
        List<User> readDatabase = userParser.readDatabase("src/main/resources/database.xml");
        List<User> list = Collections.synchronizedList(readDatabase);
        userParser.saveDatabase(readDatabase, "src/main/resources/database1.xml");

        new EchoMultiServer(Setting.port);
        Thread communicationThread = new Thread(EchoMultiServer::run);
        communicationThread.setDaemon(true);
        communicationThread.start();
        int i = 0;
        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
