package DanhSach;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Modul.*;
import Modul.Error.InvalidNumberException;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DocGhiFile;
import Modul.SupportModul.MySort;

public class DanhSachTaiKhoan implements ChucNangDS, ConsoleIO {
	public MyArray < TaiKhoan > dstk = new MyArray < > ();

	public DanhSachTaiKhoan() {
		dstk = new MyArray < > ();
	}
	public DanhSachTaiKhoan(MyArray < TaiKhoan > dstk) {
		this.dstk = dstk;
	}

	public TaiKhoan layDuLieuTk(String username) {
		for (int i = 0; i < dstk.getLength(); i++) {
			TaiKhoan tk = dstk.getAt(i);
			if (tk.getTenDangNhap().equalsIgnoreCase(username))
				return tk;
		}
		return null;
	}

	public void themTaiKhoan(String username, String password, String maNV) {
		dstk.push(new TaiKhoan(username, password, maNV));
	}

	public int getLargestId() {
		int max = 0;
		for (int i = 0; i < dstk.getLength(); i++) {
			if (max < dstk.getAt(i).getAccountid())
				max = dstk.getAt(i).getAccountid();
		}
		return max;
	}

	@Override
	public void timKiem() {
		System.out.print("> Nhập thông tin nhân viên cần tìm: ");
		MyArray < TaiKhoan > result = modulTimKiem(sc.nextLine().toLowerCase());
		if (result != null) {
			if (result.getLength() > 1) {
				System.out.println(DanhSachTaiKhoan.title());
				for (int i = 0; i < result.getLength(); i++) {
					System.out.println(result.getAt(i).toString());
				}
			} else {
				result.getAt(0).xuatThongTin();
			}
		} else {
			System.out.println("<!> Không tìm thấy kết quả nào!");
		}
	}
	@Override
	public void them() {
		TaiKhoan tk = new TaiKhoan();
		tk.nhapThongTin();
		dstk.push(tk);
		writeToFile();
	}

	@Override
	public void xoa() {
		System.out.print("> Nhập tài khoản muốn xóa: ");
		TaiKhoan tk;
		tk = layDuLieuTk(sc.nextLine().toLowerCase());
		if (tk == null) {
			System.out.println("<!> Lỗi: Không tìm thấy tài khoản!");
			return;
		}
		System.out.print("> Bạn muốn xóa tài khoản: " + tk.getTenDangNhap() + " (y/n)? ");
		char isDel = 'n';
		try {
			isDel = sc.nextLine().charAt(0);
		} catch (InputMismatchException e) {
			System.out.println("<!> Lựa chọn không hợp lệ!");
		}
		if (isDel == 'y' || isDel == 'Y') {
			dstk.removeAt(dstk.indexOf(tk));
			System.out.println("<!> Xóa thành công phòng!");
		}
		writeToFile();
	}

	@Override
	public void sua() {
		System.out.print("> Nhập tài khoản muốn sửa: ");
		TaiKhoan kh;
		kh = layDuLieuTk(sc.nextLine().toLowerCase());
		if (kh == null) {
			System.out.println("<!> Lỗi: Không tìm thấy tài khoản!");
			return;
		}
		System.out.println(" Bạn đang sửa thông tin của: " + kh.getTenDangNhap());
		kh.suaThongTin();
		writeToFile();
	}

	@Override
	public void sapXep() {
		System.out.println();
		System.out.println(Text.center("SẮP XẾP DANH SÁCH TÀI KHOẢN", 40, '-'));
		System.out.println(" 1. Theo tên đăng nhập (asc/desc)");
		System.out.println(" 2. Theo ngày tạo (asc/desc)");
		System.out.println("(Lựa chọn gồm số + asc hay desc, vd: 1asc hay 2desc");
		System.out.print("> Lựa chọn của bạn: ");
		String choice = sc.nextLine();
		if (choice.length() < 2) {
			System.out.println("<!> Lựa chọn không hợp lệ!");
			return;
		}
		int select = choice.charAt(0) - 48;
		if (select < 1 || select > 2) {
			System.out.println("<!> Lựa chọn không hợp lệ!");
			return;
		}
		if (choice.charAt(1) != 'a' && choice.charAt(1) != 'A' && choice.charAt(1) != 'D' && choice.charAt(1) != 'd') {
			System.out.println("<!> Lựa chọn không hợp lệ!");
			return;
		}
		if (choice.charAt(1) == 'a' || choice.charAt(1) == 'A')
			sapXepTangDan(select);
		else sapXepGiamDan(select);
		xuatThongTin();

	}
	@Override
	public void xuLy(int choice) {
		switch (choice) {
			case 1:
				them();
				break;
			case 2:
				timKiem();
				break;
			case 3:
				sua();
				break;
			case 4:
				xoa();
				break;
			case 5:
				sapXep();
				break;
			case 6:
				xuatThongTin();
				break;
		}

	}
	@Override
	public void sapXepTangDan(int type) {
		MySort < TaiKhoan > s = new MySort < TaiKhoan > ();
		s.sort(dstk, type, true);
	}
	@Override
	public void sapXepGiamDan(int type) {
		MySort < TaiKhoan > s = new MySort < TaiKhoan > ();
		s.sort(dstk, type, false);
	}
	@Override
	public void writeToFile() {
		String name = "./Data/TaiKhoan.txt";
		DocGhiFile < TaiKhoan > ghi = new DocGhiFile < TaiKhoan > (dstk);
		ghi.ghiFileVaoThuMuc(name);
	}
	@Override
	public void readFromFile() {
		String name = "./Data/TaiKhoan.txt";
		DocGhiFile < TaiKhoan > doc = new DocGhiFile < TaiKhoan > ();
		dstk = doc.docFileTuThuMuc(name);
	}


	@Override
	public void nhapThongTin() {
		while (true) {
			System.out.println();
			System.out.println(Text.center("DANH SÁCH TÀI KHOẢN", 40, '-'));
			System.out.println("|" + Text.leftAt(10, Text.setLength("1. Thêm tài khoản", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("2. Tìm tài khoản", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("3. Sửa tài khoản", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("4. Xóa xóa tài khoản", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("5. Sắp xếp danh sách", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("6. Xem danh sách", 27), ' ') + "|");
			System.out.println("|" + Text.leftAt(10, Text.setLength("7. Lưu và thoát", 27), ' ') + "|");
			System.out.println(Text.center("", 40, '-'));
			System.out.print("> Nhập lựa chọn: ");
			int value = 0;
			try {
				value = sc.nextInt();
				if (value < 1 || value > 7) throw new InvalidNumberException("Vui lòng chọn số từ 1-7");
			} catch (InputMismatchException e) {
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
		System.out.println(title());
		for (int i = 0; i < dstk.getLength(); i++) {
			System.out.println(dstk.getAt(i).toString());
		}
	}

	public MyArray < TaiKhoan > modulTimKiem(String str) {
		str = str.toLowerCase();
		//Tạo mảng tạm để lưu dữ liệu
		MyArray < TaiKhoan > result = new MyArray < TaiKhoan > ();
		for (int i = 0; i < dstk.getLength(); i++) {
			TaiKhoan tk = dstk.getAt(i);
			if (tk.getTenDangNhap().toLowerCase().contains(str) || tk.getNgayTao().toStringNgay().contains(str)) {
				result.push(tk);
			}
		}
		if (result.getLength() == 0) return null;
		return result;
	}

	public static String title() {
		String header = Text.center("", 64, '-');
		String format = String.format("|%20s|%20s|%20s|", "Tên tài khoản", "Mật khẩu", "Ngày tạo");
		String footer = Text.center("", 64, '-');
		return header + "\n" + format + "\n" + footer;
	}
}