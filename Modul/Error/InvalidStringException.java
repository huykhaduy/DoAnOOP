package Modul.Error;

public class InvalidStringException extends Exception{
    private String mess;
    public InvalidStringException(String input){
        mess = input;
    }

    @Override
    public String toString(){
        return "<i> Lỗi chuỗi: "+mess;
    }

}
