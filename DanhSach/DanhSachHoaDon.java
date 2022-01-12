package DanhSach;

import Controller.Program;
import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;

import java.util.InputMismatchException;

public class DanhSachHoaDon implements ChucNangDS, ConsoleIO {
    private MyArray<HoaDon> dshd = new MyArray<HoaDon>();

    public DanhSachHoaDon(){
    }

    public MyArray<HoaDon> getDshdArr() {
        return dshd;
    }

    public void setDshdArr(MyArray<HoaDon> dshd) {
        this.dshd = dshd;
    }

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin cần tìm: ");
        MyArray<HoaDon> result = modulTimKiem(sc.nextLine().toLowerCase());
        if (result != null){
            if (result.getLength()>1){
                System.out.println(DanhSachHoaDon.title());
                for (int i=0;i< result.getLength();i++){
                    System.out.println(result.getAt(i));
                }
            }
            else {
                result.getAt(0).xuatThongTin();
            }
        }
        else {
            System.out.println("<!> Không tìm thấy kết quả nào!");
        }
    }

    @Override
    public void them() {
        // Do nothing
    }

    @Override
    public void xoa() {
        //Do nothing
    }

    @Override
    public void sua() {
        //Do nothing
    }

    public HoaDon layDuLieuHD(String maHD) throws NotExsitException {
        for (int i=0;i<dshd.getLength();i++){
            HoaDon pg = dshd.getAt(i);
            if (maHD.equalsIgnoreCase(pg.getMaHoaDonStr())){
                return pg;
            }
        }
        throw new NotExsitException("mã hóa đơn "+maHD);
    }

    public HoaDon layDuLieuHDTuPhong(String maPhong) throws NotExsitException {
        for (int i=0;i<dshd.getLength();i++){
            HoaDon pg = dshd.getAt(i);
            if (maPhong.equalsIgnoreCase(pg.getMaPhong().getMaPhong())){
                return pg;
            }
        }
        throw new NotExsitException("mã hóa đơn có mã phòng "+maPhong);
    }

    public HoaDon layDuLieuHDTuPhongMoiNhat(String maPhong) throws NotExsitException {
        HoaDon hd = null;
        for (int i=0;i<dshd.getLength();i++){
            HoaDon pg = dshd.getAt(i);
            if (maPhong.equalsIgnoreCase(pg.getMaPhong().getMaPhong())){
                if (hd == null){
                    hd = pg;
                }
                else {
                    if (hd.getNgayTaoPhieu().compareDateTime(pg.getNgayTaoPhieu())<0)
                        hd = pg;
                }
            }
        }
        if (hd == null)
            throw new NotExsitException("mã hóa đơn có mã phòng "+maPhong);
        return hd;
    }

    public int getLargestId(){
        int max=0;
        for (int i=0;i<dshd.getLength();i++){
            if (dshd.getAt(i).getMaHoaDon()>max)
                max = dshd.getAt(i).getMaHoaDon();
        }
        return max;
    }

    @Override
    public void sapXep() {
        System.out.println();
        System.out.println(Text.center("SẮP XẾP DANH SÁCH HÓA ĐƠN",40,'-'));
        System.out.println(" 1. Theo mã hóa đơn (asc/desc)");
        System.out.println(" 2. Theo mã khách hàng (asc/desc)");
        System.out.println(" 3. Theo mã phòng (asc/desc)");
        System.out.println(" 4. Theo trạng thái (asc/desc)");
        System.out.println("(Lựa chọn gồm số + asc hay desc, vd: 1asc hay 2desc");
        System.out.print("> Lựa chọn của bạn: ");
        String choice = sc.nextLine();
        if (choice.length()<2){
            System.out.println("<!> Lựa chọn không hợp lệ!");
            return;
        }
        int select = choice.charAt(0)-48;
        if (select<1 || select>4){
            System.out.println("<!> Lựa chọn không hợp lệ!");
            return;
        }
        if (choice.charAt(1) != 'a' && choice.charAt(1) != 'A' && choice.charAt(1) != 'D'&& choice.charAt(1) != 'd'){
            System.out.println("<!> Lựa chọn không hợp lệ!");
            return;
        }
        if (choice.charAt(1) == 'a' || choice.charAt(1)=='A')
            sapXepTangDan(select);
        else sapXepGiamDan(select);
        xuatThongTin();
    }

    @Override
    public void xuLy(int choice) {
        switch (choice){
            case 1: thanhToan();
            break;
            case 2: timKiem();
            break;
            case 3: xuatThongTin();
            break;
            case 4: sapXep();
            break;
        }
    }

    @Override
    public void sapXepTangDan(int type) {
        MySort<HoaDon> s = new MySort<HoaDon>();
        s.sort(dshd,type,true);
    }

    @Override
    public void sapXepGiamDan(int type) {
        MySort<HoaDon> s = new MySort<HoaDon>();
        s.sort(dshd,type,false);
    }

    @Override
    public void writeToFile() {
        String name = "./Data/HoaDon.txt";
        DocGhiFile<HoaDon> ghi = new DocGhiFile<HoaDon>(dshd);
        ghi.ghiFileVaoThuMuc(name);
    }

    @Override
    public void readFromFile() {
        String name = "./Data/HoaDon.txt";
        DocGhiFile<HoaDon> doc = new DocGhiFile<HoaDon>();
        dshd = doc.docFileTuThuMuc(name);
    }

    public void thanhToan(){
        System.out.println(Text.center("THANH TOÁN HÓA ĐƠN",60,'-'));
        System.out.print("> Nhập mã phòng: ");
        DanhSachPhong dsp = Program.getDSP();
        Phong ph;
        String maPhong;
        try{
            maPhong = sc.nextLine();
            ph = dsp.layDuLieuPhong(maPhong);
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        if (!ph.getTinhTrang()){
            System.out.println("<!> Phòng chưa có ai đặt, không thể thanh toán!");
            return;
        }
        HoaDon hd;
        try {
            hd = layDuLieuHDTuPhongMoiNhat(maPhong);
        } catch (NotExsitException e) {
            System.out.println(e.toString());
            return;
        }
        if (!hd.isThanhToan()){
            System.out.println();
            System.out.println();
            hd.xuatThongTin();
            System.out.println("===========================> Tổng tiền phải thu: "+hd.getTongTien().toString()+" <===============================");
            hd.nhapThongTin();
        }

        else {
            System.out.println("<!> Hóa đơn đã được thanh toán rồi!");
        }
    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH HÓA ĐƠN",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thanh toán",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Tìm hóa đơn",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Xem danh dách",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Sắp xếp danh sách",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("5. Trở về",27),' ')+"|");
            System.out.println(Text.center("",40,'-'));
            System.out.print("> Nhập lựa chọn: ");
            int value =0;
            try{
                value = sc.nextInt();
                if (value <1 || value>5) throw new InvalidNumberException("Vui lòng chọn số từ 1-5");
            } catch (InputMismatchException e){
                System.out.println("<!> Lỗi: Vui lòng nhập số !");
            } catch (InvalidNumberException e) {
                System.out.println(e.toString());
            }
            finally {
                sc.nextLine();
            }
            if (value == 5) {
                writeToFile();
                break;
            }
            xuLy(value);
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.println(DanhSachHoaDon.title());
        for (int i=0;i< dshd.getLength();i++){
            System.out.println(dshd.getAt(i).toString());
        }
    }

    public MyArray<HoaDon> modulTimKiem(String str){
        str = str.toLowerCase();
        //Tạo mảng tạm để lưu dữ liệu
        MyArray<HoaDon> result = new MyArray<HoaDon>();
        for(int i=0;i<dshd.getLength();i++){
            HoaDon hd = dshd.getAt(i);
            if (hd.getMaHoaDonStr().toLowerCase().contains(str) || hd.getTrangThaiStr().toLowerCase().contains(str) ||
                    (hd.getThoiGianThanhToan() != null && hd.getThoiGianThanhToan().toString().contains(str)) || hd.getMaKH().getMaKHStr().toLowerCase().contains(str)
                    || hd.getMaKH().getName().toLowerCase().contains(str) || hd.getMaNV().getName().toLowerCase().contains(str)
                    || hd.getMaNV().getMaNVStr().toLowerCase().contains(str) || hd.getMaPhong().getMaPhong().toLowerCase().contains(str)) {
                result.push(hd);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }

    public static String title(){
        String header = Text.center("",154,'-');
        String format = String.format("|%15s|%15s|%15s|%15s|%15s|%20s|%25s|%25s|","Mã HD","Mã Phòng","Mã KH","Mã NV","Tổng tiền","Trạng thái","Thời gian thanh toán","Ngày tạo");
        String footer = Text.center("",154,'-');
        return header+"\n"+format+"\n"+footer;
    }
}
