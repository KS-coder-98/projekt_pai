package client.view;

import common.*;

import java.util.Scanner;

public class UI {



    public static void showMenu() {
        System.out.println("Log in -> " + MenuOptions.LOGIN.getValue());
        System.out.println("Sign up -> " + MenuOptions.REGISTER.getValue());
//        System.out.println("Change password -> " + MenuOptions.CHANGE_PASSWORD.getValue());
        System.out.println("Remind password -> " + MenuOptions.REMIND_PASSWORD.getValue());
    }

    public static void showMenuLogged() {
        System.out.println("Log in -> " + MenuOptions.LOGIN.getValue());
        System.out.println("Sign up -> " + MenuOptions.REGISTER.getValue());
        System.out.println("Change password -> " + MenuOptions.CHANGE_PASSWORD.getValue());
        System.out.println("Remind password -> " + MenuOptions.REMIND_PASSWORD.getValue());
    }

    public static void choseOptionNotLogIn() {
        System.out.println("Chose option:");
        int option = Input.readNumber();
        System.out.println(MenuOptions.valueOf(option));
        MenuOptions menuOptions = MenuOptions.valueOf(option);
        if ( menuOptions == MenuOptions.LOGIN ){
            logIn();
        }else if(menuOptions == MenuOptions.REGISTER){
            signUp();
        }else if(menuOptions == MenuOptions.REMIND_PASSWORD){
            askAboutControlQuestion();
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

    public static void signUp(){
        System.out.println("Input login");
        String login = Input.readLine();
        System.out.println("Input name");
        String name = Input.readLine();
        System.out.println("Input surname");
        String surname = Input.readLine();
        System.out.println("Input mail");
        String mail = Input.readLine();
        System.out.println("Input password");
        String password = Input.readLine();
        System.out.println("Input control question");
        String controlQuestion = Input.readLine();
        System.out.println("Input control answer to control question");
        String answerControlQuestion = Input.readLine();
        Message msg = new RegistrationMessage(login, password, name, surname, mail, controlQuestion, answerControlQuestion, null, Sender.Client, null);
        MenuOptions.REGISTER.makeAction(msg);
    }

    public static String answerToControlQuestion(String question){
        System.out.println(question);
        return Input.readLine();
    }

    public static void showPassword(String password){
        System.out.println(password);
    }

    public static void askAboutControlQuestion(){
        System.out.println("Input login");
        String login = Input.readLine();
        Message msg = new RemindMessage(login, null, null, null, null,
                null, null, null, Sender.Client, Status.CONTROL_QUESTION);
        MenuOptions.REMIND_PASSWORD.makeAction(msg);
    }

    public static void showErrorRemindPassword(){
        System.out.println("Wrong answer");
    }
}
