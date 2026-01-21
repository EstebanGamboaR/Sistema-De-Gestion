package projectoavance1;

/**
 *
 * @author Esteban Gamboa
 */
public class Servicio {
    private String nombre;
    private String descripcion;
    private String requisitos;
    private String estado;

    public Servicio(String nombre, String descripcion, String requisitos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.estado = "Activo";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Para mostrar en listas (usado por otros modulos)
    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}