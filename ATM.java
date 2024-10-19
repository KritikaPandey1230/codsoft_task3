import java.util.Scanner;

class Account {
    private double balance;

    
    public Account(double initialAmount) {
        this.balance = initialAmount;
    }

  
    public double getBalance() {
        return balance;
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit completed. Updated Balance: " + balance);
        } else {
            System.out.println("Deposit failed. Please enter a valid positive amount.");
        }
    }


    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;  
            System.out.println("Withdrawal successful. Remaining Balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds for the requested withdrawal.");
        } else {
            System.out.println("Withdrawal failed. Please enter a positive value.");
        }
    }
}

class ATM_Machine {
    private Account account;
    private Scanner input;

   
    public ATM_Machine(Account account) {
        this.account = account;
        this.input = new Scanner(System.in);
    }


    public void displayOptions() {
        System.out.println("\n===== ATM Options =====");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Exit");
    }

  
    public void execute() {
        int option;
        do {
            displayOptions();
            System.out.print("Please select an option: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid option number.");
                input.next();  
            }
            option = input.nextInt();

            switch (option) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    System.out.println("Thank you for using this ATM. Have a nice day!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a correct option.");
            }
        } while (option != 4);
    }

    
    private void showBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    
    private void depositFunds() {
        System.out.print("Enter the amount to deposit: ");
        double amount = validateInput();
        account.deposit(amount);
    }

 
    private void withdrawFunds() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = validateInput();
        account.withdraw(amount);
    }

   
    private double validateInput() {
        double amount;
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid numeric amount.");
            input.next(); 
        }
        amount = input.nextDouble();
        return amount;
    }
}

public class ATM {
    public static void main(String[] args) {
        System.out.println("Welcome to the ATM System");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your 4-digit PIN: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid 4-digit number.");
            scanner.next();
        }
        int pinCode = scanner.nextInt();


        Account userAccount = new Account(1000.0);  
        ATM_Machine atmMachine = new ATM_Machine(userAccount);
        atmMachine.execute(); 
    }
}
