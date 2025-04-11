package projectdb2.inventq.proveedor;

public class ContactoProveedor {
    private int proveedorID;
    private String tipoContacto; // Cambiado a minúscula para seguir la convención Java
    private String detalleContacto; // Cambiado a minúscula para seguir la convención Java

    public ContactoProveedor(int proveedorID, String tipoContacto, String detalleContacto) {
        this.proveedorID = proveedorID;
        this.tipoContacto = tipoContacto;
        this.detalleContacto = detalleContacto;
    }

    public String getDetalleContacto() {
        return detalleContacto;
    }

    public void setDetalleContacto(String detalleContacto) {
        this.detalleContacto = detalleContacto;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    @Override
    public String toString() {
        return "ContactoProveedor{" +
                "proveedorID=" + proveedorID +
                ", tipoContacto='" + tipoContacto + '\'' +
                ", detalleContacto='" + detalleContacto + '\'' +
                '}';
    }
}