package ui.login;

import shared.InputFormatter;

import java.util.Scanner;

public class SignUpImpl implements SignUpInterface{

    InputFormatter formatter = new InputFormatter();
    Scanner scan = new Scanner(System.in);

    String firstName;
    String lastName;
    String middleName;
    String social;
    String dateOfBirth;
    String address;
    String zipCode;
    String email;
    String phoneNumber;
    String username;
    String accountType;
    boolean active = false;

    //Scanner scan = new Scanner(System.in);

    @Override
    public void generateForm() {

    }
//914992
    @Override
    public void askfirstName() {
        boolean notValid = false;
        while(notValid == false){
            System.out.println("Please input your first name");
            String userInput = scan.nextLine();

        }
    }

    @Override
    public void askLastName() {

    }

    @Override
    public void askMiddleName() {

    }

    @Override
    public void askSocial() {

    }

    @Override
    public void askDateOfBirth() {

    }

    @Override
    public void askAddress() {

    }

    @Override
    public void askZipCode() {

    }

    @Override
    public void askEmail() {

    }

    @Override
    public void askPhoneNumber() {

    }

    @Override
    public void askUsername() {

    }

    @Override
    public void askAccountType() {

    }

    public void prompt(String info){
        System.out.println("Please enter your " + info);
    }
}
