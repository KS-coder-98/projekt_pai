package common;

import server.AppServer;
import server.service.communication.Send;

public class LoginMessage extends Message {
    public LoginMessage(String login, String password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
    }

    @Override
    public void processing(Send sender) {
        if (getSender() == Sender.Client) {
            Boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()) && user.getPassword().equals(getPassword()));
            LoginMessage loginMessageResponse;
            if (userPresent) {
                loginMessageResponse = new LoginMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.OK);
            } else {
                loginMessageResponse = new LoginMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR);
            }
            sender.addMessageToQueue(loginMessageResponse);
        } else {
            System.out.println("czy zalogowano" + getStatus());
        }
    }
}
