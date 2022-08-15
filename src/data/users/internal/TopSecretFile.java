package data.users.internal;

public class TopSecretFile {

    private static String dbPassword = "ErinLessThan3!";

    protected static String getDbPassword(){
        return dbPassword;
    }
}
