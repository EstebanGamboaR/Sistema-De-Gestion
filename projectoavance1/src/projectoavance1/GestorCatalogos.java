package projectoavance1;

import javax.swing.JOptionPane;

/**
 * Gestor de Catálogos
 */
public class GestorCatalogos {

    private Cliente[] clientes = new Cliente[50];
    private Servicio[] servicios = new Servicio[50];
    private Proyecto[] proyectos = new Proyecto[50];
    private OrdenTrabajo[] ordenes = new OrdenTrabajo[50];

    private int contClientes = 0;
    private int contServicios = 0;
    private int contProyectos = 0;
    private int contOrdenes = 0;

    private GestorUsuarios gestorUsuarios;

    public GestorCatalogos() {
    }

    public void setGestorUsuarios(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
    }

    public void registrarUsuario(Usuario u) {
        if (u == null) return;

        String tipo = u.getTipo();
        if (tipo == null) return;

        if (tipo.equalsIgnoreCase("Cliente")) {
            // Evita duplicados
            for (int i = 0; i < contClientes; i++) {
                if (clientes[i] != null && clientes[i].getCorreo().equalsIgnoreCase(u.getCorreo())) {
                    return;
                }
            }

            if (contClientes >= clientes.length) {
                JOptionPane.showMessageDialog(null, "No hay espacio en catálogo para registrar cliente");
                return;
            }


            Cliente c = new Cliente(
                u.getNombreCompleto(), 
                "",                   
                "",                    
                "",                   
                "",                    
                u.getCorreo()        
            );

            clientes[contClientes++] = c;
            JOptionPane.showMessageDialog(null, "Cliente registrado en catálogo correctamente");
        }
    }

    // SERVICIOS 
    public void agregarServicio() {
        if (contServicios >= servicios.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más servicios");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Nombre del servicio:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        for (int i = 0; i < contServicios; i++) {
            if (servicios[i] != null && servicios[i].getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe un servicio con ese nombre");
                return;
            }
        }
        String desc = JOptionPane.showInputDialog("Descripción:");
        if (desc == null) desc = "";

        String req = JOptionPane.showInputDialog("Requisitos:");
        if (req == null) req = "";

        Servicio s = new Servicio(nombre, desc, req);
        servicios[contServicios++] = s;
        JOptionPane.showMessageDialog(null, "Servicio agregado correctamente");
    }

    public void editarServicio() {
        if (contServicios == 0) {
            JOptionPane.showMessageDialog(null, "No hay servicios registrados");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Nombre del servicio a editar:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        for (int i = 0; i < contServicios; i++) {
            if (servicios[i] != null && servicios[i].getNombre().equalsIgnoreCase(nombre)) {
                String nuevaDesc = JOptionPane.showInputDialog("Nueva descripción:", servicios[i].getDescripcion());
                if (nuevaDesc != null) servicios[i].setDescripcion(nuevaDesc);

                String nuevosReq = JOptionPane.showInputDialog("Nuevos requisitos:", servicios[i].getRequisitos());
                if (nuevosReq != null) servicios[i].setRequisitos(nuevosReq);

                JOptionPane.showMessageDialog(null, "Servicio editado correctamente");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Servicio no encontrado");
    }
    public void inactivarServicio() {
        if (contServicios == 0) {
            JOptionPane.showMessageDialog(null, "No hay servicios registrados");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Nombre del servicio a inactivar:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        // Verificar si hay ordenes activas
        for (int i = 0; i < contOrdenes; i++) {
            if (ordenes[i] != null && ordenes[i].getServicioRelacionado() != null
                    && ordenes[i].getServicioRelacionado().getNombre().equalsIgnoreCase(nombre)
                    && ordenes[i].getEstado().equalsIgnoreCase("Activa")) {
                JOptionPane.showMessageDialog(null, "No se puede inactivar. El servicio tiene órdenes activas");
                return;
            }
        }

        for (int i = 0; i < contServicios; i++) {
            if (servicios[i] != null && servicios[i].getNombre().equalsIgnoreCase(nombre)) {
                servicios[i].setEstado("Inactivo");
                JOptionPane.showMessageDialog(null, "Servicio inactivado correctamente");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Servicio no encontrado");
    }
    public Servicio[] getServicios() { return servicios; }
    public int getContServicios() { return contServicios; }

    // CLIENTES 
    public void agregarCliente() {
        if (contClientes >= clientes.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más clientes");
            return;
        }
        if (gestorUsuarios == null) {
            JOptionPane.showMessageDialog(null, "GestorUsuarios no está configurado");
            return;
        }
        String correo = JOptionPane.showInputDialog("Correo del cliente (identificador en usuarios):");
        if (correo == null || correo.trim().isEmpty()) return;

        // Verificar que exista como usuario 
        Usuario[] arrU = gestorUsuarios.getUsuarios();
        boolean existe = false;
        for (int i = 0; i < gestorUsuarios.getContUsuarios(); i++) {
            if (arrU[i] != null && arrU[i].getCorreo().equalsIgnoreCase(correo)
                    && arrU[i].getTipo().equalsIgnoreCase("Cliente")
                    && arrU[i].getEstado().equalsIgnoreCase("Activo")) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            JOptionPane.showMessageDialog(null, "El cliente NO existe como usuario activo (revise GestorUsuarios).");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Nombre:");
        if (nombre == null) nombre = "";

        String apellidos = JOptionPane.showInputDialog("Apellidos:");
        if (apellidos == null) apellidos = "";

        String ciudad = JOptionPane.showInputDialog("Ciudad:");
        if (ciudad == null) ciudad = "";

        String direccion = JOptionPane.showInputDialog("Dirección:");
        if (direccion == null) direccion = "";

        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) telefono = "";

        Cliente c = new Cliente(nombre, apellidos, ciudad, direccion, telefono, correo);
        clientes[contClientes++] = c;
        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
    }

    public void editarCliente() {
        if (contClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados");
            return;
        }

        String correo = JOptionPane.showInputDialog("Correo del cliente a editar:");
        if (correo == null || correo.trim().isEmpty()) return;

        for (int i = 0; i < contClientes; i++) {
            if (clientes[i] != null && clientes[i].getCorreo().equalsIgnoreCase(correo)) {
                String tel = JOptionPane.showInputDialog("Nuevo teléfono:", clientes[i].getTelefono());
                if (tel != null) clientes[i].setTelefono(tel);

                String dir = JOptionPane.showInputDialog("Nueva dirección:", clientes[i].getDireccion());
                if (dir != null) clientes[i].setDireccion(dir);

                JOptionPane.showMessageDialog(null, "Cliente editado correctamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
    }

    public void inactivarCliente() {
        if (contClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados");
            return;
        }

        String correo = JOptionPane.showInputDialog("Correo del cliente a inactivar:");
        if (correo == null || correo.trim().isEmpty()) return;

        for (int i = 0; i < contClientes; i++) {
            if (clientes[i] != null && clientes[i].getCorreo().equalsIgnoreCase(correo)) {
                for (int j = 0; j < contProyectos; j++) {
                    if (proyectos[j] != null && proyectos[j].getCliente() != null
                            && proyectos[j].getCliente().getCorreo().equalsIgnoreCase(correo)
                            && proyectos[j].getEstado().equalsIgnoreCase("Activo")) {
                        JOptionPane.showMessageDialog(null, "No se puede inactivar, el cliente tiene proyectos activos.");
                        return;
                    }
                }

                clientes[i].setEstado("Inactivo");
                JOptionPane.showMessageDialog(null, "Cliente inactivado correctamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
    }

    public Cliente[] getClientes() { return clientes; }
    public int getContClientes() { return contClientes; }

    // PROYECTOS
    public void agregarProyecto() {
        if (contProyectos >= proyectos.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más proyectos");
            return;
        }
        if (gestorUsuarios == null) {
            JOptionPane.showMessageDialog(null, "GestorUsuarios no está configurado");
            return;
        }

        String id = JOptionPane.showInputDialog("Identificación del proyecto:");
        if (id == null || id.trim().isEmpty()) return;

        String nombre = JOptionPane.showInputDialog("Nombre del proyecto:");
        if (nombre == null) nombre = "";

        String tipo = JOptionPane.showInputDialog("Tipo:");
        if (tipo == null) tipo = "";

        String mat = JOptionPane.showInputDialog("Materiales:");
        if (mat == null) mat = "";

        // Mostrar lista
        Usuario[] u = gestorUsuarios.getUsuarios();
        String lista = "Clientes disponibles:\n";
        int[] idxToUser = new int[gestorUsuarios.getContUsuarios()];
        int total = 0;
        for (int i = 0; i < gestorUsuarios.getContUsuarios(); i++) {
            if (u[i] != null && u[i].getTipo().equalsIgnoreCase("Cliente") && u[i].getEstado().equalsIgnoreCase("Activo")) {
                lista += (total + 1) + ". " + u[i].getNickname() + " (" + u[i].getNombreCompleto() + ")\n";
                idxToUser[total] = i;
                total++;
            }
        }
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes activos en usuarios.");
            return;
        }

        int op = Integer.parseInt(JOptionPane.showInputDialog(lista + "\nSeleccione número:"));
        if (op < 1 || op > total) {
            JOptionPane.showMessageDialog(null, "Opción inválida");
            return;
        }
        Usuario uSel = u[idxToUser[op - 1]];

        // Creacion de cliente ligado al nickname del usuario
        Cliente clienteAsociado = new Cliente(uSel.getNombreCompleto(), "", "", "", "", uSel.getNickname());

        Proyecto p = new Proyecto(id, nombre, tipo, mat, clienteAsociado);
        proyectos[contProyectos++] = p;
        JOptionPane.showMessageDialog(null, "Proyecto agregado correctamente");
    }

    public void editarProyecto() {
        if (contProyectos == 0) {
            JOptionPane.showMessageDialog(null, "No hay proyectos registrados");
            return;
        }

        String id = JOptionPane.showInputDialog("ID del proyecto a editar:");
        if (id == null || id.trim().isEmpty()) return;

        for (int i = 0; i < contProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getIdentificacion().equalsIgnoreCase(id)) {
                String nombre = JOptionPane.showInputDialog("Nuevo nombre:", proyectos[i].getNombre());
                if (nombre != null) proyectos[i].setNombre(nombre);

                String mat = JOptionPane.showInputDialog("Nuevos materiales:", proyectos[i].getMateriales());
                if (mat != null) proyectos[i].setMateriales(mat);

                JOptionPane.showMessageDialog(null, "Proyecto editado correctamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Proyecto no encontrado");
    }

    public void inactivarProyecto() {
        if (contProyectos == 0) {
            JOptionPane.showMessageDialog(null, "No hay proyectos registrados");
            return;
        }

        String id = JOptionPane.showInputDialog("ID del proyecto a inactivar:");
        if (id == null || id.trim().isEmpty()) return;

        for (int i = 0; i < contProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getIdentificacion().equalsIgnoreCase(id)) {
                proyectos[i].setEstado("Inactivo");
                JOptionPane.showMessageDialog(null, "Proyecto inactivado correctamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Proyecto no encontrado");
    }

    public Proyecto[] getProyectos() { return proyectos; }
    public int getContProyectos() { return contProyectos; }

    //  ORDENES 
    public void agregarOrden() {
        if (contOrdenes >= ordenes.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más órdenes");
            return;
        }
        if (gestorUsuarios == null) {
            JOptionPane.showMessageDialog(null, "GestorUsuarios no está configurado");
            return;
        }
        String fecha = JOptionPane.showInputDialog("Fecha (dd/mm/yyyy):");
        if (fecha == null || fecha.trim().isEmpty()) return;

        String durStr = JOptionPane.showInputDialog("Duración estimada (horas):");
        if (durStr == null || durStr.trim().isEmpty()) 
            return;

        // Seleccionar servicio activo
        String listaSer = "Servicios disponibles:\n";
        int[] idxServ = new int[contServicios];
        int totalServ = 0;
        for (int i = 0; i < contServicios; i++) {
            if (servicios[i] != null && servicios[i].getEstado().equalsIgnoreCase("Activo")) {
                listaSer += (totalServ + 1) + ". " + servicios[i].getNombre() + "\n";
                idxServ[totalServ] = i;
                totalServ++;
            }
        }
        if (totalServ == 0) { JOptionPane.showMessageDialog(null, "No hay servicios activos"); return; }
        int opSer = Integer.parseInt(JOptionPane.showInputDialog(listaSer + "\nSeleccione número:"));
        if (opSer < 1 || opSer > totalServ) { JOptionPane.showMessageDialog(null, "Opción inválida"); return; }
        Servicio s = servicios[idxServ[opSer - 1]];

        // Verificar si ya existe
        for (int i = 0; i < contOrdenes; i++) {
            if (ordenes[i] != null && ordenes[i].getFecha().equalsIgnoreCase(fecha)
                    && ordenes[i].getServicioRelacionado() != null
                    && ordenes[i].getServicioRelacionado().getNombre().equalsIgnoreCase(s.getNombre())) {
                JOptionPane.showMessageDialog(null, "Ya existe una orden con esa fecha y servicio");
                return;
            }
        }

        // Seleccionar carpintero activo desde los usuarios que ya existen
        Usuario[] arrU = gestorUsuarios.getUsuarios();
        String listaCarp = "Carpinteros disponibles:\n";
        int[] idxCarp = new int[gestorUsuarios.getContUsuarios()];
        int totalCarp = 0;
        for (int i = 0; i < gestorUsuarios.getContUsuarios(); i++) {
            if (arrU[i] != null && arrU[i].getTipo().equalsIgnoreCase("Carpintero")
                    && arrU[i].getEstado().equalsIgnoreCase("Activo")) {
                listaCarp += (totalCarp + 1) + ". " + arrU[i].getNickname() + " (" + arrU[i].getNombreCompleto() + ")\n";
                idxCarp[totalCarp] = i;
                totalCarp++;
            }
        }
        if (totalCarp == 0) { JOptionPane.showMessageDialog(null, "No hay carpinteros activos"); return; }
        int opCarp = Integer.parseInt(JOptionPane.showInputDialog(listaCarp + "\nSeleccione número:"));
        if (opCarp < 1 || opCarp > totalCarp) { JOptionPane.showMessageDialog(null, "Opción inválida"); return; }
        Usuario carpSel = arrU[idxCarp[opCarp - 1]];

        String listaProj = "Proyectos disponibles (opcional):\n0. Sin proyecto\n";
        int[] idxProj = new int[contProyectos];
        int totalProj = 0;
        for (int i = 0; i < contProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getEstado().equalsIgnoreCase("Activo")) {
                listaProj += (totalProj + 1) + ". " + proyectos[i].getIdentificacion() + " - " + proyectos[i].getNombre() + "\n";
                idxProj[totalProj] = i;
                totalProj++;
            }
        }
        int selProj = 0;
        if (totalProj > 0) {
            String selStr = JOptionPane.showInputDialog(listaProj + "\nSeleccione número (0 = sin proyecto):");
            if (selStr != null && !selStr.trim().isEmpty()) {
                selProj = Integer.parseInt(selStr);
            }
        }
        int duracion = 0;
        
        OrdenTrabajo o = new OrdenTrabajo(fecha, s, duracion);
        o.setCarpintero(carpSel);
        if (selProj > 0 && selProj <= totalProj) {
            o.setProyecto(proyectos[idxProj[selProj - 1]]);
        }
        ordenes[contOrdenes++] = o;
        JOptionPane.showMessageDialog(null, "Orden agregada correctamente");
    }

    public void editarOrden() {
        if (contOrdenes == 0) {
            JOptionPane.showMessageDialog(null, "No hay órdenes registradas");
            return;
        }

        String fecha = JOptionPane.showInputDialog("Fecha de la orden a editar:");
        if (fecha == null || fecha.trim().isEmpty()) return;

        for (int i = 0; i < contOrdenes; i++) {
            if (ordenes[i] != null && ordenes[i].getFecha().equalsIgnoreCase(fecha)) {
                String durStr = JOptionPane.showInputDialog("Nueva duración (horas):", ordenes[i].getDuracionEstimada());
                if (durStr != null && !durStr.trim().isEmpty()) {
                        int dur = Integer.parseInt(durStr);
                        ordenes[i].setDuracionEstimada(dur);
                        JOptionPane.showMessageDialog(null, "Orden editada correctamente");
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Orden no encontrada");
    }

    public void inactivarOrden() {
        if (contOrdenes == 0) {
            JOptionPane.showMessageDialog(null, "No hay órdenes registradas");
            return;
        }

        String fecha = JOptionPane.showInputDialog("Fecha de la orden a inactivar:");
        if (fecha == null || fecha.trim().isEmpty()) return;

        for (int i = 0; i < contOrdenes; i++) {
            if (ordenes[i] != null && ordenes[i].getFecha().equalsIgnoreCase(fecha)) {
                ordenes[i].setEstado("Inactiva");
                JOptionPane.showMessageDialog(null, "Orden inactivada correctamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Orden no encontrada");
    }

    public OrdenTrabajo[] getOrdenes() { return ordenes; }
    public int getContOrdenes() { return contOrdenes; }

}
