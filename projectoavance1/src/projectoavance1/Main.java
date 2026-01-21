package projectoavance1;

import javax.swing.JOptionPane;

/**
 * Clase Main - Sistema Good Wood
 * Proyecto Avance 1 - Grupo Completo
 * @author carolinasalazarelizondo
 */
public class Main {
    public static void main(String[] args) {
        // Instanciar gestores de los 3 módulos
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorCatalogos gestorCatalogos = new GestorCatalogos();


        gestorUsuarios.setGestorCatalogos(gestorCatalogos);
        gestorCatalogos.setGestorUsuarios(gestorUsuarios);
        
        Factura[] facturas = new Factura[100];
        int contFacturas = 0;
        
        while(true) {
            String menu = "=== SISTEMA GOOD WOOD ===\n\n";
            menu += "MODULO 1 - USUARIOS\n";
            menu += "1. Agregar Usuario\n";
            menu += "2. Consultar Usuario\n";
            menu += "3. Inactivar Usuario\n\n";
            
            menu += "MODULO 2 - CATALOGOS\n";
            menu += "4. Agregar Cliente\n";
            menu += "5. Editar Cliente\n";
            menu += "6. Inactivar Cliente\n";
            menu += "7. Agregar Servicio\n";
            menu += "8. Editar Servicio\n";
            menu += "9. Inactivar Servicio\n";
            menu += "10. Agregar Proyecto\n";
            menu += "11. Editar Proyecto\n";
            menu += "12. Inactivar Proyecto\n";
            menu += "13. Agregar Orden de Trabajo\n";
            menu += "14. Editar Orden de Trabajo\n";
            menu += "15. Inactivar Orden de Trabajo\n\n";
            
            menu += "MODULO 3 - FACTURACION\n";
            menu += "16. Crear Factura\n";
            menu += "17. Anular Factura\n";
            menu += "18. Mostrar Facturas\n\n";
            
            menu += "0. Salir";
            
            String inputOp = JOptionPane.showInputDialog(menu);
            if(inputOp == null) {
                break; // Usuario cancelo
            }
            
            int op = Integer.parseInt(inputOp);
            
            switch(op) {
                // ===== MODULO 1 - USUARIOS =====
                case 1: 
                    gestorUsuarios.agregarUsuario(); 
                    break;
                    
                case 2: 
                    gestorUsuarios.consultarUsuario(); 
                    break;
                    
                case 3: 
                    gestorUsuarios.inactivarUsuario(gestorCatalogos);
                    break;
                
                // ===== MODULO 2 - CATALOGOS =====
                case 4: 
                    gestorCatalogos.agregarCliente(); 
                    break;
                    
                case 5: 
                    gestorCatalogos.editarCliente(); 
                    break;
                    
                case 6: 
                    gestorCatalogos.inactivarCliente(); 
                    break;
                    
                case 7: 
                    gestorCatalogos.agregarServicio(); 
                    break;
                    
                case 8: 
                    gestorCatalogos.editarServicio(); 
                    break;
                    
                case 9: 
                    gestorCatalogos.inactivarServicio(); 
                    break;
                    
                case 10: 
                    gestorCatalogos.agregarProyecto(); 
                    break;
                    
                case 11: 
                    gestorCatalogos.editarProyecto(); 
                    break;
                    
                case 12: 
                    gestorCatalogos.inactivarProyecto(); 
                    break;
                    
                case 13: 
                    gestorCatalogos.agregarOrden(); 
                    break;
                    
                case 14: 
                    gestorCatalogos.editarOrden(); 
                    break;
                    
                case 15: 
                    gestorCatalogos.inactivarOrden(); 
                    break;
                
                // ===== MODULO 3 - FACTURACION =====
                case 16: 
                    // Crear factura
                    if(contFacturas >= 100) {
                        JOptionPane.showMessageDialog(null, "No hay espacio para mas facturas");
                        break;
                    }
                    
                    if(gestorCatalogos.getContClientes() == 0) {
                        JOptionPane.showMessageDialog(null, "Primero debe agregar clientes");
                        break;
                    }
                    
                    if(gestorCatalogos.getContServicios() == 0) {
                        JOptionPane.showMessageDialog(null, "Primero debe agregar servicios");
                        break;
                    }
                    
                    String fechaHora = JOptionPane.showInputDialog("Fecha y hora (dd/mm/yyyy HH:mm):");
                    if(fechaHora == null) break;
                    
                    // Mostrar lista de clientes activos
                    String listaClientes = "Clientes disponibles:\n\n";
                    Cliente[] clientes = gestorCatalogos.getClientes();
                    int numClientesActivos = 0;
                    for(int i = 0; i < gestorCatalogos.getContClientes(); i++) {
                        if(clientes[i].getEstado().equals("Activo")) {
                            listaClientes += (i+1) + ". " + clientes[i].toString() + "\n";
                            numClientesActivos++;
                        }
                    }
                    
                    if(numClientesActivos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay clientes activos");
                        break;
                    }
                    
                    String inputCliente = JOptionPane.showInputDialog(listaClientes + "\nSeleccione numero:");
                    if(inputCliente == null) break;
                    int opCliente = Integer.parseInt(inputCliente);
                    Cliente clienteSel = clientes[opCliente - 1];
                    
                    // Mostrar lista de servicios activos
                    String listaServicios = "Servicios disponibles:\n\n";
                    Servicio[] servicios = gestorCatalogos.getServicios();
                    int numServiciosActivos = 0;
                    for(int i = 0; i < gestorCatalogos.getContServicios(); i++) {
                        if(servicios[i].getEstado().equals("Activo")) {
                            listaServicios += (i+1) + ". " + servicios[i].toString() + "\n";
                            numServiciosActivos++;
                        }
                    }
                    
                    if(numServiciosActivos == 0) {
                        JOptionPane.showMessageDialog(null, "No hay servicios activos");
                        break;
                    }
                    
                    String inputServicio = JOptionPane.showInputDialog(listaServicios + "\nSeleccione numero:");
                    if(inputServicio == null) break;
                    int opServicio = Integer.parseInt(inputServicio);
                    Servicio servicioSel = servicios[opServicio - 1];
                    
                    Factura f = new Factura(fechaHora, servicioSel, clienteSel);
                    f.facturar();
                    facturas[contFacturas] = f;
                    contFacturas++;
                    break;
                    
                case 17:
                    // Anular factura
                    if(contFacturas == 0) {
                        JOptionPane.showMessageDialog(null, "No hay facturas registradas");
                        break;
                    }
                    
                    String listaFacturas = "Facturas registradas:\n\n";
                    for(int i = 0; i < contFacturas; i++) {
                        listaFacturas += (i+1) + ". Cliente: " + facturas[i].cliente.toString() + 
                                " - Fecha: " + facturas[i].fechaHora + " - Estado: " + facturas[i].estado + "\n";
                    }
                    
                    String inputFactura = JOptionPane.showInputDialog(listaFacturas + "\nSeleccione factura a anular:");
                    if(inputFactura == null) break;
                    int opFactura = Integer.parseInt(inputFactura);
                    facturas[opFactura - 1].anularFactura();
                    break;
                    
                case 18:
                    // Mostrar facturas
                    if(contFacturas == 0) {
                        JOptionPane.showMessageDialog(null, "No hay facturas registradas");
                        break;
                    }
                    
                    Factura[] facturasParaMostrar = new Factura[contFacturas];
                    for(int i = 0; i < contFacturas; i++) {
                        facturasParaMostrar[i] = facturas[i];
                    }
                    Factura.mostrarFacturas(facturasParaMostrar);
                    break;
                    
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema Good Wood");
                    System.exit(0);
                    
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
            }
        }
    }
}