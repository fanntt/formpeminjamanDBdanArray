/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.controller;

import fanntt_28062024.db.BukuDb;
import fanntt_28062024.db.MahasiswaDb;
import fanntt_28062024.db.PeminjamanDb;
import fanntt_28062024.model.Buku;
import fanntt_28062024.model.Mahasiswa;
import fanntt_28062024.view.FormPeminjamanDb;
import fanntt_28062024.model.Peminjaman;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 */
public class PeminjamanControllerDb {

    FormPeminjamanDb formPeminjaman;
    Peminjaman peminjaman;
    MahasiswaDb mahasiswaDb;
    BukuDb bukuDb;
    PeminjamanDb peminjamanDb;

    public PeminjamanControllerDb(FormPeminjamanDb formPeminjaman) {
        this.formPeminjaman = formPeminjaman;
        mahasiswaDb = new MahasiswaDb();
        bukuDb = new BukuDb();
        peminjamanDb = new PeminjamanDb();
    }

    public void clearForm() {
        formPeminjaman.getTxtTglpinjam().setText("");
        formPeminjaman.getTxtTglkembali().setText("");
    }

    public void isiCboMahasiswa()  {
        try {
            List<Mahasiswa> list = mahasiswaDb.getAllMahasiswa();
            formPeminjaman.getCboMahasiswa().removeAllItems();
            for (Mahasiswa mahasiswa : list) {
                formPeminjaman.getCboMahasiswa()
                        .addItem(mahasiswa.getNobp() + "-" + mahasiswa.getNama());
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isiCboBuku() throws Exception {
        List<Buku> list = bukuDb.getAllBuku();
        formPeminjaman.getCboBuku().removeAllItems();
        for (Buku buku : list) {
            formPeminjaman.getCboBuku()
                    .addItem(buku.getKodeBuku() + "-" + buku.getJudulBuku());
        }
    }

    public void insert()  {
        try {
            peminjaman = new Peminjaman();
            
            Mahasiswa mahasiswa = mahasiswaDb.getMahasiswa(formPeminjaman.getCboMahasiswa().getSelectedItem().toString().split("-")[0]);
            Buku buku = bukuDb.getBuku(formPeminjaman.getCboBuku().getSelectedItem().toString().split("-")[0]);
            peminjaman.setMahasiswa(mahasiswa);
            peminjaman.setBuku(buku);
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglpinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().getText());
            peminjamanDb.insert(mahasiswa, buku, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri Data Ok");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(){
        try {
            Mahasiswa mahasiswa = mahasiswaDb.getMahasiswa(formPeminjaman.getCboMahasiswa().getSelectedItem().toString().split("-")[0]);
            Buku buku = bukuDb.getBuku(formPeminjaman.getCboBuku().getSelectedItem().toString().split("-")[0]);
            peminjaman.setMahasiswa(mahasiswa);
            peminjaman.setBuku(buku);
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglpinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglkembali().getText());
            peminjamanDb.update(peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Update Data Ok");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        try {
            String nobp = formPeminjaman.getTabelPeminjaman().getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 0).toString();
            String kodebuku = formPeminjaman.getTabelPeminjaman().getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 1).toString();
            
            peminjamanDb.delete(nobp,kodebuku);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete Data Ok");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void viewData()  {
        try {
            DefaultTableModel model
                    = (DefaultTableModel) formPeminjaman.getTabelPeminjaman().getModel();
            model.setNumRows(0);
            List<Peminjaman> list = peminjamanDb.getAllPeminjaman();
            for (Peminjaman peminjaman : list) {
                Object[] data = {
                    peminjaman.getMahasiswa().getNobp(),
                    peminjaman.getBuku().getKodeBuku(),
                    peminjaman.getTglpinjam(),
                    peminjaman.getTglkembali()
                        
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionClickTabel() {
        try {
            String nobp = formPeminjaman.getTabelPeminjaman().getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 0).toString();
            String kodebuku = formPeminjaman.getTabelPeminjaman().getValueAt(formPeminjaman.getTabelPeminjaman().getSelectedRow(), 1).toString();
            
            peminjaman = peminjamanDb.getPeminjaman(nobp, kodebuku);
            formPeminjaman.getCboMahasiswa().setSelectedItem(peminjaman.getMahasiswa().getNobp() + "-" + peminjaman.getMahasiswa().getNama());
            formPeminjaman.getCboBuku().setSelectedItem(
                    peminjaman.getBuku().getKodeBuku() + "-" + peminjaman.getBuku().getJudulBuku());
            formPeminjaman.getTxtTglpinjam().setText(peminjaman.getTglpinjam());
            formPeminjaman.getTxtTglkembali().setText(peminjaman.getTglkembali());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PeminjamanControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
