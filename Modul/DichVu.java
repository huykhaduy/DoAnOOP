package Modul;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

import Controller.Program;
import Modul.Error.InvalidNumberException;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.Check;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.DiaChi;

public class DichVu implements ConsoleIO, Serializable, MyCompare<DichVu>{
    protected String maDV;
    protected String tenDV;
    protected BigDecimal donGia = new BigDecimal("0");
    protected String donVi;
    protected DateTime ngayTao = new DateTime();

    public DichVu() {
        ngayTao.setCurrentTime();
    }

    public DichVu(String maDV, String tenDV, BigDecimal donGia, String donVi) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.donVi = donVi;
        ngayTao.setCurrentTime();
    }

    public String getMaDV() {
        return this.maDV;
    }

    public void setMaDV(String maDV) throws InvalidStringException {
        if (Check.containsSpace(maDV)){
            throw new InvalidStringException("Mã dịch vụ không tồn tại khoảng trắng");
        }
        if (maDV.length()<3){
            throw new InvalidStringException("Mã dịch vụ phải có từ 3 kí tự trở lên");
        }
        try {
            DichVu p = Program.getDSDV().layDuLieuDV(maDV);
        } catch (NotExsitException e) {
            this.maDV = maDV;
            return;
        }
        throw new InvalidStringException("Mã dịch vụ "+maDV+" đã tồn tại");
    }

    public String getTenDV() {
        return this.tenDV;
    }

    public void setTenDV(String tenDV) throws InvalidStringException {
        if (tenDV.length() <4){
            throw new InvalidStringException("Tên dịch vụ phải từ 4 kí tự trở lên!");
        }
        if (Check.containsNumber(tenDV)){
            throw new InvalidStringException("Tên dịch vụ không chứa số!");
        }
        this.tenDV = tenDV;
    }

    public BigDecimal getDonGia() {
        return this.donGia;
    }

    public void setDonGia(String donGiaStr) throws InvalidNumberException {
        if (Check.containsCharacter(donGiaStr))
            throw new InvalidNumberException("Vui lòng nhập giá tiền không chứa kí tự!");
        BigDecimal bd = new BigDecimal(donGiaStr);
        Check.checkGiaTien(bd);
        donGia = bd;
    }

    public String getDonVi() {
        return this.donVi;
    }

    public void setDonVi(String donVi) throws InvalidStringException {
        if (donVi.length() <2){
            throw new InvalidStringException("Đơn vị phải từ 2 kí tự trở lên!");
        }
        if (Check.containsNumber(donVi)){
            throw new InvalidStringException("Đơn vị không chứa số!");
        }
        this.donVi = donVi;
    }

    public DateTime getNgayTao() {
        return ngayTao;
    }

    @Override
    public void nhapThongTin(){
        int step = 1;
        System.out.println(Text.center("NHẬP THÔNG TIN DỊCH VỤ",40,'-'));
        do{
            try{
                if (step == 1){
                    System.out.print("> Nhập mã dịch vụ: ");
                    setMaDV(sc.nextLine());
                }
                if (step == 2){
                    System.out.print("> Nhập tên dịch vụ: ");
                    setTenDV(sc.nextLine());
                }
                if (step == 3){
                    System.out.print("> Nhập đơn giá: ");
                    setDonGia(sc.nextLine());
                }
                if (step == 4){
                    System.out.print("> Nhập đơn vị: ");
                    setDonVi(sc.nextLine());
                }
                step++;
            } catch (InvalidStringException e){
                System.out.println(e.toString());
            } catch (InvalidNumberException e){
                System.out.println(e.toString());
            }
        } while (step< 5);
    }

    @Override
    public void xuatThongTin() {
        System.out.println(" - Mã dịch vụ: " + maDV);
        System.out.println(" - Tên dịch vụ: " + tenDV);
        System.out.println(" - Đơn giá: " + donGia);
        System.out.println(" - Đơn vị: " + donVi);
        System.out.println(" - Ngày tạo: " + ngayTao);
    }

    public void suaThongTin(){
        int step = 1;
        String temp;
        do {
            try{
                if (step == 1){
                    System.out.print("> Nhập tên DV: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setTenDV(temp);
                }

                if (step == 2){
                    System.out.print("> Nhập đơn giá: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setDonGia(temp);
                }

                if (step == 3){
                    System.out.print("> Nhập đơn vị: ");
                    temp = sc.nextLine();
                    if (temp.equals("")){
                        step++;
                        continue;
                    }
                    setDonVi(temp);
                }

                step++;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } while (step<4);
    }

    @Override
    public String toString() {
        String format = String.format("|%15s|%25s|%15s|%15s|%20s|", maDV, tenDV, donGia, donVi, ngayTao);
        return format;
    }

    @Override
    public int compareTo(DichVu o2, int type) {
        if (type == 1){
            if (this.maDV.compareToIgnoreCase(o2.maDV) >1)
                return 1;
            return -1;
        }
        if (type == 2){
            if (this.tenDV.compareToIgnoreCase(o2.tenDV)>1)
                return 1;
            return -1;
        }
        if (type == 3){
            if (this.donGia.compareTo(o2.donGia)>1)
                return 1;
            return -1;
        }
        if (type == 4){
            if (this.ngayTao.compareDateTime(o2.ngayTao)>0)
                return 1;
            return -1;
        }
        return 0;
    }
}

    