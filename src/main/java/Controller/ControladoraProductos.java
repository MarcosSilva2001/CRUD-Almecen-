package Controller;

import static Model.Conexion.Conectar;
import Model.ConsultaLogin;
import Model.ConsultasSQL;
import Model.Producto;
import View.FormLogin;
import View.FormRegistro;
import View.VistaProductos;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ControladoraProductos implements ActionListener {

    private ConsultasSQL consultaprod;
    private Producto producto;
    private VistaProductos vista;
    private FormLogin login;
    private FormRegistro registro;
    private ConsultaLogin consultaLog = new ConsultaLogin();

    // Constructor
    public ControladoraProductos(ConsultasSQL consultaprod, Producto producto, VistaProductos vista, FormLogin login, FormRegistro registro) {
        this.consultaprod = consultaprod;
        this.producto = producto;
        this.vista = vista;
        this.login = login;
        this.registro = registro;

        this.vista.btn_añadir.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_limpiar.addActionListener(this);
        this.login.btnIniciar.addActionListener(this);

        // Asociar el método vistaTablaMouseClicked al evento de clic en la tabla
        this.vista.tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vistaTablaMouseClicked(evt);
            }
        });

        // Llamar al método para configurar el filtro del buscador
        vistaTxt_buscarKeyTyped();

        // Agregar el listener para el cambio de estado en el JComboBox
        this.vista.combo_filtro.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_filtroItemStateChanged(evt);
            }
        });
    }

    public void iniciar() {

        this.login.setVisible(true);

    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btn_añadir) {

            producto.setNombre(vista.txt_nombre.getText());
            producto.setStock(Integer.parseInt(vista.txt_stock.getText()));
            producto.setCategoria(vista.combo_categoria.getSelectedItem().toString());
            producto.setPrecio(Double.parseDouble(vista.txt_precio.getText()));

            if (consultaprod.registrar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto Añadido");
                limpiar();
                listarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Añadir ");
                limpiar();
            }
        }

        if (e.getSource() == vista.btn_modificar) {
            producto.setCodigo(Integer.parseInt(vista.txt_codigo.getText()));
            producto.setNombre(vista.txt_nombre.getText());
            producto.setStock(Integer.parseInt(vista.txt_stock.getText()));
            producto.setCategoria(vista.combo_categoria.getSelectedItem().toString());
            producto.setPrecio(Double.parseDouble(vista.txt_precio.getText()));

            if (consultaprod.modificar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto Actualizado");
                limpiar();
                listarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Actualizar");
                limpiar();
            }
        }
        if (e.getSource() == vista.btn_eliminar) {
            producto.setCodigo(Integer.parseInt(vista.txt_codigo.getText()));

            if (consultaprod.eliminar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                limpiar();
                listarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }

        }

        if (e.getSource() == login.btnIniciar) {
            boolean loginExitoso = consultaLog.consultarUsuario(login.txtUser.getText(), login.txtPass.getText());

            if (loginExitoso) {

                // Cerrar la ventana de inicio de sesión
                login.setVisible(false);

                // Mostrar la ventana principal solo si el inicio de sesión es exitoso
                // Configuración del tema FlatGradiantoDeepOceanIJTheme
               FlatArcDarkIJTheme.setup();
                this.vista.setVisible(true);
                this.vista.setTitle("Almacen");
                this.vista.setLocationRelativeTo(null);
                listarTabla();

            }
        }

        if (e.getSource() == vista.btn_limpiar) {
            limpiar();
        }
    }

    // Listar Datos en la Tabla
    public void listarTabla() {
        try {
            Connection con = Conectar();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) vista.tabla.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setPrecio(rs.getDouble("precio"));

                // Crear un array de objetos con los valores del producto
                Object[] fila = {producto.getCodigo(), producto.getNombre(), producto.getStock(), producto.getCategoria(), producto.getPrecio()};

                // Agregar fila a la tabla
                model.addRow(fila);
            }

            con.close();
        } catch (SQLException e) {
            System.err.println("Error al consultar la tabla de productos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void limpiar() {
        vista.txt_codigo.setText("");
        vista.txt_nombre.setText("");
        vista.txt_stock.setText("");
        vista.combo_categoria.setSelectedIndex(0);
        vista.txt_precio.setText("");
    }

    // Clickear las filas de la tabla y mostrar los datos
    public void vistaTablaMouseClicked(java.awt.event.MouseEvent evt) {
        int seleccion = vista.tabla.getSelectedRow();

        vista.txt_codigo.setText(String.valueOf(vista.tabla.getValueAt(seleccion, 0)));
        vista.txt_nombre.setText(String.valueOf(vista.tabla.getValueAt(seleccion, 1)));
        vista.txt_stock.setText(String.valueOf(vista.tabla.getValueAt(seleccion, 2)));
        vista.combo_categoria.setSelectedItem(String.valueOf(vista.tabla.getValueAt(seleccion, 3)));
        vista.txt_precio.setText(String.valueOf(vista.tabla.getValueAt(seleccion, 4)));
    }

    // busqueda en tiempo de escritura
    public void vistaTxt_buscarKeyTyped() {
        TableRowSorter trs = new TableRowSorter<>(vista.tabla.getModel());

        vista.txt_buscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + vista.txt_buscar.getText(), 1));
            }
        });

        vista.tabla.setRowSorter(trs);
    }

    // filtrar por categoria
    public void Combo_filtroItemStateChanged(java.awt.event.ItemEvent evt) {
        // Obtener el valor seleccionado en el JComboBox
        String filtro = vista.combo_filtro.getSelectedItem().toString();

        // Obtenemos el modelo por defecto de nuestra tabla
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();

        // Obtener el TableRowSorter asociado a la tabla
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

        // Si el filtro es "Todos", mostramos todas las filas
        if (filtro.equals("Todos")) {
            vista.tabla.setRowSorter(sorter); // Aquí debes establecer el TableRowSorter en la tabla
            sorter.setRowFilter(null); // Limpiamos el filtro para mostrar todas las filas
        } else {
            // El método regexFilter permite filtrar usando una expresión regular
            // "(?i)" se usa para hacer la búsqueda insensible a mayúsculas y minúsculas
            // El último parámetro (3) indica que se filtra por la columna "Categoría", que tiene el índice 3 en la tabla
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 3));
            vista.tabla.setRowSorter(sorter); // Establecer el TableRowSorter en la tabla
        }

    }
}
