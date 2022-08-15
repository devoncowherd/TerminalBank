package data.users.external;

import data.users.internal.TopSecretFile;
import shared.RegisteredPerson;
import java.util.Scanner;
import java.sql.*;

public class Customer extends RegisteredPerson {
    Scanner scan = new Scanner(System.in);

    @Override
    public void login() {
        String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
        String dbUsername = "root";
        String loginQuery = "SELECT * FROM employee WHERE email = (?);";
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
                    setCustomer(connection, customerPassword);
                    System.out.println("You successfully logged in!");
                } else {
                    System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Either the account doesn't exist, or the credentials do not match.\nPlease check details and try again.");
            }
        }
    }


    public void setCustomer(Connection connection, String customerEmail){
        String allCustomerInformation = "SELECT * FROM employee WHERE email = \'" + customerEmail + "\';";

        try {
            ResultSet finalResult = connection.createStatement().executeQuery(allCustomerInformation);
            finalResult.next();
            setFirstName(finalResult.getString("first_name"));
            System.out.println("Welcome, " + this.firstName + "!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void deposit(Connection connection, String email){
        String queryCurrentBalance = "SELECT checking_balance WHERE email = \'" +  email + "\'";
        ResultSet finalResult =
    }
}
