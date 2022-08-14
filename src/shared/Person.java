package shared;

public interface Person {
    String firstName = null;
    String lastName = null;
    String middleName = null;
    String social = null;
    String dateOfBirth = null;
    String address = null;
    String zipCode = null;
    String email = null;
    String phoneNumber = null;

    public void setFirstName(String firstName);
    public String getFirstName();

    public void setLastName(String lastName);
    public String getLastName();

    public void setMiddleName(String middleName);

    public String getMiddleName();

    public void setSocial(String social);

    public String getSocial();

    public void setDateOfBirth(String dateOfBirth);

    public String getDateOfBirth();

    public void setAddress(String address);

    public String getAddress();

    public void setZipCode(String zipCode);

    public String getZipCode();

    public void setEmail(String email);

    public String getEmail();

    public void setPhoneNumber(String phoneNumber);

    public String getPhoneNumber();

    public void login();
    public void logout();
}
