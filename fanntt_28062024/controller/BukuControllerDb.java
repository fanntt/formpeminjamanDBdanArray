/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.controller;

import fanntt_28062024.db.BukuDb;
import fanntt_28062024.model.Buku;
import fanntt_28062024.view.FormBuku;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 */
public class BukuControllerDb {
    FormBuku formbuku;
    Buku buku;
    BukuDb bukuDb;
    int index;
    
    public BukuControllerDb(FormBuku formbuku){
        this.formbuku = formbuku;
        bukuDb = new BukuDb();
    }

    
    
    public void cancel(){
        formbuku.getTxtKode().setText("");
        formbuku.getTxtJudul().setText("");
        formbuku.getTxtPengarang().setText("");
        formbuku.getTxtTahun().setText("");                      
    } 
    
    public void insert() throws Exception{
        buku = new Buku();
        buku.setKodeBuku(formbuku.getTxtKode().getText());
        buku.setJudulBuku(formbuku.getTxtJudul().getText());
        buku.setPengarang(formbuku.getTxtPengarang().getText());
        buku.setTahunTerbit(formbuku.getTxtTahun().getText());
        bukuDb.insert(buku);
        JOptionPane.showMessageDialog(formbuku, "Entri Data Ok");
    }
    
    public void viewData() throws Exception{
        DefaultTableModel model = (DefaultTableModel)formbuku.getTblBuku().getModel();
        model.setNumRows(0);
        List<Buku> list = bukuDb.getAllBuku();
        for(Buku buku : list){
            Object[] data = {
                buku.getKodeBuku(),
                buku.getJudulBuku(),
                buku.getPengarang(),
                buku.getTahunTerbit()                    
            };
            model.addRow(data);
        }         
    }
    
    public  void actionClickTabel() throws Exception{
        String kode= formbuku.getTblBuku().getValueAt(formbuku.getTblBuku().getSelectedRow(),0).toString();        
        buku= bukuDb.getBuku(kode);
        formbuku.getTxtKode().setText(buku.getKodeBuku());
        formbuku.getTxtJudul().setText(buku.getJudulBuku());
        formbuku.getTxtPengarang().setText(buku.getPengarang());
        formbuku.getTxtTahun().setText(buku.getTahunTerbit());    
    }
    
    public void update() throws Exception{
       buku.setKodeBuku(formbuku.getTxtKode().getText());
       buku.setJudulBuku(formbuku.getTxtJudul().getText());
       buku.setPengarang(formbuku.getTxtPengarang().getText());
       buku.setTahunTerbit(formbuku.getTxtTahun().getText());
       bukuDb.update(buku);
       JOptionPane.showMessageDialog(formbuku, "Update Data Ok");
    }
    
    public void delete() throws Exception{
        String kodebuku = formbuku.getTblBuku().getValueAt(formbuku.getTblBuku().getSelectedRow(),0).toString();
        bukuDb.delete(kodebuku);
        JOptionPane.showMessageDialog(formbuku, "Delete Data Ok");
                   
    } 
}
