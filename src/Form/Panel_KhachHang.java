// Phan Văn Thịnh code 23-11-2019
package Form;

import DoiTuong.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ketnoicsdl.KetNoiCSDL;


public class Panel_KhachHang extends javax.swing.JPanel {

    // liên kết cơ sở dữ liệu
    public static Connection conn = null;
    public static Statement statement = null;
    public static ResultSet result = null;
    KhachHang khachHang;
    ketnoicsdl.KetNoiCSDL kn = new KetNoiCSDL();
    DefaultTableModel dfTableKH;
    
    // hàm khởi tạo
    public Panel_KhachHang() {
        initComponents();
        hienThiDanhSachKhachHang();
    }
    
    // tạo mảng động danh sách khách hàng
    public ArrayList<KhachHang> KhachHangList() {
        ArrayList<KhachHang> khachHangList = new ArrayList<KhachHang>();
        conn = kn.getConn();
        String query = "select * from khachhang";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                khachHang = new KhachHang(
                        result.getString("MaKH"),result.getString("TenKH"),result.getString("NgaySinh"),
                        result.getString("SDT"),result.getString("GioiTinh"),result.getString("DiaChi"),
                        result.getDouble("TongTien"));
                khachHangList.add(khachHang);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return khachHangList;
    }
    
    // hàm hiển thị danh sach khách hàng
    public void hienThiDanhSachKhachHang(){
        ArrayList<KhachHang> list = KhachHangList();
        dfTableKH = (DefaultTableModel) BangKhachHang.getModel();
        Object[] row = new Object[7];
        for(int i = 0; i<list.size(); i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getNgaySinh();
            row[3] = list.get(i).getSoDienThoai();
            row[4] = list.get(i).getGioiTinh();
            row[5] = list.get(i).getDiaChi();
            row[6] = list.get(i).getTongTien();
            dfTableKH.addRow(row);
        }
    }
    
    //hàm tực hiện truy vấn vào cơ sở dữ liệu
    public void thucHienTruyVan(String query, String message){
        conn = kn.getConn();
       try {
            statement = conn.createStatement();
            if((statement.executeUpdate(query))==1){
                dfTableKH = (DefaultTableModel) BangKhachHang.getModel();
                dfTableKH.setRowCount(0);
                hienThiDanhSachKhachHang();               
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
        String gioiTinh;
        if(jrNam.isSelected()){
            gioiTinh = "Nam";
        }
        else {
            gioiTinh ="Nữ";
        }
        String sql = "insert into khachhang values (' "+txtMaKH.getText()+"','"+txtHoTen.getText()+"','"+txtNgaySinh.getText()+"','"
                                                       +txtSDT.getText()+"','"+gioiTinh+"','"+txtDiaChi.getText()+"','"+txtTongTien.getText()+"' )";
         thucHienTruyVan(sql, "Thêm");
         clear();
    }
     
    //hàm xóa trắng các textfield
    public void clear(){
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        jrNam.setSelected(true);
        txtDiaChi.setText("");
        txtTongTien.setText("");
    }
    
    //hàm sửa thông tin khách hàng
    private void xuLySua(){
        String gioiTinh;
        if(jrNam.isSelected()){
            gioiTinh = "Nam";
        }
        else {
            gioiTinh ="Nữ";
        }

             String sql = "update khachhang set MaKH ='"+txtMaKH.getText()+"', "
                     + "TenKH = '"+txtHoTen.getText()+"',"
                     + "NgaySinh = '"+txtNgaySinh.getText()+"',"
                     + "SDT = '"+txtSDT.getText()+"',"
                     + "GioiTinh = '"+gioiTinh+"',"
                     + "DiaChi ='"+txtDiaChi.getText()+"',"
                     + "TongTien ='"+txtTongTien.getText()+"',"
                     + "where MaKH ='"+txtMaKH.getText()+"'";
            thucHienTruyVan(sql,"Sửa");
            clear();
    }
    
    // xử lý xóa thông tin khoách hàng
     private void xuLyXoa(){       
            String sql = "delete from nhanvien where MaKH ='"+txtMaKH.getText()+"'";
            thucHienTruyVan(sql,"Xóa");
            clear();
    }
     
     //hàm tìm kiếm khách hàng
     private void searchKH() {
       String lenhTim = txtSearch.getText();
       String query1 = "select * from quanlysieuthidienmay.KhachHang where MaKH like '%"+lenhTim+"%'"
               + "or TenKH like '%"+lenhTim+"%' or SDT like '%"+lenhTim+"%'";
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
        int length = BangKhachHang.getRowCount();
        int x = BangKhachHang.getColumnCount();
        for (int row = 0; row < length; row++) {
            if (BangKhachHang.getValueAt(row,0) == null)
                break;
            for (int col = 0; col < x; col++) {
                BangKhachHang.setValueAt("", row, col);
            }
        }
       
    }
    
    //hàm show dữ liệu trong bảng
    private void showData(ResultSet rs1) throws SQLException {
        int row = 0;
        int col = 0;
       
        while (rs1.next()) {
            BangKhachHang.setValueAt(rs1.getString("MaKH"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getString("TenKH"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getString("NgaySinh"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getString("SDT"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getString("GioiTinh"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getString("DiaChi"), row, col);
            col++;
            BangKhachHang.setValueAt(rs1.getDouble("TongTien"), row, col);
            col++;
        }
    }
    
    // hàm hiển thị thông tin Khách hàng
    private void hienThongTin(){
        int i = BangKhachHang.getSelectedRow();
        TableModel model = BangKhachHang.getModel();
        txtMaKH.setText(model.getValueAt(i,0).toString());
        txtHoTen.setText(model.getValueAt(i,1).toString());
        txtNgaySinh.setText(model.getValueAt(i,2).toString());
        txtSDT.setText(model.getValueAt(i,3).toString());
        txtDiaChi.setText(model.getValueAt(i,5).toString());
        String gtinh = model.getValueAt(i,4).toString();
        if(gtinh.equals("Nam")){
            jrNam.setSelected(true);
        }
        else{
            jrNu.setSelected(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        BangKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jbTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jbThem = new javax.swing.JButton();
        jbXoa = new javax.swing.JButton();
        jbLamMoi = new javax.swing.JButton();
        jbSua = new javax.swing.JButton();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jbIn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jrNam = new javax.swing.JRadioButton();
        jrNu = new javax.swing.JRadioButton();
        jbClear = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 51));
        setPreferredSize(new java.awt.Dimension(1200, 600));

        BangKhachHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        BangKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Ngày sinh", "SDT", "Giới tính ", "Địa chỉ", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(BangKhachHang);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Tìm kiếm:");

        txtSearch.setForeground(new java.awt.Color(0, 0, 204));

        jbTim.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbTim.setForeground(new java.awt.Color(0, 0, 204));
        jbTim.setText("Tìm");
        jbTim.setBorder(null);
        jbTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTimActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin khách hàng");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Mã khách hàng:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Họ tên:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Ngày sinh:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("SĐT:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Giới tính:");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Địa chỉ:");

        txtNgaySinh.setForeground(new java.awt.Color(0, 0, 204));
        txtNgaySinh.setBorder(null);

        jbThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbThem.setForeground(new java.awt.Color(0, 0, 204));
        jbThem.setText("Thêm");
        jbThem.setBorder(null);
        jbThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbThemActionPerformed(evt);
            }
        });

        jbXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbXoa.setForeground(new java.awt.Color(0, 0, 204));
        jbXoa.setText("Xóa");
        jbXoa.setBorder(null);
        jbXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbXoaActionPerformed(evt);
            }
        });

        jbLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbLamMoi.setForeground(new java.awt.Color(0, 0, 204));
        jbLamMoi.setText("Làm mới");
        jbLamMoi.setBorder(null);
        jbLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLamMoiActionPerformed(evt);
            }
        });

        jbSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbSua.setForeground(new java.awt.Color(0, 0, 204));
        jbSua.setText("Sửa");
        jbSua.setBorder(null);
        jbSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSuaActionPerformed(evt);
            }
        });

        txtDiaChi.setForeground(new java.awt.Color(0, 0, 204));
        txtDiaChi.setBorder(null);

        txtSDT.setForeground(new java.awt.Color(0, 0, 204));
        txtSDT.setBorder(null);

        txtMaKH.setForeground(new java.awt.Color(0, 0, 204));
        txtMaKH.setBorder(null);

        txtHoTen.setForeground(new java.awt.Color(0, 0, 204));
        txtHoTen.setBorder(null);

        jbIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbIn.setForeground(new java.awt.Color(0, 0, 204));
        jbIn.setText("In");
        jbIn.setBorder(null);
        jbIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("Tổng tiền:");

        txtTongTien.setForeground(new java.awt.Color(0, 0, 204));
        txtTongTien.setBorder(null);

        buttonGroup1.add(jrNam);
        jrNam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jrNam.setForeground(new java.awt.Color(0, 0, 204));
        jrNam.setText("Nam");
        jrNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrNu);
        jrNu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jrNu.setForeground(new java.awt.Color(0, 0, 204));
        jrNu.setText("Nữ");

        jbClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbClear.setForeground(new java.awt.Color(0, 51, 153));
        jbClear.setText("Clear");
        jbClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1)
                                .addGap(35, 35, 35)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jbLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(769, 769, 769)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(919, 919, 919)
                        .addComponent(jbClear, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(902, 902, 902)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(jrNam)
                        .addGap(104, 104, 104)
                        .addComponent(jrNu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(889, 889, 889)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrNam)
                            .addComponent(jrNu))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jbClear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbTim)
                                .addComponent(jbLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 80, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbIn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbTim, txtSearch});

    }// </editor-fold>//GEN-END:initComponents

    private void jbThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbThemActionPerformed
        xuLyThem();
    }//GEN-LAST:event_jbThemActionPerformed

    private void jbXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbXoaActionPerformed
        xuLyXoa();
    }//GEN-LAST:event_jbXoaActionPerformed

    private void jbSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSuaActionPerformed
        xuLySua();
    }//GEN-LAST:event_jbSuaActionPerformed

    private void jrNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrNamActionPerformed

    private void jbLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLamMoiActionPerformed
        clear();
        BangKhachHang.setModel(new DefaultTableModel(null,new String[]{"Mã khách hàng","Họ tên","Ngày sinh","SDT","Giới tính","Địa chỉ","Tổng tiền"}));
        hienThiDanhSachKhachHang();
    }//GEN-LAST:event_jbLamMoiActionPerformed

    private void jbClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClearActionPerformed
        clear();
    }//GEN-LAST:event_jbClearActionPerformed

    private void jbTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTimActionPerformed
        searchKH();
    }//GEN-LAST:event_jbTimActionPerformed

    private void jbInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangKhachHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JRadioButton jrNam;
    private javax.swing.JRadioButton jrNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
