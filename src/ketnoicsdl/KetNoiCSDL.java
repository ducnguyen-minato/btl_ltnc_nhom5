/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ketnoicsdl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author H
 */
public class KetNoiCSDL {
    private Connection conn;
    private static final String url       = "jdbc:mysql://localhost:3306/QuanLySieuThiDienMay";
    private static final String user      = "root";
    private static final String password  = "ngocdan!!";
    
    public KetNoiCSDL() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
