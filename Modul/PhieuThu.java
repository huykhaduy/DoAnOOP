package Modul;

import Controller.Program;
import DanhSach.DanhSachDichVu;
import DanhSach.DanhSachUuDai;
import DanhSach.MyArray;
import Modul.Error.InvalidNumberException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.InputMismatchException;

public class PhieuThu extends Phieu implements ConsoleIO, Serializable {
    private MyArray<ChiTietDichVu> dsdv = new MyArray<>();
    private BigDecimal tongTien = new BigDecimal("0");

    public PhieuThu() {
        super();
    }

    public PhieuThu(KhachHang maKH,Phong maPhong) {
        super();
        this.maKH = maKH;
        this.maPhong = maPhong;
    }

    public void themDichVu() {
        ChiTietDichVu ct = new ChiTietDichVu();
        ct.nhapThongTin();
        dsdv.push(ct);
        updateTongTien();
    }
    
    public void xoaDichVu() {
        System.out.print("> Nhập mã dịch vụ muốn xóa: ");
        String madv = sc.nextLine();
        for (int i=0;i<dsdv.getLength();i++){
            if (madv.equalsIgnoreCase(dsdv.getAt(i).getMaDV())){
                dsdv.removeAt(i);
                System.out.println("<!> Xóa dịch vụ thành công");
                break;
            }
        }
    }
    
    public void updateTongTien() {
        tongTien = tinhTien();
    }

    public BigDecimal getTongTien() {
        updateTongTien();
        return this.tongTien;
    }

    public void xemDichVu(){
        DanhSachDichVu ds = Program.getDSDV();
        ds.xuatThongTin();
    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("YÊU CẦU DỊCH VỤ",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Tạo yêu cầu",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Xem dịch vụ",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Trở về",27),' ')+"|");
            System.out.println(Text.center("",40,'-'));
            System.out.print("> Nhập lựa chọn: ");
            int value =0;
            try{
                value = sc.nextInt();
                if (value <1 || value>3) throw new InvalidNumberException("Vui lòng chọn số từ 1-3");
            } catch (InputMismatchException e){
                System.out.println("<!> Lỗi: Vui lòng nhập số !");
            } catch (InvalidNumberException e) {
                System.out.println(e.toString());
            }
            finally {
                sc.nextLine();
            }
            if (value == 1)
                themDichVu();
            if (value == 2)
                xemDichVu();
            if (value == 3) {
                break;
            }
        }
    }

    public void kiemTraUuDaiCuaPhongVip(){
        if (maPhong.getLoaiPhong()==1){
            PhongVip v = (PhongVip) maPhong;
            MyArray<ChiTietDichVu> uudaiphong = v.getUuDaiVip().getDsUuDai();
            for (int i=0;i<dsdv.getLength();i++){
                for (int j=0;j<uudaiphong.getLength();j++){
                    if (dsdv.getAt(i).getMaDV().equalsIgnoreCase(uudaiphong.getAt(j).getMaDV())){
                        BigDecimal a = uudaiphong.getAt(j).getSoLuong();
                        BigDecimal b = dsdv.getAt(i).getSoLuong();
                            if (a.compareTo(b)>=0){
                                a = a.subtract(b);
                                b = new BigDecimal("0");
                                try {
                                    uudaiphong.getAt(j).setSoLuong(a.toString());
                                    dsdv.getAt(i).setSoLuong(b.toString());
                                } catch (InvalidNumberException e) {
                                    System.out.println(e.toString());
                                }
                            }
                            else {
                                b = b.subtract(a);
                                a = new BigDecimal("0");
                                try {
                                    uudaiphong.getAt(j).setSoLuong(a.toString());
                                    dsdv.getAt(i).setSoLuong(b.toString());
                                } catch (InvalidNumberException e) {
                                    System.out.println(e.toString());
                                }
                            }
//                            System.out.println("A: "+a+" B: "+b);
                    }
                }
                dsdv.getAt(i).updateGiaTien();
            }
        }
    }
    
    @Override
    public void xuatThongTin() {
        if (dsdv.getLength()>0){
            System.out.println();
            System.out.println(Text.center("THÔNG TIN PHIẾU THU",112,'-'));
            System.out.println(" - Ngày tạo: "+ngayTaoPhieu.toString());
            System.out.print(PhieuThu.title());
            for (int i=0;i<dsdv.getLength();i++){
                System.out.println(dsdv.getAt(i));
            }
        }else {
            System.out.println("<!> Phiếu thu trống");
        }

    }

    @Override
    public BigDecimal tinhTien() {
        kiemTraUuDaiCuaPhongVip();
        BigDecimal sum = new BigDecimal("0");
        for (int i=0;i<dsdv.getLength();i++) {
            sum = sum.add(dsdv.getAt(i).getGiaTien());
        }
        return sum;
    }

    public static String title(){
        String header = Text.center("",112,'-')+"\n";
        String format = String.format("|%15s|%25s|%15s|%15s|%15s|%20s|\n","Mã DV","Tên DV","Số Lượng","Đơn Giá","Giá Tiền","Ngày tạo");
        String footer = Text.center("",112,'-')+"\n";
        String returnStr = header + format + footer;
        return returnStr;
    }


}
