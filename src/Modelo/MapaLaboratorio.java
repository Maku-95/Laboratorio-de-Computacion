package Modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MapaLaboratorio extends JDialog {
    private SistemaLaboratorio sistema;
    public MapaLaboratorio(SistemaLaboratorio sistema) {
        this.sistema = sistema;
        // Configuración de la ventana
        setTitle("Mapa del Laboratorio de Computación");
        setModal(true);
        setSize(750, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        //Escritorio del profesor
        JPanel panelProfesor = new JPanel(new BorderLayout());
        panelProfesor.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 5));
        JButton btnProfesor = new JButton("<html><center>Escritorio<br>Profesor</center></html>");
        btnProfesor.setEnabled(false);
        btnProfesor.setPreferredSize(new Dimension(110, 80));
        panelProfesor.add(btnProfesor, BorderLayout.CENTER);
        add(panelProfesor, BorderLayout.WEST);
        //Bloques y separacion de pasillo
        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 0, 35));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));
        //PC-01 al PC-16
        JPanel bloqueSuperior = new JPanel(new GridLayout(4, 4, 15, 8));
        for (int i = 1; i <= 16; i++) {
            String codigo = generarCodigo(i);
            bloqueSuperior.add(crearBotonPC(codigo));
        }
        //PC-17 al PC-32
        JPanel bloqueInferior = new JPanel(new GridLayout(4, 4, 15, 8));
        for (int i = 17; i <= 32; i++) {
            String codigo = generarCodigo(i);
            bloqueInferior.add(crearBotonPC(codigo));
        }
        panelCentral.add(bloqueSuperior);
        panelCentral.add(bloqueInferior);
        add(panelCentral, BorderLayout.CENTER);
        //Explicacion de colores 
        JLabel leyenda = new JLabel(
            "Verde: Disponible   |   Rojo: En mantención / No disponible   |   Gris: Sin registrar",
            SwingConstants.CENTER
        );
        leyenda.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        add(leyenda, BorderLayout.SOUTH);
    }
    //códigos con formato PC-01, PC-02
    private String generarCodigo(int numero) {
        if (numero < 10) {
            return "PC-0" + numero;
        } else {
            return "PC-" + numero;
        }
    }
    // Creacion de botones
    private JButton crearBotonPC(String codigoID) {
        JButton boton = new JButton(codigoID);
        Computador pc = sistema.buscarComputador(codigoID);
        if (pc == null) {
            boton.setBackground(Color.LIGHT_GRAY);
        } else if (pc.getEstadoActual().equalsIgnoreCase("Disponible")) {
            boton.setBackground(Color.GREEN);
        } else {
            // Dispo o no
            boton.setBackground(Color.RED);
            boton.setForeground(Color.WHITE);
        }
        // info al clickear el mapa
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pc == null) {
                    JOptionPane.showMessageDialog(null, "El puesto " + codigoID + " aún no está registrado en el sistema.");
                } else {
                    String info = "Equipo: " + pc.getCodigoID()
                                + "\nUbicación: " + pc.getUbicacion()
                                + "\nCaracterísticas: " + pc.getCaracteristicas()
                                + "\nUso recomendado: " + pc.getUsoRecomendado()
                                + "\nEstado actual: " + pc.getEstadoActual()
                                + "\n\n" + pc.verHistorial();
                    JOptionPane.showMessageDialog(null, info, "Detalles de " + codigoID, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        return boton;
    }
}