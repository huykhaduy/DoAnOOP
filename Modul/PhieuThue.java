package Modul;

import java.io.Serializable;
import java.math.BigDecimal;

import Controller.Program;
import Modul.Error.NotExsitException;
import Modul.SupportModul.DateTime;

public class PhieuThue extends Phieu implements ConsoleIO, Serializable {
    private DateTime startDate = new DateTime();
    private DateTime endDate = new DateTime();
    private BigDecimal[] giaTien;
    private BigDecimal totalMoney;

    public PhieuThue() {
        super();
        giaTien = new BigDecimal[3];
        totalMoney = new BigDecimal("0");
    }

    public PhieuThue(KhachHang maKH, Phong maPhong) {
        super();
        this.maKH = maKH;
        this.maPhong = maPhong;
        this.giaTien = new BigDecimal[3];
        if (maPhong.getLoaiPhong()==1){
            giaTien[0] = Program.getBangGia().getGiaPhongVipNgay();
            giaTien[1] = Program.getBangGia().getGiaPhongVipGio();
            giaTien[2] = Program.getBangGia().getGiaPhongVipPhut();
        }
        else if (maPhong.getLoaiPhong() == 0){
            giaTien[0] = Program.getBangGia().getGiaPhongThuongNgay();
            giaTien[1] = Program.getBangGia().getGiaPhongThuongGio();
            giaTien[2] = Program.getBangGia().getGiaPhongThuongPhut();
        }
        totalMoney = new BigDecimal("0");
    }

    public DateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal[] getGiaTien() {
        //Calculate gia tien by giaphong multiple endDate - startDate
        return this.giaTien;
    }

    public void updateGiaTien(BigDecimal[] giaTien) {
        //Tinh toan gia tien !
        this.giaTien = giaTien;
    }

    public void traPhong() {
        //set trang thai phong ve trong
        endDate.nhapNgayGio("NHẬP NGÀY GIỜ TRẢ PHÒNG");
        while (endDate.compareDateTime(startDate) < 0) {
//             endDate.setCurrentTime();
            System.out.println("Ngày trả phòng nhỏ hơn ngày đặt phòng! Vui lòng nhập lại");
            endDate.nhapNgayGio("NHẬP NGÀY GIỜ TRẢ PHÒNG");
        }
            
        int[] counter = endDate.getDayTimeFrom(startDate);
        System.out.println(" -> Thuê " + counter[0] + " ngày " + counter[1] + " giờ " + counter[2]+" phút");
    }

    public void traPhongNow(){
        endDate.setCurrentTime();
        try {
            Phong p = Program.getDSP().layDuLieuPhong(maPhong.getMaPhong());
            p.setTinhTrang(false);
        } catch (NotExsitException e) {
            System.out.print("");
        }
    }

    public BigDecimal tinhTien(){
        endDate.setCurrentTime();
        int[] counter = endDate.getDayTimeFrom(startDate);
        BigDecimal tongtien = new BigDecimal("0");
        tongtien = tongtien.add(giaTien[0].multiply(new BigDecimal(counter[0])));
        tongtien = tongtien.add(giaTien[1].multiply(new BigDecimal(counter[1])));
        tongtien = tongtien.add(giaTien[2].multiply(new BigDecimal(counter[2])));
        if (maPhong.getTinhTrang())
            totalMoney = tongtien;
        return tongtien;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        totalMoney = tinhTien();
    }

    @Override
    public void nhapThongTin() {
//        System.out.print("> Nhập mã phòng: ");
//        setMaPhong(sc.nextInt());
        startDate.nhapNgayGio("NHẬP NGÀY GIỜ ĐẶT PHÒNG");
        while (startDate.compareDateTime(DateTime.getTimeNow()) > 0) {
//            startDate.setCurrentTime();
            System.out.println(" <!> Ngày đặt phòng không thể lón hơn thời gian hiện tại! Vui lòng nhập lại");
//            System.out.println(" -> Tự động lấy ngày đặt là hiện tại: " + startDate);
            startDate.nhapNgayGio("NHẬP NGÀY GIỜ ĐẶT PHÒNG");
        }  
    }

    @Override
    public void xuatThongTin(){
        int[] counter = endDate.getDayTimeFrom(startDate);
        System.out.println();
        System.out.println(Text.center("THÔNG TIN PHIẾU THUÊ",60,'-'));
        System.out.println(" - Ngày thuê: "+startDate.toString());
        System.out.println(" - Ngày trả: "+endDate.toString());
        System.out.println(" - Thời gian thuê: "+counter[0]+" ngày "+counter[1]+" giờ "+counter[2]+" phút");
        System.out.println(" - Giá thuê: "+giaTien[0]+"/ngày, "+giaTien[1]+"/giờ, "+giaTien[2]+"/phút");
        System.out.println(" - Tổng tiền thuê: "+totalMoney.toString());
        System.out.println(" - Ngày tạo phiếu: "+ngayTaoPhieu.toString());
    }

//    @Override
//    public void xuatThongTin() {
//        System.out.println("-----------------------------------------------------------------------------------------------------------");
//        System.out.printf("|%-15s|%-15s|%-15s|%-20s|%-20s|%-15s|","MaKH","Ma Phieu Thue","Ma Phong","Ngay thue","Ngay ket thuc","Gia tien");
//        System.out.println();
//        System.out.println("-----------------------------------------------------------------------------------------------------------");
//        System.out.printf("|%15s|%15s|%15s|%20s|%20s|%15s|\n",maKH,maPhieuThue,maPhong,startDate,endDate,giaTien);
//    }
    
    
    
}
