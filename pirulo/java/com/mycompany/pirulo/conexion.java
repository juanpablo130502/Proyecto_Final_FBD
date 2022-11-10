
package com.mycompany.pirulo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {

    Connection con = null;
    String usuario = "root";
    String contraseña = "12345";
    String bd = "world";
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null,"Se ha conectado a la base de datos");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: " + e.toString());
        } 
        return con;
    }

}

