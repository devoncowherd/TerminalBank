package data.users.external;

import shared.InputFormatter;
import shared.RegisteredPerson;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Candidate extends RegisteredPerson {

    Scanner scan = new Scanner(System.in);
    InputFormatter formatter = new InputFormatter();
    public Candidate(){
        generateForm();
    }
    private String firstName;
    private String lastName;
    private String middleName;
    private String social;
    private String dateOfBirth;
    private String address;
    private String zipCode;
    private String email;
    private String phoneNumber;
    private String userName;
    private String accountType;




    public void generateForm() {
        scan.close();
    }
}

