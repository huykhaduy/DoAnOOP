package Modul;

import Controller.Program;
import DanhSach.DanhSachHoaDon;
import DanhSach.DanhSachPhong;
import DanhSach.DanhSachTienNghi;
import Modul.Error.InvalidNumberException;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.Check;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;

public class Phong implements ConsoleIO, Serializable, MyCompare<Phong> {
    protected String maPhong;
    protected int maLau;
    protected int soGiuong;
    protected int soNguoiToiDa;
    protected boolean tinhTrang = false;
    protected int loaiPhong;
    protected DanhSachTienNghi dstn;

    public Phong(){
        dstn = Program.getDstnThuong();
    }

    public Phong(String maPhong,int maLau,int soGiuong,int soNguoiToiDa, boolean tinhTrang){
        this.maPhong=maPhong;
        this.soGiuong=soGiuong;
        this.soNguoiToiDa=soNguoiToiDa;
        this.maLau= maLau;
        this.tinhTrang=tinhTrang;
        dstn = Program.getDstnThuong();
    }

    public String getMaPhong() {
        return maPhong;
    }

    public int getMaLau() {
        return maLau;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public int getSoNguoiToiDa() {
        return soNguoiToiDa;
    }

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public int getLoaiPhong() {
        return loaiPhong;
    }

    public DanhSachTienNghi getDstn() {
        return dstn;
    }

    public void setMaPhong(String maPhong) throws InvalidStringException {
        if (Check.containsSpace(maPhong)){
            throw new InvalidStringException("Mã phòng không tồn tại khoảng trắng");
        }
        if (maPhong.length()<3){
            throw new InvalidStringException("Mã phòng phải có từ 3 kí tự trở lên");
        }
        try {
            Phong p = Program.getDSP().layDuLieuPhong(maPhong);
        } catch (NotExsitException e) {
            this.maPhong = maPhong;
            return;
        }
        throw new InvalidStringException("Mã phòng đã tồn tại!");
    }

    public void setMaLau(int maLau) throws InvalidNumberException {
        if (maLau<0) throw new InvalidNumberException("Mã lầu không được âm");
        this.maLau = maLau;
    }

    public void setSoGiuong(int soGiuong) throws InvalidNumberException {
        if (soGiuong<=0) throw new InvalidNumberException("Sô giường không được âm hoặc bằng 0");
        this.soGiuong = soGiuong;
    }

    public void setSoNguoiToiDa(int soNguoiToiDa) throws InvalidNumberException {
        if (soNguoiToiDa<=0) throw new InvalidNumberException("Sô người không được âm hoặc bằng 0");
        this.soNguoiToiDa = soNguoiToiDa;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
//        System.out.println(tinhTrang);
    }

    public void setLoaiPhong(int loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public void setDstn() {
        dstn = Program.getDstnThuong();
    }

    @Override
    public void nhapThongTin(){
        System.out.println(Text.center("NHẬP THÔNG TIN PHÒNG",40,'-'));
        int step = 1;
        do{
            try{
                if (step == 1){
                    System.out.print("> Nhập mã phòng: ");
                    setMaPhong(sc.nextLine());
                }
                if (step == 2){
                    System.out.print("> Nhập mã lầu: ");
                    setMaLau(sc.nextInt());
                }
                if (step == 3){
                    System.out.print("> Nhập số giường: ");
                    setSoGiuong(sc.nextInt());
                }
                if (step == 4){
                    System.out.print("> Nhập số người tối đa: ");
                    setSoNguoiToiDa(sc.nextInt());
                }
                step++;
            }
            catch (InputMismatchException e){
                System.out.println("<!> Vui lòng nhập số nguyên dương!");
                sc.nextLine();
            }
            catch (InvalidNumberException e){
                System.out.println(e.toString());
                sc.nextLine();
            } catch (InvalidStringException e) {
                System.out.println(e.toString());
            }
        } while (step<5);


    }
    @Override
    public void xuatThongTin(){
        DanhSachHoaDon ds = Program.getDSHD();
        HoaDon hd = null;
        try {
            hd = ds.layDuLieuHDTuPhongMoiNhat(maPhong);
        } catch (NotExsitException ignored) {
        }
        System.out.println(Text.center("THÔNG TIN PHÒNG",40,'-'));
        System.out.println(" - Mã phòng: "+maPhong);
        System.out.println(" - Loại phòng: "+getTypeStr());
        System.out.println(" - Tầng lầu: "+maLau);
        System.out.println(" - Số giường: "+soGiuong);
        System.out.println(" - Số người tối đa: "+soNguoiToiDa);
        System.out.println(" - Tình trạng: "+ getTinhTrangStr());
        if (tinhTrang && hd !=null){
            System.out.println(" - Mã khách thuê: "+ hd.getMaKH().getMaKHStr());
            System.out.println(" - Tên khách thuê: "+ hd.getMaKH().getName());
        }

        dstn.xuatThongTin();
    }

    public void suaThongTin(){
        System.out.println(Text.center("THAY ĐỔI THÔNG TIN PHÒNG",40,'-'));
        int step = 1;
        do{
            try{
                if (step == 1){
                    System.out.print("> Nhập mã lầu: ");
                    setMaLau(sc.nextInt());
                }
                if (step == 2){
                    System.out.print("> Nhập số giường: ");
                    setSoGiuong(sc.nextInt());
                }
                if (step == 3){
                    System.out.print("> Nhập số người tối đa: ");
                    setSoNguoiToiDa(sc.nextInt());
                }
                if (step == 4){
                    System.out.print("> Cập nhật danh sách tiện nghi (y/n)? ");
                    sc.nextLine();
                    if (sc.nextLine().equalsIgnoreCase("y")){
                        setDstn();
                    }
                }
                if (step == 5) thayDoiTinhTrang();
                step++;
            }
            catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên dương!");
                sc.nextLine();
            }
            catch (InvalidNumberException e){
                System.out.println(e.toString());
                sc.nextLine();
            }
        } while (step<6);
    }

    public void thayDoiTinhTrang(){
        System.out.println(Text.center("THAY ĐỔI THÔNG TIN PHÒNG",40,'-'));
        System.out.println(" 1. Trống");
        System.out.println(" 2. Đang sử dụng");
        System.out.print("> Lựa chọn của bạn: ");
        try{
            int choice = sc.nextInt();
            if (choice<1 || choice >2) throw new InvalidNumberException("Lựa chọn không hợp lệ!");
            if (choice == 1) setTinhTrang(false);
            else setTinhTrang(true);
        } catch (InvalidNumberException e) {
            System.out.println(e.toString());
        } catch (InputMismatchException e){
            System.out.println("<!> Vui lòng nhập số nguyên dương");
        } finally {
            sc.nextLine();
        }
    }

    public String toString(){
        String type = getTypeStr();
        DanhSachHoaDon ds = Program.getDSHD();
        HoaDon hd = null;
        String khachthue = "";
        if (tinhTrang){
            try {
                hd = ds.layDuLieuHDTuPhongMoiNhat(maPhong);
                khachthue = hd.getMaKH().getMaKHStr();
            } catch (NotExsitException ignored) {
            }
        }
        String s = String.format("|%15s|%15s|%15s|%15s|%15s|%15s|%15s|\n",maPhong,type,maLau,soGiuong,soNguoiToiDa,getTinhTrangStr(),khachthue);
        return s;
    }

    public String getTypeStr(){
        String type = "Thường";
        if (loaiPhong == 1){
            type = "VIP";
        }
        return type;
    }

    public String getTinhTrangStr(){
        return tinhTrang ?"Đầy":"Trống";
    }

    @Override
    public int compareTo(Phong o2, int type) {
        if (type == 1){
            if (maPhong.compareToIgnoreCase(o2.maPhong)>1)
                return 1;
            return -1;
        }
        if (type == 2){
            if (maLau>o2.maLau)
                return 1;
             return -1;
        }
        if (type == 3){
            if (soGiuong>o2.soGiuong)
                return 1;
            return -1;
        }
        if (type == 4){
            if (soNguoiToiDa>o2.soNguoiToiDa)
                return 1;
            return -1;
        }
        if (type == 5){
            if (loaiPhong>o2.loaiPhong)
                return 1;
            return -1;
        }
        if (type == 6){
            if (getTinhTrangStr().compareToIgnoreCase(o2.getTinhTrangStr())>0)
                return 1;
            return -1;
        }
        return 0;
    }
}