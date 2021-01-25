package server.service.communication;

import common.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Send extends Thread{
    private ObjectOutputStream out;
    List<Message> msgList;


    public void send(Message msg){
        try {
            out.writeObject(msg);
            out.flush();
            //todo check this
//            if ( msg instanceof FileMessage){
//                LambdaExpression.actionIf(EchoMultiServer.getListUser(),
//                        handler -> handler.getSendFile().sendFile(Path.of( msg.getPath()), msg.getId(), msg.getLogin()),
//                        handler->handler.getLogin().equals(msg.getLogin())
//                );
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main thread in function send. This function reads message from list and sends to client.
     * After sending message the message is being deleted from the list
     */
    public void run() {
        while (true) {
            if ( !msgList.isEmpty() ){
                send(msgList.get(0));
                msgList.remove(0);
            }else
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
