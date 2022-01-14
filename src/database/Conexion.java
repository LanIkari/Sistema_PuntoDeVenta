/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "dbsistema";
    private final String USER = "root";
    private final String PASSWORD = "1202945Angel";

    public Connection cadena;
    public static Conexion instancia;
   

    private Conexion() {
        this.cadena = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL + DB, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }

    public void desconectar() {
        try {
            this.cadena.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    //De esta forma ya no tendemos problemas en la consistencia de datos
    //pues primero se dara la llamada a la base de datos y despues atendera otra.
    public synchronized static Conexion getInstancia(){
        if (instancia==null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
