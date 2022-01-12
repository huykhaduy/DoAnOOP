package Modul;
import java.io.Serializable;
import java.util.*;

import Controller.Program;
import Modul.Error.InvalidNumberException;
import Modul.Error.InvalidStringException;
import Modul.Error.NotExsitException;
import Modul.NhanVien;
import Modul.SupportModul.Check;
import Modul.SupportModul.DateTime;

public class TaiKhoan implements Serializable, ConsoleIO, MyCompare < TaiKhoan > {
	private int accountid;
	private String tenDangNhap;
	private String matKhau;
	private NhanVien nhanVien;
	private DateTime ngayTao = new DateTime();

	public TaiKhoan() {
		this.accountid = Program.getDSTK().getLargestId()+1;
		ngayTao.setCurrentTime();
	}

	public TaiKhoan(String tenDangNhap, String matKhau) {
		this.accountid = Program.getDSTK().getLargestId()+1;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		ngayTao.setCurrentTime();
	}

	public TaiKhoan(String tenDangNhap, String matKhau, String maNV) {
		this.accountid = Program.getDSTK().getLargestId()+1;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		ngayTao.setCurrentTime();
		try {
			this.nhanVien = Program.getDSNV().layDuLieuNV(maNV);
		} catch (NotExsitException e) {
			System.out.println("<!> Nhân viên không tồn tại");
		}
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) throws InvalidStringException {
		if (Check.containsSpace(tenDangNhap)) {
			throw new InvalidStringException("Tên đăng nhập không tồn tại khoảng trắng");
		}
		if (tenDangNhap.length() < 3) {
			throw new InvalidStringException("Tên đăng nhập phải có từ 3 kí tự trở lên");
		}
		TaiKhoan p = Program.getDSTK().layDuLieuTk(tenDangNhap);
		if (p != null) {
			throw new InvalidStringException("Tên đăng nhập đã được sử dụng");
		}
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) throws InvalidStringException {
		if (matKhau.length() < 4) {
			throw new InvalidStringException("Mật khẩu có từ 4 kí tự trở lên");
		}
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setNhanVienStr(String maNV) throws NotExsitException {
		NhanVien p = Program.getDSNV().layDuLieuNV(maNV);
		nhanVien = p;
	}

	public DateTime getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(DateTime ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Override
	public void nhapThongTin() {
		System.out.println(Text.center("NHẬP TÀI KHOẢN", 40, '-'));
		int step = 1;
		do {
			try {
				if (step == 1) {
					System.out.print("> Nhập mã nhân viên: ");
					setNhanVienStr(sc.nextLine());
				}
				if (step == 2) {
					System.out.print("> Nhập tên đăng nhập: ");
					setTenDangNhap(sc.nextLine());
				}
				if (step == 3) {
					System.out.print("> Nhập mật khẩu: ");
					setMatKhau(sc.nextLine());
				}
				step++;
			} catch (InvalidStringException | NotExsitException e) {
				System.out.println(e.toString());
			}
		} while (step < 4);
	}

	@Override
	public void xuatThongTin() {
		System.out.println(Text.center("THÔNG TIN TÀI KHOẢN", 40, '-'));
		System.out.println(" - Mã nhân viên: " + nhanVien.getMaNV());
		System.out.println(" - Tên nhân viên: " + nhanVien.getName());
		System.out.println(" - Chức vụ: " + nhanVien.getChucVuStr());
		System.out.println(" - Tên tài khoản: " + tenDangNhap);
		System.out.println(" - Mật khẩu: " + matKhau);
		System.out.println(" - Ngày tạo: " + ngayTao);
	}

	public void suaThongTin() {
		System.out.println(Text.center("SỬA MẬT KHẨU", 40, '-'));
		int step = 1;
		do {
			try {
				if (step == 1) {
					System.out.println("> Nhập mật khẩu: ");
					setMatKhau(sc.nextLine());
				}
				if (step == 2) {
					System.out.println("> Nhập lại mật khẩu: ");
					String rePass = sc.nextLine();
					if (!rePass.equals(matKhau)) {
						throw new InvalidStringException("<!> Nhập lại mật khẩu không đúng!");
					}
				}
				step++;
			} catch (InvalidStringException e) {
				System.out.println(e.toString());
			}
		} while (step < 3);

	}

	@Override
	public String toString() {
		return String.format("|%20s|%20s|%20s|",tenDangNhap,matKhau,ngayTao.toString());
	}


	@Override
	public int compareTo(TaiKhoan o2, int type) {
		if (type == 1){
			if (this.tenDangNhap.compareToIgnoreCase(o2.tenDangNhap)>1)
				return 1;
			return -1;
		}
		if (type == 2){
			if (this.ngayTao.compareDateTime(o2.ngayTao)>1)
				return 1;
			return -1;
		}
		return 0;
	}
}