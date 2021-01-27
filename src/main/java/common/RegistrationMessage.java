package common;


import client.view.UI;
import org.w3c.dom.NodeList;
import server.AppServer;
import server.model.User;
import server.service.communication.Send;
import settings.Setting;

import java.util.List;
import java.util.UUID;


public class RegistrationMessage extends Message {

    public RegistrationMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status, String newPassword, List<String> bookLists, String query) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status, newPassword, bookLists, query);
    }

    @Override
    public void processing(Send send) {
        if ( getSender() == Sender.Client ){
            Boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()));
            RegistrationMessage registrationMessage;
            if ( userPresent ){
                registrationMessage = new RegistrationMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR_USER_ALREADY_EXIST, null, null, null);
                send.addMessageToQueue(registrationMessage);
            }else{
                registrationMessage = new RegistrationMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.OK, null, null, null);
                UUID id =  UUID.randomUUID();
                AppServer.list.add(new User(id, getLogin(), getName(), getSurname(), getMail(), getPassword(), getControlQuestion(), getAnswerControlQuestion()));
                send.addMessageToQueue(registrationMessage);
                try {
                    AppServer.userParser.saveDatabase(AppServer.list, Setting.databaseName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(getSender() == Sender.Server){
            if ( getStatus() == Status.OK ){
                System.out.println("Added user");
            }else if(getStatus() == Status.ERROR_USER_ALREADY_EXIST){
                System.out.println("User with this login already existed");
            }
            UI.noLogIn();
            System.out.println(getStatus());
        }
    }
}
