package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import logica.Habitat;

public class VentanaHabitats extends JFrame{
    private JTabbedPane pestañasHabitats;
    public VentanaHabitats(){
        setTitle("Tienda de Mascotas");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        pestañasHabitats = new JTabbedPane();

        for(Habitat habitat : Habitat.values()){
            PanelHabitat panelIndividual = new PanelHabitat(habitat.getRuta());
            pestañasHabitats.addTab(habitat.name(), panelIndividual);
        }
        add(pestañasHabitats, BorderLayout.CENTER);
        JPanel panelTienda = new JPanel();
        panelTienda.setBackground(Color.LIGHT_GRAY);
        panelTienda.setPreferredSize(new Dimension(1024, 100));

        JButton botonComprar = new JButton("Comprar Mascota");
        JButton botonVender = new JButton("Vender Mascotaa");
        panelTienda.add(botonComprar);
        panelTienda.add(botonVender);
        add(panelTienda, BorderLayout.SOUTH);
        botonComprar.addActionListener(e -> comprarMascotaHabitat());
        botonVender.addActionListener(e -> venderMascotaHabitat());
    }
    private void comprarMascotaHabitat() {
        PanelHabitat habitatActivo = (PanelHabitat) pestañasHabitats.getSelectedComponent();

        if (habitatActivo != null){
            int index = pestañasHabitats.getSelectedIndex();
            String nombrePestaña = pestañasHabitats.getTitleAt(index);

            String rutaMascota = "/Imagenes/perro.png";
            if (nombrePestaña.equals("ACUATICO")){
                rutaMascota = "/Imagenes/pez.png";
            } else if (nombrePestaña.equals("POKECASA")){
                rutaMascota = "/Imagenes/Bulbasaur.png";
            }

            boolean venta = habitatActivo.dibujarMascota(rutaMascota);

            if (!venta){
                JOptionPane.showMessageDialog(this, "El habitat " + nombrePestaña + " está lleno");
            }
        }
    }
    public void venderMascotaHabitat(){
        PanelHabitat habitatActivo = (PanelHabitat) pestañasHabitats.getSelectedComponent();
        if(habitatActivo != null){
            Component mascotaVendida = null;
            for(int i = habitatActivo.espaciosOcupados.length - 1; i >= 0; i--){
                if (habitatActivo.espaciosOcupados[i] != null) {
                    mascotaVendida = habitatActivo.espaciosOcupados[i];
                    break;
                }
            }
            if(mascotaVendida != null){
                habitatActivo.removerMascota(mascotaVendida);
            } else {
                JOptionPane.showMessageDialog(this, "No tienes mascotas en este habitat para vender");
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaHabitats().setVisible(true);
        });
    }
}
