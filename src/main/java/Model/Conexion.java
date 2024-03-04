package Model;

import jdk.jfr.StackTrace;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public  static Connection Conectar(){
        Connection conexion;
        String URL = "jdbc:mysql://localhost/almacen?serverTimezone=UTC";
        String USERNAME = "root";
        String PASSWORD = "";

        try {
            conexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//            JOptionPane.showMessageDialog(null,"Conectado a la BD");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

        return  conexion;
    }

    public static void Desconectar(Connection conexion){

        try {
            conexion.close();
//            JOptionPane.showMessageDialog(null, "Desconectado de la BD");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
         Connection con = Conectar();
    }
}
