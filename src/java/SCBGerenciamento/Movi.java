/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SCBGerenciamento;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author silvi
 */
public class Movi {
    
    private long rowid;
    private String container;
    private int tipo_movi;
    private String data_inicio;
    private String data_fim;

    public Movi(long rowid, String container, int tipo_movi, String data_inicio, String data_fim) {
        this.rowid = rowid;
        this.container = container;
        this.tipo_movi = tipo_movi;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }
    
     public static ArrayList<Movi> listar(){
        ArrayList<Movi> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        
       try{
        con = DBListener.connection();
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM movements;");
       
        while(rs.next()){
            list.add(new Movi(
                    rs.getLong("rowid"),
                    rs.getString("cd_container"),
                    rs.getInt("tipo_movi"),
                    rs.getString("data_inico"),
                    rs.getString("data_fim")
            ));
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                stmt.close();                
            }catch(Exception e){
            System.out.println(e.getMessage()); 
            }
        }
       return list;
    }
    
    public static void inseir(String container, int tipo_movi, String data_inicio, String data_fim) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
       try{
        con = DBListener.connection();
        stmt = con.prepareStatement("INSERT INTO movements VALUES (?,?,?,?);");
        stmt.setString(1, container);
        stmt.setInt(2, tipo_movi);
        stmt.setString(3, data_inicio);
        stmt.setString(4, data_fim);
                
        stmt.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                stmt.close();                
            }catch(Exception e){
            System.out.println(e.getMessage()); 
            }
        }
    }
    
    public static void deletar(Long rowid) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
       try{
        con = DBListener.connection();
        stmt = con.prepareStatement("DELETE FROM movements WHERE rowid = ?;");
        stmt.setLong(1, rowid);
                
        stmt.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                stmt.close();                
            }catch(Exception e){
            System.out.println(e.getMessage()); 
            }
        }
    }
    
    public static String moviments(int tipo){
        switch(tipo){
            case 0:
                return "Embarque";
            case 1:
                return "Descarga";
            case 2:
                return "Gate In";
            case 3:
                return "Gate Out";
            case 4:
                return "Reposicionamento";
            case 5:
                return "Pesagem";
            case 6:
                return "Scanner";
            default:
                return "Erro ao buscar o tipo de movimentação";
        }
    }
    
    
    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getTipo_movi() {
        return tipo_movi;
    }

    public void setTipo_movi(int tipo_movi) {
        this.tipo_movi = tipo_movi;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inico) {
        this.data_inicio = data_inico;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
    
    
}
