package ui.login;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleLoginImpl implements ConsoleLoginInterface {

    private boolean userTypeNotSet = true;
    private byte userType = 0;

    @Override
    public void setUserType() {
        validateTypeInput();
    }

    @Override
    public byte getUserType() {
        return userType;
    }

    @Override
    public void signUp(){

    }

    public byte validateTypeInput(){
        giveUserTypeOptions();
        Scanner scan = new Scanner(System.in);
        while(userTypeNotSet){
            byte userInput = scan.nextByte();
            try {
                if(checkResponseInRange(
                        userInput)) {
                    userType = userInput;
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                giveUserTypeOptions();
            }
        }
    }

    public boolean checkResponseInRange(byte userInput){
        return userInput == 0 || userInput == 1 || userInput == 2;
    }


    public void giveUserTypeOptions(){
        System.out.println("• Customer Login: 0\n• Apply for Account: 1\n\n\n• Employee Login: 2");
    }
}
