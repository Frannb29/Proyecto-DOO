package main;

import GUI.VentanaPrincipal;
import javax.swing.SwingUtilities;

/**
 * Clase principal que actaa como el punto de entrada del simulador.
 * Se encarga de inicializar el ciclo del codigo y transferir el control
 * del flujo a la interfaz grafica de manera segura.
 */
public class Main {

    /**
     * Metodo de arranque del sistema.
     * Despacha la creación y visualización de la ventana principal para garantizar la seguridad de hilos
     * y evitar condiciones de carrera en el renderizado de la GUI.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}