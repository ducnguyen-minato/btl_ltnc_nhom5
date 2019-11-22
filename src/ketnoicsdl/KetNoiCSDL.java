/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ketnoicsdl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class KetNoiCSDL {
    private Connection conn;
    private static final String url       = "jdbc:mysql://localhost/quanlysieuthidienmay1?" + "useUnicode=true&characterEncoding=utf-8";
    private static final String user      = "root";
    private static final String password  = "123456";
    
    public KetNoiCSDL(){
        try {
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Ket noi db thanh cong");
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiCSDL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("abc");
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
