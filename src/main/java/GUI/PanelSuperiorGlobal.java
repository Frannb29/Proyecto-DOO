package GUI;

import javax.swing.*;
import java.awt.*;
import logica.Jugador;
import logica.Reloj;

/**
 * Panel grafico de la barra de estado superior.
 * Permite la visualizacian del presupuesto del usuario y los ciclos de tiempo transcurridos en el simulador.
 */
public class PanelSuperiorGlobal extends JPanel {
    private JLabel labelPresupuesto;
    private JLabel labelTiempo;

    /**
     * Construye una nueva barra superior horizontal.
     * Configura una distribucion lineal alineada al centro mediante {@link FlowLayout}.
     * @param jugador Instancia de Jugador de la cual se leerá el presupuesto inicial.
     * @param reloj Instancia de Reloj utilizada como referencia de tiempo.
     */
    public PanelSuperiorGlobal(Jugador jugador, Reloj reloj) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        setBackground(new Color(50, 50, 50));

        labelPresupuesto=new JLabel("Presupuesto: $"+jugador.getPresupuesto());
        labelPresupuesto.setForeground(Color.WHITE);
        labelPresupuesto.setFont(new Font("Arial", Font.BOLD, 16));

        labelTiempo=new JLabel("Tiempo: 0 ticks");
        labelTiempo.setForeground(Color.WHITE);
        labelTiempo.setFont(new Font("Arial", Font.BOLD, 16));

        add(labelPresupuesto);
        add(labelTiempo);
    }

    /**
     * Sincroniza y actualiza los componentes de texto en la pantalla con las propiedades actualizadas.
     * @param jugador Instancia de Jugador.
     * @param reloj La instancia de Reloj que calcula el paso del tiempo.
     */
    public void actualizarDatos(Jugador jugador, Reloj reloj) {
        labelPresupuesto.setText("Presupuesto: $"+jugador.getPresupuesto());
        labelTiempo.setText("Tiempo: "+reloj.getSegundosTranscurridos()+" ticks");
    }
}