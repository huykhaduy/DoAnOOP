package Modul.SupportModul;

import java.io.Serializable;
import java.util.Scanner;

public class DiaChi implements Serializable {
    static Scanner sc = new Scanner(System.in);
    private String soNha;
    private String tenDuong;
    private String tenPhuongXa;
    private String tenQuanHuyen;
    private String tenTinhThanh;

    public DiaChi() {
    }

    public DiaChi(String soNha, String tenDuong, String tenPhuongXa, String tenQuanHuyen, String tenTinhThanh) {
        this.soNha = soNha;
        this.tenDuong = tenDuong;
        this.tenPhuongXa = tenPhuongXa;
        this.tenQuanHuyen = tenQuanHuyen;
        this.tenTinhThanh = tenTinhThanh;
    }

    public void NhapDiaChi() {
        System.out.println("\n       <NHẬP ĐỊA CHỈ>     ");
        System.out.print("> Nhập số nhà: ");
        soNha = sc.nextLine();
        System.out.print("> Nhập tên đường: ");
        tenDuong = sc.nextLine();
        System.out.print("> Nhập tên phường/xã: ");
        tenPhuongXa = sc.nextLine();
        System.out.print("> Nhập tên quận/huyện: ");
        tenQuanHuyen = sc.nextLine();
        System.out.print("> Nhập tên tỉnh/thành: ");
        tenTinhThanh = sc.nextLine();
    }

    public void xuatDiaChi() {
        System.out.println("Dia chi: " + toString());
    }


    public String getSoNha() {
        return this.soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getTenDuong() {
        return this.tenDuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }

    public String getTenPhuongXa() {
        return this.tenPhuongXa;
    }

    public void setTenPhuongXa(String tenPhuongXa) {
        this.tenPhuongXa = tenPhuongXa;
    }

    public String getTenQuanHuyen() {
        return this.tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getTenTinhThanh() {
        return this.tenTinhThanh;
    }

    public void setTenTinhThanh(String tenTinhThanh) {
        this.tenTinhThanh = tenTinhThanh;
    }

    @Override
    public String toString() {
        if (soNha == "" && tenDuong == "" && tenPhuongXa == "" && tenQuanHuyen == "" && tenTinhThanh == "")
            return "";
//        return soNha + " " + tenDuong + ", " + tenPhuongXa + ", " + tenQuanHuyen + ", " + tenTinhThanh;
        String outputStr ="";
        if (soNha != ""){
            outputStr += soNha+" ";
        }
        if (tenDuong != ""){
            outputStr += tenDuong +", ";
        }
        if (tenPhuongXa != ""){
            outputStr += tenPhuongXa +", ";
        }
        if (tenQuanHuyen != ""){
            outputStr += tenQuanHuyen +", ";
        }
        if (tenTinhThanh != ""){
            outputStr += tenTinhThanh;
        }
        return outputStr;
    }
}
