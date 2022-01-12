package Modul;

import Controller.Program;
import DanhSach.DanhSachHoaDon;
import DanhSach.DanhSachPhong;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class HoaDon extends Phieu implements ConsoleIO, Serializable,MyCompare<HoaDon>, Excelable {
//    private static int id=0;
    private int maHoaDon;
    private String maHoaDonStr;
    private PhieuThue phieuThue;
    private PhieuThu phieuThu;
    private boolean thanhToan = false;
    private DateTime thoiGianThanhToan;
    private BigDecimal tongTien = new BigDecimal("0");

    public HoaDon() {
        super();
        maHoaDon = Program.getDSHD().getLargestId()+1;
        setMaHoaDonStr();
        phieuThu = new PhieuThu();
        phieuThue = new PhieuThue();
    }

    public HoaDon(KhachHang maKH, Phong maPhong, NhanVien maNV) {
        super(maKH, maPhong, maNV);
        maHoaDon = Program.getDSHD().getLargestId()+1;
        setMaHoaDonStr();
        phieuThu = new PhieuThu(maKH,maPhong);
        phieuThue = new PhieuThue(maKH,maPhong);
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHoaDonStr(){
        return maHoaDonStr;
    }

    public void setMaHoaDonStr(){
        this.maHoaDonStr = "HD" + maHoaDon;
    }

    public PhieuThue getPhieuThue() {
        return phieuThue;
    }

    public void setPhieuThue(PhieuThue phieuThue) {
        this.phieuThue = phieuThue;
    }

    public PhieuThu getPhieuThu() {
        return phieuThu;
    }

    public void setPhieuThu(PhieuThu phieuThu) {
        this.phieuThu = phieuThu;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public DateTime getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(DateTime thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    // Variable of abtract

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien() {
        if (!thanhToan) tongTien = tinhTien();
    }

    @Override
    public BigDecimal tinhTien() {
        return phieuThu.getTongTien().add(phieuThue.tinhTien());
    }


    public Phong getMaPhong() {
        return maPhong;
    }

    @Override
    public void nhapThongTin() {
        setTongTien();
        System.out.print("> Xác nhận thanh toán hóa đơn "+maHoaDonStr+" (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")){
            phieuThue.traPhongNow();
            phieuThu.kiemTraUuDaiCuaPhongVip();
            thoiGianThanhToan = new DateTime();
            thoiGianThanhToan.setCurrentTime();
            thanhToan = true;
        }
    }

    @Override
    public void xuatThongTin() {
        setTongTien();
        phieuThu.kiemTraUuDaiCuaPhongVip();
        System.out.println(Text.center("THÔNG TIN HÓA ĐƠN",60,'-'));
        System.out.println(" - Mã hóa đơn: "+maHoaDonStr+"\t\tNgày tạo: "+ngayTaoPhieu.toString());
        System.out.println(" - Mã phòng: "+maPhong.getMaPhong()+"\t\tMã KH: "+maKH.getMaKHStr());
        System.out.println(" - Mã NV: "+ maNV.getMaNVStr()+"\t\tTên NV: "+maNV.getName());
        System.out.println(" - Trạng thái: "+getTrangThaiStr());
        System.out.println(" - Thời gian thanh toán: "+(thoiGianThanhToan!=null?thoiGianThanhToan.toString():""));
        System.out.println(" - Tổng tiền: "+tinhTien());
        phieuThue.xuatThongTin();
        phieuThu.xuatThongTin();
    }

    public String getTrangThaiStr(){
        return thanhToan?"Đã thanh toán":"Chưa thanh toán";
    }

    public String toString(){
        setTongTien();
        String format = String.format("|%15s|%15s|%15s|%15s|%15s|%20s|%25s|%25s|",maHoaDonStr,maPhong.getMaPhong(),maKH.getMaKHStr(),maNV.getMaNVStr(),tongTien.toString(),getTrangThaiStr(),thoiGianThanhToan!=null?thoiGianThanhToan.toString():"Trống",ngayTaoPhieu.toString());
        return format;
    }

    @Override
    public int compareTo(HoaDon o2, int type) {
        if (type == 1){
            if (maHoaDonStr.compareToIgnoreCase(o2.maHoaDonStr)>0)
                return 1;
            return -1;
        }
        if (type == 2){
            if (maKH.getMaKHStr().compareToIgnoreCase(o2.getMaKH().getMaKHStr())>0)
                return 1;
            return -1;
        }
        if (type == 3){
            if (maPhong.getMaPhong().compareToIgnoreCase(o2.getMaPhong().getMaPhong())>0)
                return 1;
            return -1;
        }
        if (type == 4){
            if (getTrangThaiStr().compareToIgnoreCase(o2.getTrangThaiStr())>0)
                return 1;
            return -1;
        }
        return 0;
    }

    @Override
    public String toExecelString() {
        return maPhong.getMaPhong()+","+maKH.getMaKHStr()+","+maNV.getMaNVStr()+","+phieuThue.getStartDate().toString()+","
                +phieuThue.getEndDate().toString()+","+phieuThue.getTotalMoney()+","+phieuThu.getTongTien()+","+tongTien.toString();
    }
}
