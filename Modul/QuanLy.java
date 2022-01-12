package Modul;

import Controller.MenuHandle;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.DiaChi;

import java.util.InputMismatchException;

public class QuanLy extends NhanVien implements ConsoleIO{
    private int diemTinDung;

    public QuanLy(){
        super();
    }

    public QuanLy(int diemTinDung) {
        super();
        this.diemTinDung = diemTinDung;
    }

    public QuanLy(String name, String soDienThoai, String cmnd, DiaChi diaChi, DateTime ngaySinh, int diemTinDung) {
        super(name, soDienThoai, cmnd, diaChi, ngaySinh);
        this.diemTinDung = diemTinDung;
    }

    public int getDiemTinDung() {
        return diemTinDung;
    }

    public void setDiemTinDung(int diemTinDung) {
        this.diemTinDung = diemTinDung;
    }

    @Override
    public  void nhapThongTin(){
        super.nhapThongTin();
        setDiemTinDung(100);
    }

    @Override
    public void xuatThongTin(){
        super.xuatThongTin();
        System.out.println(" - Điểm tín dụng: "+diemTinDung);
    }

    @Override
    public void lamViec() {
        MenuHandle.quanLyMenu();
    }

    @Override
    public int getChucVu() {
        return 10;
        // 1 is le tan
        // 10 is quan ly
        // 100 is admin
    }

    @Override
    public void suaThuocTinhCuaChucVu() {
        while (true){
            try{
                System.out.print("> Nhập số điểm tín dụng: ");
                setDiemTinDung(sc.nextInt());
                break;
            } catch (InputMismatchException e){
                System.out.println("<!> Vui lòng nhập số nguyên!");
            } finally {
                sc.nextLine();
            }
        }
    }
}
