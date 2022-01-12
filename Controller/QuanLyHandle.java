package Controller;

import DanhSach.*;
import Modul.BangGia;
import Modul.HoaDon;
import Modul.KhachHang;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.GhiFileExcel;
import Modul.Text;


import java.math.BigDecimal;
import java.util.Scanner;

public class QuanLyHandle {
    static Scanner sc = new Scanner(System.in);
    public static void quanLyPhong(){
        DanhSachPhong dsp = Program.getDSP();
        dsp.nhapThongTin();
    }

    public static void quanLyGiaThue(){
        BangGia bangGia = Program.getBangGia();
        while (true){
            System.out.println();
            System.out.println(Text.center("BẢNG GIÁ",60,'-'));
            System.out.println("|"+Text.leftAt(15,Text.setLength("1. Thay đổi giá phòng thường",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("2. Thay đổi giá phòng vip",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("3. Xem giá phòng thường",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("4. Xem giá phòng vip",42),' ')+"|");
            System.out.println("|"+Text.leftAt(15,Text.setLength("5. Trở về danh sách",42),' ')+"|");
            System.out.println(Text.center("",60,'-'));
            System.out.print("> Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    bangGia.thayDoiGiaTienThuong();
                    break;
                case "2":
                    bangGia.thayDoiGiaTienVip();
                    break;
                case "3":
                    bangGia.hienThiGiaTienThuong();
                    break;
                case "4":
                    bangGia.hienThiGiaTienVip();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    public static void quanLyTienNghi(){
        DanhSachTienNghi dstn = Program.getDstnThuong();
        DanhSachTienNghi dstnvip = Program.getDstnVip();
        while (true) {
            System.out.println();
            System.out.println(Text.center("DANH SÁCH TIỆN NGHI", 60, '-'));
            System.out.println("|" + Text.leftAt(15, Text.setLength("1. Danh sách tiện nghi thường", 42), ' ') + "|");
            System.out.println("|" + Text.leftAt(15, Text.setLength("2. Danh sách tiện nghi vip", 42), ' ') + "|");
            System.out.println("|" + Text.leftAt(15, Text.setLength("3. Trở về danh sách", 42), ' ') + "|");
            System.out.println(Text.center("", 60, '-'));
            System.out.print("> Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    dstn.nhapThongTin();
                    break;
                case "2":
                    dstnvip.nhapThongTin();
                    break;
                case "3":
                    dstn.writeToFile("./Data/TienNghiThuong.txt");
                    dstnvip.writeToFile("./Data/TienNghiVip.txt");
                    return;
                default:
                    System.out.println("<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    public static void quanLyDichVu(){
        DanhSachDichVu ds = Program.getDSDV();
        ds.nhapThongTin();
    }

    public static void quanLyUuDai(){
        DanhSachUuDai uudaiVip = Program.getDsudVip();
        uudaiVip.nhapThongTin();
    }

    public static void quanLyHoaDon(){
        DanhSachHoaDon dshd = Program.getDSHD();
        dshd.nhapThongTin();
    }

    public static void thongKeDanhThu(){
        while (true) {
            System.out.println();
            System.out.println(Text.center("THỐNG KÊ KHÁCH SẠN", 60, '-'));
            System.out.println("|" + Text.leftAt(15, Text.setLength("1. Thống kê đơn hàng và doanh thu", 42), ' ') + "|");
            System.out.println("|" + Text.leftAt(15, Text.setLength("2. Thống kê khách hàng đặt", 42), ' ') + "|");
            System.out.println("|" + Text.leftAt(15, Text.setLength("3. Trở về", 42), ' ') + "|");
            System.out.println(Text.center("", 60, '-'));
            System.out.print("> Nhập lựa chọn: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    thongKeDonHang();
                    break;
                case "2":
                    thongKeKhachHangDat();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    public static void thongKeDonHang(){
        DanhSachHoaDon dshd = Program.getDSHD();
        DateTime start = new DateTime();
        DateTime end = new DateTime();
        start.nhapNgay("NHẬP NGÀY BẮT ĐẦU");
        end.nhapNgay("NHẬP NGÀY KẾT THÚC");
        if (end.compareDateTime(start)<0){
            System.out.println("<!> Lỗi: Ngày kết thúc không thể nhỏ hơn ngày bắt đầu");
            return;
        }
        MyArray<HoaDon> hd = dshd.getDshdArr();
        MyArray<HoaDon> result = new MyArray<>();
        BigDecimal totalOrderMoney = new BigDecimal("0");
        for (int i=0;i< hd.getLength();i++){
            if (hd.getAt(i).getThoiGianThanhToan() != null && hd.getAt(i).getThoiGianThanhToan().compareDateTime(start)>=0 && hd.getAt(i).getThoiGianThanhToan().compareDateTime(end)<=0){
                result.push(hd.getAt(i));
                totalOrderMoney = totalOrderMoney.add(hd.getAt(i).getTongTien());
            }

        }
        if (result.getLength()==0){
            System.out.println("<!> Không có hóa đơn nào được thanh toán trong khoảng "+start.toStringNgay()+" đến "+end.toStringNgay());
            return;
        }
        DanhSachHoaDon newdshd = new DanhSachHoaDon();
        newdshd.setDshdArr(result);
        newdshd.xuatThongTin();
        System.out.println();
        System.out.println(Text.center("THỐNG KÊ", 60, '-'));
        System.out.println("|" + Text.leftAt(15, Text.setLength("Tổng số đơn hàng: "+result.getLength(), 42), ' ') + "|");
        System.out.println("|" + Text.leftAt(15, Text.setLength("Tổng doanh thu: "+totalOrderMoney, 42), ' ') + "|");
        System.out.println(Text.center("", 60, '-'));
        System.out.print("====> Bạn có muốn chi tiết thông tin ra file excel (y/n)? ");
        if (sc.nextLine().equalsIgnoreCase("y")){
            String title = "STT, Ma Phong, Ma KH, Ma NV,Ngay Thue,Ngay Tra,Tien Thue,Tien DV, Tong Tien";
            String name = "./Data/thongkehoadon.csv";
            GhiFileExcel<HoaDon> ghi = new GhiFileExcel<>(result);
            ghi.ghiRaFileExcel(name,title);
        }
    }
    public static void thongKeKhachHangDat(){
        DanhSachKhachHang dskh = Program.getDSKH();
        MyArray<KhachHang> kh = dskh.getDskhArr();
        if (kh.getLength()==0){
            System.out.println("<!> Không có khách hàng nào !");
            return;
        }
        String title = String.format("|%10s|%20s|%15s|%20s|%20s|","Mã KH","Tên KH","Số đơn hàng","Tổng tiền thuê","Ngày tham gia");
        int index =0;
        System.out.println(Text.center("",91,'-'));
        System.out.println(title);
        System.out.println(Text.center("",91,'-'));
        for (int i=0;i<kh.getLength();i++){
            System.out.println(kh.getAt(i).toThongKeStr());
            if (kh.getAt(index).getTotalUserMoney().compareTo(kh.getAt(i).getTotalUserMoney())<0){
                index = i;
            }
        }
        KhachHang bestKh = kh.getAt(index);
        System.out.println(Text.center("THỐNG KÊ", 60, '-'));
        System.out.println("|" + Text.leftAt(15, Text.setLength("Tổng số khách hàng: "+kh.getLength(), 42), ' ') + "|");
        System.out.println("|" + Text.leftAt(15, Text.setLength("Khách thuê: " +bestKh.getName()+"("+ bestKh.getMaKHStr()+")", 42), ' ') + "|");
        System.out.println("|" + Text.leftAt(15, Text.setLength("Có tổng tiền nhiều nhất: "+bestKh.getTotalUserMoney().toString(), 42), ' ') + "|");
        System.out.println(Text.center("", 60, '-'));
        System.out.print("====> Bạn có muốn chi tiết thông tin ra file excel (y/n)? ");
        if (sc.nextLine().equalsIgnoreCase("y")){
            String excelTitle = "STT, Ma KH, Ten KH, So Don Hang, Tong Tien Thue, Ngay Tham Gia";
            String name = "./Data/thongkekhachhang.csv";
            GhiFileExcel<KhachHang> ghi = new GhiFileExcel<>(kh);
            ghi.ghiRaFileExcel(name,excelTitle);
        }
    }
}
