package Controller;

import Modul.NhanVien;
import Modul.Text;

import java.util.Scanner;

public class MenuHandle {
    static Scanner sc = new Scanner(System.in);
    public static void loginMenu(){
        String username;
        String password;
        while (true){
            System.out.println();
            System.out.println(Text.center("ĐĂNG NHẬP TÀI KHOẢN",40,'-'));
            System.out.print(" > Nhập tên tài khoản: ");
            username = sc.nextLine();
            System.out.print(" > Nhập mật khẩu: ");
            password = sc.nextLine();
            if (!LoginHandle.login(username,password)){
                System.out.println("<!> Đăng nhập thất bại "+username);
            }
            else {
                System.out.println("<!> Đăng nhập thành công "+username);
                break;
            }
        }
    }

    public static void showWorkMenu(){
        NhanVien nv = Program.getTaikhoan().getNhanVien();
        nv.lamViec();
    }

//    public static void leTanMenu(){
//        while (true){
//            System.out.println();
//            System.out.format("%-30s%-80s\n","","----------------------------MENU LỄ TÂN---------------------------");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","|PHÍM","|","CHỨC NĂNG","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 1","| Đặt phòng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 2","| Tìm phòng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 3","| Xem giá phòng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 4","| Xem ưu đãi","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 5","| Yêu cầu dịch vụ","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 6","| Thanh toán hóa đơn","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 7","| Thêm khách hàng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 8","| Sửa khách hàng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 9","| Tìm khách hàng","","|");
//            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 0","| Đăng xuất","","|");
//            System.out.format("%-30s%-80s\n","","------------------------------------------------------------------");
//            System.out.format("%-30s%s","","Nhập lựa chọn của bạn: ");
//            String choice = sc.nextLine();
//            switch (choice){
//                case "0": LoginHandle.logOut();
//                    return;
//                case "1": LeTanHandle.datPhong();
//                    break;
//                case "2": LeTanHandle.hienThiPhong();
//                    break;
//                case "3": LeTanHandle.xemGiaPhong();
//                    break;
//                default:
//                    System.out.format("%-30s%s","","<!> Lựa chọn không hợp lệ!");
//                    break;
//            }
//        }
//    }

    public static void leTanMenu2(){
        while (true){
            System.out.println();
            System.out.format("%-30s%-80s\n","","----------------------------MENU LỄ TÂN---------------------------");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","|PHÍM","|","CHỨC NĂNG","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 1","| Thao tác đặt phòng","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 2","| Yêu cầu dịch vụ","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 3","| Thanh toán hóa đơn","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 4","| Thao tác khách hàng","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 0","| Đăng xuất","","|");
            System.out.format("%-30s%-80s\n","","------------------------------------------------------------------");
            System.out.format("%-30s%s","","Nhập lựa chọn của bạn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "0": LoginHandle.logOut();
                    return;
                case "1": LeTanHandle2.thaoTacDatPhong();
                    break;
                case "2": LeTanHandle2.yeuCauDichVu();
                    break;
                case "3": LeTanHandle2.thanhToanHoaDon();
                    break;
                case "4":
                    LeTanHandle2.thaoTacKhachHang();
                    break;
                default:
                    System.out.format("%-30s%s","","<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    public static void quanLyMenu(){
        while (true){
            System.out.println();
            System.out.format("%-30s%-80s\n","","------------------------------MENU QUẢN LÝ------------------------");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","|PHÍM","|","CHỨC NĂNG","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 1","| Quản lý phòng","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 2","| Quản lý giá thuê phòng","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 3","| Quản lý tiện nghi","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 4","| Quản lý dịch vụ","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 5","| Quản lý ưu đãi","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 6","| Quản lý hóa đơn","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 7","| Thống kê","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 0","| Đăng xuất","","|");
            System.out.format("%-30s%-80s\n","","------------------------------------------------------------------");
            System.out.format("%-30s%s","","Nhập lựa chọn của bạn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "0": LoginHandle.logOut();
                    return;
                case "1":
                    QuanLyHandle.quanLyPhong();
                    break;
                case "2":
                    QuanLyHandle.quanLyGiaThue();
                    break;
                case "3":
                    QuanLyHandle.quanLyTienNghi();
                    break;
                case "4":
                    QuanLyHandle.quanLyDichVu();
                    break;
                case "5":
                    QuanLyHandle.quanLyUuDai();
                    break;
                case "6":
                    QuanLyHandle.quanLyHoaDon();
                    break;
                case "7":
                    QuanLyHandle.thongKeDanhThu();
                default:
                    System.out.format("%-30s%s","","<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }

    }

    public static void adminMenu(){
        while (true){
            System.out.println();
            System.out.format("%-30s%-80s\n","","-----------------------------MENU ADMIN--------------------------");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","|PHÍM","|","CHỨC NĂNG","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 1","| Quản lý tài khoản","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 2","| Quản lý thông tin nhân viên","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 3","| Quản lý thông tin khách hàng","","|");
            System.out.format("%-30s%-5s%-30s%-30s%s\n","","| 0","| Đăng xuất","","|");
            System.out.format("%-30s%-80s\n","","------------------------------------------------------------------");
            System.out.format("%-30s%s","","Nhập lựa chọn của bạn: ");
            String choice = sc.nextLine();
            switch (choice){
                case "0": LoginHandle.logOut();
                    return;
                case "1": AdminHandle.quanLyTaiKhoan();
                    break;
                case "2": AdminHandle.quanLyThongTinNhanVien();
                    break;
                case "3": AdminHandle.quanLyThongTinKhachHang();
                    break;
                default:
                    System.out.format("%-30s%s","","<!> Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }
}
