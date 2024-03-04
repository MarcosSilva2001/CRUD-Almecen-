package Model;

import Model.Conexion;
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
        // Se inicializa a null
        String usuarioCorrecto = null;
        String passCorrecto = null;

        String sql = "SELECT nombre, clave FROM usuarios";
        PreparedStatement pst;
        ResultSet rs;

        try {

            pst = conexion.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                usuarioCorrecto = rs.getString(1);
                passCorrecto = rs.getString(2);

                if (user.equals(usuarioCorrecto) && pass.equals(passCorrecto)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                    return true;
                }

                // Si el bucle termina y no se encontró un usuario con las credenciales proporcionadas
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
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
        return false;

    }
}