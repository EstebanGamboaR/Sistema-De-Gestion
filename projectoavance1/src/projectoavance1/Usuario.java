package projectoavance1;

/**
 * Representa un usuario del sistema de gestión "Good Wood".
 * <p>
 * Un usuario puede tener uno de tres roles: {@code Administrador},
 * {@code Cliente} o {@code Carpintero}. El estado inicial siempre es
 * {@code "Activo"} y puede cambiar a {@code "Inactivo"} a través de
 * {@link GestorUsuarios#inactivarUsuario}.
 * </p>
 *
 * @author Esteban Gamboa
 * @version 1.0
 * @see GestorUsuarios
 */
public class Usuario {
    private String nombreCompleto;
    private String nickname;
    private String password;
    private String estado;
    private String tipo;
    private String correo;

    /**
     * Crea un nuevo usuario con estado inicial {@code "Activo"}.
     *
     * @param nombreCompleto Nombre completo del usuario.
     * @param nickname       Identificador único de acceso (no puede repetirse en el sistema).
     * @param password       Contraseña de acceso.
     * @param tipo           Rol del usuario: {@code "Administrador"}, {@code "Cliente"} o {@code "Carpintero"}.
     * @param correo         Correo electrónico (requerido para Clientes y Carpinteros; vacío para Administradores).
     */
    public Usuario(String nombreCompleto, String nickname, String password, String tipo, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.nickname = nickname;
        this.password = password;
        this.tipo = tipo;
        this.estado = "Activo";
        this.correo = correo;
    }

    /** @return Nombre completo del usuario. */
    public String getNombreCompleto() { return nombreCompleto; }

    /** @param nombreCompleto Nuevo nombre completo. */
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    /** @return Nickname único de acceso. */
    public String getNickname() { return nickname; }

    /** @param nickname Nuevo nickname. */
    public void setNickname(String nickname) { this.nickname = nickname; }

    /** @return Contraseña del usuario. */
    public String getPassword() { return password; }

    /** @param password Nueva contraseña. */
    public void setPassword(String password) { this.password = password; }

    /**
     * Retorna el estado actual del usuario.
     *
     * @return {@code "Activo"} o {@code "Inactivo"}.
     */
    public String getEstado() { return estado; }

    /**
     * Cambia el estado del usuario.
     *
     * @param estado Nuevo estado: {@code "Activo"} o {@code "Inactivo"}.
     */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Retorna el rol del usuario.
     *
     * @return {@code "Administrador"}, {@code "Cliente"} o {@code "Carpintero"}.
     */
    public String getTipo() { return tipo; }

    /** @param tipo Nuevo tipo/rol del usuario. */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /** @return Correo electrónico del usuario. */
    public String getCorreo() { return correo; }

    /** @param correo Nuevo correo electrónico. */
    public void setCorreo(String correo) { this.correo = correo; }

    /**
     * Retorna una representación textual del usuario.
     * Formato: {@code NombreCompleto (@nickname) Tipo}
     *
     * @return Cadena con nombre, nickname y tipo del usuario.
     */
    @Override
    public String toString() {
        return nombreCompleto + " (@" + nickname + ") " + tipo;
    }
}