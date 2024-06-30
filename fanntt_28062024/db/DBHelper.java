                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBHelper {

    private String url = "jdbc:mysql://localhost/dbmahasiswa";
    private String username = "root";
    private String password = "";
    private Connection con;

    public Connection getKoneksi() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public static void main(String[] args) {
        DBHelper db = new DBHelper();
        try {
            db.getKoneksi();
            JOptionPane.showMessageDialog(null, "Koneksi OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                    "Koneksi Gagal "+ex.getMessage());
        }
    }
}
