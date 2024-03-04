package Model;

public class Producto {

    private int codigo;
    private String nombre;
    private int stock;
    private String categoria;
    private Double precio;

    public Producto() {
    }

    public Producto(int codigo, String nombre, int stock, String categoria, Double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{"
                + "codigo=" + codigo
                + ", nombre='" + nombre + '\''
                + ", stock=" + stock
                + ", categoria='" + categoria + '\''
                + ", precio=" + precio
                + '}';
    }
}
