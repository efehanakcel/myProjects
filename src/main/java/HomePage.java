import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HomePage {


    public static void start() {
        try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Welcome to X Bank \n" +
                        "**************");

            do {
                System.out.println("Select 1 to Create an Account");
                System.out.println("Select 2 to Log-In");
                System.out.println("Select 3 to Exit");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please enter a correct value");
                    scanner.nextLine(); // Consume the invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        AccountMethods.createAccount();
                        break;

                    case 2:
                       Account loggedInAccount =  AccountMethods.login(AccountMethods.accountList);
                       if (loggedInAccount == null){
                           break;
                       }else {

                           AccountMethods.accountMenu(loggedInAccount);
                       }


                        continue;
                    case 3:
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                        continue;
                }
                boolean devam = false;
                do {
                    System.out.println("****************************************************** \n" +
                            "Would you like to continue the process? Enter 1 for YES, 2 for Exit");

                    int devamMi;
                    try{
                        devamMi = scanner.nextInt();
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid choice. Please enter a correct value");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }
                    if (devamMi == 1) {
                        devam = true;
                        break;
                    }
                    if (devamMi == 2) {
                        System.out.println("Exiting the program. Thank you.");
                        break;
                    }else{
                        System.out.println("Please enter a valid value");
                    }
                }while (true);
                if (!devam){
                    break;
                }


            } while (true);
        }

    }

}
















