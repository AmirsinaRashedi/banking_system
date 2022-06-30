import domain.Account;
import domain.Customer;
import util.ApplicationContext;
import util.Menu;

import java.util.List;
import java.util.Scanner;

public class BankingSystemApplication {

    public static void main(String[] args) {

        Scanner intInput = new Scanner(System.in);

        Scanner stringInput = new Scanner(System.in);

        int choice;

        while (true) {

            try {

                Menu.mainMenu();

                choice = intInput.nextInt();

                switch (choice) {

                    case 1: {

                        ApplicationContext.customerService.createCustomer();

                        break;
                    }

                    case 2: {

                        createAccount();

                        break;
                    }


                    case 3: {

                        seeAccounts();

                        break;
                    }

                    case 4: {

                        createCard();

                        break;
                    }

                    case 5: {

                        ApplicationContext.cardService.changePassword();

                        break;
                    }

                    case 6: {

                        ApplicationContext.transactionService.doATransaction();

                        break;

                    }

                    case 7: {

                        ApplicationContext.transactionRepository.findTransaction();

                        break;
                    }


                    case 8: {

                        List<Customer> customerList = ApplicationContext.customerRepository.customSearch();

                        customerList.forEach(i -> System.out.println(i));

                        break;
                    }

                    case 9: {
                        return;
                    }

                    default:
                        System.out.println("input out of bound");
                        break;

                }

            } catch (Exception e) {

                System.out.println("error :( ");

                System.out.println(e.getMessage());
            }


        }


    }

    private static void createCard() {

        Scanner intInput = new Scanner(System.in);

        Scanner stringInput = new Scanner(System.in);

        System.out.println("enter your Ssn:");

        String ssn = stringInput.nextLine();

        Customer currentCustomer = ApplicationContext.customerRepository.findBySsn(ssn);

        int accountCount = 0;

        if (currentCustomer != null) {

            for (Account account : ApplicationContext.accountRepository.findByOwnerSsn(ssn)) {

                System.out.println(++accountCount + "- " + account);

            }

            System.out.print("select the account you want to create a card for: ");

            int accountSelected = intInput.nextInt();

            if (accountSelected > 0 && accountSelected <= accountCount)
                ApplicationContext.cardService.createCard(ApplicationContext.accountRepository.findByOwnerSsn(ssn).get(accountSelected - 1));
            else
                throw new RuntimeException("input out of bounds");
        } else
            System.out.println("this user does not exist");
    }

    private static void seeAccounts() {

        Scanner stringInput = new Scanner(System.in);

        System.out.println("enter your Ssn:");

        String ssn = stringInput.nextLine();

        Customer currentCustomer = ApplicationContext.customerRepository.findBySsn(ssn);

        if (currentCustomer != null) {

            for (Account account : ApplicationContext.accountRepository.findByOwnerSsn(ssn)) {

                System.out.println(account);

            }

        } else
            System.out.println("this user does not exist");
    }

    private static void createAccount() {

        Scanner stringInput = new Scanner(System.in);

        System.out.println("enter your Ssn:");

        String ssn = stringInput.nextLine();

        Customer currentCustomer = ApplicationContext.customerRepository.findBySsn(ssn);

        if (currentCustomer != null) {

            ApplicationContext.accountService.setUpAccount(currentCustomer);

        } else
            System.out.println("this user does not exist");
    }


}
