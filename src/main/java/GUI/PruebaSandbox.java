package GUI;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PruebaSandbox {

    public static void main(String[] args) {
        Jugador jugador=new Jugador();
        jugador.setPresupuesto(1500); 
        
        Simulador simulador=new Simulador(jugador); 
        Tienda tienda=Tienda.getInstancia();

        simulador.iniciarSimulacion(); 

        SwingUtilities.invokeLater(() -> {
            JFrame ventana=new JFrame("Sandbox");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(1024, 768);
            ventana.setLocationRelativeTo(null);
            ventana.setLayout(new BorderLayout());

            Reloj reloj=simulador.getReloj(); 
            
            PanelSuperiorGlobal topBar=new PanelSuperiorGlobal(jugador, reloj);
            PanelClientes panelClientes=new PanelClientes(jugador);
            PanelTienda panelTienda=new PanelTienda(jugador);
            
            ventana.add(topBar, BorderLayout.NORTH);
            ventana.add(panelClientes, BorderLayout.EAST);
            
            JTabbedPane pestañasCentrales=new JTabbedPane();
            pestañasCentrales.addTab("Tienda de Suministros", panelTienda);
            
            ventana.add(pestañasCentrales, BorderLayout.CENTER);

            Timer timerGUI=new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    topBar.actualizarDatos(jugador, reloj);
                    panelClientes.actualizarLista();
                    
                    ventana.repaint();
                }
            });
            timerGUI.start();
            ventana.setVisible(true);
        });
    }
}