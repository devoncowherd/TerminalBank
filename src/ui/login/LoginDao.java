package ui.login;

import data.users.internal.TopSecretFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDao {
    String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
    String dbUsername = "root";

    public void insertCustomer(String firstName,
                               String lastName,
                               String email,
                               //String accountType,
                               String password,
                               int checkingBalance){
        try{
            String signupStatement = "INSERT INTO customer(first_name,last_name,email,password, checking_balance) VALUES((?),(?),(?),(?),(?))";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(signupStatement);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,email);
            //preparedStatement.setString(4,accountType);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,checkingBalance);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
