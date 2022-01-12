package Controller;

import DanhSach.DanhSachTaiKhoan;
import Modul.TaiKhoan;

public class LoginHandle {

    public static boolean checkLogin(){
        return Program.getTaikhoan() != null;
    }

    public static boolean login(String username, String password){
        DanhSachTaiKhoan ds = Program.getDSTK();
        TaiKhoan tk = ds.layDuLieuTk(username);
        if (tk != null){
            if (password.equals(tk.getMatKhau())){
                Program.setTaiKhoan(tk);
                return true;
            }
        }
        return false;
    }

    public static void logOut(){
        Program.setTaiKhoan(null);
    }
}
