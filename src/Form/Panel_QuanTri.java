/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DoiTuong.NhanVien;
import DoiTuong.TaiKhoan;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ketnoicsdl.KetNoiCSDL;

/**
 *
 * @author Dinh Duc
 */
public class Panel_QuanTri extends javax.swing.JPanel {

    /**
     * Creates new form Panel_chose1
     */
    public static Connection conn = null;
    public static Statement statement = null;
    public static ResultSet result = null;
    NhanVien nhanVien;
    TaiKhoan taiKhoan;
    ketnoicsdl.KetNoiCSDL kn = new KetNoiCSDL();
    DefaultTableModel dfTable;
    String tenAnh = null;
    byte[] anhNhanVien = null;
    PreparedStatement ps;
    ArrayList<String> listComboItem = new ArrayList<>();
    ArrayList<String> listComboItem1 = new ArrayList<>();
    ArrayList<NhanVien> nv = new ArrayList<>();
    ArrayList<TaiKhoan> tk = new ArrayList<>();
    
    public Panel_QuanTri() {
        initComponents();
        hienThiDanhSachNV();
        hienThiDanhSachTK();
        
    }

    public ArrayList<NhanVien>  NhanVienList(){
        ArrayList<NhanVien> nhanVienList = new ArrayList<NhanVien>();
        conn = kn.getConn();
        String query = "select * from nhanvien";
         try {
              statement = conn.createStatement();
              result = statement.executeQuery(query);
              while(result.next()){
                  nhanVien = new NhanVien(result.getString(1),result.getString(2),
                          result.getString(3),result.getString(4),result.getString(5)
                          ,result.getString(6),result.getString(7),
                          result.getString(8),result.getString(9),result.getBytes(10));
                  nhanVienList.add(nhanVien);          
              }       
        } 
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
         return nhanVienList;
    }
    public void hienThiDanhSachNV(){
        ArrayList<NhanVien> list = NhanVienList();
        dfTable = (DefaultTableModel) BangNhanVien.getModel();
        Object[] row = new Object[9];
        
        for(int i =0; i < list.size();i++){
            
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getChucVu();
            row[3] = list.get(i).getGioiTinh();
            row[4] = list.get(i).getNgaySinh();
            row[5] = list.get(i).getDiaChi();
            row[6] = list.get(i).getSoDienThoai();
            row[7] = list.get(i).getEmail();
            row[8] = list.get(i).getGhiChu();
            dfTable.addRow(row);
        }
        
    }
     
    private void xuLyThem() {
    String gioiTinh;
    if(jrNam.isSelected()){
        gioiTinh = "Nam";
    }
    else {
        gioiTinh ="Nữ";
    }
    conn= kn.getConn();
    try{
         ps = conn.prepareStatement("insert into nhanvien values (?,?,?,?,?,?,?,?,?,?)");
         ps.setString(1, txtMaNV.getText());
         ps.setString(2, txtHoTen.getText());
         ps.setString(3, cbChucVu.getSelectedItem().toString());
         ps.setString(4, gioiTinh);
         ps.setString(5, txtNgaySinh.getText());
         ps.setString(6, txtDiaChi.getText());
         ps.setString(7, txtSoDT.getText());
         ps.setString(8, txtEmail.getText());
         ps.setString(9, txtghiChu.getText());
         ps.setBytes(10, anhNhanVien);

        int x = ps.executeUpdate();
        if(x==1){
            dfTable = (DefaultTableModel) BangNhanVien.getModel();
            dfTable.setRowCount(0);
            hienThiDanhSachNV();
         }
    }
    catch(SQLException ex){
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
    clear();
}
    private void clear() {                                            
       txtMaNV.setText("");
       txtHoTen.setText("");
       txtNgaySinh.setText("");
       txtDiaChi.setText("");
       txtSoDT.setText("");
       txtEmail.setText("");
       txtghiChu.setText("");
       txtSearch.setText("");
       label_anh.setIcon(null);
    }
    private void xuLySua(){
    String gioiTinh;
    if(jrNam.isSelected()){
        gioiTinh = "Nam";
    }
    else {
        gioiTinh ="Nữ";
    }

String sql = "update nhanvien set TenNV=?,ChucVu=?,GioiTinh=?,NgaySinh=?,DiaChi=?,SDT=?,Email=?,GhiChu=?,AnhNV=? where MaNV =?";
conn= kn.getConn();
try{
     ps = conn.prepareStatement(sql);

     ps.setString(1, txtHoTen.getText());
     ps.setString(2, cbChucVu.getSelectedItem().toString());
     ps.setString(3, gioiTinh);
     ps.setString(4, txtNgaySinh.getText());
     ps.setString(5, txtDiaChi.getText());
     ps.setString(6, txtSoDT.getText());
     ps.setString(7, txtEmail.getText());
     ps.setString(8, txtghiChu.getText());
     ps.setBytes(9, anhNhanVien);
     ps.setString(10, txtMaNV.getText());

    int x = ps.executeUpdate();

    if(x==1){
        dfTable = (DefaultTableModel) BangNhanVien.getModel();
        dfTable.setRowCount(0);
        hienThiDanhSachNV();
}
}catch(SQLException ex){
       System.out.println("SQLException: " + ex.getMessage());
       System.out.println("SQLState: " + ex.getSQLState());
       System.out.println("VendorError: " + ex.getErrorCode());
}
clear();
}
    private void xuLyXoa(){
    conn = kn.getConn();
    String sql = "delete from nhanvien where MaNV =?";
    try{
        ps = conn.prepareStatement(sql);
        ps.setString(1,txtMaNV.getText());
        int x = ps.executeUpdate();
        if(x==1){
        dfTable = (DefaultTableModel) BangNhanVien.getModel();
        dfTable.setRowCount(0);
        hienThiDanhSachNV();
        }
    }
   catch(SQLException ex){
       System.out.println("SQLException: " + ex.getMessage());
       System.out.println("SQLState: " + ex.getSQLState());
       System.out.println("VendorError: " + ex.getErrorCode());
}
    clear();
}
    private void searchNV() {
       String lenhTim = txtSearch.getText();
       String query1 = "select * from quanlysieuthidienmay.nhanvien where MaNV like '%"+lenhTim+"%'"
               + "or TenNV like '%"+lenhTim+"%' or ChucVu like '%"+lenhTim+"%'or SDT like '%"+lenhTim+"%'";
       try{
           conn = kn.getConn();
           statement = conn.createStatement();      
           result = statement.executeQuery(query1);

           this.xoaBang();
           this.showData(result);
           
          }
      catch (SQLException ex) {
        } 
    }
    private void xoaBang() {
        int length = BangNhanVien.getRowCount();
        int x = BangNhanVien.getColumnCount();
        for (int row = 0; row < length; row++) {
            if (BangNhanVien.getValueAt(row,0) == null)
                break;
            for (int col = 0; col < x; col++) {
                BangNhanVien.setValueAt("", row, col);
            }
        }
       
    }
    private void showData(ResultSet rs1) throws SQLException {
        int row = 0;
        int col = 0;
       
        while (rs1.next()) {
            
            BangNhanVien.setValueAt(rs1.getString(1), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(2), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(3), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(4), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(5), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(6), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(7), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(8), row, col);
            col++;
            BangNhanVien.setValueAt(rs1.getString(9), row, col);
            col++;
          byte[] img = rs1.getBytes("AnhNV");
          ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(label_anh.getWidth(), label_anh.getHeight(), Image.SCALE_SMOOTH));
          label_anh.setIcon(imageIcon);
        }
    }
    private void hienThongTin(){
        int i = BangNhanVien.getSelectedRow();
        TableModel model = BangNhanVien.getModel();
        txtMaNV.setText(model.getValueAt(i,0).toString());
        txtHoTen.setText(model.getValueAt(i,1).toString());
        
        txtNgaySinh.setText(model.getValueAt(i,4).toString());
        txtDiaChi.setText(model.getValueAt(i,5).toString());
        txtSoDT.setText(model.getValueAt(i,6).toString());
        txtEmail.setText(model.getValueAt(i,7).toString());
        txtghiChu.setText(model.getValueAt(i,8).toString());
        String gtinh = model.getValueAt(i,3).toString();
        if(gtinh.equals("Nam")){
            jrNam.setSelected(true);
        }
        else{
            jrNu.setSelected(true);
        }
        String chucVu = model.getValueAt(i, 2).toString();
        switch (chucVu) {
            case "Thủ kho":
                cbChucVu.setSelectedIndex(0);
                break;
            case "Nhân viên bán hàng":
                cbChucVu.setSelectedIndex(1);
                break;
            case "Nhân viên giao hàng":
                cbChucVu.setSelectedIndex(2);
                break;
            case "Kế toán":
                cbChucVu.setSelectedIndex(3);
                break;
            default:
                break;
        }
        byte[] img1 = (NhanVienList().get(i).getAnhNV());
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(label_anh.getWidth(), label_anh.getHeight(), Image.SCALE_SMOOTH));
        label_anh.setIcon(imageIcon1);
    }
 
    
 // code panel_taikhoan


     public ArrayList<TaiKhoan>  TaiKhoanList(){
        ArrayList<TaiKhoan> taiKhoanList = new ArrayList<TaiKhoan>();
        conn = kn.getConn();
        String query = "select * from nhanvien, taikhoan where taikhoan.MaNV = nhanvien.MaNV";
         try {
              statement = conn.createStatement();
              result = statement.executeQuery(query);
              while(result.next()){
                          taiKhoan = new TaiKhoan(result.getString(1),result.getString(2),
                          result.getString(3),result.getString(4),result.getString(5)
                          ,result.getString(6),result.getString(7),
                          result.getString(8),result.getString(9),result.getBytes(10),result.getString(11),result.getString(12));
                  taiKhoanList.add(taiKhoan);          
              }       
        } 
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
         return taiKhoanList;
    }
    public void hienThiDanhSachTK(){
        ArrayList<TaiKhoan> list = TaiKhoanList();
        dfTable = (DefaultTableModel) BangTaiKhoan.getModel();
        Object[] row = new Object[6];
        int c = 0;
        for(int i =0; i < list.size();i++){
            c++;
            row[0] = c;
            row[1] = list.get(i).getId();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getUser();
            row[4] = list.get(i).getPassword();
            row[5] = list.get(i).getChucVu();
            dfTable.addRow(row);
        }
        
    }

   
    public void HienThiComboBox(){
        cbMaNV.removeAllItems();
        cbTenNV.removeAllItems();
        listComboItem.clear();
        String query = " select * from nhanvien";
        conn = kn.getConn();
        try{
             statement = conn.createStatement();
           result = statement.executeQuery(query);
           while(result.next()){
               cbMaNV.addItem(result.getString("MaNV"));
               listComboItem.add(result.getString("MaNV"));
               cbTenNV.addItem(result.getString("TenNV"));
               listComboItem1.add(result.getString("TenNV"));
               
           }
        }
        catch(SQLException ex){
            
        }
        
        
    }
   
    public void BangTaiKhoanClick(){
        
       int i = BangTaiKhoan.getSelectedRow();
       TableModel model = BangTaiKhoan.getModel();
   
        cbMaNV.setSelectedIndex(listComboItem.indexOf(model.getValueAt(i,1).toString()));
        cbTenNV.setSelectedIndex(listComboItem1.indexOf(model.getValueAt(i,2).toString()));
        txtTaiKhoan.setText(model.getValueAt(i,3 ).toString());
        txtMatKhau.setText(model.getValueAt(i, 4).toString());
        String quyen = model.getValueAt(i, 5).toString();
        switch (quyen) {
            case "Thủ kho":
                cbQuyen.setSelectedIndex(0);
                break;
            case "Nhân viên bán hàng":
                cbQuyen.setSelectedIndex(1);
                break;
            case "Giám đốc":
                cbQuyen.setSelectedIndex(2);
                break;
            default:
                break;
        }
       
        
        
      
    }
    public void TruyVan(String query){
         
        conn = kn.getConn();
       try {
            statement = conn.createStatement();
            if((statement.executeUpdate(query))==1){
                dfTable = (DefaultTableModel) BangTaiKhoan.getModel();
                dfTable.setRowCount(0);
                hienThiDanhSachTK();
                
            }
            
            
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        label_anh = new javax.swing.JLabel();
        btnDoiAnh = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BangNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbChucVu = new javax.swing.JComboBox<>();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jrNam = new javax.swing.JRadioButton();
        jrNu = new javax.swing.JRadioButton();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JScrollPane();
        txtghiChu = new javax.swing.JTextArea();
        btnClear = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Panel_TaiKhoanNV = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BangTaiKhoan = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbMaNV = new javax.swing.JComboBox<>();
        cbTenNV = new javax.swing.JComboBox<>();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        cbQuyen = new javax.swing.JComboBox<>();
        btnClearTK = new javax.swing.JButton();
        btnThemTK = new javax.swing.JButton();
        btnXoaTK = new javax.swing.JButton();
        btnSuaTK = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(1300, 630));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 255));
        jLabel11.setText("Tìm Kiếm");

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(102, 102, 255));
        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(102, 102, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        label_anh.setBackground(new java.awt.Color(255, 255, 255));

        btnDoiAnh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDoiAnh.setForeground(new java.awt.Color(102, 102, 255));
        btnDoiAnh.setText("Đổi ảnh");
        btnDoiAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiAnhActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 255));
        jLabel19.setText("Ảnh Nhân Viên");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel11)
                .addGap(49, 49, 49)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(label_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDoiAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        BangNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã  nhân viên", "Tên nhân viên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Ghi chú"
            }
        ));
        BangNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(BangNhanVien);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Mã nhân viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Họ tên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Chức vụ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("Giới tính");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setText("Ngày sinh");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setText("Địa chỉ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 255));
        jLabel8.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 255));
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 255));
        jLabel10.setText("Ghi chú");

        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên bán hàng", "Thủ kho", "Giám đốc" }));

        buttonGroup1.add(jrNam);
        jrNam.setText("Nam");

        buttonGroup1.add(jrNu);
        jrNu.setText("Nữ");

        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });

        txtghiChu.setColumns(20);
        txtghiChu.setRows(5);
        txtGhiChu.setViewportView(txtghiChu);

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnClear.setForeground(new java.awt.Color(102, 102, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThem.setForeground(new java.awt.Color(102, 102, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnIn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnIn.setForeground(new java.awt.Color(102, 102, 255));
        btnIn.setText("In");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSua.setForeground(new java.awt.Color(102, 102, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(102, 102, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(70, 70, 70)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(58, 58, 58)
                                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jrNam)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jrNu))
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrNam)
                    .addComponent(jrNu)
                    .addComponent(jLabel5))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 204));
        jLabel12.setText("THÔNG TIN NHÂN VIÊN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân viên", jPanel4);

        Panel_TaiKhoanNV.setBackground(new java.awt.Color(204, 204, 255));
        Panel_TaiKhoanNV.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                Panel_TaiKhoanNVComponentShown(evt);
            }
        });

        BangTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Username", "Password", "Quyền"
            }
        ));
        BangTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BangTaiKhoan);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setText("Tên nhân viên");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel14.setText("Tài khoản");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel15.setText("Mật khẩu");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel16.setText("Quyền");

        cbQuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thủ kho", "Nhân viên bán hàng", "Giám đốc" }));

        btnClearTK.setText("Clear");
        btnClearTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTKActionPerformed(evt);
            }
        });

        btnThemTK.setText("Thêm");
        btnThemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTKActionPerformed(evt);
            }
        });

        btnXoaTK.setText("Xóa");
        btnXoaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTKActionPerformed(evt);
            }
        });

        btnSuaTK.setText("Sửa");
        btnSuaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTKActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel17.setText("THÔNG TIN TÀI KHOẢN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(btnSuaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClearTK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(btnThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                                .addComponent(btnXoaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cbQuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMatKhau)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel13))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel17)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_TaiKhoanNVLayout = new javax.swing.GroupLayout(Panel_TaiKhoanNV);
        Panel_TaiKhoanNV.setLayout(Panel_TaiKhoanNVLayout);
        Panel_TaiKhoanNVLayout.setHorizontalGroup(
            Panel_TaiKhoanNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TaiKhoanNVLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        Panel_TaiKhoanNVLayout.setVerticalGroup(
            Panel_TaiKhoanNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TaiKhoanNVLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_TaiKhoanNVLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài Khoản", Panel_TaiKhoanNV);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinhActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchNV();
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
       
        clear();
        BangNhanVien.setModel(new DefaultTableModel(null,new String[]{"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày Sinh","Địa chỉ","Số điện thọai","Email","Ghi Chú"}));
        hienThiDanhSachNV();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
      
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
 
        xuLyThem();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
 
        xuLySua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xuLyXoa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void BangNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangNhanVienMouseClicked

        hienThongTin();
    }//GEN-LAST:event_BangNhanVienMouseClicked

    private void BangTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangTaiKhoanMouseClicked
        // TODO add your handling code here:
        BangTaiKhoanClick();
        
    }//GEN-LAST:event_BangTaiKhoanMouseClicked

    private void Panel_TaiKhoanNVComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Panel_TaiKhoanNVComponentShown
    
      HienThiComboBox();
    }//GEN-LAST:event_Panel_TaiKhoanNVComponentShown

    private void btnThemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTKActionPerformed
        // TODO add your handling code here:
        String query = "insert into taikhoan values ('"+txtTaiKhoan.getText()+"',"
                + "'"+txtMatKhau.getText()+"','"+cbMaNV.getSelectedItem().toString()+"')";  
        TruyVan(query);
            
        
    }//GEN-LAST:event_btnThemTKActionPerformed

    private void btnXoaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTKActionPerformed
        // TODO add your handling code here:
        String query = "delete from taikhoan where Username = '"+txtTaiKhoan.getText()+"'";   
        TruyVan(query);
        
           
    }//GEN-LAST:event_btnXoaTKActionPerformed

    private void btnSuaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTKActionPerformed
        // TODO add your handling code here:
        String query = "update taikhoan set Username = '"+txtTaiKhoan.getText()+"', MatKhau ='"+txtMatKhau.getText()+"',MaNV = '"+cbMaNV.getSelectedItem().toString()+"' where Username = '"+txtTaiKhoan.getText()+"' ";
        TruyVan(query);
    }//GEN-LAST:event_btnSuaTKActionPerformed

    private void btnClearTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTKActionPerformed
        // TODO add your handling code here:
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        cbMaNV.setSelectedIndex(0);
        cbTenNV.setSelectedIndex(0);
        cbQuyen.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearTKActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed

        MessageFormat header = new MessageFormat("BẢNG NHÂN VIÊN");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            try {
                BangNhanVien.print(JTable.PrintMode.NORMAL,header,footer);
            } catch (PrinterException ex) {
                Logger.getLogger(Panel_QuanTri.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnDoiAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiAnhActionPerformed
        // TODO add your handling code here:
      JFileChooser doiAnh = new JFileChooser();
      doiAnh.showOpenDialog(null);
      File f = doiAnh.getSelectedFile();
      tenAnh = f.getAbsolutePath();
      ImageIcon imageIcon = new ImageIcon(new ImageIcon(tenAnh).getImage().getScaledInstance(label_anh.getWidth(), label_anh.getHeight(), Image.SCALE_SMOOTH));
      label_anh.setIcon(imageIcon);
      try{
          File image = new File(tenAnh);
          FileInputStream fis = new FileInputStream(image);
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          byte[] buf = new byte[1024];
          for (int readNum; (readNum = fis.read(buf)) !=-1;){
              bos.write(buf,0,readNum);
              
          }
          anhNhanVien = bos.toByteArray();
      }
      catch(IOException e){
          
      }
      
    }//GEN-LAST:event_btnDoiAnhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangNhanVien;
    private javax.swing.JTable BangTaiKhoan;
    private javax.swing.JPanel Panel_TaiKhoanNV;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearTK;
    private javax.swing.JButton btnDoiAnh;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaTK;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTK;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTK;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbChucVu;
    private javax.swing.JComboBox<String> cbMaNV;
    private javax.swing.JComboBox<String> cbQuyen;
    private javax.swing.JComboBox<String> cbTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton jrNam;
    private javax.swing.JRadioButton jrNu;
    private javax.swing.JLabel label_anh;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JScrollPane txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextArea txtghiChu;
    // End of variables declaration//GEN-END:variables
}
