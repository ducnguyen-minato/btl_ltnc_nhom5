
package GiaoDienNguoiDung;

import DoiTuong.HangHoa;
import DoiTuong.KhachHang;
import DoiTuong.NhanVien;

/**
 *
 * @author Dinh Duc
 */
public class Main {
    // Tạo đối tượng tĩnh là 1 nhân viên hiện tại đang dùng là ai dựa vào tài khoản đăng nhập
    public static NhanVien nhanvien;
    public static KhachHang khachHang;
    public static HangHoa hangHoa;
    
    
    public static void main(String[] args) {
        // TODO code application logic here       
        GiaoDienDangNhap dgdn = new GiaoDienDangNhap();
        dgdn.setLocationRelativeTo(null);
        dgdn.show();
        
        
    }
    
}
