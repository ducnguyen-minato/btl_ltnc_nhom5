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
public class NhanVien extends Nguoi{
    private String chucVu;
    private String email;
    private String anh_Path;
    private String ghiChu;

    public NhanVien(String id, String name, String ngaySinh, String soDienThoai, String gioiTinh, String diaChi, String chucVu, String email, String anh_Path, String ghiChu) {
        super(id, name, ngaySinh, soDienThoai, gioiTinh, diaChi);
        this.chucVu = chucVu;
        this.email = email;
        this.anh_Path = anh_Path;
        this.ghiChu = ghiChu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnh_Path() {
        return anh_Path;
    }

    public void setAnh_Path(String anh_Path) {
        this.anh_Path = anh_Path;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
    
    
    
}
