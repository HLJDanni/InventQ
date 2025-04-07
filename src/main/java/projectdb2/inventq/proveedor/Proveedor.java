package projectdb2.inventq.proveedor;

public class Proveedor {
    private int id; // ID del proveedor
    private String nombre; // Nombre del proveedor
    private String telefono; // Teléfono del proveedor
    private String email; // Correo electrónico del proveedor
    private String direccion; // Dirección del proveedor

    // Constructor
    public Proveedor() {
    }

    public Proveedor(int id, String nombre, String telefono, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}