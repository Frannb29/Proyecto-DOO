package GUI;

import javax.swing.*;
import java.awt.*;
import logica.Jugador;
import logica.Reloj;

public class PanelSuperiorGlobal extends JPanel {
    private JLabel labelPresupuesto;
    private JLabel labelTiempo;

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

    public void actualizarDatos(Jugador jugador, Reloj reloj) {
        labelPresupuesto.setText("Presupuesto: $"+jugador.getPresupuesto());
        labelTiempo.setText("Tiempo: "+reloj.getSegundosTranscurridos()+" ticks");
    }
}