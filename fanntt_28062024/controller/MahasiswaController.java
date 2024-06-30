/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.controller;

import fanntt_28062024.view.FormMahasiswa;
import fanntt_28062024.model.Mahasiswa;
import fanntt_28062024.model.MahasiswaDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * 
 */
public class MahasiswaController {
    FormMahasiswa formMahasiswa;
    Mahasiswa mahasiswa;
    MahasiswaDao mahasiswaDao;
    int index;
    
    public MahasiswaController(FormMahasiswa formMahasiswa){
        this.formMahasiswa = formMahasiswa;
        mahasiswaDao = new MahasiswaDao();
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
        mahasiswa = new Mahasiswa();
        mahasiswa.setNobp(formMahasiswa.getTxtNobp().getText());
        mahasiswa.setNama(formMahasiswa.getTxtNama().getText());
        mahasiswa.setAlamat(formMahasiswa.getTxtAlamat().getText());
        mahasiswa.setJenisKelamin(formMahasiswa.getCboJenisKelamin().getSelectedItem().toString());
        mahasiswa.setTanggalLahir(formMahasiswa.getTxtTanggalLahir().getText());
        mahasiswaDao.insert(mahasiswa);
        JOptionPane.showMessageDialog(formMahasiswa, "Entri Data Ok");
    }
    
    public void viewData(){
        DefaultTableModel model = (DefaultTableModel)formMahasiswa.getTabelMahasiswa().getModel();
        model.setNumRows(0);
        List<Mahasiswa> list = mahasiswaDao.getAllMahasiswa();
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
    }
    
    public  void actionClickTabel(){
        index = formMahasiswa.getTabelMahasiswa().getSelectedRow();
        mahasiswa = mahasiswaDao.getMahasiswa(index);
        formMahasiswa.getTxtNobp().setText(mahasiswa.getNobp());
        formMahasiswa.getTxtNama().setText(mahasiswa.getNama());
        formMahasiswa.getTxtAlamat().setText(mahasiswa.getAlamat());
        formMahasiswa.getCboJenisKelamin().setSelectedItem(mahasiswa.getJenisKelamin());
    }
    
    public void update(){
        
        mahasiswa.setNobp(formMahasiswa.getTxtNobp().getText());
        mahasiswa.setNama(formMahasiswa.getTxtNama().getText());
        mahasiswa.setAlamat(formMahasiswa.getTxtAlamat().getText());
        mahasiswa.setJenisKelamin(formMahasiswa.getCboJenisKelamin()
                .getSelectedItem().toString());
        mahasiswa.setTanggalLahir(formMahasiswa.getTxtTanggalLahir().getText());
        mahasiswaDao.update(index, mahasiswa);
        JOptionPane.showMessageDialog(formMahasiswa, "Update Data Ok");
    }
    
    public void delete(){
        mahasiswaDao.delete(index);
        JOptionPane.showMessageDialog(formMahasiswa, "Delete Data Ok");
    }
    
}
