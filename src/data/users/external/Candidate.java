package data.users.external;

import shared.InputFormatter;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Candidate {

    InputFormatter formatter = new InputFormatter();
    public Candidate(){
        generateForm();
    }
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean checkedForMiddle;
    private String social;
    private String dateOfBirth;
    private String address;
    private String zipCode;
    private String email;
    private String phoneNumber;
    private String userName;
    private String accountType;


    public void setFirstName(String firstName){

        while(this.firstName == null){
            System.out.println("Please Enter Your Legal First Name:");
            if(formatter.validateName(firstName)){
                this.firstName = firstName;
            }
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName){
        while(this.lastName == null){
            System.out.println("Please Enter Your Legal Last Name:");
            if(formatter.validateName(lastName)){
                this.lastName = lastName;
            }
        }
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setMiddleName() {
        if(checkHasMiddleName()){
            Scanner scan = new Scanner(System.in);
            while(this.middleName == null) {
                System.out.println("Please Enter Your Legal Middle Name:");
                String userInput = scan.nextLine();
                if(formatter.validateName(userInput)){
                    this.middleName = userInput;
                    scan.close();
                }
            }
        } else {
            this.middleName = "";
        }
    }

    public boolean checkHasMiddleName(){
        System.out.println("Do you have a middle name?");
        Scanner scan = new Scanner(System.in);
        byte userInput = -1;

        while(checkedForMiddle == false){
            try{
                System.out.println("0 : Yes, 1 : False");
                userInput = scan.nextByte();
                if(userInput == 0 || userInput == 1){
                    checkedForMiddle = true;
                    if(userInput == 0){
                        String middleName;
                        scan.close();
                        return true;
                    }
                    scan.close();
                    return false;
                }
            } catch(InputMismatchException e){
                e.printStackTrace();
                System.out.println("Please ONLY input either \"0\" or \"1\" without quotations");
            }
        }
        return false;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getSocial() {
        return this.social;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setZipCode(String zipCode){
        while(this.zipCode == null){
            if(formatter.validateZip(zipCode)){
                this.zipCode = zipCode;
            }
        }
    }


    public String getZipCode(){
        return this.zipCode;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setUsername(String username){
        this.userName = username;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public String getAccountType(){
        return this.accountType;
    }

    public void generateForm() {

    }
}

