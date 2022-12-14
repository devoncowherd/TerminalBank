package ui.login;

import data.users.external.Candidate;
import data.users.external.Customer;
import data.users.internal.Employee;
import ui.styling.Style;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleLoginImpl implements ConsoleLoginInterface {

    private boolean userTypeNotSet = true;
    private byte userType = 0;

    private String welcomeMessage = "🐮 Welcome to Cowherd Bank 🐮";
    private String prompt = "Please Select from the Options Below";

    @Override
    public void startApp() {
        welcomeUser();
        validateTypeInput();
    }

    @Override
    public void setUserType() {
        validateTypeInput();
    }

    @Override
    public byte getUserType() {
        return userType;
    }

    @Override
    public void signUp() {
        Candidate candidate = new Candidate();

    }

    public void validateTypeInput(){
        while(this.userTypeNotSet){
            prompt();
            try {
                Scanner scan = new Scanner(System.in);
                byte userInput = scan.nextByte();
                if(checkResponseInRange(
                        userInput)) {
                    this.userTypeNotSet = false;
                    processChoice(userInput);
                    scan.close();
                }
            } catch(InputMismatchException e) {
                //e.printStackTrace();
                prompt();
            }
        }
    }

    public boolean checkResponseInRange(byte userInput){
        return userInput == 0 || userInput == 1 || userInput == 2 || userInput == 3;
    }


    public void giveUserTypeOptions(){
        System.out.println("• 0 : Customer Login\n• 1 : Employee Login\n• 2 : Signup\n• 3 : Exit");
    }

    public void welcomeUser(){
        Style.squiggle(welcomeMessage);
        System.out.println(welcomeMessage);
        Style.squiggle(welcomeMessage);
    }

    public void prompt(){
        Style.dash(prompt);
        System.out.println(prompt);
        Style.dash(prompt);
        giveUserTypeOptions();
    }

    public void closeProgram(byte userInput){
        if(userInput == 3){
            System.out.println("Thanks for Banking with Cowherd!\nClosing Application...");
            System.exit(0);
        }
    }
    public void startSignUp(byte userInput){
        if(userInput == 2){
            SignUpImpl signUp = new SignUpImpl();
            signUp.generateForm();
        }
    }

    public void loginEmployee(byte userInput){
        if(userInput == 1){
            Employee employee = new Employee();
            employee.login();
        }
    }

    public void loginCustomer(byte userInput){
        if(userInput == 0){
            Customer customer = new Customer();
            customer.login();
        }
    }


    public void processChoice(byte userInput){
        closeProgram(userInput);
        startSignUp(userInput);
        loginEmployee(userInput);
        loginCustomer(userInput);
    }
}
