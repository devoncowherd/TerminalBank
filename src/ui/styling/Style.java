package ui.styling;

public class Style {

    public static void squiggle(String s){
        for(int i = 0; i < s.length(); ++i){
            System.out.print("~");
        }
        System.out.println("");
    }

    public static void dash(String s){
        for(int i = 0; i < s.length(); ++i){
            System.out.print("-");
        }
        System.out.println("");
    }
}
