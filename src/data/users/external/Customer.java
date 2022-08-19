package data.users.external;

import data.users.internal.TopSecretFile;
import data.users.internal.TransactionLogger;
import shared.RegisteredPerson;
import ui.login.ConsoleLoginImpl;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Customer extends RegisteredPerson {

    Scanner scan = new Scanner(System.in);
    CustomerDao customerDao = new CustomerDao();
    TransactionLogger transactionLogger = new TransactionLogger();
    @Override
    public void login() {
        //String test = "SELECT * FROM employee";
        boolean successfulLog = false;

        while (successfulLog == false) {
            try {
                System.out.println("Please input your email.");
                String customerEmail = scan.nextLine();
                System.out.println("Please input your password.");
                String customerPassword = scan.nextLine();

                if (customerDao.refreshResult(customerEmail).getString("password").equals(customerPassword)) {
                    if(customerDao.refreshResult(customerEmail).getBoolean("active")){
                        successfulLog = true;
                        setCustomer(customerDao.refreshResult(customerEmail), customerEmail);
                        System.out.println("You successfully logged in!");
                        getCustomerOptions(customerEmail);
                    } else {
                        System.out.println("Account is inactive or in approval process.\nContact your local branch for further assistance.");
                        ConsoleLoginImpl console = new ConsoleLoginImpl();
                        console.startApp();
                    }

                } else {
                    System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
                }

            } catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
            }
        }
    }


    public void setCustomer(ResultSet resultSet, String customerEmail){
        String allCustomerInformation = "SELECT * FROM employee WHERE email = \'" + customerEmail + "\';";

        try {
            setFirstName(resultSet.getString("first_name"));
            setEmail(resultSet.getString("email"));
            System.out.println("Welcome, " + this.firstName + "!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setEmail(String email) {this.email = email; }

    public void showCheckingBalance(ResultSet resultSet, String customerEmail){
        String queryCurrentBalance = "SELECT checking_balance WHERE email = \'" +  customerEmail + "\'";
        try {
            System.out.println("Your current balance is " + resultSet.getString("checking_balance"));
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void getCustomerOptions(String customerEmail){

        boolean run = true;
        while(run){
            System.out.println("• Checking Balance: 0\n• Deposit : 1\n• Withdraw : 2\n• Logout: 3\n• Transfer Money : 4");
            try{
                byte userInput = scan.nextByte();
                if(userInput == 3) {
                    run = false;
                }
                processChoice(customerDao.refreshResult(customerEmail), customerEmail, userInput);
            } catch(InputMismatchException e){
                e.printStackTrace();
            }
        }
    }

    public void processChoice(ResultSet resultSet,String email,byte userInput){
        if(userInput == 0){
            showCheckingBalance(resultSet, email);
        } else if(userInput == 1) {
           depositChecking(resultSet, email);
        } else if(userInput == 2) {
            withdrawChecking(resultSet,email);
        } else if(userInput == 3) {
            ConsoleLoginImpl console = new ConsoleLoginImpl();
            console.startApp();
        } else if(userInput == 4){
            transferMoney(email);
        }
        //withdrawChecking(connection, email)
    }


    public void depositChecking(ResultSet resultSet, String customerEmail){
        boolean validDeposit = false;
        while(validDeposit == false) {
            try {
                System.out.println("How much would you like to deposit?\nNote: Please only input valid AND positive integer values.");
                int userInput = scan.nextInt();
                validDeposit = validateDeposit(userInput);
                if(validDeposit) {
                    processDeposit(userInput,resultSet,customerEmail);
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                depositChecking(resultSet,customerEmail);
            }
        }
    }

    public void withdrawChecking(ResultSet resultSet, String customerEmail){
        boolean validWithdraw = false;
        while(validWithdraw == false){
            System.out.println("How much would you like to withdraw?");
            try{
                int userInput = scan.nextInt();
                validWithdraw = validateWithdraw(resultSet, userInput);
                if(validWithdraw){
                    processWithdraw(userInput, customerDao.refreshResult(customerEmail), customerEmail);
                }
            } catch (InputMismatchException e) {
                    e.printStackTrace();
            }
        }
    }

    public boolean validateWithdraw(ResultSet resultSet, int withdrawAmount){
        try{
            int currentBalance = resultSet.getInt("checking_balance");
            if(currentBalance < withdrawAmount){
                System.out.println("Insufficient Funds.");
                return false;
            }

            if(withdrawAmount <= -1){
                System.out.println("Invalid Withdraw");
                return false;
            }
            if(withdrawAmount > currentBalance){
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean validateDeposit(int depositAttempt){
        if(depositAttempt > 0){
            return true;
        }
        return false;
    }

    public void processDeposit(int depositAmount, ResultSet resultSet, String customerEmail) {
        try{
            String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
            String dbUsername = "root";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            int currentTotal = resultSet.getInt("checking_balance");
            int newTotal = currentTotal + depositAmount;
            String depositStatement = "UPDATE customer SET checking_balance =\'" + newTotal + "\' WHERE email = \'" + customerEmail + "\'";
            connection.createStatement().executeUpdate(depositStatement);
            transactionLogger.logDeposit(customerEmail,depositAmount);
            System.out.println("Your New Checking Balance is: " + customerDao.refreshResult(customerEmail).getInt("checking_balance"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processWithdraw(int withdrawAmount, ResultSet resultSet,String customerEmail){
        try{
            String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
            String dbUsername = "root";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            int currentTotal = resultSet.getInt("checking_balance");
            int newTotal = currentTotal - withdrawAmount;
            System.out.println(withdrawAmount);
            String depositStatement = "UPDATE customer SET checking_balance =\'" + newTotal + "\' WHERE email = \'" + email + "\'";
            connection.createStatement().executeUpdate(depositStatement);
            transactionLogger.logWithdraw(customerEmail,withdrawAmount);
            System.out.println("Your New Checking Balance is: " + customerDao.refreshResult(customerEmail).getInt("checking_balance"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transferMoney(String customerEmail){
        String customerBalanceQuery = "SELECT checking_balance FROM customer WHERE email =\'" + customerEmail + "\'" ;
        String recipientBalanceQuery = null;

        try{
            String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
            String dbUsername = "root";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            String customerIdQuery = "SELECT customer_id FROM customer WHERE email = \'" + customerEmail + "\'";
            ResultSet idResult =  connection.createStatement().executeQuery(customerIdQuery);
            idResult.next();
            int customerID = idResult.getInt("customer_id");

            int recipientID = -1;
            while(recipientID == -1){
                try{
                    System.out.println("What is the ID of the account would you like to transfer money to?");
                    recipientID = scan.nextInt();
                    recipientBalanceQuery = "SELECT checking_balance FROM customer WHERE customer_id =\'" + recipientID + "\'" ;
                } catch(InputMismatchException e){
                    System.out.println("Invalid Input");
                }
            }

            ResultSet customerResultSet = connection.createStatement().executeQuery(customerBalanceQuery);
            customerResultSet.next();

            ResultSet recipientResultSet = connection.createStatement().executeQuery(recipientBalanceQuery);
            recipientResultSet.next();

            int customerAccountBalance = customerResultSet.getInt("checking_balance");
            int sendingAmount = -1;

            int recipientAccountBalance = recipientResultSet.getInt("checking_balance");


            while(sendingAmount == -1){
                try{
                    System.out.println("What is the amount you would like to transfer?");
                    sendingAmount = scan.nextInt();
                    if(sendingAmount > 0 && sendingAmount <= customerAccountBalance){
                        int recipientNewBalance = sendingAmount + recipientAccountBalance;
                        int customerNewBalance = customerAccountBalance - sendingAmount;
                        String recipientUpdate = "UPDATE customer SET checking_balance = " + recipientNewBalance + " WHERE customer_id = " + recipientID;
                        String customerUpdate = "UPDATE customer SET checking_balance = " + customerNewBalance + " WHERE email = \'" + customerEmail + "\'";
                        connection.createStatement().executeUpdate(recipientUpdate);
                        connection.createStatement().executeUpdate(customerUpdate);
                        transactionLogger.logTransfer(customerID,recipientID,(-1)*(sendingAmount),sendingAmount);
                        System.out.println("Transfer successfully processed!");
                    } else {
                        System.out.println("Something went wrong! Check your balance and try again");
                    }
                } catch(InputMismatchException e){
                    e.printStackTrace();
                }
            }
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
