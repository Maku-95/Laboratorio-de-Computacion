package Modelo;

import java.util.ArrayList;

public class SistemaLaboratorio {
    private ArrayList<Computador> listaComputadores;

    public SistemaLaboratorio() {
        this.listaComputadores = new ArrayList<>();
    }

    public void registrarComputador(Computador pc) {
        listaComputadores.add(pc);
    }

    // Encontrar pc por ID
    public Computador buscarComputador(String codigoID) {
        for (int i = 0; i < listaComputadores.size(); i++) {
            Computador pc = listaComputadores.get(i);
            if (pc.getCodigoID().equalsIgnoreCase(codigoID)) {
                return pc;
            }
        }
        return null;
    }

    public String mostrarDisponibles() {
        StringBuilder texto = new StringBuilder(" COMPUTADORES DISPONIBLES \n");
        boolean hayDisponibles = false;

        for (int i = 0; i < listaComputadores.size(); i++) {
            Computador pc = listaComputadores.get(i);
            if (pc.getEstadoActual().equalsIgnoreCase("Disponible")) {
                texto.append("ID: ").append(pc.getCodigoID())
                     .append(" | Ubicación: ").append(pc.getUbicacion())
                     .append(" | Uso: ").append(pc.getUsoRecomendado()).append("\n");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            return "No hay computadores disponibles en este momento.";
        }
        return texto.toString();
    }

    public String mostrarEnMantencion() {
        StringBuilder texto = new StringBuilder("COMPUTADORES EN MANTENCIÓN \n");
        boolean hayEnMantencion = false;

        for (int i = 0; i < listaComputadores.size(); i++) {
            Computador pc = listaComputadores.get(i);
            if (pc.getEstadoActual().equalsIgnoreCase("En mantencion")) {
                texto.append("ID: ").append(pc.getCodigoID())
                     .append(" | Ubicación: ").append(pc.getUbicacion()).append("\n");
                hayEnMantencion = true;
            }
        }

        if (!hayEnMantencion) {
            return "No hay computadores en mantención.";
        }
        return texto.toString();
    }
}