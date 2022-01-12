package Modul;

import Controller.MenuHandle;
import Modul.Error.InvalidNumberException;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.DiaChi;

import java.util.InputMismatchException;

public class LeTan extends NhanVien implements ConsoleIO{
    private int soPhongDaDat = 0;

    public LeTan(){
        super();
    }

    public LeTan(int soPhongDaDat) {
        super();
        this.soPhongDaDat = soPhongDaDat;
    }

    public LeTan(String name, String soDienThoai, String cmnd, DiaChi diaChi, DateTime ngaySinh, int soPhongDaDat) {
        super(name, soDienThoai, cmnd, diaChi, ngaySinh);
        this.soPhongDaDat = soPhongDaDat;
    }

    public int getSoPhongDaDat() {
        return soPhongDaDat;
    }

    public void setSoPhongDaDat(int soPhongDaDat) throws InvalidNumberException {
        if (soPhongDaDat<0)
            throw new InvalidNumberException("số phòng đã đặt không được âm");
    }

//    @Override
//    public void nhapThongTin() {
//        super.nhapThongTin();
//    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println(" - Số phòng đã đặt: "+soPhongDaDat);
    }

    @Override
    public void lamViec() {
        MenuHandle.leTanMenu2();
    }

    public int getChucVu(){
        return 1;
        // 1 is le tan
        // 10 is quan ly
        // 100 is admin
    }

    @Override
    public void suaThuocTinhCuaChucVu() {
        while (true){
            try{
                System.out.print("> Nhập số phòng đã đặt: ");
                setSoPhongDaDat(sc.nextInt());
                break;
            } catch (InvalidNumberException e) {
                System.out.println(e.toString());
            } catch (InputMismatchException e){
                System.out.println("<!> Vui lòng nhập số nguyên!");
            } finally {
                sc.nextLine();
            }
        }
    }
}
