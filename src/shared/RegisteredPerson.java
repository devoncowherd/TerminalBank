package shared;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisteredPerson implements Person {
    Scanner scan = new Scanner(System.in);
    InputFormatter formatter = new InputFormatter();
    protected String firstName = null;
    protected String lastName = null;
    protected String middleName = null;
    protected String social = null;
    protected String dateOfBirth = null;
    protected String address = null;
    protected String zipCode = null;
    protected String email = null;

    protected String username = null;

    String accountType = null;

    String phoneNumber = null;

    boolean checkedForMiddle = false;
    public void setFirstName(){
        String tempFirst;
        while(this.firstName == null){
            System.out.println("Please Enter Your Legal First Name:");
            tempFirst = scan.nextLine();
            if(formatter.validateName(tempFirst)){
                this.firstName = tempFirst;
            }
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(){
        String tempLast = "";
        while(this.lastName == null){
            System.out.println("Please Enter Your Legal Last Name:");
            tempLast = scan.nextLine();
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
            String tempMiddle;
            while(this.middleName == null) {
                System.out.println("Please Enter Your Legal Middle Name:");
                tempMiddle = scan.nextLine();
                if(formatter.validateName(tempMiddle)){
                    this.middleName = tempMiddle;
                    scan.close();
                }
            }
        } else {
            this.middleName = "";
        }
    }

    public boolean checkHasMiddleName(){
        System.out.println("Do you have a middle name?");
        byte userInput = -1;

        while(checkedForMiddle == false){
            try{
                System.out.println("0 : Yes, 1 : False");
                userInput = scan.nextByte();
                if(userInput == 0 || userInput == 1){
                    if(userInput == 0){
                        return true;
                    }
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

    public void setSocial() {
        String tempSocial;
        while(this.social == null){
            System.out.println("Please enter your Social Security Number\nFormat:XXXXXXXXX");
            tempSocial = scan.nextLine();
            if(formatter.validateSocial(tempSocial)){
                this.social = tempSocial;
            }
        }
    }

    public String getSocial() {
        return this.social;
    }

    public void setDateOfBirth(){
        String tempDateOfBirth;
        while(this.dateOfBirth == null){
            System.out.println("Please Enter your Date of Birth.\nFormat:XXXX-XX-XXXX");
            tempDateOfBirth = scan.nextLine();
            if(formatter.validateDateOfBirth(tempDateOfBirth)){
                this.dateOfBirth = dateOfBirth;
            }
        }
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setAddress(){
        String tempAddress;
        while(this.address == null){
            System.out.println("Please enter your address");
            tempAddress = scan.nextLine();
            if(//formatter.validateAddress(tempAddress)
            true//
            ){
              this.address = tempAddress;
            }
        }
    }

    public String getAddress(){
        return this.address;
    }

    public void setZipCode(){
        String tempZip;
        while(this.zipCode == null){
            System.out.println("Please Enter Your Zip.");
            tempZip = scan.nextLine();
            if(formatter.validateZip(tempZip)){
                this.zipCode = tempZip;
            }
        }
    }


    public String getZipCode(){
        return this.zipCode;
    }

    public void setEmail(){
        String tempEmail;
        while(this.email == null){
            System.out.println("Please Enter Your Email.");
            tempEmail = scan.nextLine();
            if(formatter.validateEmail(tempEmail)){
                this.email = tempEmail;
            }
        }
    }

    public String getEmail(){
        return this.email;
    }

    public void setPhoneNumber(){
        String tempPhone;
        while(this.phoneNumber == null){
            System.out.println("Please Enter Your Phone Number.");
            tempPhone = scan.nextLine();
            if(formatter.validatePhone(tempPhone)){
                this.phoneNumber = tempPhone;
            }
        }
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setUsername(){
        String tempUsername;
        while(this.username == null){
            System.out.println("Please Enter Your New Username");
            tempUsername = scan.nextLine();
            if(formatter.validateUsername(tempUsername)){
                this.username = username;
            }
        }
    }

    public String getUserName(){
        return this.username;
    }


    public void login() {

    }
    public void logout() {

    }
}
