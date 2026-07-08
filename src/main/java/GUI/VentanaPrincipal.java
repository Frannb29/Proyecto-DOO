package GUI;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private Simulador simulador;
    private Jugador jugador;
    private Reloj reloj; 
  
    private PanelSuperiorGlobal topBar;
    private JTabbedPane menuPestañas;
    private PanelClientes panelClientes;
    private PanelInventario panelInventario;
    private VentanaHabitats ventanaHabitats;

    public VentanaPrincipal() {
        jugador = new Jugador();
        simulador = new Simulador(jugador);
        reloj = simulador.getReloj();
        simulador.iniciarSimulacion();

        setTitle("Simulador de Tienda de Mascotas");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        topBar=new PanelSuperiorGlobal(jugador, reloj);
        add(topBar, BorderLayout.NORTH);

        menuPestañas=new JTabbedPane();
        menuPestañas.addTab("Tienda Suministros", new PanelTienda(jugador));

        panelInventario = new PanelInventario();
        add(panelInventario, BorderLayout.SOUTH);

        ventanaHabitats = new VentanaHabitats(jugador, simulador);
        menuPestañas.addTab("Mis Hábitats", ventanaHabitats);
        
        add(menuPestañas, BorderLayout.CENTER);

        panelClientes=new PanelClientes(jugador);
        add(panelClientes, BorderLayout.EAST);

        iniciarTimerGUI();
    }

    private void iniciarTimerGUI() {
        Timer timerGUI=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topBar.actualizarDatos(jugador, reloj);
                panelClientes.actualizarLista();
                panelInventario.actualizarCantidades();
                ventanaHabitats.actualizarHabitat();
                repaint(); 
            }
        });
        timerGUI.start();
    }
}