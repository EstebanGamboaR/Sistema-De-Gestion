/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectoavance1;

/**
 *
 * @author carolinasalazarelizondo
 */
public class Proyecto {
    private String identificacion;
    private String nombre;
    private String tipo;
    private String materiales;
    private Cliente cliente;
    private String estado;

    public Proyecto(String identificacion, String nombre, String tipo, String materiales, Cliente cliente) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.materiales = materiales;
        this.cliente = cliente;
        this.estado = "Activo";
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
