package Modelo;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        SistemaLaboratorio sistema = new SistemaLaboratorio();
        int opcion = 0;

        while (opcion != 8) {
            String menu = "GESTIÓN LABORATORIO DE COMPUTACIÓN \n"
                        + "1. Registrar computador\n"
                        + "2. Ver computadores disponibles\n"
                        + "3. Ver computadores en mantención\n"
                        + "4. Ingresar mantención a un computador\n"
                        + "5. Cambiar estado de un computador\n"
                        + "6. Ver historial de un computador\n"
                        + "7. Ver mapa del laboratorio\n"
                        + "8. Salir\n\n"
                        + "Ingrese una opción:";

            String entrada = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            // Si el usuario aprieta Cancelar
            if (entrada == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        String id = JOptionPane.showInputDialog("Ingrese el código ID del equipo (ej: PC-01):");
                        String caract = JOptionPane.showInputDialog("Ingrese características (ej: Procesador, RAM, etc.):");
                        String uso = JOptionPane.showInputDialog("Ingrese uso recomendado (ej: Programación):");
                        String ubicacion = JOptionPane.showInputDialog("Ingrese ubicación (ej: Mesa 3):");

                        Computador nuevoPc = new Computador(id, caract, uso, ubicacion);
                        sistema.registrarComputador(nuevoPc);
                        JOptionPane.showMessageDialog(null, "Computador registrado con éxito.");
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, sistema.mostrarDisponibles());
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, sistema.mostrarEnMantencion());
                        break;

                    case 4:
                        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del computador con falla:");
                        Computador pcEncontrado = sistema.buscarComputador(idBuscar);

                        if (pcEncontrado != null) {
                            String idMant = JOptionPane.showInputDialog("Ingrese ID de mantención (ej: M-01):");
                            String fecha = JOptionPane.showInputDialog("Ingrese fecha (ej: 19/11/2026):");
                            String problema = JOptionPane.showInputDialog("Describa el problema reportado:");

                            Mantencion nuevaMant = new Mantencion(idMant, fecha, problema);
                            pcEncontrado.agregarMantencion(nuevaMant);
                            JOptionPane.showMessageDialog(null, "Mantención registrada. El equipo pasó a 'En mantencion'.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró un computador con ese ID.");
                        }
                        break;

                    case 5:
                        String idEstado = JOptionPane.showInputDialog("Ingrese el ID del computador:");
                        Computador pcEstado = sistema.buscarComputador(idEstado);

                        if (pcEstado != null) {
                            String[] estados = {"Disponible", "En mantencion", "No disponible"};
                            String nuevoEstado = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Seleccione el nuevo estado para " + idEstado + ":",
                                    "Cambiar Estado",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    estados,
                                    estados[0]
                            );

                            if (nuevoEstado != null) {
                                pcEstado.cambiarEstado(nuevoEstado);
                                JOptionPane.showMessageDialog(null, "Estado actualizado a: " + nuevoEstado);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Computador no encontrado.");
                        }
                        break;

                    case 6:
                        String idHist = JOptionPane.showInputDialog("Ingrese el ID del computador:");
                        Computador pcHist = sistema.buscarComputador(idHist);

                        if (pcHist != null) {
                            JOptionPane.showMessageDialog(null, pcHist.verHistorial());
                        } else {
                            JOptionPane.showMessageDialog(null, "Computador no encontrado.");
                        }
                        break;

                    case 7:
                            MapaLaboratorio mapa = new MapaLaboratorio(sistema);
                            mapa.setVisible(true);
                        break;

                    case 8:
                            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese un número del 1 al 7.");
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}