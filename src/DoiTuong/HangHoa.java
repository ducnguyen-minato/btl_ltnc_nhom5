// Phan Văn Thịnh code 23//11/2019
package DoiTuong;


public class HangHoa {
    private String maHangHoa;
    private String tenHangHoa;
    private String nhaSanXuat;
    private String maNhaCungCap;
    private String anh_Path;
    //private int soLuongTrongKho;
    private double giaNhap;
    private double giaBan;

    public HangHoa(String maHangHoa, String tenHangHoa, String tenNhaSanXuat, String maNhaCungCap, String anh_Path, double giaNhap, double giaBan){
        this.maHangHoa = maHangHoa;
        this.tenHangHoa = tenHangHoa;
        this.nhaSanXuat = tenNhaSanXuat;
        this.maNhaCungCap = maNhaCungCap;
        this.anh_Path = anh_Path;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }
    
    public String getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getAnh_Path() {
        return anh_Path;
    }

    public void setAnh_Path(String anh_Path) {
        this.anh_Path = anh_Path;
    }

    /*public int getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(int soLuongTrongKho) {
        this.soLuongTrongKho = soLuongTrongKho;
    }*/

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
    
}


