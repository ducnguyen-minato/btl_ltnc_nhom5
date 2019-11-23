//Phan Văn Thịnh code 23-11-2019
package Form;

import DoiTuong.HangHoa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ketnoicsdl.KetNoiCSDL;


public class Panel_HangHoa extends javax.swing.JPanel {

    // liên kết cơ sở dữ liệu
    public static Connection conn = null;
    public static Statement statement = null;
    public static ResultSet result = null;
    HangHoa hangHoa;
    ketnoicsdl.KetNoiCSDL kn = new KetNoiCSDL();
    DefaultTableModel dfTableHH;
    
    //ham khởi tạo
    public Panel_HangHoa() {
        initComponents();
        hienThiDanhSachHangHoa();
    }

    
    // tạo mảng động danh sách hàng hóa
    public ArrayList<HangHoa> HangHoaList() {
        ArrayList<HangHoa> hangHoaList = new ArrayList<HangHoa>();
        conn = kn.getConn();
        String query = "select * from hanghoa";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                hangHoa = new HangHoa(
                        result.getString("MaHH"),result.getString("TenHH"),result.getString("NSanXuat"),
                        result.getString("MaNCC"),result.getString("Anh_path") ,result.getDouble("GiaNhap"),
                        result.getDouble("GiaBan"));
                hangHoaList.add(hangHoa);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return hangHoaList;
    }
    
    // hàm hiển thị danh sach hàng hóa
    public void hienThiDanhSachHangHoa(){
        ArrayList<HangHoa> list = HangHoaList();
        dfTableHH = (DefaultTableModel) BangHangHoa.getModel();
        Object[] row = new Object[6];
        for(int i = 0; i<list.size(); i++){
            row[0] = list.get(i).getMaHangHoa();
            row[1] = list.get(i).getTenHangHoa();
            row[2] = list.get(i).getNhaSanXuat();
            row[3] = list.get(i).getMaNhaCungCap();
            row[4] = list.get(i).getGiaNhap();
            row[5] = list.get(i).getGiaBan();
            dfTableHH.addRow(row);
        }
    }
    
    //hàm tực hiện truy vấn vào cơ sở dữ liệu
    public void thucHienTruyVan(String query, String message){
        conn = kn.getConn();
       try {
            statement = conn.createStatement();
            if((statement.executeUpdate(query))==1){
                dfTableHH = (DefaultTableModel) BangHangHoa.getModel();
                dfTableHH.setRowCount(0);
                hienThiDanhSachHangHoa();               
            }
            else 
                JOptionPane.showMessageDialog(null,"Data not"+message);
            
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
    }
    
    // ham thuc hien chuc nag them
     private void xuLyThem() {
        String sql = "insert into hanghoa values (' "+txtMaHH.getText()+"','"+txtTenHH.getText()+"','"+txtNSX.getText()+"','"
                                                       +txtMaNCC.getText()+"','"+txtGiaNhap.getText()+"','"+txtGiaBan.getText()+"' )";
         thucHienTruyVan(sql, "Thêm");
         clear();
    }
     
    //hàm xóa trắng các textfield
    public void clear(){
        txtMaHH.setText("");
        txtTenHH.setText("");
        txtNSX.setText("");
        txtMaNCC.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
    }
    
    //hàm sửa thông tin Hàng hóa
    private void xuLySua(){
            String sql = "update hanghoa set MaHH ='"+txtMaHH.getText()+"', "
                     + "TenHH = '"+txtTenHH.getText()+"',"
                     + "NSanXuat = '"+txtNSX.getText()+"',"
                     + "MaNCC = '"+txtMaNCC.getText()+"',"
                     + "GiaNhap ='"+txtGiaNhap.getText()+"',"
                     + "GiaBan ='"+txtGiaBan.getText()+"',"
                     + "where MaHH ='"+txtMaHH.getText()+"'";
            thucHienTruyVan(sql,"Sửa");
            clear();
    }
    
    // xử lý xóa thông tin Hàng hóa
     private void xuLyXoa(){       
            String sql = "delete from nhanvien where MaKH ='"+txtMaHH.getText()+"'";
            thucHienTruyVan(sql,"Xóa");
            clear();
    }
     
     //hàm tìm kiếm hàng hóa
     private void searchHH() {
       String lenhTim = txtSearch.getText();
       String query1 = "select * from quanlysieuthidienmay.HangHoa where MaHH like '%"+lenhTim+"%'"
               + "or TenHH like '%"+lenhTim+"%' or MaNCC like '%"+lenhTim+"%'";
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
    
    // hàm xóa bảng
    private void xoaBang() {
        int length = BangHangHoa.getRowCount();
        int x = BangHangHoa.getColumnCount();
        for (int row = 0; row < length; row++) {
            if (BangHangHoa.getValueAt(row,0) == null)
                break;
            for (int col = 0; col < x; col++) {
                BangHangHoa.setValueAt("", row, col);
            }
        }
       
    }
    
    //hàm show dữ liệu trong bảng
    private void showData(ResultSet rs1) throws SQLException {
        int row = 0;
        int col = 0;
       
        while (rs1.next()) {
            BangHangHoa.setValueAt(rs1.getString("MaHH"), row, col);
            col++;
            BangHangHoa.setValueAt(rs1.getString("TenHH"), row, col);
            col++;
            BangHangHoa.setValueAt(rs1.getString("NSanXuat"), row, col);
            col++;
            BangHangHoa.setValueAt(rs1.getString("MaNCC"), row, col);
            col++;
            BangHangHoa.setValueAt(rs1.getString("GiaNhap"), row, col);
            col++;
            BangHangHoa.setValueAt(rs1.getString("GiaBan"), row, col);
            col++;
        }
    }
    
    // hàm hiển thị thông tin hàng hóa
    private void hienThongTin(){
        int i = BangHangHoa.getSelectedRow();
        TableModel model = BangHangHoa.getModel();
        txtMaHH.setText(model.getValueAt(i,0).toString());
        txtTenHH.setText(model.getValueAt(i,1).toString());
        txtNSX.setText(model.getValueAt(i,2).toString());
        txtMaNCC.setText(model.getValueAt(i,3).toString());
        txtGiaNhap.setText(model.getValueAt(i,4).toString());
        txtGiaBan.setText(model.getValueAt(i,5).toString());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        BangHangHoa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNSX = new javax.swing.JTextField();
        jbLamMoi = new javax.swing.JButton();
        jbThem = new javax.swing.JButton();
        jbXoa = new javax.swing.JButton();
        jbSua = new javax.swing.JButton();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();
        txtMaHH = new javax.swing.JTextField();
        txtTenHH = new javax.swing.JTextField();
        jbIn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jbTim = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jbClear = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 0));
        setForeground(new java.awt.Color(255, 51, 0));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(null);

        BangHangHoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hàng hóa", "Tên hàng hóa", "Nhà sản xuất", "Mã nhà cung cấp", "Giá nhập", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(BangHangHoa);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 720, 400);

        jLabel2.setBackground(new java.awt.Color(0, 0, 153));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin hàng hóa");
        add(jLabel2);
        jLabel2.setBounds(903, 13, 220, 30);

        jLabel3.setBackground(new java.awt.Color(0, 0, 153));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Mã hàng hóa:");
        add(jLabel3);
        jLabel3.setBounds(770, 80, 100, 30);

        jLabel4.setBackground(new java.awt.Color(0, 0, 153));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Tên hàng hóa:");
        add(jLabel4);
        jLabel4.setBounds(770, 130, 100, 30);

        jLabel5.setBackground(new java.awt.Color(0, 0, 153));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Nhà sản xuất:");
        add(jLabel5);
        jLabel5.setBounds(770, 180, 100, 30);

        jLabel6.setBackground(new java.awt.Color(0, 0, 153));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 204));
        jLabel6.setText("Mã nhà cung cấp:");
        add(jLabel6);
        jLabel6.setBounds(770, 230, 120, 30);

        jLabel7.setBackground(new java.awt.Color(0, 0, 153));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Giá nhập:");
        add(jLabel7);
        jLabel7.setBounds(770, 280, 100, 30);

        jLabel8.setBackground(new java.awt.Color(0, 0, 153));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("Giá bán:");
        add(jLabel8);
        jLabel8.setBounds(770, 330, 100, 30);

        txtNSX.setForeground(new java.awt.Color(0, 51, 204));
        txtNSX.setBorder(null);
        add(txtNSX);
        txtNSX.setBounds(890, 180, 283, 30);

        jbLamMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbLamMoi.setForeground(new java.awt.Color(0, 51, 204));
        jbLamMoi.setText("Làm mới");
        jbLamMoi.setBorder(null);
        jbLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLamMoiActionPerformed(evt);
            }
        });
        add(jbLamMoi);
        jbLamMoi.setBounds(650, 510, 80, 50);

        jbThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbThem.setForeground(new java.awt.Color(0, 51, 204));
        jbThem.setText("Thêm");
        jbThem.setBorder(null);
        jbThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbThemActionPerformed(evt);
            }
        });
        add(jbThem);
        jbThem.setBounds(10, 510, 90, 50);

        jbXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbXoa.setForeground(new java.awt.Color(0, 51, 204));
        jbXoa.setText("Xóa");
        jbXoa.setBorder(null);
        jbXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbXoaActionPerformed(evt);
            }
        });
        add(jbXoa);
        jbXoa.setBounds(330, 510, 90, 50);

        jbSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbSua.setForeground(new java.awt.Color(0, 51, 204));
        jbSua.setText("Sửa");
        jbSua.setBorder(null);
        jbSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSuaActionPerformed(evt);
            }
        });
        add(jbSua);
        jbSua.setBounds(170, 510, 90, 50);

        txtGiaBan.setForeground(new java.awt.Color(0, 51, 204));
        txtGiaBan.setBorder(null);
        add(txtGiaBan);
        txtGiaBan.setBounds(890, 330, 283, 30);

        txtGiaNhap.setForeground(new java.awt.Color(0, 51, 204));
        txtGiaNhap.setBorder(null);
        add(txtGiaNhap);
        txtGiaNhap.setBounds(890, 280, 283, 30);

        txtMaNCC.setForeground(new java.awt.Color(0, 51, 204));
        txtMaNCC.setBorder(null);
        add(txtMaNCC);
        txtMaNCC.setBounds(890, 230, 283, 30);

        txtMaHH.setForeground(new java.awt.Color(0, 51, 204));
        txtMaHH.setBorder(null);
        add(txtMaHH);
        txtMaHH.setBounds(890, 80, 283, 30);

        txtTenHH.setForeground(new java.awt.Color(0, 51, 204));
        txtTenHH.setBorder(null);
        add(txtTenHH);
        txtTenHH.setBounds(890, 130, 283, 30);

        jbIn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbIn.setForeground(new java.awt.Color(0, 51, 204));
        jbIn.setText("In");
        jbIn.setBorder(null);
        add(jbIn);
        jbIn.setBounds(490, 510, 90, 50);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Tìm kiếm");
        add(jLabel1);
        jLabel1.setBounds(120, 440, 90, 40);
        add(txtSearch);
        txtSearch.setBounds(230, 440, 260, 40);

        jbTim.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbTim.setForeground(new java.awt.Color(0, 51, 204));
        jbTim.setText("Tìm");
        jbTim.setBorder(null);
        jbTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTimActionPerformed(evt);
            }
        });
        add(jbTim);
        jbTim.setBounds(530, 440, 100, 40);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_hanghoa.png"))); // NOI18N
        add(jLabel9);
        jLabel9.setBounds(900, 420, 260, 150);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 2, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Hình ảnh hàng hóa:");
        add(jLabel10);
        jLabel10.setBounds(980, 570, 110, 16);

        jbClear.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbClear.setForeground(new java.awt.Color(0, 0, 204));
        jbClear.setText("Clear");
        jbClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClearActionPerformed(evt);
            }
        });
        add(jbClear);
        jbClear.setBounds(960, 380, 140, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jbThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbThemActionPerformed
        xuLyThem();
    }//GEN-LAST:event_jbThemActionPerformed

    private void jbLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLamMoiActionPerformed
        clear();
        BangHangHoa.setModel(new DefaultTableModel(null,new String[]{"Mã hàng hóa","Tên hàng hóa","Nhà sản xuất","Mã nhà cung cấp","Giá nhập","Giá bán"}));
        hienThiDanhSachHangHoa();
    }//GEN-LAST:event_jbLamMoiActionPerformed

    private void jbSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSuaActionPerformed
        xuLySua();
    }//GEN-LAST:event_jbSuaActionPerformed

    private void jbTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTimActionPerformed
        searchHH();
    }//GEN-LAST:event_jbTimActionPerformed

    private void jbXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbXoaActionPerformed
        xuLyXoa();
    }//GEN-LAST:event_jbXoaActionPerformed

    private void jbClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClearActionPerformed
        clear();
    }//GEN-LAST:event_jbClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangHangHoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbClear;
    private javax.swing.JButton jbIn;
    private javax.swing.JButton jbLamMoi;
    private javax.swing.JButton jbSua;
    private javax.swing.JButton jbThem;
    private javax.swing.JButton jbTim;
    private javax.swing.JButton jbXoa;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaHH;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtNSX;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenHH;
    // End of variables declaration//GEN-END:variables
}
