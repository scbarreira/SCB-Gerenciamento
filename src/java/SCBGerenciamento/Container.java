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
public class Container {
    
    private String container;
    private String cliente;
    private int tipo;
    private int status;
    private int categoria;

    public Container(String container, String cliente, int tipo, int status, int categoria) {
        this.container = container;
        this.cliente = cliente;
        this.tipo = tipo;
        this.status = status;
        this.categoria = categoria;
    }
    
    public static ArrayList<Container> listar(){
        ArrayList<Container> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        
       try{
        con = DBListener.connection();
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM containers;");
       
        while(rs.next()){
            list.add(new Container(
                    rs.getString("cd_container"),
                    rs.getString("cliente"),
                    rs.getInt("tipo"),
                    rs.getInt("status"),
                    rs.getInt("categoria")
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
    
    public static void inseir(String container, String cliente, int tipo, int status, int categoria) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
       try{
        con = DBListener.connection();
        stmt = con.prepareStatement("INSERT INTO containers VALUES (?,?,?,?,?);");
        stmt.setString(1, container);
        stmt.setString(2, cliente);
        stmt.setInt(3, tipo);
        stmt.setInt(4, status);
        stmt.setInt(5, categoria);
                
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
    
    public static void deletar(String container) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
       try{
        con = DBListener.connection();
        stmt = con.prepareStatement("DELETE FROM containers WHERE cd_container = ?;");
        stmt.setString(1, container);
                
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

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    
    
}
