package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConsultaLogin {

    public void guardarUsuario(String usuario, String password) {
        Conexion con = new Conexion();
        String sql = "insert into usuarios(nombre, clave) values ('" + usuario + "', '" + password + "');";
        Statement st;
        Connection conexion = con.Conectar();
        try {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Usuario Registrado Corerctamente");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean consultarUsuario(String user, String pass) {
        Conexion con = new Conexion();
        Connection conexion = con.Conectar();
        String sql = "SELECT nombre, clave FROM usuarios WHERE nombre = ?";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String passCorrecto = rs.getString("clave");
                if (pass.equals(passCorrecto)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
