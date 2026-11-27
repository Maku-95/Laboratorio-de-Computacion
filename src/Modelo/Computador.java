package Modelo;

import java.util.ArrayList;

public class Computador {
    private String codigoID;
    private String caracteristicas;
    private String usoRecomendado;
    private String estadoActual;
    private ArrayList<Mantencion> historial;

    public Computador(String codigoID, String caracteristicas, String usoRecomendado, String ubicacion) {
        this.codigoID = codigoID;
        this.caracteristicas = caracteristicas;
        this.usoRecomendado = usoRecomendado;
        this.estadoActual = "Disponible";
        this.historial = new ArrayList<>();
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }
    public void agregarMantencion(Mantencion mantencion) {
        this.historial.add(mantencion);
        this.estadoActual = "En mantencion";
    }
    public String verHistorial() {
        if (historial.isEmpty()) {
            return "El computador " + codigoID + " no tiene mantenciones registradas.";
        }
        StringBuilder texto = new StringBuilder();
        texto.append(" Historial del equipo ").append(codigoID).append("\n");
        
        for (int i = 0; i < historial.size(); i++) {
            Mantencion m = historial.get(i);
            texto.append(m.mostrarDetalles()).append("\n----------------------\n");
        }
        return texto.toString();
    }
    
    public String getCodigoID() {
        return codigoID;
    }
    public void setCodigoID(String codigoID) {
        this.codigoID = codigoID;
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getUsoRecomendado() {
        return usoRecomendado;
    }
    public void setUsoRecomendado(String usoRecomendado) {
        this.usoRecomendado = usoRecomendado;
    }
    public String getEstadoActual() {
        return estadoActual;
    }
    public ArrayList<Mantencion> getHistorial() {
        return historial;
    }
}