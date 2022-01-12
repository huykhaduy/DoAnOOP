package Modul;

import java.util.Scanner;

public interface ConsoleIO {
    Scanner sc = new Scanner(System.in);
    //Get user input to class by Scanner
    void nhapThongTin();
    //Show user input to class by printf
    void xuatThongTin();
}
