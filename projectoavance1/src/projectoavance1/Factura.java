/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectoavance1;
import javax.swing.JOptionPane;

/**
 *
 * @author carolinasalazarelizondo
 */
public class Factura {
    public String fechaHora;
    public Servicio servicioPrestado;
    public Cliente cliente;
    String estado;

    public Factura(String fechaHora, Servicio servicioPrestado, Cliente cliente) {
        this.fechaHora = fechaHora;
        this.servicioPrestado = servicioPrestado;
        this.cliente = cliente;
    }

    // Método para registrar una nueva factura
    public void facturar() {
        JOptionPane.showMessageDialog(null,
                "Factura registrada con éxito\n"
                + "Fecha: " + fechaHora
                + "\nCliente: " + cliente
                + "\nServicio: " + servicioPrestado);
    }

    // Método para anular una factura
    public void anularFactura() {
        JOptionPane.showMessageDialog(null,
                "La factura del cliente " + cliente + " ha sido ANULADA.");
    }

    // Método para mostrar las facturas 
    public static void mostrarFacturas(Factura[] facturas) {
        String lista = "=== LISTA DE FACTURAS ===\n";
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                lista += "\nFactura #" + (i + 1)
                        + "\nFecha: " + facturas[i].fechaHora
                        + "\nCliente: " + facturas[i].cliente
                        + "\nServicio: " + facturas[i].servicioPrestado + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, lista);
    }
}
