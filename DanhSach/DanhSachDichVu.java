package DanhSach;

import Modul.ConsoleIO;
import Modul.DichVu;
import Modul.Error.*;
import Modul.Error.InvalidNumberException;
import Modul.SupportModul.DocGhiFile;
import Modul.Text;

import java.util.InputMismatchException;

public class DanhSachDichVu implements ConsoleIO,ChucNangDS {
    private MyArray<DichVu> dsdv = new MyArray<DichVu>();

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin dịch vụ cần tìm: ");
        MyArray<DichVu> ds = modulTimKiem(sc.nextLine().toLowerCase());
        if (ds == null){
            System.out.println("<!> Không tìm thấy dịch vụ ! Vui lòng kiểm tra lại");
            return;
        }
        System.out.println(DanhSachDichVu.title());
        for (int i=0;i<ds.getLength();i++){
            System.out.println(ds.getAt(i));
        }
    }

    @Override
    public void them() {
        DichVu newDv = new DichVu();
        newDv.nhapThongTin();
        dsdv.push(newDv);
        writeToFile();
    }

    @Override
    public void xoa() {
        System.out.print("> Nhập mã dịch vụ muốn xóa: ");
        DichVu dv;
        try{
            dv = layDuLieuDV(sc.nextLine().toLowerCase());
        } catch (NotExsitException e) {
            System.out.println(e.toString());
            return;
        }
        System.out.print("> Bạn muốn xóa DV: "+dv.getTenDV() +" (y/n)? ");
        char isDel = 'n';
        try{
            isDel = sc.nextLine().charAt(0);
        } catch (InputMismatchException e){
            System.out.println("<!> Lựa chọn không hợp lệ!");
        }
        if (isDel == 'y'|| isDel =='Y'){
            dsdv.removeAt(dsdv.indexOf(dv));
            System.out.println("<!> Xóa thành công dịch vụ!");
        }
        writeToFile();
    }

    @Override
    public void sua() {
        System.out.print("> Nhập mã DV muốn sửa: ");
        DichVu dv;
        try{
            dv = layDuLieuDV(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e);
            return;
        }
        System.out.println(" Bạn đang sửa thông tin dịch vụ: "+dv.getTenDV());
        dv.suaThongTin();
        writeToFile();
    }

    @Override
    public void sapXep() {
    }

    public DichVu layDuLieuDV(String maDV) throws NotExsitException {
        for (int i=0;i<dsdv.getLength();i++){
            DichVu dv = dsdv.getAt(i);
            if (dv.getMaDV().equalsIgnoreCase(maDV)){
                return dv;
            }
        }
        throw new NotExsitException("dịch vụ "+maDV);
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

    }

    @Override
    public void sapXepGiamDan(int type) {

    }

    @Override
    public void writeToFile() {
        String name = "./Data/DichVu.txt";
        DocGhiFile<DichVu> ghi = new DocGhiFile<DichVu>(dsdv);
        ghi.ghiFileVaoThuMuc(name);
    }

    @Override
    public void readFromFile() {
        String name = "./Data/DichVu.txt";
        DocGhiFile<DichVu> ghi = new DocGhiFile<DichVu>();
        dsdv = ghi.docFileTuThuMuc(name);
    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH DỊCH VỤ",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm dịch vụ",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Sửa dịch vụ",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Tìm dịch vụ",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xóa dịch vụ",27),' ')+"|");
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
            } finally {
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
        System.out.println(DanhSachDichVu.title());
        for(int i=0;i<dsdv.getLength();i++){
            System.out.println(dsdv.getAt(i));
        }
    }

    public MyArray<DichVu> modulTimKiem(String str){
        str = str.toLowerCase();
        MyArray<DichVu> result = new MyArray<DichVu>();
        for(int i=0;i<dsdv.getLength();i++){
            DichVu dv = dsdv.getAt(i);
            if (dv.getMaDV().toLowerCase().contains(str) || dv.getTenDV().toLowerCase().contains(str) ||
                    dv.getDonGia().toString().contains(str) || dv.getDonVi().toLowerCase().contains(str)
                    || dv.getNgayTao().toString().contains(str)) {
                        result.push(dv);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }

    public static String title(){
        String header = Text.center("",96,'-');
        String format = String.format("|%15s|%25s|%15s|%15s|%20s|","Mã DV","Tên DV","Đơn giá","Đơn vị","Ngày tạo");
        String footer = Text.center("",96,'-');
        return header+"\n"+format+"\n"+footer;
    }


}
