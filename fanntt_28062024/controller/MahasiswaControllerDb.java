/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.controller;

import fanntt_28062024.view.FormMahasiswa;
import fanntt_28062024.model.Mahasiswa;
import fanntt_28062024.db.MahasiswaDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 *
 */
public class MahasiswaControllerDb {
    FormMahasiswa formMahasiswa;
    Mahasiswa mahasiswa;
    MahasiswaDb mahasiswaDb;
    
    public MahasiswaControllerDb(FormMahasiswa formMahasiswa){
        this.formMahasiswa = formMahasiswa;
        mahasiswaDb = new MahasiswaDb();
    }

    public void cancel(){
        formMahasiswa.getTxtNobp().setText("");
        formMahasiswa.getTxtNama().setText("");
        formMahasiswa.getTxtAlamat().setText("");
        formMahasiswa.getTxtTanggalLahir().setText("");    
        isiJenisKelamin();
    } 
    
    public void isiJenisKelamin(){
        formMahasiswa.getCboJenisKelamin().removeAllItems();
        formMahasiswa.getCboJenisKelamin().addItem("L");
        formMahasiswa.getCboJenisKelamin().addItem("P");
    }
    
    public void insert(){
        try {
            mahasiswa = new Mahasiswa();
            mahasiswa.setNobp(formMahasiswa.getTxtNobp().getText());
            mahasiswa.setNama(formMahasiswa.getTxtNama().getText());
            mahasiswa.setAlamat(formMahasiswa.getTxtAlamat().getText());
            mahasiswa.setJenisKelamin(formMahasiswa.getCboJenisKelamin()
                    .getSelectedItem().toString());
            mahasiswa.setTanggalLahir(formMahasiswa.getTxtTanggalLahir().getText());
            mahasiswaDb.insert(mahasiswa);
            JOptionPane.showMessageDialog(formMahasiswa, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formMahasiswa, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formMahasiswa.getTabelMahasiswa().getModel();
            model.setNumRows(0);
            List<Mahasiswa> list = mahasiswaDb.getAllMahasiswa();
            for(Mahasiswa mahasiswa : list){
                Object[] data = {
                    mahasiswa.getNobp(),
                    mahasiswa.getNama(),
                    mahasiswa.getAlamat(),
                    mahasiswa.getJenisKelamin(),
                    mahasiswa.getTanggalLahir()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String nobp = formMahasiswa.getTabelMahasiswa()
                    .getValueAt(formMahasiswa.getTabelMahasiswa().getSelectedRow(), 0).toString();
            mahasiswa = mahasiswaDb.getMahasiswa(nobp);
            formMahasiswa.getTxtNobp().setText(mahasiswa.getNobp());
            formMahasiswa.getTxtNama().setText(mahasiswa.getNama());
            formMahasiswa.getTxtAlamat().setText(mahasiswa.getAlamat());
            formMahasiswa.getCboJenisKelamin().setSelectedItem(mahasiswa.getJenisKelamin());
            formMahasiswa.getTxtTanggalLahir().setText(mahasiswa.getTanggalLahir());
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        
        try {
            mahasiswa.setNobp(formMahasiswa.getTxtNobp().getText());
            mahasiswa.setNama(formMahasiswa.getTxtNama().getText());
            mahasiswa.setAlamat(formMahasiswa.getTxtAlamat().getText());
            mahasiswa.setJenisKelamin(formMahasiswa.getCboJenisKelamin()
                    .getSelectedItem().toString());
            mahasiswa.setTanggalLahir(formMahasiswa.getTxtTanggalLahir().getText());
            mahasiswaDb.update(mahasiswa);
            JOptionPane.showMessageDialog(formMahasiswa, "Update Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            String nobp = formMahasiswa.getTabelMahasiswa()
                    .getValueAt(formMahasiswa.getTabelMahasiswa().getSelectedRow(), 0).toString();
            mahasiswaDb.delete(nobp);
            JOptionPane.showMessageDialog(formMahasiswa, "Delete Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
