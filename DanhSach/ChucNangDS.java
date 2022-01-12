package DanhSach;

public interface ChucNangDS {
    void timKiem();
    void them();
    void xoa();
    void sua();
    void sapXep();
    void xuLy(int choice);
    void sapXepTangDan(int type);
    void sapXepGiamDan(int type);
    void writeToFile();
    void readFromFile();
}
