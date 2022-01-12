package Modul;

import Controller.MenuHandle;
import Modul.SupportModul.DateTime;
import Modul.SupportModul.DiaChi;

public class Admin extends NhanVien implements ConsoleIO{
    private boolean[] maQuyen = new boolean[3];
    //Co 3 ma quyen: 1 - tạo tài khoản, 120 - sửa tài khoản, 3 - xóa tài khoản

    public Admin(){
        super();
    }

    public Admin(boolean[] maQuyen) {
        super();
        this.maQuyen = maQuyen;
    }

    public Admin(String name, String soDienThoai, String cmnd, DiaChi diaChi, DateTime ngaySinh, boolean[] maQuyen) {
        super(name, soDienThoai, cmnd, diaChi, ngaySinh);
        this.maQuyen = maQuyen;
    }

    @Override
    public void lamViec() {
        MenuHandle.adminMenu();
    }

    @Override
    public int getChucVu() {
        return 100;
    }

    @Override
    public void suaThuocTinhCuaChucVu() {
        thietLapQuyen();
    }

    @Override
    public void nhapThongTin() {
        super.nhapThongTin();
        System.out.println("--------THIẾT LẬP QUYỀN ADMIN------");
        thietLapQuyen();
    }

    public void thietLapQuyen(){
        String[] storeText = new String[3];
        storeText[0] = " 1. Quyền thêm tài khoản (y/n): ";
        storeText[1] = " 2. Quyền sửa tài khoản (y/n): ";
        storeText[2] = " 3. Quyền xóa tài khoản (y/n): ";
        int index =0;
        while (index <3){
            System.out.print(storeText[index]);
            char choice = 'n';
            try{
                choice = sc.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(" <!> Lựa chọn không hợp lệ ! Vui lòng chọn lại");
                continue;
            }
            if (choice == 'y' || choice =='n' || choice == 'Y' || choice == 'N'){
                if (choice == 'y' || choice == 'Y')
                    maQuyen[index] = true;
                else maQuyen[index] = false;
                index++;
            }
            else System.out.println(" <!> Lựa chọn không hợp lệ ! Vui lòng chọn lại");
        }
    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("> Quyền hạn: Thêm("+maQuyen[0]+"), Sửa("+maQuyen[1]+"), Xóa("+maQuyen[2]+")");
    }
}
