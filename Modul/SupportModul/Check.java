package Modul.SupportModul;

import Modul.Error.InvalidNumberException;

import java.math.BigDecimal;

public class Check {
    public static boolean containsNumber(String str){
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)>='0' && str.charAt(i)<='9'){
                return true;
            }
        }
        return false;
    }

    public static boolean containsCharacter(String str){
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)<'0' || str.charAt(i)>'9'){
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpace(String str){
        for (int i=0;i<str.length();i++)
            if (str.charAt(i) == ' ')
                return true;
        return false;
    }

    public static void checkGiaTien(BigDecimal money) throws InvalidNumberException {
        if (money.compareTo(BigDecimal.ZERO)<0)
            throw new InvalidNumberException("Giá trị không được âm");
    }

}
