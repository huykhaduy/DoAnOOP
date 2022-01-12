//package Controller;
//
//import DanhSach.*;
//import Modul.*;
//import Modul.Error.NotExsitException;
//
//import java.util.Scanner;
//
//public class LeTanHandle {
//    static Scanner sc = new Scanner(System.in);
//    public static void datPhong(){
//        DanhSachPhong dsp = Program.getDSP();
//        BangGia gia = Program.getBangGia();
//        DanhSachHoaDon dshd = Program.getDSHD();
//        DanhSachKhachHang dskh = Program.getDSKH();
//        DanhSachUuDai dsuudai = Program.getDsudThuong();
//        DanhSachUuDai dsuudaiVip = Program.getDsudVip();
//        TaiKhoan tk = Program.getTaikhoan();
//        System.out.print("> Nhập mã khách hàng: ");
//        KhachHang kh;
//        try {
//            kh = dskh.layDuLieuKh(sc.nextLine());
//        } catch (NotExsitException e) {
//            System.out.println(e.toString());
//            System.out.print(" Bạn có muốn tạo khách hàng (y/n)? ");
//            if (sc.nextLine().equalsIgnoreCase("y")){
//                themKhachHang();
//                int index = dskh.getDskhArr().getLength()-1;
//                kh = dskh.getDskhArr().getAt(index);
//            }
//            else return;
//        }
//        Phong ph;
//        while (true){
//            try {
//                System.out.println();
//                System.out.print("> Nhập mã phòng: ");
//                ph =  dsp.layDuLieuPhong(sc.nextLine());
//                break;
//            } catch (NotExsitException e) {
//                System.out.println(e.toString());
//                System.out.print(" Bạn có muốn xem phòng trống (y/n)? ");
//                if (sc.nextLine().equalsIgnoreCase("y")){
//                    dsp.hienThiPhongTrong();
//                }
//            }
//        }
//        HoaDon newHD = new HoaDon(kh,ph,tk.getNhanVien());
//        newHD.getPhieuThue().nhapThongTin();
//        ph.setTinhTrang(true);
//        if (ph.getLoaiPhong()==1){
//            PhongVip v = (PhongVip) ph;
//            v.setUuDaiVipClone(dsuudaiVip);
//        }
//        ph.setDstn();
//        dshd.getDshdArr().push(newHD);
//    }
//
//    public static void themKhachHang(){
//        DanhSachKhachHang dskh = Program.getDSKH();
//        dskh.them();
//    }
//
//    public static void hienThiPhong(){
//        DanhSachPhong dsp = Program.getDSP();
////        dsp.hienThiPhongTrong();
//        dsp.timKiem();
//    }
//
//    public static void yeuCauDichVu(){
//        System.out.println("> Nhập mã phòng yêu cầu: ");
//        DanhSachPhong dsp = Program.getDSP();
//        Phong ph;
//        try{
//            ph = dsp.layDuLieuPhong(sc.nextLine());
//        } catch (NotExsitException e){
//            System.out.println(e.toString());
//            System.out.println("<!> Mã phòng không tồn tại");
//            return;
//        }
//        if (!ph.getTinhTrang()){
//            System.out.println("<!> Phòng trống, không thể yêu cầu dịch vụ!");
//            return;
//        }
//        DanhSachHoaDon dshd = Program.getDSHD();
//    }
//
//    public static void xemGiaPhong(){
//        BangGia bangGia = Program.getBangGia();
//        while (true){
//            System.out.println();
//            System.out.println(Text.center("BẢNG GIÁ",60,'-'));
//            System.out.println("|"+Text.leftAt(15,Text.setLength("1. Xem giá phòng thường",42),' ')+"|");
//            System.out.println("|"+Text.leftAt(15,Text.setLength("2. Xem giá phòng vip",42),' ')+"|");
//            System.out.println("|"+Text.leftAt(15,Text.setLength("3. Trở về danh sách",42),' ')+"|");
//            System.out.println(Text.center("",60,'-'));
//            System.out.print("> Nhập lựa chọn: ");
//            String choice = sc.nextLine();
//            switch (choice){
//                case "1":
//                    bangGia.hienThiGiaTienThuong();
//                    break;
//                case "2":
//                    bangGia.hienThiGiaTienVip();
//                    break;
//                case "3":
//                    return;
//                default:
//                    System.out.println("<!> Lựa chọn không hợp lệ!");
//                    break;
//            }
//        }
//    }
//
//    public static void xemUuDai(){
//
//    }
//}
