package common;

import client.view.UI;
import org.w3c.dom.NodeList;
import server.AppServer;
import server.model.User;
import server.service.communication.Send;
import settings.Setting;

import java.util.List;

public class ChangePasswordMessage extends Message{
    public ChangePasswordMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status, String newPassword, List<String> bookLists, String query) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status, newPassword, bookLists, query);
    }

    @Override
    public void processing(Send sender) {
        if (getSender() == Sender.Client) {
            Boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()) && user.getPassword().equals(getPassword()));
            ChangePasswordMessage changePasswordMessage;
            if ( userPresent ){
                User foundUser = AppServer.list.stream().filter(user -> user.getLogin().equals(getLogin())).findFirst().get();
                if ( foundUser.getPassword().equals(getPassword()) ){
                    foundUser.setPassword(getNewPassword());
                    AppServer.list.removeIf(user -> user.getLogin().equals(foundUser.getLogin()));
                    AppServer.list.add(foundUser);
                    changePasswordMessage = new ChangePasswordMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.OK, null, null, null);
                    try {
                        AppServer.userParser.saveDatabase(AppServer.list, Setting.databaseName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    changePasswordMessage = new ChangePasswordMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR, null, null, null);
                }
            }else{
                changePasswordMessage = new ChangePasswordMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR, null, null, null);
            }
            sender.addMessageToQueue(changePasswordMessage);
        } else if(getSender() == Sender.Server){
            if ( getStatus() == Status.OK ){
                UI.changedPasswordOK();
            }else{
                UI.changedPasswordERROR();
            }
        }
    }
}
