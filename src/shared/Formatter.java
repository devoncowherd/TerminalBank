package shared;

public class Formatter {

    //TODO: Set pattern and matcher

    private String nameRegex = "^[a-Z]{1-25}$";
    private String socialRegex =" ^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
    private String emailRegex = "(?:^|\\s)[\\w!#$%&'*+/=?^`{|}~-](\\.?[\\w!#$%&'*+/=?^`{|}~-]+)*@\\w+[.-]?\\w*\\.[a-zA";
    private String phoneRegex = "(?:(?:\\+?1[-.\\s])?\\(?\\d{3}\\)?[-.\\s])?\\d{3}[-.\\s]\\d{4}(?:\\s(?:x|#|[eE]xt[.]?|[eE]xtension){1} ?\\d{1,7})?\\b\n"
    private String addressRegex = "\\d{1,5}(\\s[\\w-.,]*){1,6},\\s[A-Z]{2}\\s\\d{5}\\b\n";
    private String dateOfBirthRegex = "(?:0[1-9]|[12][0-9]|3[01])[-/.](?:0[1-9]|1[012])[-/.](?:19\d{2}|20[01][0-9]|2020)\b"
    private String zipRegex = "";
    private String usernameRegex = "";

    public static boolean validateName(String name) {
        return false;
    }

    public static boolean validateSocial(String social) {
        return false;
    }

    public static boolean validateDateOfBirth(String dateOfBirth) {
        return false;
    }

    public static boolean validateAddress(String address) {
        return false;
    }

    public static boolean validateZip(String zip){
        return false;
    }

    public static boolean validateEmail(String email){
        return false;
    }

    public static boolean validatePhone(String email){
        return false;
    }

    public static boolean validateUsername(String username){
        return false;
    }






}
