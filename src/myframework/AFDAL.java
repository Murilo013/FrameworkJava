/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myframework;

import java.sql.*;

/**
 *
 * @author asenj
 */
public class AFDAL {

    static Connection conn = null;

    public static void conectdb(String dbname, String user, String password) {
    Erro.setErro(false);
    try {
        Class.forName("org.postgresql.Driver");
        
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);

        if (conn != null) {
            System.out.println("CONEXÃO ESTABELECIDA");
        } else {
            System.out.println("CONEXÃO FALHA");
        }

    } catch (Exception e) {
        Erro.setErro(e.getMessage());
        System.out.println("Erro: " + e.getMessage());
    }
}

    public static void desconecta() {
        Erro.setErro(false);
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }

    public static void executeSQL(String _sql) {
        Erro.setErro(false);      
        if (conn == null) {
            Erro.setErro("Conexão com o banco está nula.");
            return;
        }
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(_sql);
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }       
    }
    
    public static ResultSet executeQuery(String _sql) {
    Erro.setErro(false);
    try {
        Statement st = conn.createStatement();
        return st.executeQuery(_sql);
    } catch (Exception e) {
        Erro.setErro(e.getMessage());
        return null;
    }
}
}
