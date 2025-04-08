package projectdb2.inventq.proveedor;

public class Proveedor {
    private int proveedorID; // ID del proveedor
    private String nombre; // Nombre del proveedor
    private String telefono; // Teléfono del proveedor
    private String email; // Correo electrónico del proveedor
    private String direccion; // Dirección del proveedor

    // Constructor
    public Proveedor(int proveedorID, String nombre, String telefono, String email, String direccion) {
        this.proveedorID = proveedorID;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "proveedorID=" + proveedorID +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}