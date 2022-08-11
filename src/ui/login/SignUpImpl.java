package ui.login;

import java.util.Scanner;

public class SignUpImpl implements SignUpInterface{

    Scanner scan = new Scanner(System.in);

    @Override
    public void generateForm() {

    }

    @Override
    public void askfirstName() {
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
