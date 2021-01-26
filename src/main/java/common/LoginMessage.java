package common;

import client.view.UI;
import server.AppServer;
import server.service.communication.Send;

public class LoginMessage extends Message {
    public LoginMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status, String newPassword) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status, newPassword);
    }

    @Override
    public void processing(Send sender) {
        if (getSender() == Sender.Client) {
            Boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()) && user.getPassword().equals(getPassword()));
            LoginMessage loginMessageResponse;
            if (userPresent) {
                loginMessageResponse = new LoginMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.OK, null);
            } else {
                loginMessageResponse = new LoginMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR, null);
            }
            sender.addMessageToQueue(loginMessageResponse);
        } else if(getSender() == Sender.Server){
            if ( getStatus() == Status.OK ){
                UI.logged();
            }else{
                UI.noLogIn();
            }
        }
    }
}
