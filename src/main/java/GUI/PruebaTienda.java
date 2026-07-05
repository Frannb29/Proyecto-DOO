package GUI;
import javax.swing.*;
import logica.*;

public class PruebaTienda {
    public static void main(String[] args) {
        Jugador j=new Jugador();
        j.setPresupuesto(1000);
        JFrame ventana = new JFrame("Tienda de Mascotas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 500);
        ventana.add(new PanelTienda(j));
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
