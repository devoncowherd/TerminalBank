package data.users.internal;

import shared.RegisteredPerson;
import ui.login.ConsoleLoginImpl;
import ui.styling.Style;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee extends RegisteredPerson {

    Scanner scan = new Scanner(System.in);

    boolean successfulLog = false;

    @Override
    public void login() {
        String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
        String dbUsername = "root";
        String loginQuery = "SELECT * FROM employee WHERE email = (?);";
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

            } catch(SQLException e){
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


    public void checkCandidates(){

    }


    public void showITMenu(Connection connection){
        boolean run = true;

        while(run){
            System.out.println("View Accounts: 0\nCheck Applications : 1\nLogout : 2");
            byte userInput = scan.nextByte();
            boolean inputApproriate = false;
            while(inputApproriate == false){
                try {
                    if(userInput == 0){
                        String queryAllAccounts = "SELECT * FROM customer";
                        try {
                            ResultSet finalResult = connection.createStatement().executeQuery(queryAllAccounts);
                            while(finalResult.next()){
                                Style style = new Style();
                                style.dash("                                  ");
                                System.out.printf("Owner:%s %s %s\nChecking:%d\n",finalResult.getString("first_name"),finalResult.getString("middle_name"),finalResult.getString("last_name"),finalResult.getInt("checking_balance"));
                            }
                            inputApproriate = true;
                        } catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(userInput == 1){
                        System.out.println("Current Applications:");
                    }
                    if(userInput == 2){
                        System.out.println("Logging out...!\nHave a great rest of your day!");
                        ConsoleLoginImpl console = new ConsoleLoginImpl();

                        console.startApp();
                    }

                } catch(InputMismatchException e){
                    e.printStackTrace();
                }
            }
            inputApproriate = false;
        }
    }
}

