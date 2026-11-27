package Modelo;

public class Mantencion {
    private String idMantencion;
    private String fechaIngreso;
    private String problemaReportado;
    private String reparacionRealizada;
    private boolean estadoMantencion; 

    public Mantencion(String idMantencion, String fechaIngreso, String problemaReportado) {
        this.idMantencion = idMantencion;
        this.fechaIngreso = fechaIngreso;
        this.problemaReportado = problemaReportado;
        this.reparacionRealizada = "Pendiente";
        this.estadoMantencion = false;
    }
    public void completarMantencion(String reparacion) {
        this.reparacionRealizada = reparacion;
        this.estadoMantencion = true;
    }
    public String mostrarDetalles() {
        String estadoTexto = estadoMantencion ? "Completada" : "En proceso";
        return "ID Mantención: " + idMantencion +
               "\nFecha de ingreso: " + fechaIngreso +
               "\nProblema reportado: " + problemaReportado +
               "\nReparación realizada: " + reparacionRealizada +
               "\nEstado: " + estadoTexto;
    }
    public String getIdMantencion() {
        return idMantencion;
    }
    public void setIdMantencion(String idMantencion) {
        this.idMantencion = idMantencion;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getProblemaReportado() {
        return problemaReportado;
    }
    public void setProblemaReportado(String problemaReportado) {
        this.problemaReportado = problemaReportado;
    }
    public String getReparacionRealizada() {
        return reparacionRealizada;
    }
    public void setReparacionRealizada(String reparacionRealizada) {
        this.reparacionRealizada = reparacionRealizada;
    }
    public boolean isEstadoMantencion() {
        return estadoMantencion;
    }
    public void setEstadoMantencion(boolean estadoMantencion) {
        this.estadoMantencion = estadoMantencion;
    }
}