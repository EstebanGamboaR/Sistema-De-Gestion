/*
 * Sistema Good Wood - Facturación
 */
package projectoavance1;
import javax.swing.JOptionPane;

/**
 *
 * @author Esteban Gamboa
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
        this.estado = "Activa"; // Agregué "Activa" por defecto para consistencia
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
        this.estado = "Anulada";
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
                        + "\nServicio: " + facturas[i].servicioPrestado 
                        + "\nEstado: " + facturas[i].estado + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, lista);
    }
}