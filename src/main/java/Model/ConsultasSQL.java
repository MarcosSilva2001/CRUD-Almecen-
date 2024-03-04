package Model;

import static Model.Conexion.Conectar;
import View.VistaProductos;
import java.awt.List;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ConsultasSQL extends Conexion { // extends Conexion para heredar y utilizar la conecion sin instanciar

    public boolean registrar(Producto producto) {
        PreparedStatement ps = null;
        Connection con = Conectar();

        String sql = "INSERT INTO productos ( nombre, stock, categoria, precio) VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setString(3, producto.getCategoria());
            ps.setDouble(4, producto.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificar(Producto producto) {
        PreparedStatement ps = null;
        Connection con = Conectar();

        String sql = "UPDATE productos SET nombre=?, stock = ? , categoria = ? , precio = ? WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStock());
            ps.setString(3, producto.getCategoria());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Producto producto) {
        PreparedStatement ps = null;
        Connection con = Conectar();

        String sql = "DELETE FROM productos WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
