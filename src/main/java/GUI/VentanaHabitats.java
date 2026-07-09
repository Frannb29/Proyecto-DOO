package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import logica.*;

/**
 * Panel grafico encargado de mostrar los habitats.
 * Utiliza un contenedor de pestañas {@link JTabbedPane} para cambiar entre los diferentes habitats.
 * Permite la compra y validacion de las mascotas y habitats.
 */
public class VentanaHabitats extends JPanel{

    private ArrayList<Habitat> habitatsComprados = new ArrayList<>();
    private ArrayList<PanelHabitat> panelesHabitats = new ArrayList<>();
    private JTabbedPane pestañasHabitats;
    private Jugador jugador;
    private Simulador simulador;

    /**
     * Construye e inicializa el centro de control de los habitats del simulador.
     * Coloca el primer habitat base por defecto y los botones de compra de mascotas y habitats.
     * @param jugador Instancia del jugador.
     * @param simulador Coordina la instanciacion de las mascotas.
     */
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
        JButton botonComprarHabitat = new JButton("Comprar Habitat");

        panelTienda.add(botonComprar);
        panelTienda.add(botonComprarHabitat);
        add(panelTienda, BorderLayout.SOUTH);

        botonComprar.addActionListener(e -> comprarMascotaHabitat());
        botonComprarHabitat.addActionListener(e -> compraHabitat());
    }

    /**
     * Registra un habitat en las colecciones, inicializa su panel de renderizado
     * y agrega una nueva pestaña al contenedor.
     * @param habitat El habitat correspondiente a desbloquear.
     */
    private void desbloquearHabitat(Habitat habitat){
        habitatsComprados.add(habitat);
        PanelHabitat panelIndividual = new PanelHabitat(habitat);
        panelesHabitats.add(panelIndividual);
        pestañasHabitats.addTab(habitat.name(), panelIndividual);
    }

    /**
     * Recibe una mascota ya instanciada, resuelve la ruta de su sprite e itera sobre las pestañas para su renderizado
     * en el {@link PanelHabitat} correspondiente.
     * @param nuevaMascota La nueva mascota que se agregará.
     */
    public void agregarMascota(Mascotas nuevaMascota){
        String rutaImagen = "/Imagenes/perro.png";

        switch (nuevaMascota.getTipo()) {
            case PERRO: rutaImagen = "/Imagenes/perro.png"; break;
            case GATO: rutaImagen = "/Imagenes/Gato.png"; break;
            case CONEJO: rutaImagen = "/Imagenes/Conejo.png"; break;
            case HAMSTER: rutaImagen = "/Imagenes/Hamster.png"; break;
            case BULBASAUR: rutaImagen = "/Imagenes/Bulbasaur.png"; break;
            case EEVEE: rutaImagen = "/Imagenes/Eevee.png"; break;
            case PEZ_DORADO: rutaImagen = "/Imagenes/pez_dorado.png"; break;
            case PULPO: rutaImagen = "/Imagenes/pulpo.png"; break;
            case TORTUGA: rutaImagen = "/Imagenes/tortuga.png"; break;
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

    /**
     * Compara todos los habitat con los habitats ya desbloqueadas, filtra un menu
     * de seleccion de items disponibles y procesa la compra del nuevo habitat capturando excepciones.
     */
    private void compraHabitat(){
        Habitat[] totalHabitats = Habitat.values();

        ArrayList<Habitat> habitatsDisponibles=new ArrayList<>();
        for (Habitat h : totalHabitats) {
            if (!habitatsComprados.contains(h)) {
                habitatsDisponibles.add(h);
            }
        }

        if (habitatsDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Ya has comprado todos los habitats disponibles.");
            return;
        }

        Habitat[] opciones=habitatsDisponibles.toArray(new Habitat[0]);

        Habitat elegido=(Habitat) JOptionPane.showInputDialog(
                this,
                "Selecciona el hábitat que deseas comprar:",
                "Comprar Hábitat",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (elegido!=null) {
            try {
                Tienda.getInstancia().comprarHabitat(elegido, this.jugador);
                desbloquearHabitat(elegido);
                JOptionPane.showMessageDialog(this, "¡Hábitat "+elegido.name()+" comprado con éxito!");
            }
            catch (PagoInsuficienteException e){
                JOptionPane.showMessageDialog(this, "No tienes dinero suficiente para comprar el hábitat "+elegido.name());
            }
        }
    }

    /**
     * Analiza el nombre de la pestaña que el jugador tiene seleccionada actualmente para mostrar solo las mascotas de ese habitat
     * y ejecuta el proceso de compra a travas del simulador, gestionando excepciones.
     */
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
        }
    }

    /**
     * Ejecuta una actualizacion que recorre todos los subpaneles de habitats para sincronizar el estado visual de sus indicadores.
     */
    public void actualizarHabitat() {
        for(PanelHabitat panel : panelesHabitats){
            panel.actualizarBarras();
        }
    }

    /**
     * Busca el panel del hábitat correspondiente a la mascota vendida y remueve
     * su componente gráfico, liberando de forma automática la capacidad visual.
     * @param mascotaLogica La instancia de la mascota que se lleva el cliente.
     */
    public void despacharMascotaVendida(Mascotas mascotaLogica) {
        Habitat habitatDeMascota = mascotaLogica.getHabitat();
        for(PanelHabitat panelHabitat : panelesHabitats){
            if(panelHabitat.getEspaciosOcupados() != null){
                JPanel contenedorEncontrado = null;

                for(JPanel contenedorMascota : panelHabitat.getEspaciosOcupados()){
                    Mascotas m = (Mascotas) contenedorMascota.getClientProperty("mascota");
                    if(m == mascotaLogica){
                        contenedorEncontrado = contenedorMascota;
                        break;
                    }
                }
                if(contenedorEncontrado != null){
                    panelHabitat.removerMascota(contenedorEncontrado);
                    break;
                }
            }
        }
    }
}