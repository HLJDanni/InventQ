package projectdb2.inventq.Producto;

public class Categoria {

    private int CategoriaID; // ID del producto
    private String nombre; // Nombre del producto


    // Constructor
    public Categoria(int CategoriaID, String nombre) {
        this.CategoriaID = CategoriaID;
        this.nombre = nombre;

    }

    // Getter para ProductoID
    public int getCategoriaID() { // Corregido el nombre del método
        return CategoriaID;
    }

    public String getNombre() {
        return nombre;
    }



    @Override
    public String toString() {
        return "Categoria{" + // Corregido: Cambié "Proveedor" a "Producto"
                "CategoriaID=" + CategoriaID +
                ", nombre='" + nombre +'}';

    }
}