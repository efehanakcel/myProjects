import java.util.*;

public class AccountMethods {
    static List<Account> accountList = new ArrayList<>();


    public static void createAccount() {
        Scanner scanner = new Scanner(System.in);



            String username;
            while (true) {
                System.out.println("Please enter your username");
                username = scanner.nextLine();
                boolean spaceControl = username.replaceAll("[^ ]", "").isEmpty();
                if (!spaceControl) {
                    System.out.println("Username may not contain blank");
                    continue;
                }
                break;
            }

            String name;
            while (true) {


                System.out.println("Please enter your name");
                name = scanner.nextLine();
                boolean spaceControl2 = name.replaceAll("[^ ]", "").isEmpty();
                if (!spaceControl2) {
                    System.out.println("Name may not contain blank");
                    continue;
                }
                break;
            }


            String surname;
            while (true) {

                System.out.println("Please enter your surname");
                surname = scanner.nextLine();
                boolean spaceControl3 = name.replaceAll("[^ ]", "").isEmpty();
                if (!spaceControl3) {
                    System.out.println("Surname may not contain blank");
                    continue;
                }
                break;
            }

            String password;
            while (true) {
                System.out.println("Please enter your password");
                password = scanner.nextLine();
                boolean spaceControl4 = password.replaceAll("[^ ]", "").isEmpty();
                boolean upperCaseControl = password.replaceAll("[^A-Z]", "").length() > 0;
                boolean lowerCaseControl = password.replaceAll("[^a-z]", "").length() > 0;
                boolean digitControl = password.replaceAll("[^0-9]", "").length() > 0;
                boolean symbolControl = password.replaceAll("[^\\p{Punct}]", "").length() > 0;


                if (!spaceControl4 || !upperCaseControl || !lowerCaseControl || !digitControl || !symbolControl) {
                    System.out.println("Password must contain at least one upper case, one lower case, digit and a special character");
                    continue;
                }
                break;
            }

            String email;
            while (true) {

                System.out.println("Please enter your email");
                email = scanner.nextLine();
                boolean emailControl = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                if (!emailControl) {
                    System.out.println("Please enter a valid email address.");
                    continue;
                }
                break;
            }


            String phoneNumber;
            while (true) {
                System.out.println("Please enter your phone number");
                phoneNumber = scanner.nextLine();
                boolean phoneControl = phoneNumber.length() == 9;
                if (!phoneControl) {
                    System.out.println("Please enter a 9-digit phone number");
                    continue;
                }
                break;
            }

            String birthDate;
            while (true) {
                System.out.println("Please enter your birth date (DD.MM.YYYY)");
                birthDate = scanner.nextLine();
                boolean birthDateControl = birthDate.matches("^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.\\d{4}$");
                if (!birthDateControl) {
                    System.out.println("Please enter your birth date in DD.MM.YYYY format");
                    continue;
                }
                break;
            }

            String accountNumber = AccountMethods.randomNumberGenerator();

            Account account = new Account(username, name, surname, password, email, phoneNumber, birthDate, accountNumber);

            accountList.add(account);
            System.out.println("You have successfully created your account");




    }

    static String randomNumberGenerator() {


        String random16DigitNumber = "";
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            random16DigitNumber = random16DigitNumber + digit;
        }

        // Print the generated number
        return "PL" + random16DigitNumber;


    }


    static Account login(List<Account> accountList) {
        Scanner scanner = new Scanner(System.in);

        int maxAttempts = 0;

        for (int attempt = 0; attempt <= maxAttempts; attempt++) {
            System.out.println("Please enter your username");
            String enteredUsername = scanner.nextLine();
            System.out.println("Please enter your password");
            String enteredPassword = scanner.nextLine();
            for (Account account : accountList) {

                if (enteredUsername.equals(account.getUsername()) && enteredPassword.equals(account.getPassword())) {
                    return account;
                }
            }
            {
                maxAttempts++;
                if(maxAttempts == 3){
                    System.out.println("Wrong username or password");
                    System.out.println("Remaining try : " + (2 - attempt));
                    break;
                }
                    System.out.println("Wrong username or password");
                    System.out.println("Please try again");
                    System.out.println("Remaining try : " + (2 - attempt));
                }

        }
        return null;
    }

    static void accountMenu(Account loggedInAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have successfully logged in");
        System.out.println("Name : " + loggedInAccount.getName() + "\n" +
                "Surname : " + loggedInAccount.getSurname() + "\n" +
                "Balance : " + loggedInAccount.getBalance() + "\n" +
                "USD Balance : " + loggedInAccount.getUsdBalance() + "\n" +
                "EUR Balance : " + loggedInAccount.getEurBalance() + "\n" +
                "GBP Balance : " + loggedInAccount.getGbpBalance() + "\n" +
                "Account Number : " + loggedInAccount.getAccountNumber());
        while (true) {
            System.out.println("Select 1 to make a transfer \n" +
                    "Select 2 to deposit \n" +
                    "Select 3 to see the Balance \n" +
                    "Select 4 to change password \n" +
                    "Select 5 to exchange value");
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

                    System.out.println("Please enter the amount you want to transfer");
                    double transferringAmount;
                    try {
                        transferringAmount = scanner.nextDouble();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for transferring amount. Please enter a valid integer.");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }
                    System.out.println("Please enter the account number you want to transfer");
                    String recipientAccountNumber = scanner.nextLine();
                    try {
                        if (!recipientAccountNumber.matches("PL\\d{16}")) {
                            throw new IllegalArgumentException("Invalid account number format");
                        }
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }



                    boolean recipientFound = false;
                    for (Account recipient : accountList) {
                        if(recipientAccountNumber.equals(loggedInAccount.getAccountNumber())){
                            System.out.println("Recipient account number can not same as the sender account number");
                            continue;
                        }
                        if (recipientAccountNumber.equals(recipient.getAccountNumber())) {
                            recipientFound = true;
                            if (loggedInAccount.getBalance() >= transferringAmount) {
                                recipient.setBalance(recipient.getBalance() + transferringAmount);
                                loggedInAccount.setBalance(loggedInAccount.getBalance() - transferringAmount);
                                System.out.println("Transfer has been successfully made");
                                break;
                            } else {
                                System.out.println("You do not have enough balance to transfer");
                            }
                            break;
                        }
                    }

                    if (!recipientFound) {
                        System.out.println("Recipient account not found.");
                        continue;
                    }

                    break;
                case 2:

                    System.out.println("Please enter the amount you want to deposit");
                    double depositAmount = scanner.nextDouble();
                    loggedInAccount.setBalance(loggedInAccount.getBalance() + depositAmount);
                    System.out.println("You have successfully deposit the amount \n" +
                            "New Balance : " + loggedInAccount.getBalance());

                    break;


                case 3:

                    System.out.println("Balance : " + loggedInAccount.getBalance());
                    break;
                case 4:
                    System.out.println("Please enter your current password to change your password");
                    String enteredCurrentPassword = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Please enter your phone number to change your password");
                    String enteredPhoneNumber = scanner.nextLine();
                    if (enteredCurrentPassword.equals(loggedInAccount.getPassword()) &&
                            enteredPhoneNumber.equals(loggedInAccount.getPhoneNumber())) {
                        System.out.println("Please enter the new password");
                        String newPassword = scanner.nextLine();
                        loggedInAccount.setPassword(newPassword);
                        break;
                    }else {
                        System.out.println("Wrong input please try again");
                        continue;
                    }

                case 5:
                    AccountMethods.exChange(loggedInAccount);
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
            boolean devam = false;
            do {
                System.out.println("****************************************************** \n" +
                        "Would you like to continue the process? Enter 1 for YES, 2 for Main Page");

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
                    System.out.println("You are directed to the main menu");
                    break;
                }else{
                    System.out.println("Please enter a valid value");
                }
            }while (true);
            if (devam){
                continue;
            }
            break;

        }
    }

    static void changePassword(Account loggedInAccount) {
        Scanner scanner = new Scanner(System.in);
        while (true) {



            boolean devam = false;
            do {
                System.out.println("****************************************************** \n" +
                        "Would you like to try again? Enter 1 for YES, 2 for Account Menu");

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
                    System.out.println("You are directed to account menu. Thank you.");
                    break;
                }else{
                    System.out.println("Please enter a valid value");
                }
            }while (true);
            if (!devam){

                break;
            }

        }
    }

    static void exChange(Account loggedInAccount) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose the currency you want to exhange :");

            System.out.println("1 : USD");
            System.out.println("2 : EUR");
            System.out.println("3 : GBP");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("USD/PLN \n" +
                            "Current Rates : \n" +
                            "Buy : 4.10 --- Sell : 4.02");
                    System.out.println("Select 1 to Buy  - Select 2 to Sell");
                    int choice1 = scanner.nextInt();
                    if (choice1 == 1) {
                        System.out.println("Current balance : PLN" + loggedInAccount.getBalance());
                        System.out.println("Please enter the amount you want to buy");
                        double buyAmount1 = scanner.nextDouble();
                        if (buyAmount1 * 4.10 > loggedInAccount.getBalance()){
                            System.out.println("Insufficient balance");
                            continue;
                        }else {
                           loggedInAccount.setBalance (loggedInAccount.getBalance() - (buyAmount1 * 4.10));
                           loggedInAccount.setUsdBalance(buyAmount1 + loggedInAccount.getUsdBalance());
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New USD Balance : $" + loggedInAccount.getUsdBalance());
                        }
                        break;

                    }else {
                        System.out.println("Current Balance : $" + loggedInAccount.getUsdBalance());
                        System.out.println("Please enter the amount you want to sell");
                        double sellAmount1 = scanner.nextDouble();
                        if(sellAmount1 > loggedInAccount.getUsdBalance()){
                            System.out.println("Insufficient balance");
                            continue;
                        }else {
                            loggedInAccount.setBalance(loggedInAccount.getBalance() + (sellAmount1 * 4.02));
                            loggedInAccount.setUsdBalance(loggedInAccount.getUsdBalance() - sellAmount1);
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New USD Balance : $" + loggedInAccount.getUsdBalance());
                        }
                    }
                    break;
                case 2:
                    System.out.println("EUR/PLN \n" +
                            "Current Rates : \n" +
                            "Buy : 4.34 --- Sell : 4.29");
                    System.out.println("Select 1 to Buy  - Select 2 to Sell");
                    int choice2 = scanner.nextInt();
                    if (choice2 == 1) {
                        System.out.println("Current balance : PLN" + loggedInAccount.getBalance());
                        System.out.println("Please enter the amount you want to buy");
                        double buyAmount2 = scanner.nextDouble();
                        if (buyAmount2 * 4.34 > loggedInAccount.getBalance()){
                            System.out.println("Insufficient balance");
                            continue;
                        }else {
                            loggedInAccount.setBalance (loggedInAccount.getBalance() - (buyAmount2 * 4.34));
                            loggedInAccount.setEurBalance(buyAmount2 + loggedInAccount.getEurBalance());
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New EUR Balance : €" + loggedInAccount.getEurBalance());
                        }
                        break;

                    }else {
                        System.out.println("Current Balance : €" + loggedInAccount.getEurBalance());
                        System.out.println("Please enter the amount you want to sell");
                        double sellAmount2 = scanner.nextDouble();
                        if (sellAmount2 > loggedInAccount.getEurBalance()) {
                            System.out.println("Insufficient balance");
                            continue;
                        } else {
                            loggedInAccount.setBalance(loggedInAccount.getBalance() + (sellAmount2 * 4.10));
                            loggedInAccount.setEurBalance(loggedInAccount.getEurBalance() - sellAmount2);
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New USD Balance : €" + loggedInAccount.getEurBalance());
                        }
                    }

                    break;
                case 3:
                    System.out.println("GBP/PLN \n" +
                            "Current Rates : \n" +
                            "Buy : 5.20 --- Sell : 5.11");
                    int choice3 = scanner.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Current balance : PLN" + loggedInAccount.getBalance());
                        System.out.println("Please enter the amount you want to buy");
                        double buyAmount3 = scanner.nextDouble();
                        if (buyAmount3 * 5.20 > loggedInAccount.getBalance()){
                            System.out.println("Insufficient balance");
                            continue;
                        }else {
                            loggedInAccount.setBalance (loggedInAccount.getBalance() - (buyAmount3 * 5.20));
                            loggedInAccount.setGbpBalance(buyAmount3 + loggedInAccount.getGbpBalance());
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New GBP Balance : £" + loggedInAccount.getGbpBalance());
                        }
                        break;

                    }else {
                        System.out.println("Current Balance : £" + loggedInAccount.getGbpBalance());
                        System.out.println("Please enter the amount you want to sell");
                        double sellAmount3 = scanner.nextDouble();
                        if (sellAmount3 > loggedInAccount.getGbpBalance()) {
                            System.out.println("Insufficient balance");
                            continue;
                        } else {
                            loggedInAccount.setBalance(loggedInAccount.getBalance() + (sellAmount3 * 5.11));
                            loggedInAccount.setGbpBalance(loggedInAccount.getGbpBalance() - sellAmount3);
                            System.out.println("Exchange has been successfully made \n" +
                                    "New Balance : PLN" + loggedInAccount.getBalance() + "\n" +
                                    "New USD Balance : £" + loggedInAccount.getEurBalance());
                        }
                    }

                    break;

                default:
                    System.out.println("Invalid choice");
                    continue;
            }
            boolean devam = false;
            do {
                System.out.println("****************************************************** \n" +
                        "Would you like to continue the process? Enter 1 for YES, 2 for Account Menu");

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
                    System.out.println("You are directed to account menu. Thank you.");
                    break;
                }else{
                    System.out.println("Please enter a valid value");
                }
            }while (true);
            if (!devam){

                break;
            }
        }
    }


}















