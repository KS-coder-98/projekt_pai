package server;

import server.model.User;
import server.model.parser.UserParsers;
import settings.Setting;
import server.service.communication.EchoMultiServer;

import java.io.InputStream;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
//        new EchoMultiServer(Setting.port);
//        Thread communicationThread = new Thread(EchoMultiServer::run);
//        communicationThread.setDaemon(true);
//        communicationThread.start();
//        System.out.printf("stop!!!");
//        int i = 0;
//        while (true){
//            System.out.printf(i+"");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        UserParsers read = new UserParsers();
        List<User> readDatabase = read.readDatabase("src/main/resources/database.xml");
        System.out.println(readDatabase.size());
//        for (User user : readDatabase) {
//            read.saveDatabase();
//        }
        read.saveDatabase(readDatabase, "src/main/resources/database1.xml");


    }
}
