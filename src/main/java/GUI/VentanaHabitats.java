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
    private Simulador simulador;
    public VentanaHabitats(Jugador jugador, Simulador simulador){
        int tiempoTick = 1000;
        this.jugador = jugador;
        this.simulador = simulador;

        setLayout(new BorderLayout());
        pestañasHabitats = new JTabbedPane();

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
        PanelHabitat panelIndividual = new PanelHabitat(habitat);
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
                rutaImagen = "/Imagenes/Eevee.png";
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

            TipoMascota[] opciones=null;

            if (nombrePestaña.equals("CASA")) {
                opciones=new TipoMascota[]{
                        TipoMascota.PERRO,TipoMascota.GATO,TipoMascota.CONEJO,TipoMascota.HAMSTER
                };
            } else if (nombrePestaña.equals("POKECASA")){
                opciones=new TipoMascota[]{TipoMascota.EEVEE,TipoMascota.BULBASAUR};
            }else if (nombrePestaña.equals("ACUATICO")){
                opciones=new TipoMascota[]{TipoMascota.PEZ_DORADO,TipoMascota.PULPO,TipoMascota.TORTUGA};
            }

            TipoMascota elegido=(TipoMascota) JOptionPane.showInputDialog(this,"Eliga una mascota","Comprar Mascota", JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);

            if(elegido==null){
                return;
            }
            String rutaMascota="";
            switch (elegido) {
                case PERRO: rutaMascota="/Imagenes/perro.png"; break;
                case GATO: rutaMascota="/Imagenes/Gato.png"; break;
                case CONEJO: rutaMascota="/Imagenes/Conejo.png"; break;
                case HAMSTER: rutaMascota="/Imagenes/Hamster.png"; break;
                case BULBASAUR: rutaMascota="/Imagenes/Bulbasaur.png"; break;
                case EEVEE: rutaMascota= "/Imagenes/Eevee.png"; break;
                case PEZ_DORADO: rutaMascota="/Imagenes/pez_dorado.png"; break;
                case TORTUGA: rutaMascota="/Imagenes/tortuga.png"; break;
                case PULPO: rutaMascota="/Imagenes/pulpo.png"; break;
            }
            try {
                Mascotas nuevaMascota = simulador.comprarMascota(elegido);
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
    public void actualizarHabitat() {
        for(PanelHabitat panel : panelesHabitats){
            panel.actualizarBarras();
        }
    }
}
