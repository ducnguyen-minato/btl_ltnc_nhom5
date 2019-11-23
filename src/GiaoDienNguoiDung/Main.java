/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDienNguoiDung;

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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here       
        GiaoDienDangNhap dgdn = new GiaoDienDangNhap();
        dgdn.setLocationRelativeTo(null);
        dgdn.show();
        
        
    }
    
}
