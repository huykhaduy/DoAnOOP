package Controller;

import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachNhanVien;
import DanhSach.DanhSachTaiKhoan;

public class AdminHandle {
    public static void quanLyTaiKhoan(){
        DanhSachTaiKhoan dstk = Program.getDSTK();
        dstk.nhapThongTin();
    }

    public static void quanLyThongTinNhanVien(){
        DanhSachNhanVien dsnv = Program.getDSNV();
        dsnv.nhapThongTin();
    }

    public static void quanLyThongTinKhachHang(){
        DanhSachKhachHang dskh = Program.getDSKH();
        dskh.nhapThongTin();
    }
}
