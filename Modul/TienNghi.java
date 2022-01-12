package Modul;

import Controller.Program;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.Check;
import Modul.SupportModul.DateTime;

import java.io.Serializable;

public class TienNghi implements ConsoleIO, MyCompare<TienNghi>, Serializable {
    private String maTienNghi;
    private String tenTienNghi;
    private DateTime ngayTao = new DateTime();

    public TienNghi(){
        ngayTao.setCurrentTime();
    }

    public TienNghi(String maTienNghi, String tenTienNghi){
        this.tenTienNghi=tenTienNghi;
        this.maTienNghi= maTienNghi;
        ngayTao.setCurrentTime();
    }

    public String getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(String maTienNghi) throws InvalidStringException {
        if (maTienNghi.length()<3)
            throw new InvalidStringException("Mã tiện nghi phải có ít nhất 3 kí tự");
        if (Check.containsSpace(maTienNghi))
            throw new InvalidStringException("Mã tiện nghi không chứa khoản trống!");
        int count=0;
        try {
            TienNghi p = Program.getDstnThuong().layDuLieuTn(maTienNghi);
        } catch (NotExsitException e) {
            count++;
        }
        try {
            TienNghi p;
            p = Program.getDstnVip().layDuLieuTn(maTienNghi);
        } catch (NotExsitException e) {
            count++;
        }
        if (count<2){
            throw new InvalidStringException("Mã tiện nghi đã tồn tại!");
        }
        this.maTienNghi = maTienNghi;
    }

    public String getTenTienNghi() {
        return tenTienNghi;
    }

    public void setTenTienNghi(String tenTienNghi) throws InvalidStringException {
        if (tenTienNghi.length()<4)
            throw new InvalidStringException("Tên tiện nghi phải có ít nhất 4 kí tự");
        this.tenTienNghi = tenTienNghi;
    }

    @Override
    public void nhapThongTin() {
        System.out.println(Text.center("NHẬP THÔNG TIN TIỆN NGHI",40,'-'));
        int step = 1;
        do{
            try{
                if (step == 1){
                    System.out.print("> Nhập mã tiện nghi: ");
                    setMaTienNghi(sc.nextLine());
                }
                if (step == 2){
                    System.out.print("> Nhập tên tiện nghi: ");
                    setTenTienNghi(sc.nextLine());
                }
                step++;
            } catch (InvalidStringException e) {
                System.out.println(e.toString());
            }
        } while (step<3);
    }

    @Override
    public void xuatThongTin() {
        System.out.println();
        System.out.println(Text.center("THÔNG TIN TIỆN NGHI",40,'-'));
        System.out.println(" - Mã tiện nghi: "+maTienNghi);
        System.out.println(" - Tên tiện nghi: "+tenTienNghi);
        System.out.println(" - Ngày tạo: "+ngayTao.toString());
    }

    public void suaThongTin(){
        while (true){
            try {
                System.out.print("> Nhập tên tiện nghi: ");
                setTenTienNghi(sc.nextLine());
                break;
            } catch (InvalidStringException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public String toString(){
        String format = String.format("|%15s|%30s|%20s|",maTienNghi,tenTienNghi,ngayTao.toString());
        return format;
    }

    @Override
    public int compareTo(TienNghi o2, int type) {
        if (type == 1){
            if (this.maTienNghi.compareToIgnoreCase(o2.maTienNghi) >0)
                return 1;
            return -1;
        }
        if (type == 2){
            if (this.tenTienNghi.compareToIgnoreCase(o2.tenTienNghi)>0)
                return 1;
            return -1;
        }
        if (type == 3){
            if (this.ngayTao.compareDateTime(o2.ngayTao)>0)
                return 1;
            return -1;
        }
        return 0;
    }
}
