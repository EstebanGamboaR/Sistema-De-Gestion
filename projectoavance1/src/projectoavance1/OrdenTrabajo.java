
package projectoavance1;

/**
 *
 * @author carolinasalazarelizondo
 */
public class OrdenTrabajo {
    private String fecha;
    private Servicio servicioRelacionado;
    private int duracionEstimada;
    private String estado;
    private Usuario carpintero;
    private Proyecto proyecto;
    
    public OrdenTrabajo(String fecha, Servicio servicioRelacionado, int duracionEstimada) {
        this.fecha = fecha;
        this.servicioRelacionado = servicioRelacionado;
        this.duracionEstimada = duracionEstimada;
        this.estado = "Activa";
        this.carpintero = null;
        this.proyecto = null;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public Servicio getServicioRelacionado() {
        return servicioRelacionado;
    }
    
    public void setServicioRelacionado(Servicio servicioRelacionado) {
        this.servicioRelacionado = servicioRelacionado;
    }
    
    public int getDuracionEstimada() {
        return duracionEstimada;
    }
    
    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Usuario getCarpintero(){
        return carpintero;
    }
    
    public void setCarpintero(Usuario carpintero){
        this.carpintero = carpintero;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
