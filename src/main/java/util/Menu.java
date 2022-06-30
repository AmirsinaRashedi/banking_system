package util;

public class Menu {

    private Menu() {
    }


    public static void mainMenu() {

        System.out.println("1- create customer");
        System.out.println("2- create account");
        System.out.println("3- see accounts");
        System.out.println("4- create card for account");
        System.out.println("5- change card password");
        System.out.println("6- transfer money");
        System.out.println("7- view transactions");
        System.out.println("8- customer custom search");
        System.out.println("9- exit");
    }


    public static void transactionMenu() {
        System.out.println("1- find by account");
        System.out.println("2- find by date");
        System.out.println("3- find using both");
        System.out.println();
    }


}
