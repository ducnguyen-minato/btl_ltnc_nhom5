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
public class NhanVien extends Nguoi{
    private String chucVu;
    private String gioiTinh;
    private String ngaySinh;
    private byte[] anhNV;

   

    
    public NhanVien(String id, String name, String chucVu,String gioiTinh, String ngaySinh, String diaChi, String soDienThoai,  String email, String ghiChu,byte[] anhNV) {
        super(id, name,soDienThoai,  diaChi, email, ghiChu);       
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.anhNV = anhNV;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
     public byte[] getAnhNV() {
        return anhNV;
    }

    public void setAnhNV(byte[] anhNV) {
        this.anhNV = anhNV;
    }
    
}
 /*   private String maNhanVien;
    private String tenNhanVien;
    private String chucVu;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String soDT;
    private String email;
    private String ghiChu;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
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
    public NhanVien(String maNV,String tenNV,String chucVu,String gioiTinh,
            String ngaySinh,String diaChi,String soDT, String email, String ghiChu){
    this.maNhanVien = maNV;
    this.tenNhanVien = tenNV;
    this.chucVu = chucVu;
    this.gioiTinh = gioiTinh;
    this.ngaySinh = ngaySinh;
    this.diaChi = diaChi;
    this.soDT = soDT;
    this.email = email;
    this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return maNhanVien; //To change body of generated methods, choose Tools | Templates.
    }
    
}*/
