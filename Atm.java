import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atm {
    long availableAmountInMachine;
    long transactionFee;
    List<UserData> userData;

    class UserData {
        private User user;
        private String password;
        private long availableBalance;
        private List<String> recentTransaction;

        UserData(User user, String password) {
            this.user = user;
            this.password = password;
            recentTransaction = new ArrayList<>();
        }

        public List<String> getRecentTransaction() {
            return recentTransaction;
        }

        public User getUser() {
            return user;
        }

        public long getAvailableBalance() {
            return availableBalance;
        }

        public String getPassword() {
            return password;
        }

        public void setRecentTransaction(String recentTransaction) {
            this.recentTransaction.add(recentTransaction);
        }

        public void setAvailableBalance(long availableBalance) {
            this.availableBalance = availableBalance;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    Atm(long availableAmountInMachine, long transactionFee) {
        userData = new ArrayList<>();
        this.availableAmountInMachine = availableAmountInMachine;
        this.transactionFee = transactionFee;
    }

    void createNewUser(String bankAccountNumber, String password) {
        userData.add(new UserData(new User(bankAccountNumber), password));
    }


    boolean login(String bankAccountNumber, String password) {
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                if (u.getPassword().equals(password)) {
                    return true;
                } else {
                    System.out.println("Your password is wrong");
                    return false;
                }
            }
        }

        System.out.println("We could not verify your account information!");
        return false;
    }

    boolean identity(String name, int age, String phoneNumber) {
        for (UserData u : userData) {
            if (u.getUser().getName().equals(name)) {
                if (u.getUser().getAge() == age && u.getUser().getPhoneNumber() == phoneNumber) {
                    return true;
                }

                break;
            }
        }

        System.out.println("Verify failed!!!");
        return false;

    }

    void changePassword(String bankAccountNumber, String newPassword) {
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                u.setPassword(newPassword);
                System.out.println("Change successful!!");
                return;
            }
        }

    }


    long availableBalance(String bankAccountNumber) {
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                return u.getAvailableBalance();
            }
        }

        return 0;
    }

    void withDrawl(String bankAccountNumber, long amount) {
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                if (this.availableAmountInMachine + this.transactionFee < amount) {
                    System.out.println("The money in this machine is not enough.");
                    break;
                }

                u.setAvailableBalance(u.getAvailableBalance() - amount + this.transactionFee);
                u.setRecentTransaction("WithDrawl" + " - " + amount);
                this.availableAmountInMachine -= amount - this.transactionFee;
                break;
            }
        }
    }

    void deposit(String bankAccountNumber, long amount) {
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                u.setAvailableBalance(u.getAvailableBalance() + amount - this.transactionFee);
                u.setRecentTransaction("Deposit" + " - " + amount);
                //System.out.println(u.getRecentTransaction());
                this.availableAmountInMachine += amount + this.transactionFee;
                break;
            }
        }

    }

    List<String> recentTransactions(String bankAccountNumber) {
        List<String> result = new ArrayList<>();
        for (UserData u : userData) {
            if (u.getUser().getBankAccountNumber().equals(bankAccountNumber)) {
                if (u.getRecentTransaction().size() >= 10) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(u.getRecentTransaction().
                                get(u.getRecentTransaction().size() - 1 - i));
                    }
                } else {
                    for (int i = u.getRecentTransaction().size() - 1; i >= 0; i--) {
                        System.out.println(u.getRecentTransaction().get(i));
                    }
                }

                result = u.getRecentTransaction();
                return result;
            }
        }

        return result;
    }

    public void start() {
        System.out.println("Are you a new user?(yes or no)");
        Scanner reader = new Scanner(System.in);

        while (true) {
            String str = reader.next();
            if (str.equals("yes")) {
                System.out.println("Please enter your bank account number:");
                String bankAccountNumber = reader.next();
                System.out.println("Please enter your password:");
                String password = reader.next();
                createNewUser(bankAccountNumber, password);
                break;
            } else if (str.equals("no")) {
                while (true) {
                    System.out.println("Do you forget your password(yes or no)");
                    String answer = reader.next();
                    if (answer.toLowerCase().equals("no")) {
                        while (true) {
                            System.out.println("Please enter your bankAccountNumber:");
                            String bankAccountNumber = reader.next();
                            System.out.println("Please enter your password:");
                            String password = reader.next();
                            if (login(bankAccountNumber, password)) {
                                System.out.println("Login successful!");
                                afterLogin(reader, bankAccountNumber);
                                break;
                            } else {
                                System.out.println("Login failed.");

                            }
                        }
                        break;
                    } else if (answer.toLowerCase().equals("yes")) {
                        System.out.println(" reset the password ");
                        while (true) {
                            System.out.println("Please enter your name:");
                            String name = reader.next();
                            System.out.println("Please enter your age:");
                            String age = reader.next();
                            System.out.println("Please enter your phone number:");
                            String phoneNumber = reader.next();

                            if (identity(name, Integer.parseInt(age), phoneNumber)) {
                                System.out.println("Reset new password ");
                                String newPassword = reader.next();
                                changePassword(phoneNumber, newPassword);
                                break;
                            } else {
                                System.out.println("Reset failed.");

                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid input");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input,please re-enter.");
            }
        }
    }

    private void afterLogin(Scanner reader, String bankAccountNumber) {
        System.out.println("Choose a option with its number");
        System.out.println("1: Available Balance");
        System.out.println("2: Withdrawal");
        System.out.println("3: Deposit");
        System.out.println("4: Recent Transactions");
        System.out.println("5: Change Password");
        System.out.println("6: Exit");

        String option = null;
        while (true) {
            while (true) {
                option = reader.next();
                if (option.matches("[0-6]")) {
                    switch (Integer.parseInt(option)) {
                        case 1:
                            System.out.println("Balance: " + availableBalance(bankAccountNumber));
                            break;
                        case 2:
                            System.out.println("How much do you want to withdraw?");
                            String amountWithdraw = reader.next();
                            int amountCase1 = Integer.parseInt(amountWithdraw);
                            withDrawl(bankAccountNumber, amountCase1);
                            break;
                        case 3:
                            System.out.println("How much do you want to deposit");
                            String amountDeposit = reader.next();
                            int amountCase2 = Integer.parseInt(amountDeposit);
                            deposit(bankAccountNumber, amountCase2);
                            break;
                        case 4:
                            recentTransactions(bankAccountNumber);
                            break;
                        case 5:
                            System.out.println("Reset your new password");
                            String newPassword = reader.next();
                            changePassword(bankAccountNumber, newPassword);
                            break;
                        case 6:
                            return;
                    }
                    break;
                } else {
                    System.out.println("Please enter the number between 1 to 6");
                }
            }
        }


    }
}


