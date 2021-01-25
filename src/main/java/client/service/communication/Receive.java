package client.service.communication;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Receive extends Thread{
    ObjectInputStream in;

    public Receive(ObjectInputStream in) {
        this.in = in;
    }

    /**
     * This function is responsible for receiving message and running precess this message
     */
    public void run() {
        try {
            Message inputLine;
            while ((inputLine = (Message) in.readObject()) != null) {
                inputLine.processing();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
