package Modul;

import java.util.InputMismatchException;

public class Text {
    public static void printCenter(String s, int lineWidth, char aroundCharacter) {
        int strlen = s.length();
        if (lineWidth < strlen)
            throw new StringIndexOutOfBoundsException();
        int startPosition = lineWidth / 2 - strlen / 2;
        int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
                - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
        if (startPosition == 0 || lineWidth - endPosition == 0)
            throw new InputMismatchException();
        String myStr = "%" + startPosition + "s%s%" + (lineWidth - endPosition) + "s";
        String headStr = "";
        String tailStr = "";
        for (int i = 1; i <= startPosition; i++) {
            headStr += aroundCharacter;
        }
        for (int i = 1; i <= lineWidth - endPosition; i++) {
            tailStr += aroundCharacter;
        }
        myStr = String.format(myStr, headStr, s, tailStr);
        System.out.print(myStr);
    }
    
    public static void printlnCenter(String s, int lineWidth, char aroundCharacter) {
        int strlen = s.length();
        if (lineWidth < strlen)
            throw new StringIndexOutOfBoundsException();
        int startPosition = lineWidth / 2 - strlen / 2;
        int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
                - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
        if (startPosition == 0 || lineWidth - endPosition == 0)
            throw new InputMismatchException();
        String myStr = "%" + startPosition + "s%s%" + (lineWidth - endPosition) + "s";
        String headStr = "";
        String tailStr = "";
        for (int i = 1; i <= startPosition; i++) {
            headStr += aroundCharacter;
        }
        for (int i = 1; i <= lineWidth - endPosition; i++) {
            tailStr += aroundCharacter;
        }
        myStr = String.format(myStr, headStr, s, tailStr);
        System.out.println(myStr);
        // System.out.println("Start: " + startPosition);
        // System.out.println("End: " + endPosition);
        // System.out.println("Size: " + strlen+" head: "+headStr.length()+" tail: "+tailStr.length());
        // System.out.println("Sum " + (lineWidth-endPosition));
    }

    public static String center(String s, int lineWidth, char aroundCharacter) {
        int strlen = s.length();
        if (lineWidth < strlen)
            throw new StringIndexOutOfBoundsException();
        int startPosition = lineWidth / 2 - strlen / 2;
        int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
                - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
        if (startPosition == 0 || lineWidth - endPosition == 0)
            throw new InputMismatchException();
        String myStr = "%" + startPosition + "s%s%" + (lineWidth - endPosition) + "s";
        String headStr = "";
        String tailStr = "";
        for (int i = 1; i <= startPosition; i++) {
            headStr += aroundCharacter;
        }
        for (int i = 1; i <= lineWidth - endPosition; i++) {
            tailStr += aroundCharacter;
        }
        myStr = String.format(myStr, headStr, s, tailStr);
        return myStr;
    }

    // public static String centerAt(int index, String s, int lineWidth, char aroundCharacter) {
    //     int strlen = s.length();
    //     if (lineWidth < strlen)
    //         throw new StringIndexOutOfBoundsException();
    //     int startPosition = index;
    //     int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
    //             - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
    //     if (startPosition == 0 || lineWidth - endPosition == 0)
    //         throw new InputMismatchException();
    //     String myStr = "%" + startPosition + "s%s%" + (lineWidth - endPosition) + "s";
    //     String headStr = "";
    //     String tailStr = "";
    //     for (int i = 1; i <= startPosition; i++) {
    //         headStr += aroundCharacter;
    //     }
    //     for (int i = 1; i <= lineWidth - endPosition; i++) {
    //         tailStr += aroundCharacter;
    //     }
    //     myStr = String.format(myStr, headStr, s, tailStr);
    //     return myStr;
    // }

    public static String leftAt(int index, String s, char aroundCharacter) {
        // if (lineWidth < strlen)
        //     throw new StringIndexOutOfBoundsException();
        int startPosition = index;
        // int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
        //         - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
        if (startPosition == 0)
            throw new InputMismatchException();
        String myStr = "%" + startPosition + "s%s";
        String headStr = "";
        String tailStr = "";
        for (int i = 1; i <= startPosition; i++) {
            headStr += aroundCharacter;
        }
        myStr = String.format(myStr, headStr, s, tailStr);
        return myStr;
    }
    

    public static String center(String s, int lineWidth, char aroundCharacter, int startChar) {
        int strlen = s.length();
        if (lineWidth < strlen)
            throw new StringIndexOutOfBoundsException();
        int startPosition = lineWidth / 2 - strlen / 2;
        int endPosition = (lineWidth / 2 + strlen % 2 == 0 ? (strlen / 2) : (strlen % 2) - 1)
                - (startPosition - lineWidth - (lineWidth % 2 == 0 ? 1 : 0));
        if (startPosition == 0 || lineWidth - endPosition == 0)
            throw new InputMismatchException();
        String myStr = "%" + startPosition + "s%s%" + (lineWidth - endPosition) + "s";
        String headStr = "";
        String tailStr = "";
        for (int i = 1; i <= startPosition; i++) {
            if (startPosition - startChar - i <= 0)
                headStr += aroundCharacter;
            else
                headStr += " ";
        }
        for (int i = 1; i <= lineWidth - endPosition; i++) {
            if (i >= startChar) {
                tailStr += " ";
            } else
                tailStr += aroundCharacter;
        }
        myStr = String.format(myStr, headStr, s, tailStr);
        return myStr;
    }
    
    public static String setLength(String s,int length) {
        if (s.length() > length)
            throw new StringIndexOutOfBoundsException();
        int size = s.length();
        for (int i = size; i <= length; i++) {
            s += " ";
        }
        return s;
    }
}
