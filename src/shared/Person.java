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

    public void setFirstName();
    public String getFirstName();

    public void setLastName();
    public String getLastName();

    public void setMiddleName();

    public String getMiddleName();

    public void setSocial();

    public String getSocial();

    public void setDateOfBirth();

    public String getDateOfBirth();

    public void setAddress();

    public String getAddress();

    public void setZipCode();

    public String getZipCode();

    public void setEmail();

    public String getEmail();

    public void setPhoneNumber();

    public String getPhoneNumber();


}
