package data.users.internal;

import shared.RegisteredPerson;
import ui.login.ConsoleLoginImpl;
import ui.styling.Style;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee extends RegisteredPerson {

    Scanner scan = new Scanner(System.in);
    Style style = new Style();
    String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
    String dbUsername = "root";
    String loginQuery = "SELECT * FROM employee WHERE email = (?);";
    String activeUpdate = "UPDATE customer SET active = (?) WHERE email = (?)";
    String checkingUpdate = "UPDATE customer SET checking_balance = (?) WHERE email = (?)";
    String candidateQuery = "SELECT * FROM customer WHERE active = 0";
    String customerQuery = "SELECT * FROM customer WHERE email = (?)";
    String customerAccountQuery = "SELECT * FROM customer WHERE email = (?);";
    String allTransactionQuery = "SELECT * FROM transaction";

    public ResultSet refreshResult(String customerEmail){
        try{
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(customerAccountQuery);
            preparedStatement.setString(1, customerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    boolean successfulLog = false;

    @Override
    public void login() {

        //String test = "SELECT * FROM employee";

        while(successfulLog == false){
            try {
                System.out.println("Please input your email.");
                String employeeEmail = scan.nextLine();
                System.out.println("Please input your password.");
                String employeePassword = scan.nextLine();

                Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
                PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
                preparedStatement.setString(1,employeeEmail);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                if(resultSet.getString("password").equals(employeePassword)) {
                    successfulLog = true;
                    setEmployee(connection, employeeEmail);
                    System.out.println("You successfully logged in!");
                    checkEmployeeType(resultSet,email);
                }
                else {
                    System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
                }

            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
            }
        }

    }
    public void updateEmployeeInformation() {

    }

    public void setEmployee(Connection connection, String employeeEmail){
        String allEmployeeInformation = "SELECT * FROM employee WHERE email = \'" + employeeEmail + "\';";

        try{
            ResultSet finalResult = connection.createStatement().executeQuery(allEmployeeInformation);
            finalResult.next();
            setFirstName(finalResult.getString("first_name"));
            System.out.println("Welcome, " + this.firstName + "!");
            showITMenu(connection);

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void checkEmployeeType(ResultSet resultSet, String email){

        try{
            if(resultSet.getString("department").equals("IT")){
                //IT infoTech = new IT();
                //infoTech.startShift();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }


    public void checkCandidates(Connection connection, String sql){
        try{
            ResultSet finalResult = connection.createStatement().executeQuery(sql);
            while(finalResult.next()){
                Style style = new Style();
                style.dash("                                  ");
                System.out.printf(
                        "Account Number: %d\nEmail: %s\nOwner:%s %s\nChecking:%d\nStatus: %d\n",
                        finalResult.getInt("customer_id"),
                        finalResult.getString("email"),
                        finalResult.getString("first_name"),
                        finalResult.getString("last_name"),
                        finalResult.getInt("checking_balance"),
                        finalResult.getInt("active")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }


    public void showITMenu(Connection connection){
        boolean run = true;

        while(run){

            boolean exit = false;
            while(exit == false){
                System.out.println("\n\n0 : View Accounts\n1 : Check Candidates\n2 : Logout\n3 : Change Customer Account Status\n4 : View All Transactions");
                byte userInput = scan.nextByte();
                try {
                    if(userInput == 0){
                        String queryAllAccounts = "SELECT * FROM customer";
                        try {
                            ResultSet finalResult = connection.createStatement().executeQuery(queryAllAccounts);
                            while(finalResult.next()){
                                Style style = new Style();
                                style.dash("                                  ");
                                System.out.printf("ID: %d\nEmail:%s\nOwner:%s %s\nChecking:%d\nStatus:%d\n",
                                        finalResult.getInt("customer_id"),
                                        finalResult.getString("email"),
                                        finalResult.getString("first_name"),
                                        finalResult.getString("last_name"),
                                        finalResult.getInt("checking_balance"),
                                        finalResult.getInt("active"));
                            }
                        } catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(userInput == 1){
                        System.out.println("Current Applications:");
                        checkCandidates(connection,candidateQuery);
                    }
                    if(userInput == 2){
                        System.out.println("Logging out...!\nHave a great rest of your day!");
                        ConsoleLoginImpl console = new ConsoleLoginImpl();
                        console.startApp();
                    }
                    if(userInput == 3){
                        changeCustomerStatus();
                    }
                    if(userInput == 4){
                        showAllTransactions();
                    }

                } catch(InputMismatchException e){
                    e.printStackTrace();
                }
            }
            exit = false;
        }

    }

    public void changeCustomerStatus(){

        try{
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(activeUpdate);
            System.out.println("Which account would you like to evaluate?(hint: enter customer email)");
            scan.nextLine();
            String targetCustomer = scan.nextLine();
            System.out.println("Would you like to set the account to active or inactive?\n(hint: \n0 = inactive\n1 = active");
            int newStatus = scan.nextInt();
            preparedStatement.setInt(1,newStatus);
            preparedStatement.setString(2,targetCustomer);
            preparedStatement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void showAllTransactions(){
        style.dash();
        try {
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            ResultSet resultSet = connection.createStatement().executeQuery(allTransactionQuery);
            while(resultSet.next()){
                System.out.printf("Transaction ID: %d\n" +
                        "Customer ID:%d\n" +
                        "Recipient ID: %d\n" +
                        "Time Stamp: %tc\n" +
                        "Withdrawn: %d\n" +
                        "Deposited: %d\n",
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("recipient_id"),
                        resultSet.getTimestamp("time_stamp"),
                        resultSet.getInt("withdraw"),
                        resultSet.getInt("deposit"));
                style.dash();
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}

