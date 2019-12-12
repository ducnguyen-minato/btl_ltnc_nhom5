/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

/**
 *
 * @author Dat Tran
 */
public class KhachHang extends Nguoi{
    private double tongTien;
    private String loaiKhachHang;
    public KhachHang(String id, String name, String diaChi, String soDienThoai, String email,double tongTien, String loaiKhachHang, String ghiChu) {
        super(id, name, soDienThoai, diaChi, email, ghiChu);
        this.tongTien = tongTien;
        this.loaiKhachHang = loaiKhachHang;
    }
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }
}
   