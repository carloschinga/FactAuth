package pe.fact.gestor.auth.entity;

public class Personal {
    private Integer codiPers;
    private String nombPers;
    private String appaPers;
    private String usuario;
    private String clave;

    // Getters y Setters
    public Integer getCodiPers() { return codiPers; }
    public void setCodiPers(Integer codiPers) { this.codiPers = codiPers; }

    public String getNombPers() { return nombPers; }
    public void setNombPers(String nombPers) { this.nombPers = nombPers; }

    public String getAppaPers() { return appaPers; }
    public void setAppaPers(String appaPers) { this.appaPers = appaPers; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}