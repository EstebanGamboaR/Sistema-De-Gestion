package projectoavance1;

/**
 * Clase Usuario - Módulo 1
 * @author carolinasalazarelizondo
 */
public class Usuario {
    private String nombreCompleto;
    private String nickname;
    private String password;
    private String estado;
    private String tipo;
    private String correo;
    
    public Usuario(String nombreCompleto, String nickname, String password, String tipo, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.nickname = nickname;
        this.password = password;
        this.tipo = tipo;
        this.estado = "Activo";
        this.correo = correo;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return nombreCompleto + " (@" + nickname + ") " + tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}