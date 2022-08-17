package data.users.external;

import data.users.internal.TopSecretFile;

import java.sql.*;

public class CustomerDao {
    String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
    String dbUsername = "root";
    String customerAccountQuery = "SELECT * FROM customer WHERE email = (?);";


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
}
