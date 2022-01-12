package Controller;

import DanhSach.*;
import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;

import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class LeTanHandle2 {
    static Scanner sc = new Scanner(System.in);


    //GROUP 1
    public static void thaoTacDatPhong(){
        while (true){
            System.out.println(Text.center("THAO TÁC ĐẶT PHÒNG",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Đặt phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Tìm phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Xem giá phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xem tiện nghi thường",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("5. Xem tiện nghi vip",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("6. Xem ưu đãi phòng vip",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("7. Trở về",27),' ')+"|");
            System.out.println(Text.center("",40,'-'));
            System.out.print("> Nhập lựa chọn: ");
            String choice =sc.nextLine();
            switch (choice){
                case "1":
                    datPhong();
                    break;
                case "2":
                    hienThiPhong();
                    break;
                case "3":
                    xemGiaPhong();
                    break;
                case "4":
                    xemTienNghiThuong();
                    break;
                case "5":
                    xemTienNghiVip();
                    break;
                case "6":
                    xemUuDai();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }

    }

    public static void datPhong(){
        DanhSachPhong dsp = Program.getDSP();
        BangGia gia = Program.getBangGia();
        DanhSachHoaDon dshd = Program.getDSHD();
        DanhSachKhachHang dskh = Program.getDSKH();
        DanhSachUuDai dsuudai = Program.getDsudThuong();
        DanhSachUuDai dsuudaiVip = Program.getDsudVip();
        TaiKhoan tk = Program.getTaikhoan();
        System.out.print("> Nhập mã khách hàng: ");
        KhachHang kh;
        try {
            kh = dskh.layDuLieuKh(sc.nextLine());
        } catch (NotExsitException e) {
            System.out.println(e.toString());
            System.out.print(" Bạn có muốn tạo khách hàng (y/n)? ");
            if (sc.nextLine().equalsIgnoreCase("y")){
                themKhachHang();
                int index = dskh.getDskhArr().getLength()-1;
                kh = dskh.getDskhArr().getAt(index);
            }
            else return;
        }
        Phong ph;
        while (true){
            try {
                System.out.print("> Nhập mã phòng: ");
                ph =  dsp.layDuLieuPhong(sc.nextLine());
                if (ph.getTinhTrang())
                    throw new InvalidStringException("mã phòng được thuê!");
                break;
            } catch (NotExsitException | InvalidStringException e) {
                System.out.println(e.toString());
                System.out.print(" Bạn có muốn xem phòng trống (y/n)? ");
                if (sc.nextLine().equalsIgnoreCase("y")){
                    dsp.hienThiPhongTrong();
                }
                else {
                    return;
                }
            }
        }
        ph.setTinhTrang(true);
        HoaDon newHD = new HoaDon(kh,ph,tk.getNhanVien());
        newHD.getPhieuThue().nhapThongTin();
        if (ph.getLoaiPhong()==1){
            PhongVip v = (PhongVip) ph;
            v.setUuDaiVipClone();
        }
        ph.setDstn();
        dshd.getDshdArr().push(newHD);
        LeTan lt = (LeTan) tk.getNhanVien();
        try {
            lt.setSoPhongDaDat(lt.getSoPhongDaDat()+1);
        } catch (InvalidNumberException ignored) {
        }
        Program.saveAll();
    }

    public static void themKhachHang(){
        DanhSachKhachHang dskh = Program.getDSKH();
        dskh.them();
        Program.saveAll();
    }

    public static void hienThiPhong(){
        DanhSachPhong dsp = Program.getDSP();
//        dsp.hienThiPhongTrong();
        dsp.timKiem();
    }

    public static void xemGiaPhong(){
        BangGia bangGia = Program.getBangGia();
        while (true){
            System.out.println();
            System.out.println(Text.center("BẢNG GIÁ",60,'-'));
            System.out.println("|"+Text.leftAt(15,Text.setLength("1. Xem giá phòng thường",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("2. Xem giá phòng vip",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("3. Trở về danh sách",42),' ')+"|");
            System.out.println(Text.center("",60,'-'));
            System.out.print("> Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    bangGia.hienThiGiaTienThuong();
                    break;
                case "2":
                    bangGia.hienThiGiaTienVip();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    public static void xemTienNghiThuong(){
        DanhSachTienNghi tn = Program.getDstnThuong();
        tn.xuatThongTin();
    }

    public static void xemTienNghiVip(){
        DanhSachTienNghi vip = Program.getDstnVip();
        vip.xuatThongTin();
    }

    public static void xemUuDai(){
        DanhSachUuDai uudai = Program.getDsudVip();
        uudai.xuatThongTin();
    }

    //GROUP 2
    public static void yeuCauDichVu(){
        System.out.print("> Nhập mã phòng yêu cầu: ");
        DanhSachHoaDon dshd = Program.getDSHD();
        HoaDon hd;
        try {
            hd = dshd.layDuLieuHDTuPhongMoiNhat(sc.nextLine());
        } catch (NotExsitException e) {
            System.out.println(e.toString());
            return;
        }
        hd.getPhieuThu().nhapThongTin();
        Program.saveAll();
    }

    //GROUP 3

    public static void thanhToanHoaDon(){
        DanhSachHoaDon dshd = Program.getDSHD();
        dshd.nhapThongTin();
        Program.saveAll();
    }

    //GROUP 4
    public static void thaoTacKhachHang(){
        DanhSachKhachHang dskh = Program.getDSKH();
        dskh.nhapThongTin();
        Program.saveAll();
    }
}

