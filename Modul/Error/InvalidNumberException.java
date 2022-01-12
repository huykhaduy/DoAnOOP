package Modul.Error;

public class InvalidNumberException extends Exception{
    private String mess;
    public InvalidNumberException(String input){
        mess = input;
    }

    @Override
    public String toString(){
        return "<i> Lỗi số: "+ mess;
    }
}
