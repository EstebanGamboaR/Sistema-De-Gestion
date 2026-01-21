package projectoavance1;

import javax.swing.JOptionPane;

/**
 * Gestor de Usuarios
 * @author Esteban Gamboa
 */
public class GestorUsuarios {

    private Usuario[] usuarios;
    private int contUsuarios;
    private GestorCatalogos gestorCatalogos;

    public GestorUsuarios() {
        usuarios = new Usuario[50];
        contUsuarios = 0;
    }

    // ÚNICO método válido para establecer el gestor de catálogos
    public void setGestorCatalogos(GestorCatalogos gestorCatalogos) {
        this.gestorCatalogos = gestorCatalogos;
    }

    // Método para agregar usuario
    public void agregarUsuario() {
        if (contUsuarios >= 50) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más usuarios");
            return;
        }

        JOptionPane.showMessageDialog(null, "=== Agregar Usuario ===");

        String nombreCompleto = JOptionPane.showInputDialog("Digite el nombre completo: ");
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        String nickname = JOptionPane.showInputDialog("Digite el nickname: ");
        if (nickname == null || nickname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        // Verificar que no exista el nickname
        for (int i = 0; i < contUsuarios; i++) {
            if (usuarios[i].getNickname().equals(nickname)) {
                JOptionPane.showMessageDialog(null, "El nickname ya existe. Intente con otro.");
                return;
            }
        }

        String password = JOptionPane.showInputDialog("Digite la contraseña: ");
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        String tipo = JOptionPane.showInputDialog("Digite el tipo de usuario (Cliente / Administrador / Carpintero): ");
        if (tipo == null || tipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }
        
        String correo;

        if (tipo.equalsIgnoreCase("Cliente") || (tipo.equalsIgnoreCase("Carpintero"))) {
            correo = JOptionPane.showInputDialog("Digite el correo del cliente:");
            if (correo == null || correo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Correo inválido. Operación cancelada.");
                return;
            }
        } else {
            correo = "";
        }

        
        Usuario u = new Usuario(nombreCompleto, nickname, password, tipo, correo);
        usuarios[contUsuarios] = u;
        contUsuarios++;

        // Si existe gestor de catálogos, registra el usuario automáticamente
        if (gestorCatalogos != null) {
            if (tipo.equalsIgnoreCase("Cliente") || tipo.equalsIgnoreCase("Carpintero")){
                gestorCatalogos.registrarUsuario(u);
            }
        }

        JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
    }

    // Método para consultar usuario
    public void consultarUsuario() {
        if (contUsuarios == 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        String nickname = JOptionPane.showInputDialog("Digite el nickname del usuario a consultar:");
        if (nickname == null || nickname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        for (int i = 0; i < contUsuarios; i++) {
            if (usuarios[i].getNickname().equalsIgnoreCase(nickname)) {
                String info = "=== Datos del Usuario ===\n\n";
                info += "Nombre completo: " + usuarios[i].getNombreCompleto() + "\n";
                info += "Nickname: " + usuarios[i].getNickname() + "\n";
                info += "Tipo: " + usuarios[i].getTipo() + "\n";
                info += "Estado: " + usuarios[i].getEstado();

                JOptionPane.showMessageDialog(null, info);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }

    // Método para inactivar usuario (con validación de dependencias)
    public void inactivarUsuario(GestorCatalogos gestorCatalogos) {
        if (contUsuarios == 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        String nickname = JOptionPane.showInputDialog("Digite el nickname del usuario a inactivar: ");
        if (nickname == null || nickname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        for (int i = 0; i < contUsuarios; i++) {
            Usuario u = usuarios[i];

            if (u.getNickname().equalsIgnoreCase(nickname)) {

                if (u.getEstado().equalsIgnoreCase("Inactivo")) {
                    JOptionPane.showMessageDialog(null, "El usuario ya está inactivo.");
                    return;
                }

                // === VALIDACIONES SEGÚN EL TIPO ===

                
                if (u.getTipo().equalsIgnoreCase("Cliente")) {
                    Proyecto[] proyectos = gestorCatalogos.getProyectos();
                    for (int j = 0; j < gestorCatalogos.getContProyectos(); j++) {
                        if (proyectos[j] != null &&
                            proyectos[j].getCliente() != null &&
                            proyectos[j].getCliente().getNombre().equalsIgnoreCase(u.getNombreCompleto()) &&
                            proyectos[j].getEstado().equalsIgnoreCase("Activo")) {

                            JOptionPane.showMessageDialog(null,
                                "Este cliente tiene proyectos activos, no se puede inactivar.");
                            return;
                        }
                    }
                }

              
                if (u.getTipo().equalsIgnoreCase("Carpintero")) {
                    OrdenTrabajo[] ordenes = gestorCatalogos.getOrdenes();
                    for (int j = 0; j < gestorCatalogos.getContOrdenes(); j++) {
                        if (ordenes[j] != null &&
                            ordenes[j].getEstado().equalsIgnoreCase("Activa") &&
                            ordenes[j].getCarpintero() != null &&
                            ordenes[j].getCarpintero().getNickname().equalsIgnoreCase(u.getNickname())) {

                            JOptionPane.showMessageDialog(null,
                                "Este carpintero tiene órdenes activas, no se puede inactivar.");
                            return;
                        }
                    }
                }

                
                if (u.getTipo().equalsIgnoreCase("Administrador")) {
                    for (int k = 0; k < contUsuarios; k++) {
                        Usuario x = usuarios[k];
                        if ((x.getTipo().equalsIgnoreCase("Cliente") ||
                             x.getTipo().equalsIgnoreCase("Carpintero")) &&
                            x.getEstado().equalsIgnoreCase("Activo")) {

                            JOptionPane.showMessageDialog(null,
                                "No se puede inactivar, hay usuarios activos dependientes de este administrador.");
                            return;
                        }
                    }
                    
                    int adminsActivos = 0;
                    for(int k = 0 ;k < contUsuarios; k++){
                        if(usuarios[k].getTipo().equalsIgnoreCase("Administrador") &&
                           usuarios[k].getEstado().equalsIgnoreCase("Activo")){
                            adminsActivos++;
                        }
                    }
                    
                    if(adminsActivos <= 1){
                        JOptionPane.showMessageDialog(null, "No se puede inactivar este administrador ya que es el ultimo que queda en el sistema");
                        return;
                    }
                }

                
                u.setEstado("Inactivo");
                JOptionPane.showMessageDialog(null, "Usuario inactivado correctamente.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }


    // Getters
    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public int getContUsuarios() {
        return contUsuarios;
    }
}