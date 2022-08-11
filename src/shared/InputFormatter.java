package shared;

import java.util.regex.Pattern;

public class InputFormatter {

    private String nameRegex = "^[a-Z]{1-25}$";
    private String socialRegex =" ^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
    private String emailRegex = "(?:^|\\s)[\\w!#$%&'*+/=?^`{|}~-](\\.?[\\w!#$%&'*+/=?^`{|}~-]+)*@\\w+[.-]?\\w*\\.[a-zA";
    private String addressRegex = "\\d{1,5}(\\s[\\w-.,]*){1,6},\\s[A-Z]{2}\\s\\d{5}\\b\n";
    private String dateOfBirthRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
    private String zipRegex = "(^\\d{5}$)|(^\\d{9}$)|(^\\d{5}-\\d{4}$)";
    private String usernameRegex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    public boolean validateName(String name) {
        return comparePattern(this.nameRegex, name);
    }

    public boolean validateSocial(String social) {
        return comparePattern(this.socialRegex, social);
    }

    public boolean validateDateOfBirth(String dateOfBirth) {
        return comparePattern(this.dateOfBirthRegex, dateOfBirth);
    }

    public boolean validateAddress(String address) {
        return comparePattern(this.addressRegex, address);
    }

    public boolean validateZip(String zip){
        return comparePattern(this.zipRegex,zip);
    }

    public boolean validateEmail(String email){
        return comparePattern(this.emailRegex,email);
    }

    public boolean validatePhone(String phoneNumber){
        phoneNumber.replaceAll( "[^\\d]", "" );
        if(phoneNumber.length() == 10) {
            return true;
        }
        return false;
    }

    public boolean validateUsername(String username){
        return comparePattern(this.usernameRegex,username);
    }

    public boolean comparePattern(String pattern, String input){
        return Pattern.compile(pattern).matcher(input).matches();
    }

}
