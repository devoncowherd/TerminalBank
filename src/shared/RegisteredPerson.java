package shared;

public class RegisteredPerson implements Person{
    String firstName = null;
    String lastName = null;
    String middleName = null;
    String social = null;
    String dateOfBirth = null;
    String address = null;
    String zipCode = null;
    String email = null;
    String phoneNumber = null;
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getFirstName(){
        return this.firstName;
    }

    @Override
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getMiddleName(){
        return this.middleName;
    }
    @Override
    public void setSocial(String social) {
        this.social = social;
    }

    @Override
    public String getSocial() {
        return this.social;
    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getZipCode() {
        return this.zipCode;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
