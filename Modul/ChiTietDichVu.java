package Modul;

import Controller.Program;
import DanhSach.DanhSachDichVu;
import Modul.Error.InvalidNumberException;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.Check;
import Modul.SupportModul.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChiTietDichVu implements ConsoleIO, MyCompare<ChiTietDichVu>, Serializable {
    private DichVu dv = new DichVu();
    private BigDecimal soLuong = new BigDecimal("0");
    private BigDecimal giaTien = new BigDecimal("0");
    private DateTime ngayTao = new DateTime();

    public ChiTietDichVu() {
        ngayTao.setCurrentTime();
    }

    public ChiTietDichVu(String maDV) {
        DanhSachDichVu ds = Program.getDSDV();
        try {
            dv = ds.layDuLieuDV(maDV);
        } catch (NotExsitException e) {
            System.out.println("mã dịch vụ "+maDV);
        }
        ngayTao.setCurrentTime();
    }

    public String getMaDV(){
        return dv.maDV;
    }

    public String getTenDV(){
        return dv.tenDV;
    }

    public DateTime getNgayTao(){
        return dv.ngayTao;
    }

    public String getDonVi(){
        return dv.donVi;
    }

    public ChiTietDichVu(DanhSachDichVu ds,String maDV) throws NotExsitException {
        DichVu dv = ds.layDuLieuDV(maDV);
    }

    public DichVu getDanhSachDV(DanhSachDichVu ds,String maDV) throws NotExsitException {
        return ds.layDuLieuDV(maDV);
    }

    private void setMaDV(String maDV) throws NotExsitException {
        DanhSachDichVu ds = Program.getDSDV();
        dv = ds.layDuLieuDV(maDV);
    }

    public BigDecimal getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(String soLuong) throws InvalidNumberException {
        if (Check.containsCharacter(soLuong)) throw new InvalidNumberException("Vui lòng nhập số nguyên dương!");
        BigDecimal bg = new BigDecimal(soLuong);
        Check.checkGiaTien(bg);
        this.soLuong = bg;
    }

    public BigDecimal getGiaTien() {
        updateGiaTien();
        return this.giaTien;
    }

    public void updateGiaTien() {
        // getDonGia
        giaTien = soLuong.multiply(dv.donGia);
    }

    @Override
    public void nhapThongTin() {
        int step =1;
        do{
            try {
                if (step == 1){
                    System.out.print("> Nhập mã dịch vụ: ");
                    setMaDV(sc.nextLine());
                }
                if (step == 2){
                    System.out.print("> Nhập số lượng: ");
                    setSoLuong(sc.nextLine());
                }
                step++;
            } catch (InvalidNumberException | NotExsitException e) {
                System.out.println(e.toString());
            }
        } while (step <3);
        updateGiaTien();
    }

    public void xuatThongTin() {
        //Kiem tra dich vu co ton tai hay k
        //donGia = getDonGia();
        System.out.println(" - Mã dịch vụ: " + dv.maDV);
        System.out.println(" - Số lượng: " + soLuong);
        System.out.println(" - Đơn giá: " + dv.donGia);
        System.out.println(" - Giá tiền: " + giaTien);
        System.out.println(" - Ngày giờ: " + ngayTao.toString());
    }

    public void suaThongTin(){
        System.out.print("> Nhập số lượng: ");
        try {
            setSoLuong(sc.nextLine());
        } catch (InvalidNumberException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public String toString() {
        String format = String.format("|%15s|%25s|%15s|%15s|%15s|%20s|", dv.maDV, dv.tenDV, soLuong, dv.donGia, giaTien, ngayTao.toString());
        return format;
    }

    public String getUuDaiStr(){
        String format = String.format("|%15s|%25s|%10s|%15s|%20s|\n", dv.maDV, dv.tenDV, soLuong, dv.donVi, dv.ngayTao);
        return format;
    }

    @Override
    public int compareTo(ChiTietDichVu o2, int type) {
        if (type == 1){
            if (this.dv.maDV.compareToIgnoreCase(o2.dv.maDV) >1)
                return 1;
            return -1;
        }
        if (type == 2){
            if (this.dv.tenDV.compareToIgnoreCase(o2.dv.tenDV)>1)
                return 1;
            return -1;
        }
        if (type == 3){
            if (this.dv.donGia.compareTo(o2.dv.donGia)>1)
                return 1;
            return -1;
        }
        if (type == 4){
            if (this.dv.ngayTao.compareDateTime(o2.dv.ngayTao)>0)
                return 1;
            return -1;
        }
        if (type == 5){
            if (this.soLuong.compareTo(o2.soLuong)>0)
                return 1;
            return -1;
        }
        if (type == 6){
            if (this.giaTien.compareTo(o2.giaTien)>0)
                return 1;
            return -1;
        }
        return 0;
    }
}
