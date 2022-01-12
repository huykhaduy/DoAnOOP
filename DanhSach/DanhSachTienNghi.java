package DanhSach;

import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;
import Modul.TienNghi;

import java.io.Serializable;
import java.util.InputMismatchException;

public class DanhSachTienNghi implements ChucNangDS, ConsoleIO, Serializable {
    private MyArray<TienNghi> dstienNghi = new MyArray<TienNghi>();

    public DanhSachTienNghi() {
    }

    public MyArray<TienNghi> getDstienNghi() {
        return dstienNghi;
    }

    public void setDstienNghi(MyArray<TienNghi> dstienNghi) {
        this.dstienNghi = dstienNghi;
    }

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin tiện nghi cần tìm: ");
        MyArray<TienNghi> tienNghi = modulTimKiem(sc.nextLine().toLowerCase());
        if (tienNghi != null){
            if (tienNghi.getLength()>1){
                System.out.println(DanhSachTienNghi.title());
                for (int i=0;i<tienNghi.getLength();i++){
                    System.out.println(tienNghi.getAt(i).toString());
                }
            }
            else {
                tienNghi.getAt(0).xuatThongTin();
            }
        }
       else
            System.out.println("Không tìm thấy thông tin ưu đãi!");
    }

    @Override
    public void them() {
        TienNghi tienich = new TienNghi();
        tienich.nhapThongTin();
        dstienNghi.push(tienich);
    }

    @Override
    public void xoa() {
        System.out.print("> Nhập mã tiện nghi muốn xóa: ");
        TienNghi tienNghi;
        try{
            tienNghi = layDuLieuTn(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        System.out.print(" Bạn có muốn xóa tiện nghi: "+ tienNghi.getMaTienNghi() +" (y/n)? ");
        char isDel = 'n';
        try{
            isDel = sc.nextLine().charAt(0);
        } catch (InputMismatchException e){
            System.out.println("<!> Lựa chọn không hợp lệ!");
        }
        if (isDel == 'y'|| isDel =='Y'){
            dstienNghi.removeAt(dstienNghi.indexOf(tienNghi));
            System.out.println("<!> Xóa thành công ưu đãi khỏi danh sách!");
        }
    }

    @Override
    public void sua() {
        System.out.print("> Nhập mã tiện nghi muốn thay đổi: ");
        TienNghi tienich;
        try{
            tienich = layDuLieuTn(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        System.out.println(" Bạn đang sửa mã tiện nghi: "+tienich.getMaTienNghi());
        tienich.suaThongTin();
    }

    public TienNghi layDuLieuTn(String maTienNghi) throws NotExsitException {
        for (int i=0;i<dstienNghi.getLength();i++){
            if (dstienNghi.getAt(i).getMaTienNghi().equalsIgnoreCase(maTienNghi)) return dstienNghi.getAt(i);
        }
        throw new NotExsitException("tiện nghi "+maTienNghi);
    }

    @Override
    public void sapXep() {
        System.out.println();
        System.out.println(Text.center("SẮP XẾP DANH SÁCH TIỆN NGHI",40,'-'));
        System.out.println(" 1. Theo mã tiện nghi (asc/desc)");
        System.out.println(" 2. Theo tên tiện nghi (asc/desc)");
        System.out.println(" 3. Theo ngày tạo (asc/desc)");
        System.out.println("(Lựa chọn gồm số + asc hay desc, vd: 1asc hay 2desc");
        System.out.print("> Lựa chọn của bạn: ");
        String choice = sc.nextLine();
        if (choice.length()<2){
            System.out.println("<!> Lựa chọn không hợp lệ!");
            return;
        }
        int select = choice.charAt(0)-48;
        if (select<1 || select>3){
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
            case 2: sua();
                break;
            case 3: timKiem();
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
        MySort<TienNghi> s = new MySort<>();
        s.sort(dstienNghi,type,true);
    }

    @Override
    public void sapXepGiamDan(int type) {
        MySort<TienNghi> s = new MySort<>();
        s.sort(dstienNghi,type,false);
    }

    @Override
    public void writeToFile() {
        String name = "./Data/TienNghi.txt";
        DocGhiFile<TienNghi> ghi = new DocGhiFile<TienNghi>(dstienNghi);
        ghi.ghiFileVaoThuMuc(name);
    }
    //Overloading
    public void writeToFile(String name) {
        DocGhiFile<TienNghi> ghi = new DocGhiFile<TienNghi>(dstienNghi);
        ghi.ghiFileVaoThuMuc(name);
    }

    @Override
    public void readFromFile() {
        String name = "./Data/TienNghi.txt";
        DocGhiFile<TienNghi> doc = new DocGhiFile<>();
        MyArray<TienNghi> test = doc.docFileTuThuMuc(name);
        if (test != null && test.getLength()>0)
            dstienNghi = test;
    }
    //Overloading
    public void readFromFile(String name) {
        DocGhiFile<TienNghi> doc = new DocGhiFile<>();
        MyArray<TienNghi> test = doc.docFileTuThuMuc(name);
        if (test != null && test.getLength()>0)
            dstienNghi = test;
    }


    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH TIỆN NGHI",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm tiện nghi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Sửa tiện nghi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Tìm tiện nghi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xóa tiện nghi",27),' ')+"|");
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
        if (dstienNghi.getLength()>0){
            System.out.println(Text.center("THÔNG TIN TIỆN NGHI",69,' '));
            System.out.println(DanhSachTienNghi.title());
            for (int i=0;i<dstienNghi.getLength();i++){
                System.out.println(dstienNghi.getAt(i).toString());
            }
            System.out.println();
        }
        else {
            System.out.println("<!> Danh sách tiện nghi trống");
        }

    }

    public MyArray<TienNghi> modulTimKiem(String str){
        str = str.toLowerCase();
        MyArray<TienNghi> result = new MyArray<>();
        for(int i=0;i<dstienNghi.getLength();i++){
            TienNghi tienich = dstienNghi.getAt(i);
            if (tienich.getMaTienNghi().toLowerCase().contains(str) || tienich.getTenTienNghi().toLowerCase().contains(str)){
                result.push(tienich);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }

    public static String title(){
        String header = Text.center("",69,'-');
        String format = String.format("|%15s|%30s|%20s|","Mã Tiện Nghi","Tên Tiện Nghi","Ngày tạo");
        String footer = Text.center("",69,'-');
        return header+"\n"+format+"\n"+footer;
    }
}
