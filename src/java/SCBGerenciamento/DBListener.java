/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SCBGerenciamento;

import java.sql.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author silvi
 */
public class DBListener implements ServletContextListener {

    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:C:\\Users\\silvi\\OneDrive\\Documentos\\NetBeansProjects\\CrudWeb\\dados\\dados.db"; 
    
    public static Connection connection() throws Exception{
        return DriverManager.getConnection(URL);
    }
    
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con = null;
        Statement stmt = null;
        
        
        try{
        Class.forName(CLASS_NAME);
        con = DBListener.connection();
        stmt = con.createStatement();
        
        String createContainer = ""
                +"CREATE TABLE IF NOT EXISTS containers("
                +"cd_container VARCHAR(11) NOT NULL PRIMARY KEY UNIQUE,"
                +"ccliente VARCHAR(255) NOT NULL"
                +"tipo int NOT NULL,"
                +"status int NOT NULL,"
                +"categoria int NOT NULL"
                +");";
        
       String createMovement = ""
                +"CREATE TABLE IF NOT EXISTS movements("
                +"cd_container VARCHAR(11)"
                +"tipo_movi int NOT NULL,"
                +"data_inicio DATATIME NOT NULL,"
                +"data_fim DATATIME NOT NULL"
                +");";
       
                
                stmt.execute(createContainer);
                stmt.execute(createMovement);
       
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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
