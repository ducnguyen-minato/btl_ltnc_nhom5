/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDienNguoiDung;

import DoiTuong.NhanVien;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ketnoicsdl.KetNoiCSDL;

/**
 *
 * @author Dinh Duc
 */
public class GiaoDienDangNhap extends javax.swing.JFrame {
    private  String taiKhoan ;
    private String matKhau;
    /**
     * Creates new form GiaoDienDangNhap
     */
    public GiaoDienDangNhap() {
        initComponents();
      
      
    }
    public String layTaiKhoan(){
        String  taiKhoan1 = tf_UseName.getText();
        return taiKhoan1;
      
    }
    public void kiemTraDangNhap () {
        taiKhoan = tf_UseName.getText();
        matKhau = jPassword.getText();
        String sqlKiemTra = "SELECT COUNT(*) FROM TaiKhoan WHERE username = '" + taiKhoan + 
                "' AND MatKhau = '" + matKhau +"'";
        // Lấy count truy vấn tài khoản: 0(không tồn tại) và 1(tồn tại trong db); 
        //không thể bằng 2 vì username là khóa chính nên không thể trùng
        try {
            KetNoiCSDL kn = new KetNoiCSDL();
            Statement stmt = kn.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sqlKiemTra);
            rs.next();
            if (rs.getInt(1)==1) {
                // to do xu ly sau khi ĐĂNG NHẬP THÀNH CÔNG
                this.dispose(); //tắt giao diện đăng nhập
                
                //Khởi tạo đối tượng nhân viên đang dùng bằng tài khoản đang sử dụng
                String sqlLayMaNV = "SELECT MaNV FROM TaiKhoan WHERE username = '" + taiKhoan + 
                "' AND MatKhau = '" + matKhau +"'";
                ResultSet rs_MaNV = stmt.executeQuery(sqlLayMaNV);
                rs_MaNV.next();
                String maNV = rs_MaNV.getString(1);
                    //Lấy data trong bảng nhân viên từ mã nhân viên có được:
                    String sqlLayNhanVien = "SELECT * FROM nhanvien WHERE MaNV = '" + maNV + "'";
                    ResultSet rs_NhanVien = stmt.executeQuery(sqlLayNhanVien);
                    rs_NhanVien.next();
                    Main.nhanvien = new NhanVien(rs_NhanVien.getString(1), rs_NhanVien.getString(2), 
                            rs_NhanVien.getString(3), rs_NhanVien.getString(4), rs_NhanVien.getString(5), 
                            rs_NhanVien.getString(6), rs_NhanVien.getString(7), rs_NhanVien.getString(8), 
                            rs_NhanVien.getString(9),rs_NhanVien.getBytes(10));
                //System.out.println(Main.nhanvien.getName());
                // Mở giao diện chính
                GiaoDienChinh gdChinh = new GiaoDienChinh();
                gdChinh.a=layTaiKhoan();
                gdChinh.setLocationRelativeTo(null);
                gdChinh.show();
            } else {
                jLabel5.setText("Tài khoản Không tồn tại");
                // to do xu ly sau khi ĐĂNG NHẬP THẤT BẠI
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Loi ket noi csdl!");
            Logger.getLogger(GiaoDienDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // ham kiem tra ki tu nhap vao, tra ve gia tri fasle neu rong va nguoc lai
    private boolean kiemTraKiTuNhap () {
        if (tf_UseName.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(this, "Tai khoan khong de trong!");
            if (jPassword.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(this, "Mat khau khong de trong!");
            return false;
        }
            return false;
        }
        if (jPassword.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(this, "Mat khau khong de trong!");
            return false;
        }
        return true;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_UseName = new javax.swing.JTextField();
        jb_DangNhap = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIÊU THỊ ĐIỆN MÁY ES5");
        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/es5.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ĐĂNG NHẬP");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_account.png"))); // NOI18N
        jLabel3.setOpaque(true);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_password.png"))); // NOI18N
        jLabel4.setOpaque(true);

        tf_UseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_UseNameActionPerformed(evt);
            }
        });
        tf_UseName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_UseNameKeyPressed(evt);
            }
        });

        jb_DangNhap.setBackground(new java.awt.Color(0, 0, 255));
        jb_DangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jb_DangNhap.setForeground(new java.awt.Color(255, 255, 255));
        jb_DangNhap.setText("ĐĂNG NHẬP");
        jb_DangNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jb_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_DangNhapActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        jPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addGap(260, 260, 260))
                    .addComponent(jb_DangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPassword))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_UseName))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_UseName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_UseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_UseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_UseNameActionPerformed

    private void jb_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_DangNhapActionPerformed
        
        if (kiemTraKiTuNhap()) {
            kiemTraDangNhap();
            
            layTaiKhoan();
        } 
    }//GEN-LAST:event_jb_DangNhapActionPerformed
// xử lý nút bấm Phan Văn Thịnh 15-11-2019
    private void tf_UseNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_UseNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            tf_UseName.transferFocus();       
        }
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            tf_UseName.transferFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }//GEN-LAST:event_tf_UseNameKeyPressed

    private void jPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            jPassword.transferFocusBackward();
        }
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (kiemTraKiTuNhap()) {
                kiemTraDangNhap();
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }//GEN-LAST:event_jPasswordKeyPressed
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienDangNhap().setVisible(true);
            }
        });
    }
    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JButton jb_DangNhap;
    private javax.swing.JTextField tf_UseName;
    // End of variables declaration//GEN-END:variables
}
