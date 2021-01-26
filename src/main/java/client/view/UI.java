package client.view;

import common.*;

public class UI {

    public static boolean isLogIn = false;

    public static void showMenu() {
        System.out.println("Log in -> " + MenuOptions.LOGIN.getValue());
        System.out.println("Sign up -> " + MenuOptions.REGISTER.getValue());
        System.out.println("Remind password -> " + MenuOptions.REMIND_PASSWORD.getValue());
    }

    public static void showMenuLogged() {
        System.out.println("Log out -> " + MenuOptions.LOG_OUT.getValue());
        System.out.println("Change password -> " + MenuOptions.CHANGE_PASSWORD.getValue());
    }

    public static void logged() {
        isLogIn = true;
        showMenuLogged();
        choseOption();
    }

    public static void noLogIn() {
        isLogIn = false;
        showMenu();
        choseOption();
    }

    public static void choseOption() {
        System.out.println("Chose option:");
        int option = Input.readNumber();
        System.out.println(MenuOptions.valueOf(option));
        MenuOptions menuOptions = MenuOptions.valueOf(option);
        if (menuOptions == MenuOptions.LOGIN && !isLogIn) {
            logIn();
        } else if (menuOptions == MenuOptions.REGISTER && !isLogIn) {
            signUp();
        } else if (menuOptions == MenuOptions.REMIND_PASSWORD && !isLogIn) {
            askAboutControlQuestion();
        } else if (menuOptions == MenuOptions.LOG_OUT && isLogIn) {
            noLogIn();
        } else if (menuOptions == MenuOptions.CHANGE_PASSWORD && isLogIn) {
            changePassword();
        }
    }

    public static void changedPasswordOK() {
        System.out.println("Password was changed");
        UI.logged();
    }

    public static void changedPasswordERROR() {
        System.out.println("Something went wrong");
        UI.logged();
    }

    public static void changePassword() {
        System.out.println("Input login");
        String login = Input.readLine();
        System.out.println("Input old password");
        String oldPassword = Input.readLine();
        System.out.println("Input new password");
        String newPassword = Input.readLine();
        Message msg = new ChangePasswordMessage(login, oldPassword, null, null, null, null, null, null, Sender.Client, null, newPassword);
        MenuOptions.CHANGE_PASSWORD.makeAction(msg);
    }

    public static void logIn() {
        System.out.println("Input login");
        String login = Input.readLine();
        System.out.println("Input password");
        String password = Input.readLine();
        Message msg = new LoginMessage(login, password, null, null, null,
                null, null, null, Sender.Client, null, null);
        MenuOptions.LOGIN.makeAction(msg);
    }

    public static void signUp() {
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
        Message msg = new RegistrationMessage(login, password, name, surname, mail, controlQuestion, answerControlQuestion, null, Sender.Client, null, null);
        MenuOptions.REGISTER.makeAction(msg);
    }

    public static String answerToControlQuestion(String question) {
        System.out.println("Answer the question " + question);
        return Input.readLine();
    }

    public static void showPassword(String password) {
        System.out.println("Your password: " + password);
        noLogIn();
    }

    public static void askAboutControlQuestion() {
        System.out.println("Input login");
        String login = Input.readLine();
        Message msg = new RemindMessage(login, null, null, null, null,
                null, null, null, Sender.Client, Status.CONTROL_QUESTION, null);
        MenuOptions.REMIND_PASSWORD.makeAction(msg);
    }

    public static void showErrorRemindPassword() {
        System.out.println("Wrong answer");
        noLogIn();
    }
}
