
package Modul;

import Controller.Program;
import DanhSach.DanhSachUuDai;

public class PhongVip extends Phong {
    private DanhSachUuDai uuDaiVip;

    public PhongVip(){
        super();
        loaiPhong = 1;
        setDstn();
//        try {
//            uuDaiVip = Program.getDsudVip().clone();
//        } catch (CloneNotSupportedException e) {
//            System.out.println("<!> Phương thức clone không hỗ trợ!");
//        }
        uuDaiVip = Program.getDsudVip().returnReadFromFileValue();

    }
    public PhongVip(String maphong, int malau, int sogiuong, int songuoitoida, boolean tinhtrang){
        super(maphong,malau,sogiuong,songuoitoida,tinhtrang);
        loaiPhong = 1;
        setDstn();
//        try {
//            uuDaiVip = Program.getDsudVip().clone();
//        } catch (CloneNotSupportedException e) {
//            System.out.println("<!> Phương thức clone không hỗ trợ!");
//        }
        uuDaiVip = Program.getDsudVip().returnReadFromFileValue();
    }

    public DanhSachUuDai getUuDaiVip() {
        return uuDaiVip;
    }

    public void setUuDaiVipClone() {
//        try {
//            this.uuDaiVip = uuDaiVip.clone();
//        } catch (CloneNotSupportedException e) {
//            System.out.println("Copy giá trị thất bại!");
//        }
        uuDaiVip = Program.getDsudVip().returnReadFromFileValue();
    }

    @Override
    public void setDstn(){
        dstn = Program.getDstnVip();
    }

    @Override
    public void xuatThongTin(){
        super.xuatThongTin();
        uuDaiVip.xuatThongTin();
    }


}