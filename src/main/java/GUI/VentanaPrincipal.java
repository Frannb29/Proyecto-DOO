package GUI;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal y contenedora raíz.
 * Sigue el patrón acoplamiento para coordinar e inicializar los modelos
 * de negocio y estructurar la interfaz grafica usando una distribucion basada en {@link BorderLayout}.
 * Tiene un temporizador que actaa como el bucle de refresco global
 * para actualizar el estado de todos los subpaneles activos del sistema.
 */
public class VentanaPrincipal extends JFrame {
    private Simulador simulador;
    private Jugador jugador;
    private Reloj reloj;

    private PanelSuperiorGlobal topBar;
    private JTabbedPane menuPestañas;
    private PanelClientes panelClientes;
    private PanelInventario panelInventario;
    private VentanaHabitats ventanaHabitats;

    /**
     * Construye e inicializa el marco principal.
     * Crea las instancias logicas base, arranca el hilo de simulacion,
     * establece las dimensiones del frame y ensambla la barra superior,
     * las pestañas de navegacion, el inventario y la seccion de clientes.
     */
    public VentanaPrincipal() {
        jugador = new Jugador();
        simulador = new Simulador(jugador);
        reloj = simulador.getReloj();
        simulador.iniciarSimulacion();

        setTitle("Simulador de Tienda de Mascotas");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla del usuario
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

    /**
     * Inicializa y arranca el ciclo de refresco de la GUI de forma segura.
     * Instancia un Timer de Swing que se ejecuta cada 1 segundo para un redibujado.
     */
    private void iniciarTimerGUI() {
        Timer timerGUI=new Timer(1000, new ActionListener() {
            /**
             * Intercepta el tic del temporizador y ejecuta llamadas
             * para sincronizar el estado de la UI con la parte logica.
             * @param e Objeto de evento de accion.
             */
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