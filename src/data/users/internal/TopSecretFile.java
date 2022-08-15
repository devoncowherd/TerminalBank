package data.users.internal;

public class TopSecretFile {

    private static String dbPassword = "ErinLessThan3!";

    public static String getDbPassword(){
        return dbPassword;
    }
}
