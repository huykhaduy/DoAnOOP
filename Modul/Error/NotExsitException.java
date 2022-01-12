package Modul.Error;

public class NotExsitException extends Exception{
    private String mess;
    public NotExsitException(String input){
        mess = input;
    }

    @Override
    public String toString(){
        return "<i> Không tìm thấy: "+mess;
    }
}
