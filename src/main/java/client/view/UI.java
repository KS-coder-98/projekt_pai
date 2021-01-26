package client.view;

import common.LoginMessage;
import common.Message;
import common.Sender;

import java.util.Scanner;

public class UI {



    public static void showMenu() {
        System.out.println("Log in -> " + MenuOptions.LOGIN.getValue());
        System.out.println("Sign up -> " + MenuOptions.REGISTER.getValue());
        System.out.println("Change password -> " + MenuOptions.CHANGE_PASSWORD.getValue());
        System.out.println("Remind password -> " + MenuOptions.REMIND_PASSWORD.getValue());
    }

    public static void choseOption() {
        System.out.println("Chose option:");
        int option = Input.readNumber();
        System.out.println(MenuOptions.valueOf(option));
        MenuOptions menuOptions = MenuOptions.valueOf(option);
        if ( menuOptions == MenuOptions.LOGIN ){
            logIn();
        }else if(menuOptions == MenuOptions.REGISTER){
            //todo
        }
    }

    public static void logIn() {
        System.out.println("Input login");
        String login = Input.readLine();
        System.out.println("Input password");
        String password = Input.readLine();
        Message msg = new LoginMessage(login, password, null, null, null,
                null, null, null, Sender.Client, null);
        MenuOptions.LOGIN.makeAction(msg);
    }
}
