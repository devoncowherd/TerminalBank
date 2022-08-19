package data.users.internal;

import java.sql.*;

public class TransactionLogger {

    String dbURL = "jdbc:mysql://localhost:3306/cowherd_bank";
    String dbUsername = "root";
    String allTransactionsQuery = "SELECT * FROM transaction";
    String transactionTransferInsert = "INSERT INTO transaction" +
            "(customer_id,recipient_id, time_stamp,withdraw,deposit) " +
            "VALUES((?),(?),CURRENT_TIMESTAMP,(?),(?))";

    String customerEmail;
    String transactionDepositInsert = "INSERT INTO transaction(customer_id, recipient_id, time_stamp,withdraw,deposit) VALUES((?),NULL,CURRENT_TIMESTAMP,null,(?))";
    String transactionWithdrawInsert = "INSERT INTO transaction(customer_id, recipient_id, time_stamp,withdraw,deposit) VALUES((?),NULL,CURRENT_TIMESTAMP,(?),null)";

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(dbURL, dbUsername, TopSecretFile.getDbPassword());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void logTransfer(int customerID, int recipientID, int customerWithdraw, int recipientDeposit){
        Connection connection = getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(transactionTransferInsert);
            preparedStatement.setInt(1,customerID);
            preparedStatement.setInt(2,recipientID);
            preparedStatement.setInt(3,customerWithdraw);
            preparedStatement.setInt(4,recipientDeposit);
            preparedStatement.executeUpdate();
            connection.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void logDeposit(String customerEmail, int customerDepositAmount){
        this.customerEmail = customerEmail;
        Connection connection = getConnection();
        try{
            String customerIDQuery = "SELECT customer_id FROM customer WHERE email=\'" +  customerEmail + "\'";
            ResultSet resultSet = connection.createStatement().executeQuery(customerIDQuery);
            resultSet.next();
            System.out.println(resultSet.getInt("customer_id"));

            PreparedStatement preparedStatement = connection.prepareStatement(transactionDepositInsert);
            preparedStatement.setInt(1, resultSet.getInt("customer_id"));
            preparedStatement.setInt(2, customerDepositAmount);
            preparedStatement.executeUpdate();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void logWithdraw(String customerEmail,int customerWithdrawAmount ){
        this.customerEmail = customerEmail;

        try{
            Connection connection = getConnection();
            String customerIDQuery = "SELECT customer_id FROM customer WHERE email=\'" +  customerEmail + "\'";
            ResultSet resultSet = connection.createStatement().executeQuery(customerIDQuery);
            resultSet.next();

            PreparedStatement preparedStatement = connection.prepareStatement(transactionWithdrawInsert);
            preparedStatement.setInt(1, resultSet.getInt("customer_id"));
            preparedStatement.setInt(2,(-1) * customerWithdrawAmount);
            preparedStatement.executeUpdate();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
