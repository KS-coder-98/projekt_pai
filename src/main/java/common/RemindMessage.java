package common;

import client.AppClient;
import client.view.UI;
import org.w3c.dom.NodeList;
import server.AppServer;
import server.model.User;
import server.service.communication.Send;

import java.util.List;

public class RemindMessage extends Message {
    public RemindMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status, String newPassword, List<String> bookLists, String query) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status, newPassword, bookLists, query);
    }

    @Override
    public void processing(Send send) {
        RemindMessage remindMessage;

        if (getSender() == Sender.Client) {
            boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()));
            if (userPresent) {
                User foundUser = AppServer.list.stream().filter(user -> user.getLogin().equals(getLogin())).findFirst().get();
                if (getStatus() == Status.CONTROL_QUESTION) {
                    remindMessage = new RemindMessage(foundUser.getLogin(), null, null, null, null, foundUser.getControlQuestion(), null, null, Sender.Server, Status.CONTROL_QUESTION, null, null, null);
                } else if (getStatus() == Status.ANSWER_TO_CONTROL_QUESTION && foundUser.getAnswerControlQuestion().equals(getAnswerControlQuestion())) {
                    remindMessage = new RemindMessage(foundUser.getLogin(), foundUser.getPassword(), null, null, null, null, null, null, Sender.Server, Status.REMIND_PASSWORD_OK, null, null, null);
                } else {
                    remindMessage = new RemindMessage(foundUser.getLogin(), null, null, null, null, null, null, null, Sender.Server, Status.REMIND_PASSWORD_ERROR, null, null, null);
                }
                send.addMessageToQueue(remindMessage);
            }
        } else if (getSender() == Sender.Server) {
            if (getStatus() == Status.CONTROL_QUESTION) {
                String answer = UI.answerToControlQuestion(getControlQuestion());
                remindMessage = new RemindMessage(getLogin(), null, null, null, null, null, answer, null, Sender.Client, Status.ANSWER_TO_CONTROL_QUESTION, null, null, null);
                AppClient.client.getSend().addMessageToQueue(remindMessage);
            } else if (getStatus() == Status.REMIND_PASSWORD_OK) {
                UI.showPassword(getPassword());
            } else {
                UI.showErrorRemindPassword();
            }
        }
    }
}
