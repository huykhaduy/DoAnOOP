package Modul;

import Modul.SupportModul.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;


public abstract class Phieu implements Serializable {
    protected KhachHang maKH;
    protected Phong maPhong;
    protected NhanVien maNV;
    protected DateTime ngayTaoPhieu = new DateTime();
 
    public Phieu() {
        ngayTaoPhieu.setCurrentTime();
    }

    public Phieu(KhachHang maKH, Phong maPhong, NhanVien maNV) {
        this.maKH = maKH;
        this.maPhong = maPhong;
        this.maNV = maNV;
        ngayTaoPhieu.setCurrentTime();
    }

    public KhachHang getMaKH() {
        return maKH;
    }

    public void setMaKH(KhachHang maKH) {
        this.maKH = maKH;
    }

    public Phong getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Phong maPhong) {
        this.maPhong = maPhong;
    }

    public NhanVien getMaNV() {
        return maNV;
    }

    public void setMaNV(NhanVien maNV) {
        this.maNV = maNV;
    }

    public DateTime getNgayTaoPhieu() {
        return ngayTaoPhieu;
    }

    public void setNgayTaoPhieu(DateTime ngayTaoPhieu) {
        this.ngayTaoPhieu = ngayTaoPhieu;
    }

    public abstract BigDecimal tinhTien();

}
