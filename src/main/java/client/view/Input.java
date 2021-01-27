package client.view;

import java.util.Scanner;

public class Input {

    private static final Scanner scan = new Scanner(System.in);

    public static String readLine() {
        return scan.nextLine();
    }

    public static int readNumber() {
        int val = scan.nextInt();
        scan.nextLine();
        return val;
    }
}
