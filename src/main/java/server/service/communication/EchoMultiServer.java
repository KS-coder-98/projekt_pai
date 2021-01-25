package server.service.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class EchoMultiServer{
    private static ServerSocket socket;
    private static int port;
    private static List<EchoClientHandler> listUser;


    public EchoMultiServer(int port){
        EchoMultiServer.port = port;
        listUser = new ArrayList<>();

    }

    public static void run() {
        try {
            socket = new ServerSocket(port);
            while (true){
                EchoClientHandler echoClientHandle = new EchoClientHandler(socket.accept());
                echoClientHandle.start();
                listUser.add(echoClientHandle);
                System.out.println(listUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    /**
     * Stop function is in charge of closing socket
     */
    public static void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
