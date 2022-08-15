package data.users.internal;

import shared.RegisteredPerson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Employee extends RegisteredPerson {
    Connection connection = null;

    if(connection == null){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cowherd_bank", "root", TopSecregtFile.getDbPassword());

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void login(){

    };
    public void updateEmployeeInformation(){}

}
