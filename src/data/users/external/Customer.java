package data.users.external;

import data.users.internal.TopSecretFile;
import shared.RegisteredPerson;
import ui.login.ConsoleLoginImpl;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Customer extends RegisteredPerson {
    Scanner scan = new Scanner(System.in);

    @Override
    public void login() {
        String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
        String dbUsername = "root";
        String loginQuery = "SELECT * FROM customer WHERE email = (?);";
        //String test = "SELECT * FROM employee";
        boolean successfulLog = false;

        while (successfulLog == false) {
            try {
                System.out.println("Please input your email.");
                String customerEmail = scan.nextLine();
                System.out.println("Please input your password.");
                String customerPassword = scan.nextLine();

                Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
                PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
                preparedStatement.setString(1, customerEmail);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                if (resultSet.getString("password").equals(customerPassword)) {
                    successfulLog = true;
                    setCustomer(resultSet, customerEmail);
                    System.out.println("You successfully logged in!");
                    getCustomerOptions(resultSet, customerEmail, connection);
                } else {
                    System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
            }
        }
    }


    public void setCustomer(ResultSet resultSet, String customerEmail){
        String allCustomerInformation = "SELECT * FROM employee WHERE email = \'" + customerEmail + "\';";

        try {
            setFirstName(resultSet.getString("first_name"));
            System.out.println("Welcome, " + this.firstName + "!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void showCheckingBalance(ResultSet resultSet, String email){
        String queryCurrentBalance = "SELECT checking_balance WHERE email = \'" +  email + "\'";
        try {
            System.out.println("Your current balance is " + resultSet.getString("checking_balance"));
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void getCustomerOptions(ResultSet resultSet, String email,Connection connection){

        boolean run = true;
        while(run){
            System.out.println("• Checking Balance: 0\n• Deposit : 1\n• Withdraw : 2\n• Logout: 3");
            try{
                byte userInput = scan.nextByte();
                if(userInput == 3){
                    try{
                        connection.close();
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    run = false;
                }
                processChoice(resultSet, email, userInput);
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
        }
        //withdrawChecking(connection, email)
    }


    public void depositChecking(ResultSet resultSet, String email){
        boolean validDeposit = false;
        while(validDeposit == false) {
            try {
                System.out.println("How much would you like to deposit?\nNote: Please only input valid AND positive integer values.");
                int userInput = scan.nextInt();
                validDeposit = validateDeposit(userInput);
                if(validDeposit) {
                    processDeposit(userInput,resultSet,email);
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                depositChecking(resultSet,email);
            }
        }
    }

    public void withdrawChecking(ResultSet resultSet, String email){
        boolean validWithdraw = false;
        while(validWithdraw == false){
            System.out.println("How much would you like to withdraw?");
            try{
                int userInput = scan.nextInt();
                validWithdraw = validateWithdraw(resultSet, userInput);
                if(validWithdraw){
                    processWithdraw(userInput, resultSet, email);
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
                System.out.println("Invalid Total");
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

    public void processDeposit(int depositAmount, ResultSet resultSet, String email) {
        try{
            String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
            String dbUsername = "root";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            int currentTotal = resultSet.getInt("checking_balance");
            int newTotal = currentTotal + depositAmount;
            String depositStatement = "UPDATE customer SET checking_balance =\'" + newTotal + "\' WHERE email = \'" + email + "\'";
            connection.createStatement().executeUpdate(depositStatement);
            System.out.println("Your New Checking Balance is: " + resultSet.getInt("checking_balance"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processWithdraw(int withdrawAmount, ResultSet resultSet,String email){
        try{
            String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
            String dbUsername = "root";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            int currentTotal = resultSet.getInt("checking_balance");
            int newTotal = currentTotal - withdrawAmount;
            System.out.println(withdrawAmount);
            String depositStatement = "UPDATE customer SET checking_balance =\'" + newTotal + "\' WHERE email = \'" + email + "\'";
            connection.createStatement().executeUpdate(depositStatement);
            System.out.println("Your New Checking Balance is: " + resultSet.getInt("checking_balance"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
