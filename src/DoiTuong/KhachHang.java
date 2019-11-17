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
    private int tongTien;

    public KhachHang(String id, String name, String ngaySinh, String soDienThoai, String gioiTinh, String diaChi, int tongTien) {
        super(id, name, ngaySinh, soDienThoai, gioiTinh, diaChi);
        this.tongTien = tongTien;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    
    
}
