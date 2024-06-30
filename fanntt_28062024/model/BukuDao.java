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
public class BukuDao {
    private List<Buku> data = new ArrayList<Buku>();

    public BukuDao() {
        data.add(new Buku("B001", "Pascal", "Fahmi", "1099"));
        data.add(new Buku("B002", "Matematika", "Sintia", "1895"));
        data.add(new Buku("B003", "Basis Data", "Fadhlur", "1005"));
        data.add(new Buku("B004", "PBO", "Zaki", "2008"));
        
    }
    
    public void insert(Buku m){
        data.add(m);
    }
    
    public void update(int index,Buku m){
        data.set(index,m);
    }
    
    public void delete(int index){
        data.remove(index);
    }
    
    public Buku getBuku(int index){
        return data.get(index);
    }
    
    public List<Buku> getAllBuku(){
        return data;
    }
}
