package data;

public class Candidate {

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


    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
        this.zipCode = zipCode;
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
}
