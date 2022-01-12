package DanhSach;

import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;

import java.io.Serializable;
import java.util.InputMismatchException;

public class DanhSachUuDai implements ConsoleIO, ChucNangDS, Cloneable, Serializable {
    private MyArray<ChiTietDichVu> dsUuDai = new MyArray<ChiTietDichVu>();

    public DanhSachUuDai(){
    }

    public DanhSachUuDai(MyArray<ChiTietDichVu> ctdv){
        dsUuDai = ctdv;
    }

    public DanhSachUuDai clone() throws CloneNotSupportedException {
        return (DanhSachUuDai) super.clone();
    }

    public MyArray<ChiTietDichVu> getDsUuDai() {
        return dsUuDai;
    }

    public void setDsUuDai(MyArray<ChiTietDichVu> dsUuDai) {
        this.dsUuDai = dsUuDai;
    }

    @Override
    public void nhapThongTin() {
        while (true){
            System.out.println();
            System.out.println(Text.center("DANH SÁCH DỊCH VỤ ƯU ĐÃI",40,'-'));
            System.out.println("|"+Text.leftAt(10,Text.setLength("1. Thêm ưu đãi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("2. Sửa ưu đãi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("3. Tìm ưu đãi",27),' ')+"|");
            System.out.println("|"+Text.leftAt(10,Text.setLength("4. Xóa ưu đãi",27),' ')+"|");
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
        if (dsUuDai.getLength()>0){
            System.out.println(Text.center("THÔNG TIN DỊCH VỤ ƯU ĐÃI",91,' '));
            System.out.println(DanhSachUuDai.title());
            for (int i=0;i<dsUuDai.getLength();i++){
                System.out.println(dsUuDai.getAt(i).getUuDaiStr());
            }
            System.out.println();
        }
        else {
            System.out.println("<!> Danh sách ưu đãi trống");
        }
    }

    @Override
    public void timKiem() {
        System.out.print("> Nhập thông tin ưu đãi cần tìm: ");
        MyArray<ChiTietDichVu> ctdv = modulTimKiem(sc.nextLine().toLowerCase());
        if (ctdv == null){
            System.out.println("Không tìm thấy thông tin ưu đãi!");
            return;
        }
        if (ctdv.getLength()>1){
            System.out.println(DanhSachUuDai.title());
            for (int i=0;i<ctdv.getLength();i++){
                System.out.println(ctdv.getAt(i).toString());
            }
        }
        else ctdv.getAt(0).xuatThongTin();
    }

    @Override
    public void them() {
        ChiTietDichVu ct = new ChiTietDichVu();
        ct.nhapThongTin();
        dsUuDai.push(ct);
        writeToFile();
    }

//    public void them(DanhSachDichVu ds){
//        ChiTietDichVu ct = new ChiTietDichVu();
//        ct.nhapThongTin(ds);
//        dsUuDai.push(ct);
//        writeToFile();
//    }

    @Override
    public void xoa() {
        System.out.print("> Nhập mã dịch vụ ưu đãi: ");
        ChiTietDichVu ct;
        try{
            ct = layDuLieuCt(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        System.out.print(" Bạn có muốn xóa dịch vụ ưu đãi: "+ct.getMaDV() +" (y/n)? ");
        char isDel = 'n';
        try{
            isDel = sc.nextLine().charAt(0);
        } catch (InputMismatchException e){
            System.out.println("<!> Lựa chọn không hợp lệ!");
        }
        if (isDel == 'y'|| isDel =='Y'){
            dsUuDai.removeAt(dsUuDai.indexOf(ct));
            System.out.println("<!> Xóa thành công ưu đãi khỏi danh sách!");
        }
        writeToFile();
    }

    @Override
    public void sua() {
        System.out.print("> Nhập mã DV muốn thay đổi: ");
        ChiTietDichVu ct;
        try{
            ct = layDuLieuCt(sc.nextLine().toLowerCase());
        } catch (NotExsitException e){
            System.out.println(e.toString());
            return;
        }
        System.out.println(" Bạn đang sửa mã DV: "+ct.getMaDV());
        ct.suaThongTin();
        writeToFile();
    }

    @Override
    public void sapXep() {
    }

    public ChiTietDichVu layDuLieuCt(String maCTDV) throws NotExsitException {
        for (int i=0;i<dsUuDai.getLength();i++){
            if (dsUuDai.getAt(i).getMaDV().equalsIgnoreCase(maCTDV)) return dsUuDai.getAt(i);
        }
        throw new NotExsitException("chi tiết dịch vụ "+maCTDV);
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
    public void writeToFile() {
        String name = "./Data/UuDaiVip.txt";
        DocGhiFile<ChiTietDichVu> ghi = new DocGhiFile<ChiTietDichVu>(dsUuDai);
        ghi.ghiFileVaoThuMuc(name);
    }

    @Override
    public void readFromFile() {
        String name = "./Data/UuDaiVip.txt";
        DocGhiFile<ChiTietDichVu> doc = new DocGhiFile<ChiTietDichVu>();
        dsUuDai = doc.docFileTuThuMuc(name);
    }

    public DanhSachUuDai returnReadFromFileValue() {
        String name = "./Data/UuDaiVip.txt";
        DocGhiFile<ChiTietDichVu> doc = new DocGhiFile<ChiTietDichVu>();
        MyArray<ChiTietDichVu> ctdv = doc.docFileTuThuMuc(name);
        return new DanhSachUuDai(ctdv);
    }

    @Override
    public void sapXepTangDan(int type) {
        MySort<ChiTietDichVu> s = new MySort<ChiTietDichVu>();
        s.sort(dsUuDai,type,true);
    }

    @Override
    public void sapXepGiamDan(int type) {
    }

    public MyArray<ChiTietDichVu> modulTimKiem(String str){
        str = str.toLowerCase();
        MyArray<ChiTietDichVu> result = new MyArray<ChiTietDichVu>();
        for(int i=0;i<dsUuDai.getLength();i++){
            ChiTietDichVu ct = dsUuDai.getAt(i);
            if (ct.getMaDV().toLowerCase().contains(str) || ct.getTenDV().toLowerCase().contains(str) ||
                    ct.getGiaTien().toString().contains(str) || ct.getSoLuong().toString().contains(str)
                    || ct.getDonVi().toLowerCase().contains(str) || ct.getNgayTao().toStringNgay().contains(str)){
                result.push(ct);
            }
        }
        if (result.getLength() == 0 ) return null;
        return result;
    }

    public static String title(){
        String header = Text.center("",91,'-');
        String format = String.format("|%15s|%25s|%10s|%15s|%20s|","Mã DV","Tên DV","Số lượt","Đơn vị","Ngày tạo");
        String footer = Text.center("",91,'-');
        return header+"\n"+format+"\n"+footer;
    }
}
