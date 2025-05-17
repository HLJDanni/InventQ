package projectdb2.inventq.Producto;

public class Producto {

    private int ProductoID; // ID del producto
    private String nombre; // Nombre del producto
    private String descripcion; // Descripción del producto
    private double precio; // Precio del producto
    private int ID_Categoria;

    // Constructor
    public Producto(int productoID, String nombre, String descripcion, double precio, int ID_Categoria) {
        this.ProductoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ID_Categoria = ID_Categoria;
    }

    // Getter para ProductoID
    public int getProductoID() { // Corregido el nombre del método
        return ProductoID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getID_Categoria() {
        return ID_Categoria;
    }

    @Override
    public String toString() {
        return "Producto{" + // Corregido: Cambié "Proveedor" a "Producto"
                "ProductoID=" + ProductoID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", ID_Categoria="+ ID_Categoria +// Corregido: Cambié a precio sin comillas
                '}';
    }
}