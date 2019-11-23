/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

/**
 *
 * @author Dinh Duc
 */
public class KhachHang extends Nguoi{
    private Double tongTien;

    public KhachHang(String id, String name, String ngaySinh, String soDienThoai, String gioiTinh, String diaChi, Double tongTien) {
        super(id, name, ngaySinh, soDienThoai, gioiTinh, diaChi);
        this.tongTien = tongTien;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    
    
}
