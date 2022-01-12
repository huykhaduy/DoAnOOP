package Modul.SupportModul;

import java.io.Serializable;
import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import Modul.Text;

public class DateTime implements Serializable {
    static private Scanner sc = new Scanner(System.in);
    // static private LocalDateTime timeNow = LocalDateTime.now();
    private LocalDateTime myDateTime;
    private int dd;
    private int mm;
    private int yy;
    private int minutes;
    private int hours;
    
    public DateTime() {
    }

//    public LocalDateTime getMyDateTime() {
//        return myDateTime;
//    }
//
//    public void setMyDateTime(LocalDateTime myDateTime) {
//        this.myDateTime = myDateTime;
//    }

    public void nhapNgayGio() {
        System.out.println("\n--------------------------NHẬP NGÀY VÀ GIỜ-------------------");
        System.out.println("(Nếu không nhập hoặc nhập sai sẽ tựng dộng lấy thời gian hiện tại)");
        System.out.print("> Nhập ngày: ");
        setDdStr(sc.nextLine());
        System.out.print("> Nhập tháng: ");
        setMmStr(sc.nextLine());
        System.out.print("> Nhập năm: ");
        setYyStr(sc.nextLine());
        System.out.print("> Nhập giờ: ");
        setHoursStr(sc.nextLine());
        System.out.print("> Nhập phút: ");
        setMinutesStr(sc.nextLine());
        kiemTraThang2();
        myDateTime = LocalDateTime.of(yy, mm, dd, hours, minutes);
    }

    public void nhapNgayGio(String s) {
        System.out.printf("\n"+Text.center(s,80,'-')+"\n");
        System.out.println(Text.center("Nếu không nhập hoặc nhập sai sẽ tựng dộng lấy thời gian hiện tại",80,' '));
        System.out.print("> Nhập ngày: ");
        setDdStr(sc.nextLine());
        System.out.print("> Nhập tháng: ");
        setMmStr(sc.nextLine());
        System.out.print("> Nhập năm: ");
        setYyStr(sc.nextLine());
        System.out.print("> Nhập giờ: ");
        setHoursStr(sc.nextLine());
        System.out.print("> Nhập phút: ");
        setMinutesStr(sc.nextLine());
        kiemTraThang2();
        myDateTime = LocalDateTime.of(yy, mm, dd, hours, minutes);
    }

    public void nhapNgay() {
        System.out.println("\n--------------------------NHẬP NGÀY VÀ GIỜ-------------------");
        System.out.println("(Nếu không nhập hoặc nhập sai sẽ tựng dộng lấy thời gian hiện tại)");
        System.out.print("> Nhập ngày: ");
        setDdStr(sc.nextLine());
        System.out.print("> Nhập tháng: ");
        setMmStr(sc.nextLine());
        System.out.print("> Nhập năm: ");
        setYyStr(sc.nextLine());
        kiemTraThang2();
        myDateTime = LocalDateTime.of(yy, mm, dd, hours, minutes);
    }

    public void nhapNgay(String s) {
        System.out.printf("\n"+Text.center(s,80,'-')+"\n");
        System.out.println(Text.center("Nếu không nhập hoặc nhập sai sẽ tựng dộng lấy thời gian hiện tại",80,' '));
        System.out.print("> Nhập ngày: ");
        setDdStr(sc.nextLine());
        System.out.print("> Nhập tháng: ");
        setMmStr(sc.nextLine());
        System.out.print("> Nhập năm: ");
        setYyStr(sc.nextLine());
        kiemTraThang2();
        myDateTime = LocalDateTime.of(yy, mm, dd, hours, minutes);
    }

    public void nhapNgaySinh(){
        System.out.print("> Nhập ngày sinh: ");
        setDdStr(sc.nextLine());
        System.out.print("> Nhập tháng sinh: ");
        setMmStr(sc.nextLine());
        System.out.print("> Nhập năm sinh: ");
        setYyStr(sc.nextLine());
        kiemTraThang2();
        myDateTime = LocalDateTime.of(yy, mm, dd, hours, minutes);
    }

    public String getNgaySinh(){
        return dd+"/"+mm+"/"+yy;
    }

    public void xuatNgay() {
        System.out.printf("Ngày: %02d/%02d/%04d\n", dd, mm, yy);
    }

    public void xuatNgayVaGio() {
        System.out.printf("Ngày: %02d/%02d/%04d\n", dd, mm, yy);
        System.out.printf("Giờ: %02d:%02d\n", hours, minutes);
    }
    
    public void setCurrentTime() {
        myDateTime = LocalDateTime.now();
        update();
    }
    
    public static DateTime getTimeNow(){
        DateTime dateTime = new DateTime();
        dateTime.setCurrentTime();
        return dateTime;
    }

    @Override
    public String toString() {
        if (myDateTime == null)
            return "";
        String myReturn = String.format("%02d:%02d %02d/%02d/%04d", hours, minutes, dd, mm, yy);
        return myReturn;
    }

    public String toStringNgay() {
        if (myDateTime == null)
            return "";
        String format = String.format("%02d/%02d/%04d",dd,mm,yy);
        return format;
    }

    public void themNgay(int soNgay) {
        myDateTime = myDateTime.plusDays(soNgay);
        update();
    }

    public void themGioPhut(int soGio, int soPhut) {
        myDateTime = myDateTime.plusHours(soGio);
        myDateTime = myDateTime.plusMinutes(soPhut);
        update();
    }

    // public long getDaysFrom(DateTime fromDateTime) {
    //     long dateBetween = ChronoUnit.DAYS.between(fromDateTime.myDateTime,this.myDateTime);
    //     return dateBetween;
    // }

    public int[] getDayTimeFrom(DateTime myOldTime) {
        int soNgay = 0;
        if (this.yy != myOldTime.yy) {
            soNgay += demNgayQuaNam(myOldTime.yy);
        }
        soNgay += myDateTime.getDayOfYear() - myOldTime.myDateTime.getDayOfYear();
        int soGio = 0;
        if (myDateTime.getHour() <= myOldTime.myDateTime.getHour()) {
            soNgay--;
            soGio = 24;
        }
        LocalDateTime tempDate = myOldTime.myDateTime.plusDays(soNgay);
        soGio += myDateTime.getHour() - tempDate.getHour();
        int soPhut = 0;
        if (myDateTime.getMinute() < myOldTime.myDateTime.getMinute()) {
            soGio--;
            soPhut = 60;
        }
        tempDate = tempDate.plusHours(soGio);
        soPhut += myDateTime.getMinute() - tempDate.getMinute();
        if (soPhut >= 60) {
            soGio += soPhut / 60;
            soPhut = soPhut % 60;
        }

        if (soGio >= 24) {
            soNgay += soGio / 24;
            soGio = soGio % 24;
        }

        int[] arr = new int[3];
        arr[0] = soNgay;
        arr[1] = soGio;
        arr[2] = soPhut;
        return arr;
    }
    
    public int compareDateTime(DateTime oldTime){
        return myDateTime.compareTo(oldTime.myDateTime);
    }

    private void update() {
        this.dd = myDateTime.getDayOfMonth();
        this.mm = myDateTime.getMonthValue();
        this.yy = myDateTime.getYear();
        this.hours = myDateTime.getHour();
        this.minutes = myDateTime.getMinute();
    }


    public int getDay() {
        return this.dd;
    }

    public int getMonth() {
        return this.mm;
    }

    public int getYy() {
        return this.yy;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getHours() {
        return this.hours;
    }

    public DateTime copy() {
        DateTime dt = new DateTime();
        dt.dd = dd;
        dt.mm = mm;
        dt.yy = yy;
        dt.hours = hours;
        dt.minutes = minutes;
        dt.myDateTime = myDateTime;
        return dt;
    }

    private void setDd(int dd) {
        if (dd >= 1 && dd <= 31)
            this.dd = dd;
        else {
            this.dd = LocalDateTime.now().getDayOfMonth(); // gán dd bằng giá trị timeNow.getDayOfMonth()
            System.out.println(" <!> Tự động lấy ngày hôm nay: " + LocalDateTime.now().getDayOfMonth());
            // System.out.println(dd); // không ra giá trị của timeNow.getDayOfMonth(); mà lại ra giá trị cũ
        }
    }

    private void setDdStr(String dd) {
        try {
            setDd(Integer.parseInt(dd));
        } catch (NumberFormatException e) {
            setDd(100);
        }
    }

    private void setMm(int mm) {
        if (mm >= 1 && mm <= 12)
            this.mm = mm;
        else {
            this.mm = LocalDateTime.now().getMonthValue();
            System.out.println(" <!> Tự động lấy tháng này: " + LocalDateTime.now().getMonthValue());
        }
    }

    private void setMmStr(String mm) {
        try {
            setMm(Integer.parseInt(mm));
        } catch (NumberFormatException e) {
            setMm(100);
        }
    }

   

    private void setYy(int yy) {
        if (yy >= 1900 && yy <= 2200)
            this.yy = yy;
        else {
            this.yy = LocalDateTime.now().getYear();
            System.out.println(" <!> Tự động lấy năm này: " + LocalDateTime.now().getYear());
        }
    }

    private void setYyStr(String yy) {
        try {
            setYy(Integer.parseInt(yy));
        } catch (NumberFormatException e) {
            setYy(100);
        }
    }

    private void setMinutes(int minutes) {
        if (minutes >= 0 && minutes <= 59)
            this.minutes = minutes;
        else {
            this.minutes = LocalDateTime.now().getMinute();
            System.out.println(" <!> Tự động lấy phút hiện tại: " + LocalDateTime.now().getMinute());
        }
    }

    private void setMinutesStr(String minutesStr) {
        try {
            setMinutes(Integer.parseInt(minutesStr));
        } catch (NumberFormatException e) {
            setMinutes(100);
        }
    }

    private void setHours(int hours) {
        if (hours >= 0 && hours <= 23)
            this.hours = hours;
        else {
            this.hours = LocalDateTime.now().getHour();
            System.out.println(" <!> Tự động lấy giờ hiện tại: " + LocalDateTime.now().getHour());
        }
    }

    private void setHoursStr(String hoursStr) {
        try {
            setHours(Integer.parseInt(hoursStr));
        } catch (NumberFormatException e) {
            setHours(100);
        }
    }

    private void kiemTraThang2() {
        if (mm == 2) {
            if (yy % 4 != 0) {
                if (dd == 29) {
                    dd--;
                    System.out.println(" <!> Năm "+yy+" có tháng 2 28 ngày, tự động lấy ngày : " + dd);
                }
                    
            }
        }
    }

    private int soNgayTrongNam(int year) {
        if (year % 4 == 0)
            return 366;
        return 365;
    }

    private int demNgayQuaNam(int yy) {
        int sum = 0;
        for (int i = yy; i < this.yy; i++) {
            sum += soNgayTrongNam(i);
        }
        return sum;
    }
    
}