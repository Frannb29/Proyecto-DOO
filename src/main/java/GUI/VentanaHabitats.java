package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import logica.*;

public class VentanaHabitats extends JPanel{

    private ArrayList<Habitat> habitatsComprados = new ArrayList<>();
    private ArrayList<PanelHabitat> panelesHabitats = new ArrayList<>();
    private JTabbedPane pestañasHabitats;
    private Jugador jugador;
    public VentanaHabitats(Jugador jugador){
        int tiempoTick = 1000;
        this.jugador = jugador;

        setLayout(new BorderLayout());
        pestañasHabitats = new JTabbedPane();

        Timer loopJuego = new Timer(tiempoTick, e -> {
            for (Mascotas m : Tienda.getInstancia().getMascotas()){
                m.pasarTiempo();
            }
            PanelHabitat habitatActivo = (PanelHabitat) pestañasHabitats.getSelectedComponent();
            if (habitatActivo != null) {
                habitatActivo.actualizarBarrasMascotas();
            }
        });
        loopJuego.start();

        Habitat[] totalHabitats = Habitat.values();
        if(totalHabitats.length > 0){
            desbloquearHabitat(totalHabitats[0]);
        }
        add(pestañasHabitats, BorderLayout.CENTER);

        JPanel panelTienda = new JPanel();
        panelTienda.setBackground(Color.LIGHT_GRAY);
        panelTienda.setPreferredSize(new Dimension(1024, 100));

        JButton botonComprar = new JButton("Comprar Mascota");
        JButton botonVender = new JButton("Vender Mascota");
        JButton botonComprarHabitat = new JButton("Comprar Habitat");
        panelTienda.add(botonComprar);
        panelTienda.add(botonVender);
        panelTienda.add(botonComprarHabitat);
        add(panelTienda, BorderLayout.SOUTH);
        botonComprar.addActionListener(e -> comprarMascotaHabitat());
        botonVender.addActionListener(e -> venderMascotaHabitat());
        botonComprarHabitat.addActionListener(e -> compraHabitat());
    }
    private void desbloquearHabitat(Habitat habitat){
        habitatsComprados.add(habitat);
        PanelHabitat panelIndividual = new PanelHabitat(habitat.getRuta());
        panelesHabitats.add(panelIndividual);
        pestañasHabitats.addTab(habitat.name(), panelIndividual);

    }
    public void agregarMascota(Mascotas nuevaMascota){
        String rutaImagen = "/Imagenes/perro.png";

        switch (nuevaMascota.getTipo()) {
            case GATO:
                rutaImagen = "/Imagenes/Gato.png";
                break;
            case CONEJO:
                rutaImagen = "/Imagenes/Conejo.png";
                break;
            case HAMSTER:
                rutaImagen = "/Imagenes/Hamster.png";
                break;
            case BULBASAUR:
                rutaImagen = "/Imagenes/Bulbasaur.png";
                break;
            case EEVEE:
                rutaImagen = "/Imagenes/eevee.png";
                break;
            case PEZ_DORADO:
                rutaImagen = "/Imagenes/pez_dorado.png";
                break;
            case PULPO:
                rutaImagen = "/Imagenes/pulpo.png";
                break;
            case TORTUGA:
                rutaImagen = "/Imagenes/tortuga.png";
                break;
        }
        Habitat habitatRequerido = nuevaMascota.getHabitat();

        for(int i = 0; i < pestañasHabitats.getTabCount(); i++){
            String nombrePestaña = pestañasHabitats.getTitleAt(i);
            if(nombrePestaña.equals(habitatRequerido.name())){
                PanelHabitat panelDestino = (PanelHabitat) pestañasHabitats.getComponentAt(i);
                panelDestino.dibujarMascota(rutaImagen, nuevaMascota);

                pestañasHabitats.repaint();
                return;
            }
        }
    }
    private void compraHabitat(){
        Habitat[] totalHabitats = Habitat.values();
        if (habitatsComprados.size() >= totalHabitats.length) {
            JOptionPane.showMessageDialog(this, "¡Ya has comprado todos los habitats disponibles!");
            return;
        }
        Habitat siguienteHabitat = totalHabitats[habitatsComprados.size()];
        try {
            Tienda.getInstancia().comprarHabitat(siguienteHabitat, this.jugador);
            desbloquearHabitat(siguienteHabitat);
            JOptionPane.showMessageDialog(this, "¡Habitat comprado!");
        }
        catch (PagoInsuficienteException e){
            JOptionPane.showMessageDialog(this, "Pago insuficiente");
        }
    }
    private void comprarMascotaHabitat() {
        PanelHabitat habitatActivo = (PanelHabitat) pestañasHabitats.getSelectedComponent();

        if (habitatActivo != null) {
            int index = pestañasHabitats.getSelectedIndex();
            String nombrePestaña = pestañasHabitats.getTitleAt(index);

            TipoMascota tipo = TipoMascota.PERRO;
            String rutaMascota = "/Imagenes/perro.png";
            if (nombrePestaña.equals("ACUATICO")) {
                tipo = TipoMascota.PEZ_DORADO;
                rutaMascota = "/Imagenes/pez_dorado.png";
            } else if (nombrePestaña.equals("POKECASA")) {
                tipo = TipoMascota.BULBASAUR;
                rutaMascota = "/Imagenes/Bulbasaur.png";
            }

            try {
                Mascotas nuevaMascota = Tienda.getInstancia().comprarMascota(tipo, this.jugador);
                boolean compraExitosa = habitatActivo.dibujarMascota(rutaMascota, nuevaMascota);
                if (!compraExitosa) {
                    JOptionPane.showMessageDialog(this, "Error visual al renderizar la mascota");
                } else {
                    pestañasHabitats.repaint();
                }
            }
            catch (PagoInsuficienteException e){
                JOptionPane.showMessageDialog(this, "No tienes dinero suficiente para comprar esta mascota");
            }
            catch (HabitatLlenoExcepcion e){
                JOptionPane.showMessageDialog(this, "El habitat " + nombrePestaña + " está lleno");
            }
            catch (HabitatBloqueadoException e){
                JOptionPane.showMessageDialog(this, "El habitat " + nombrePestaña + " no lo has comprado");
            }
        }
    }
    public void venderMascotaHabitat(){
        PanelHabitat habitatActivo = (PanelHabitat) pestañasHabitats.getSelectedComponent();
        if(habitatActivo != null){
            ArrayList<JPanel> listaMascotas = habitatActivo.getEspaciosOcupados();
            if(!listaMascotas.isEmpty()){
                JPanel mascotaVendida = listaMascotas.get(listaMascotas.size() - 1);
                habitatActivo.removerMascota(mascotaVendida);
            } else {
                JOptionPane.showMessageDialog(this, "No tienes mascotas en este habitat para vender");
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador jugador = new Jugador();
            JFrame ventanaPrincipal = new JFrame("Tienda de Mascotas");
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setSize(1024, 768);
            ventanaPrincipal.setLocationRelativeTo(null);
            VentanaHabitats panelJuego = new VentanaHabitats(jugador);
            ventanaPrincipal.add(panelJuego);
            ventanaPrincipal.setVisible(true);
        });
    }
}
