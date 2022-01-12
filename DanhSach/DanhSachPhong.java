package DanhSach;

import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;

import java.util.InputMismatchException;

public class DanhSachPhong implements ChucNangDS, ConsoleIO {
    private MyArray<Phong> dsp = new MyArray<Phong>();

    public DanhSachPhong(){
    }

    public MyArray<Phong> getDspArr() {
        return dsp;
    }

    public void setDspArr(MyArray<Phong> dsp) {
        this.dsp = dsp;
    }

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin phòng cần tìm: ");
        MyArray<Phong> result = modulTimKiem(sc.nextLine().toLowerCase());
        if (result != null){
            if (result.getLength()>1){
                System.out.println(DanhSachPhong.title());
                for (int i=0;i< result.getLength();i++){
                    System.out.println(result.getAt(i).toString());
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

    public void hienThiPhongTrong(){
        System.out.println();
        System.out.println(Text.center("DANH SÁCH PHÒNG TRỐNG",90,' '));
        if (dsp.getLength()>0){
            System.out.println(DanhSachPhong.title());
            boolean isHasRoom = false;
            for (int i=0;i<dsp.getLength();i++){
                if (!dsp.getAt(i).getTinhTrang()){
                    System.out.println(dsp.getAt(i).toString());
                    isHasRoom = true;
                }
            }
            if (!isHasRoom){
                System.out.println(Text.center("<!> Khồng còn phòng trống !",90,' '));
            }
        }
        else {
            System.out.println("<!> Khách sạn chưa có phòng nào! Vui lòng liện hệ quản lý");
        }

    }

    public Phong layDuLieuPhong(String maPhong) throws NotExsitException {
        for (int i=0;i<dsp.getLength();i++){
            Phong kh = dsp.getAt(i);
            if (maPhong.equalsIgnoreCase(kh.getMaPhong())){
                return kh;
            }
        }
        throw new NotExsitException("mã phòng "+maPhong);
    }

    @Override
    public void them() {
        while (true){
            System.out.println();
            System.out.println(Text.center("THÊM PHÒNG",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm phòng thường",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Thêm phòng vip",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Trở về danh sách",27),' ')+"|");
            System.out.println(Text.center("",40,'-'));
            System.out.print("> Nhập lựa chọn: ");
            int choice = 0;
            try{
                choice = sc.nextInt();
                if (choice <1 || choice>3) throw new InvalidNumberException("Lựa chọn phải từ 1-3");
            }catch (InputMismatchException e){
                System.out.println("<!> Vui lòng nhập số");
            } catch (InvalidNumberException e){
                System.out.println(e);
            } finally {
                sc.nextLine();
            }
            if (choice == 1){
                Phong pt = new Phong();
                pt.nhapThongTin();
                dsp.push(pt);
            }
            if (choice == 2 ){
                Phong pv = new PhongVip();
                pv.nhapThongTin();
                dsp.push(pv);
            }
            if (choice == 3){
                break;
            }
            writeToFile();
        }
    }

    public void themPhong(Phong phong){
        dsp.push(phong);
    }

    @Override
    public void xoa() {
        System.out.print("> Nhập mã phòng muốn xóa: ");
        Phong nv;
        try{
            nv = layDuLieuPhong(sc.nextLine().toLowerCase());
        } catch (NotExsitException e) {
            System.out.println(e.toString());
            return;
        }
        System.out.print("> Bạn muốn xóa phòng: "+nv.getMaPhong() +" (y/n)? ");
        char isDel = 'n';
        try{
            isDel = sc.nextLine().charAt(0);
        } catch (InputMismatchException e){
            System.out.println("<!> Lựa chọn không hợp lệ!");
        }
        if (isDel == 'y'|| isDel =='Y'){
            dsp.removeAt(dsp.indexOf(nv));
            System.out.println("<!> Xóa thành công phòng!");
        }
        writeToFile();
    }

    @Override
    public void sua() {
        System.out.print("> Nhập mã phòng muốn sửa: ");
        Phong kh;
        try{
            kh = layDuLieuPhong(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        System.out.println(" Bạn đang sửa thông tin của: "+kh.getMaPhong());
        kh.suaThongTin();
        writeToFile();
    }

    public Phong layDuLieuPHong(String maPhong) throws NotExsitException {
        for (int i=0;i<dsp.getLength();i++){
            Phong pg = dsp.getAt(i);
            if (maPhong.equalsIgnoreCase(pg.getMaPhong())){
                return pg;
            }
        }
        throw new NotExsitException("mã phòng "+maPhong);
    }

    @Override
    public void sapXep() {
        System.out.println();
        System.out.println(Text.center("SẮP XẾP DANH SÁCH PHÒNG",40,'-'));
        System.out.println(" 1. Theo mã phòng (asc/desc)");
        System.out.println(" 2. Theo mã lầu (asc/desc)");
        System.out.println(" 3. Theo số giường (asc/desc)");
        System.out.println(" 4. Theo số người tối đa (asc/desc)");
        System.out.println(" 5. Theo loại phòng (asc/desc)");
        System.out.println(" 6. Theo trạng thái (asc/desc)");
        System.out.println("(Lựa chọn gồm số + asc hay desc, vd: 1asc hay 2desc");
        System.out.print("> Lựa chọn của bạn: ");
        String choice = sc.nextLine();
        if (choice.length()<2){
            System.out.println("<!> Lựa chọn không hợp lệ!");
            return;
        }
        int select = choice.charAt(0)-48;
        if (select<1 || select>6){
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
            case 1: them();
                break;
            case 2: timKiem();
                break;
            case 3: sua();
                break;
            case 4: xoa();
                break;
            case 5: sapXep();
                break;
            case 6: xuatThongTin();
                break;
        }
    }

    @Override
    public void sapXepTangDan(int type) {
        MySort<Phong> s = new MySort<>();
        s.sort(dsp,type,true);
    }

    @Override
    public void sapXepGiamDan(int type) {
        MySort<Phong> s = new MySort<>();
        s.sort(dsp,type,false);
    }

    @Override
    public void writeToFile() {
        String name = "./Data/Phong.txt";
        DocGhiFile<Phong> ghi = new DocGhiFile<Phong>(dsp);
        ghi.ghiFileVaoThuMuc(name);
    }

    @Override
    public void readFromFile() {
        String name = "./Data/Phong.txt";
        DocGhiFile<Phong> doc = new DocGhiFile<Phong>();
        dsp = doc.docFileTuThuMuc(name);
    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH PHÒNG",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Tìm phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Sửa phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xóa phòng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("5. Sắp xếp danh sách",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("6. Xem danh sách",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("7. Lưu và thoát",27),' ')+"|");
            System.out.println(Text.center("",40,'-'));
            System.out.print("> Nhập lựa chọn: ");
            int value =0;
            try{
                value = sc.nextInt();
                if (value <1 || value>7) throw new InvalidNumberException("Vui lòng chọn số từ 1-7");
            } catch (InputMismatchException e){
                System.out.println("<!> Lỗi: Vui lòng nhập số !");
            } catch (InvalidNumberException e) {
                System.out.println(e.toString());
            }
            finally {
                sc.nextLine();
            }
            if (value == 7) {
                writeToFile();
                break;
            }
            xuLy(value);
        }
    }

    @Override
    public void xuatThongTin() {
        System.out.println(DanhSachPhong.title());
        for (int i=0;i<dsp.getLength();i++){
            System.out.println(dsp.getAt(i));
        }
    }

    public static String title(){
        String header = Text.center("",112,'-');
        String format = String.format("|%15s|%15s|%15s|%15s|%15s|%15s|%15s|","Mã Phòng","Loại Phòng","Mã Lầu","Số Giường","Số người","Tình trạng","KH Thuê");
        String footer = Text.center("",112,'-');
        return header+"\n"+format+"\n"+footer;
    }

    public MyArray<Phong> modulTimKiem(String str){
        str = str.toLowerCase();
        //Tạo mảng tạm để lưu dữ liệu
        MyArray<Phong> result = new MyArray<Phong>();
        for(int i=0;i<dsp.getLength();i++){
            Phong pg = dsp.getAt(i);
            if (pg.getMaPhong().toLowerCase().contains(str) || Integer.toString(pg.getMaLau()).contains(str) ||
                    Integer.toString(pg.getSoGiuong()).contains(str) || Integer.toString(pg.getSoNguoiToiDa()).contains(str)
                    || pg.getTypeStr().toLowerCase().contains(str) || pg.getTinhTrangStr().toLowerCase().contains(str)) {
                result.push(pg);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }
}
