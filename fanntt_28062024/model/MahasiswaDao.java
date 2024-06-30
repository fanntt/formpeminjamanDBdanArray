/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanntt_28062024.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class MahasiswaDao {
    private List<Mahasiswa> data = new ArrayList<Mahasiswa>();

    public MahasiswaDao() {
        data.add(new Mahasiswa("19312100", "Sintia", "Pesisir Selatan", "P", "01-08-2005"));
        data.add(new Mahasiswa("20312101", "Zaki", "Bukittingi", "P", "10-12-2004"));
        data.add(new Mahasiswa("22312102", "Fadhlur", "Koto Panjang", "L", "20-11-2000"));
        data.add(new Mahasiswa("23112102", "Fahmi", "Padang Panjang", "L", "15-01-2001"));
        
    }
    
    public void insert(Mahasiswa m){
        data.add(m);
    }
    
    public void update(int index,Mahasiswa m){
        data.set(index,m);
    }
    
    public void delete(int index){
        data.remove(index);
    }
    
    public Mahasiswa getMahasiswa(int index){
        return data.get(index);
    }
    
    public List<Mahasiswa> getAllMahasiswa(){
        return data;
    }
}
