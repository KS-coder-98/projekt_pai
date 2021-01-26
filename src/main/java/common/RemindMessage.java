package common;

import client.AppClient;
import client.view.UI;
import server.AppServer;
import server.model.User;
import server.service.communication.Send;

public class RemindMessage extends Message {
    public RemindMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
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
                    remindMessage = new RemindMessage(foundUser.getLogin(), null, null, null, null, foundUser.getControlQuestion(), null, null, Sender.Server, Status.CONTROL_QUESTION);
                }
                else if (getStatus() == Status.ANSWER_TO_CONTROL_QUESTION && foundUser.getAnswerControlQuestion().equals(getAnswerControlQuestion())) {
                    remindMessage = new RemindMessage(foundUser.getLogin(), foundUser.getPassword(), null, null, null, null, null, null, Sender.Server, Status.REMIND_PASSWORD_OK);
                } else {
                    remindMessage = new RemindMessage(foundUser.getLogin(), null, null, null, null, null, null, null, Sender.Server, Status.REMIND_PASSWORD_ERROR);
                }
                send.addMessageToQueue(remindMessage);
            }
        } else if (getSender() == Sender.Server) {
            if (getStatus() == Status.CONTROL_QUESTION) {
                String answer = UI.answerToControlQuestion(getControlQuestion());
                remindMessage = new RemindMessage(getLogin(), null, null, null, null, null, answer, null, Sender.Client, Status.ANSWER_TO_CONTROL_QUESTION);
                AppClient.client.getSend().addMessageToQueue(remindMessage);
            }else if (getStatus() == Status.REMIND_PASSWORD_OK){
                UI.showPassword(getPassword());
            }else {
                UI.showErrorRemindPassword();
            }
        }
    }
}
