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
public class NhaCungCap {
  private int maNhacungCap;
  private String tenNhaCungCap;
  private String diaChi;
  private String soDienThoai;
  private String email;
  private String ghiChu;

    public int getMaNhacungCap() {
        return maNhacungCap;
    }

    public void setMaNhacungCap(int maNhacungCap) {
        this.maNhacungCap = maNhacungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhaCungCap(int maNCC, String tenNCC, String diaChi, String soDT, String email, String ghiChu) {
        this.maNhacungCap = maNCC;
        this.tenNhaCungCap = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDT;
        this.email = email;
        this.ghiChu = ghiChu;
                
    }
    
    
}
