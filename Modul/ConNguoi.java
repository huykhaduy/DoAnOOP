package Modul;

import Modul.SupportModul.*;
import Modul.Error.*;

import java.io.Serializable;
import java.util.InputMismatchException;

public class ConNguoi implements ConsoleIO, Serializable {
    protected String name;
    protected String soDienThoai;
    protected String cmnd;
    protected DiaChi diaChi = new DiaChi();
    protected DateTime ngaySinh = new DateTime();

    public ConNguoi() {
    }

    public ConNguoi(String name, String soDienThoai, String cmnd, DiaChi diaChi, DateTime ngaySinh) {
        this.name = name;
        this.soDienThoai = soDienThoai;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidStringException {
        if (Check.containsNumber(name)) throw new InvalidStringException("Tên không chứa số!");
        if (name.length() < 3) throw new InvalidStringException("Tên quá ngắn, tối thiểu 3 kí tự");
        this.name = name;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) throws InvalidStringException {
        if (Check.containsCharacter(soDienThoai)) throw new InvalidStringException("Số điện thoại không chứa chữ cái!");
        if (soDienThoai.length() < 9 || soDienThoai.length() > 12)
            throw new InvalidStringException("Số điện thoại phải có từ 9-12 số");
        this.soDienThoai = soDienThoai;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) throws InvalidStringException {
        if (Check.containsCharacter(cmnd)) throw new InvalidStringException("Số CMND/CCCD không chứa chữ cái!");
        if (cmnd.length() != 9 && cmnd.length() != 12)
            throw new InvalidStringException("Số CMND/CCCD phải có 9 hoặc 12 số!");
        this.cmnd = cmnd;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public DateTime getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(DateTime ngaySinh) throws InvalidStringException {
        if (ngaySinh.compareDateTime(DateTime.getTimeNow())>=0)
            throw new InvalidStringException("Ngày sinh lớn hơn ngày hiện tại!");
        this.ngaySinh = ngaySinh;
    }

    @Override
    public void nhapThongTin() {
        int step = 1;
        do {
            try {
                if (step == 1) {
                    System.out.print("> Nhập họ tên đầy đủ: ");
                    setName(sc.nextLine());
                }
                if (step == 2) {
                    System.out.print("> Nhập số CMND/CCCD: ");
                    setCmnd(sc.nextLine());
                }
                if (step == 3) {
                    DateTime ng = new DateTime();
                    ng.nhapNgaySinh();
                    setNgaySinh(ng);
                }
                if (step == 4) {
                    System.out.print("> Nhập số điện thoại: ");
                    setSoDienThoai(sc.nextLine());
                }
                if (step == 5) {
                    diaChi.NhapDiaChi();
                }
                step++;
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } while (step < 6);
    }

    @Override
    public void xuatThongTin() {
        System.out.println(" - Họ và tên: " + name);
        System.out.println(" - Số CCCD/CMND: " + cmnd);
        System.out.println(" - Ngày sinh: " + ngaySinh.getNgaySinh());
        System.out.println(" - Số điện thoại: " + soDienThoai);
        System.out.println(" - Địa chỉ: " + diaChi);
    }

    public void suaThongTin(){
        //Sửa thuộc tính chung cho nhân viên
        int step = 1;
        String temp;
        do {
            try{
                if (step == 1){
                    System.out.print("> Nhập họ tên NV: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setName(temp);
                }

                if (step == 2){
                    System.out.print("> Nhập CCCD/CMND: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setCmnd(temp);
                }

                if (step == 3){
                    System.out.print("> Nhập số điện thoại: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setSoDienThoai(temp);
                }

                if (step == 4){
                    DateTime dt = new DateTime();
                    dt.nhapNgaySinh();
                    if (dt.compareDateTime(DateTime.getTimeNow())>=0){
                        step++;
                        continue;
                    }
                    setNgaySinh(dt);
                }

                if (step ==5){
                    DiaChi dc = new DiaChi();
                    dc.NhapDiaChi();
                    if (dc.toString().equals("")){
                        step++;
                        continue;
                    }
                    setDiaChi(dc);
                }

                step++;
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } while (step<6);
    }

}
