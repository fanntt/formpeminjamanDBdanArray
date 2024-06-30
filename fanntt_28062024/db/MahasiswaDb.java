/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.db;
import fanntt_28062024.model.Mahasiswa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * 
 */
public class MahasiswaDb {
    
    public void insert(Mahasiswa mahasiswa) throws Exception {
        DBHelper db = new DBHelper();
        String query = "insert into mahasiswa values(?,?,?,?,?)";
        PreparedStatement ps = db.getKoneksi().prepareStatement(query);
        ps.setString(1, mahasiswa.getNobp());
        ps.setString(2, mahasiswa.getNama());
        ps.setString(3, mahasiswa.getAlamat());
        ps.setString(4, mahasiswa.getJenisKelamin());
        ps.setString(5, mahasiswa.getTanggalLahir());
        ps.executeUpdate();
    }
    
    public void update(Mahasiswa mahasiswa) throws Exception {
        DBHelper db = new DBHelper();
        String query = "update mahasiswa set nama=?, alamat=?, "
                + "jeniskelamin=?, tanggallahir=? where nobp=?";
        PreparedStatement ps = db.getKoneksi().prepareStatement(query);
        ps.setString(1, mahasiswa.getNama());
        ps.setString(2, mahasiswa.getAlamat());
        ps.setString(3, mahasiswa.getJenisKelamin());
        ps.setString(4, mahasiswa.getTanggalLahir());
        ps.setString(5, mahasiswa.getNobp());
        ps.executeUpdate();
    }
    
    public void delete(String nobp) throws Exception{
        DBHelper db = new DBHelper();
        String query = "delete from mahasiswa where nobp=?";
        PreparedStatement ps = db.getKoneksi().prepareStatement(query);
        ps.setString(1, nobp);
        ps.executeUpdate();
    }
    
    public Mahasiswa getMahasiswa(String nobp) throws Exception{
        DBHelper db = new DBHelper();
        String query = "select * from mahasiswa where nobp=?";
        PreparedStatement ps = db.getKoneksi().prepareStatement(query);
        ps.setString(1, nobp);
        Mahasiswa mahasiswa = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            mahasiswa = new Mahasiswa();
            mahasiswa.setNobp(rs.getString(1));
            mahasiswa.setNama(rs.getString(2));
            mahasiswa.setAlamat(rs.getString(3));
            mahasiswa.setJenisKelamin(rs.getString(4));
            mahasiswa.setTanggalLahir(rs.getString(5));
        }
        return mahasiswa;
    }
    
    public List<Mahasiswa> getAllMahasiswa()  throws Exception{
        DBHelper db = new DBHelper();
        String query = "select * from mahasiswa";
        PreparedStatement ps = db.getKoneksi().prepareStatement(query);
        List<Mahasiswa> list = new ArrayList();
        Mahasiswa mahasiswa = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            mahasiswa = new Mahasiswa();
            mahasiswa.setNobp(rs.getString(1));
            mahasiswa.setNama(rs.getString(2));
            mahasiswa.setAlamat(rs.getString(3));
            mahasiswa.setJenisKelamin(rs.getString(4));
            mahasiswa.setTanggalLahir(rs.getString(5));
            list.add(mahasiswa);
        }
        return list;
    }
}
