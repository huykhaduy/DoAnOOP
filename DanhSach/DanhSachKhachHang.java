package DanhSach;

import Modul.KhachHang;
import Modul.NhanVien;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.DiaChi;
import Modul.Error.*;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;
import Modul.Text;

import java.io.*;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Objects;

public class DanhSachKhachHang implements ChucNangDS,Modul.ConsoleIO {
    private MyArray<KhachHang> dskh = new MyArray<KhachHang>();
    public DanhSachKhachHang(){
    }

    public MyArray<KhachHang> getDskhArr() {
        return dskh;
    }

    public void setDskhArr(MyArray<KhachHang> dskh) {
        this.dskh = dskh;
    }

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin cần tìm: ");
        MyArray<KhachHang> result = modulTimKiem(sc.nextLine().toLowerCase());
        if (result != null){
            if (result.getLength()>1){
                System.out.println(DanhSachKhachHang.title());
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
        KhachHang kh = new KhachHang();
        kh.nhapThongTin();
        dskh.push(kh);
        writeToFile();
    }

    @Override
    public void xoa() {
        System.out.print("> Nhập mã KH muốn xóa: ");
        KhachHang kh;
        try{
            kh = layDuLieuKh(sc.nextLine().toLowerCase());
        }
        catch (NotExsitException e){
            System.out.println(e);
            return;
        }
        System.out.print("> Bạn muốn xóa KH: "+kh.getName() +" (y/n)? ");
        char isDel = 'n';
        try{
            isDel = sc.nextLine().charAt(0);
        } catch (InputMismatchException e){
            System.out.println("<!> Lựa chọn không hợp lệ!");
        }
        if (isDel == 'y'|| isDel =='Y'){
            dskh.removeAt(dskh.indexOf(kh));
            System.out.println("<!> Xóa thành công người dùng!");
        }
        writeToFile();
    }

    @Override
    public void sua() {
        System.out.print("> Nhập mã KH muốn sửa: ");
        KhachHang kh;
        try{
            kh = layDuLieuKh(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e);
            return;
        }
        System.out.println(" Bạn đang sửa thông tin của: "+kh.getName());
        kh.suaThongTin();
        writeToFile();
    }

    @Override
    public void sapXep() {
        System.out.println();
        System.out.println(Text.center("SẮP XẾP DANH SÁCH KHÁCH HÀNG",40,'-'));
        System.out.println(" 1. Theo mã khách hàng (asc/desc)");
        System.out.println(" 2. Theo tên khách hàng (asc/desc)");
        System.out.println(" 3. Theo CCCD/CMND (asc/desc)");
        System.out.println(" 4. Theo số điện thoại (asc/desc)");
        System.out.println(" 5. Theo ngày sinh (asc/desc)");
        System.out.println(" 6. Theo ngày tạo (asc/desc)");
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
            sapXepTangDan(select-1);
        else sapXepGiamDan(select-1);
        xuatThongTin();
    }

    public KhachHang layDuLieuKh(String maKHStr) throws NotExsitException {
        for (int i=0;i<dskh.getLength();i++){
            KhachHang kh = dskh.getAt(i);
            if (maKHStr.equalsIgnoreCase(kh.getMaKHStr())){
                return kh;
            }
        }
        throw new NotExsitException("khách hàng "+maKHStr);
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
        MySort<KhachHang> s = new MySort<>();
        s.sort(dskh,type,true);
    }

    @Override
    public void sapXepGiamDan(int type) {
        MySort<KhachHang> s = new MySort<>();
        s.sort(dskh,type,false);
    }

    @Override
    public void writeToFile() {
        String name = "./Data/KhachHang.txt";
        DocGhiFile<KhachHang> write = new DocGhiFile<>(dskh);
        write.ghiFileVaoThuMuc(name);
//        try {
//            FileOutputStream filein = new FileOutputStream(name);
//            ObjectOutputStream fileobj = new ObjectOutputStream(filein);
//            for (int i = 0; i < dskh.getLength(); i++) {
//                KhachHang kh = dskh.getAt(i);
//                fileobj.writeObject(kh);
//            }
//            fileobj.close();
//        } catch (IOException e) {
//            System.out.println("<!> Lỗi ghi vào file " + name);
//            e.printStackTrace();
//            if (e instanceof NotSerializableException) {
//                e.printStackTrace();
//            }
//            if (e instanceof InvalidClassException) {
//                System.out.println("<!> Invalid class " + name);
//            }
//        }
    }

    @Override
    public void readFromFile() {
        String name = "./Data/KhachHang.txt";
        DocGhiFile<KhachHang> read = new DocGhiFile<>();
        MyArray<KhachHang> test = read.docFileTuThuMuc(name);
        if (test != null && test.getLength()>0)
            dskh = test;
//        KhachHang kh;
//        FileInputStream fis;
//        ObjectInputStream fileobj = null;
//        try{
//            fis = new FileInputStream(name);
//            fileobj = new ObjectInputStream(fis);
//            while (true){
//                kh = (KhachHang) fileobj.readObject();
//                dskh.push(kh);
//            }
//        }  catch (IOException | ClassNotFoundException ignored) {
//        } finally {
//            try {
//                if (fileobj != null) {
//                    fileobj.close();
//                }
//            } catch (IOException e) {
//                System.out.println("");
//            }
//        }

    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH KHÁCH HÀNG",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm khách hàng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Tìm khách hàng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Sửa khách hàng",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xóa khách hàng",27),' ')+"|");
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
                System.out.println(e);
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
        System.out.println(DanhSachKhachHang.title());
        for (int i=0;i< dskh.getLength();i++){
            System.out.println(dskh.getAt(i));
        }
    }

    public int getLargestId(){
        if (dskh.getLength()==0) return 0;
        int max = 0;
        for (int i=0;i<dskh.getLength();i++){
            if (dskh.getAt(i).getMaKH()>max){
                max=dskh.getAt(i).getMaKH();
            }
        }
        return max;
    }

    public MyArray<KhachHang> modulTimKiem(String str){
        str = str.toLowerCase();
        //Tạo mảng tạm để lưu dữ liệu
        MyArray<KhachHang> result = new MyArray<KhachHang>();
        for(int i=0;i<dskh.getLength();i++){
            KhachHang kh = dskh.getAt(i);
            if (kh.getMaKHStr().toLowerCase().contains(str) || kh.getNgayThamGia().toStringNgay().toLowerCase().contains(str) ||
                    kh.getNgaySinh().toStringNgay().toLowerCase().contains(str) || kh.getName().toLowerCase().contains(str)
                    || kh.getSoDienThoai().toLowerCase().contains(str) || kh.getCmnd().toLowerCase().contains(str) ||
                    kh.getDiaChi().toString().toLowerCase().contains(str)) {
                result.push(kh);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }

    public static String title(){
        String header = Text.center("",128,'-');
        String format = String.format("|%10s|%20s|%15s|%15s|%15s|%15s|%30s|","MaKH","Tên KH","Ngày sinh","CCCD/CMND","SDT","Ngày tạo","Địa chỉ");
        String footer = Text.center("",128,'-');
        return header+"\n"+format+"\n"+footer;
    }
}

