package Controller;

import Model.ConsultasSQL;
import Model.Producto;
import View.FormLogin;
import View.FormRegistro;
import View.VistaProductos;

public class Main {

    public static void main(String[] args) {
        

        // instancias
        ConsultasSQL consultaprod = new ConsultasSQL();
        Producto producto = new Producto();
        VistaProductos vista = new VistaProductos();
        FormLogin login = new FormLogin();
        FormRegistro registro = new FormRegistro();

        ControladoraProductos control = new ControladoraProductos(consultaprod, producto, vista,login,registro);
        control.iniciar();

    }
}
