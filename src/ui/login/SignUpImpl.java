package ui.login;

import shared.InputFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignUpImpl implements SignUpInterface {

    InputFormatter formatter = new InputFormatter();
    Scanner scan = new Scanner(System.in);
    LoginDao loginDao = new LoginDao();

    ConsoleLoginImpl consoleLoginImpl = new ConsoleLoginImpl();
    String firstName = null;
    String lastName = null;
    String middleName = null;
    String social = null;
    String dateOfBirth = null;
    String address = null;
    String zipCode = null;
    String email = null;
    String phoneNumber = null;
    String username = null;
    String accountType = null;
    String password = null;
    int initialBalance = -1;

    @Override
    public void generateForm() {
        askfirstName();
        askLastName();
        askEmail();
        askAccountType();
        askPassword();
        createCandidate();
    }
//914992
    @Override
    public void askfirstName() {
        while(this.firstName == null || !formatter.validateName(this.firstName)){
            System.out.println("Please input your legal first name.");
            this.firstName = scan.nextLine().toUpperCase();
        }
    }

    @Override
    public void askLastName() {
        while(this.lastName == null || formatter.validateName(this.lastName) == false){
            System.out.println("Please input your legal last name.");
            this.lastName = scan.nextLine().toUpperCase();
        }
    }

    @Override
    public void askMiddleName() {
        while(this.middleName == null || formatter.validateName(this.middleName) == false){
            System.out.println("Please input your legal last name.");
            this.middleName = scan.nextLine().toUpperCase();
        }
    }

    @Override
    public void askSocial() {
        while(this.social == null || formatter.validateSocial(this.social) == false){
            System.out.println("Please input your social.\nformat:XXXXXXXXX");
            this.social = scan.nextLine();
        }
    }

    @Override
    public void askDateOfBirth() {
        while(this.dateOfBirth == null || formatter.validateDateOfBirth(this.dateOfBirth) == false){
            System.out.println("Please input your birthday");
            this.dateOfBirth = scan.nextLine();
        }
    }

    @Override
    public void askAddress() {
        while(this.address == null || formatter.validateAddress(this.address) == false){
            System.out.println("Please input your address");
            this.address = scan.nextLine();
        }
    }

    @Override
    public void askZipCode() {
        while(this.zipCode == null || formatter.validateZip(this.zipCode) == false){
            System.out.println("Please input your zip code.");
            this.zipCode = scan.nextLine();
        }
    }

    @Override
    public void askEmail() {
        while(this.email == null || formatter.validateEmail(this.email) == false){
            System.out.println("Please enter your valid email address");
            this.email = scan.nextLine();
        }
    }

    @Override
    public void askPhoneNumber() {
        while(this.phoneNumber == null || formatter.validatePhone(this.phoneNumber) == false){
            System.out.println("Please enter your phone number,");
            this.phoneNumber = scan.nextLine();
        }
    }





    @Override
    public void askUsername() {

    }

    @Override
    public void askPassword(){
        while(this.password == null ){
            System.out.println("Please input your password");
            this.password = scan.nextLine();
        }
    }
    @Override
    public void askAccountType() {
        boolean validChoice = false;
        while(validChoice == false){
            System.out.println("What account type would you like?\n• 0 : Checking\n• 1 : Savings\n• 2 : Apply For Credit Line");
            try {
                int userInput = scan.nextInt();
                if(userInput == 0) {
                    validChoice = true;
                    while(this.initialBalance < 0){
                        System.out.println("How much would you like to deposit today?\n(Notice: Amount will be refunded in 7-10 business days if account not approved)");
                        try{
                            userInput = scan.nextInt();
                            if(userInput >= 0){
                                this.initialBalance = userInput;
                            }
                        } catch(InputMismatchException e){
                            e.printStackTrace();
                        }
                    }
                }
            } catch(InputMismatchException e){
                e.printStackTrace();
            }
        }
    }

    public void prompt(String info){
        System.out.println("Please enter your " + info);
    }


    public void createCandidate(){
        loginDao.insertCustomer(
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                this.initialBalance);
        System.out.println("Your application may take 7-10 business days to process");
        consoleLoginImpl.startApp();
    }
}
