package GUI;
import logica.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelInventario extends JPanel {
    private JButton botonAlimentoPerro;
    private JButton botonAlimentoGato;
    private JButton botonAlimentoConejo;
    private JButton botonAlimentoHamster;
    private JButton botonAlimentoEevee;
    private JButton botonAlimentoBulbasaur;
    private JButton botonAlimentoPez;
    private JButton botonAlimentoPulpo;
    private JButton botonAlimentoTortuga;
    private JButton botonMedicinaPequeña;
    private JButton botonMedicinaMediana;
    private JButton botonMedicinaGrande;
    public PanelInventario(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(60,60,60));
        JPanel panelBotones=new JPanel();
        panelBotones.setLayout(new GridLayout(1,0,15,10));
        panelBotones.setBackground(new Color(60,60,60));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        botonAlimentoPerro=crearBoton("/Imagenes/ALIMENTO_PERRO.png");
        botonAlimentoGato=crearBoton("/Imagenes/ALIMENTO_GATO.png");
        botonAlimentoConejo=crearBoton("/Imagenes/ALIMENTO_CONEJO.png");
        botonAlimentoHamster=crearBoton("/Imagenes/ALIMENTO_HAMSTER.png");
        botonAlimentoEevee=crearBoton("/Imagenes/ALIMENTO_EEVEE.png");
        botonAlimentoBulbasaur=crearBoton("/Imagenes/ALIMENTO_BULBASAUR.png");
        botonAlimentoPez=crearBoton("/Imagenes/ALIMENTO_PEZ.png");
        botonAlimentoPulpo=crearBoton("/Imagenes/ALIMENTO_PULPO.png");
        botonAlimentoTortuga=crearBoton("/Imagenes/ALIMENTO_TORTUGA.png");
        botonMedicinaPequeña=crearBoton("/Imagenes/MEDICINA_PEQUEÑA.png");
        botonMedicinaMediana=crearBoton("/Imagenes/MEDICINA_MEDIANA.png");
        botonMedicinaGrande=crearBoton("/Imagenes/MEDICINA_GRANDE.png");
        panelBotones.add(botonAlimentoPerro);
        panelBotones.add(botonAlimentoGato);
        panelBotones.add(botonAlimentoConejo);
        panelBotones.add(botonAlimentoHamster);
        panelBotones.add(botonAlimentoEevee);
        panelBotones.add(botonAlimentoBulbasaur);
        panelBotones.add(botonAlimentoPez);
        panelBotones.add(botonAlimentoPulpo);
        panelBotones.add(botonAlimentoTortuga);
        panelBotones.add(botonMedicinaPequeña);
        panelBotones.add(botonMedicinaMediana);
        panelBotones.add(botonMedicinaGrande);
        JScrollPane scroll=new JScrollPane(panelBotones);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
        this.add(scroll,BorderLayout.CENTER);
        configurarAcciones();
    }
    public JButton crearBoton(String rutaImagen){
        JButton boton=new JButton("0");
        boton.setPreferredSize(new Dimension(80,80));
        boton.setBackground(Color.LIGHT_GRAY);
        boton.setMargin(new Insets(0, 0, 0, 0));
        try{
            URL urlImagen=getClass().getResource(rutaImagen);
            if(urlImagen!=null){
                ImageIcon icono=new ImageIcon(urlImagen);
                Image imagen=icono.getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH);
                boton.setIcon(new ImageIcon(imagen));
            }
            else{
                boton.setText("Producto");
            }
        }
        catch(Exception e){
            boton.setText("Error");
        }
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setForeground(Color.BLACK);
        return boton;
    }
    public void actualizarCantidades(){
        Tienda tienda = Tienda.getInstancia();
        botonAlimentoPerro.setText(String.valueOf(tienda.getCantidadAlimentoPerro()));
        botonAlimentoGato.setText(String.valueOf(tienda.getCantidadAlimentoGato()));
        botonAlimentoConejo.setText(String.valueOf(tienda.getCantidadAlimentoConejo()));
        botonAlimentoHamster.setText(String.valueOf(tienda.getCantidadAlimentoHamster()));
        botonAlimentoEevee.setText(String.valueOf(tienda.getCantidadAlimentoEevee()));
        botonAlimentoBulbasaur.setText(String.valueOf(tienda.getCantidadAlimentoBulbasaur()));
        botonAlimentoPez.setText(String.valueOf(tienda.getCantidadAlimentoPez()));
        botonAlimentoPulpo.setText(String.valueOf(tienda.getCantidadAlimentoPulpo()));
        botonAlimentoTortuga.setText(String.valueOf(tienda.getCantidadAlimentoTortuga()));
        botonMedicinaPequeña.setText(String.valueOf(tienda.getCantidadMedicinaPequeña()));
        botonMedicinaMediana.setText(String.valueOf(tienda.getCantidadMedicinaMediana()));
        botonMedicinaGrande.setText(String.valueOf(tienda.getCantidadMedicinaGrande()));
    }
    private void configurarAcciones(){
        botonAlimentoPerro.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_PERRO));
        botonAlimentoGato.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_GATO));
        botonAlimentoConejo.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_CONEJO));
        botonAlimentoHamster.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_HAMSTER));
        botonAlimentoEevee.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_EEVEE));
        botonAlimentoBulbasaur.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_BULBASAUR));
        botonAlimentoPez.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_PEZ));
        botonAlimentoPulpo.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_PULPO));
        botonAlimentoTortuga.addActionListener(e -> procesarUsoSuministro(TipoSuministro.ALIMENTO_TORTUGA));
        botonMedicinaPequeña.addActionListener(e -> procesarUsoSuministro(TipoSuministro.MEDICINA_PEQUEÑA));
        botonMedicinaMediana.addActionListener(e -> procesarUsoSuministro(TipoSuministro.MEDICINA_MEDIANA));
        botonMedicinaGrande.addActionListener(e -> procesarUsoSuministro(TipoSuministro.MEDICINA_GRANDE));
    }
    private void procesarUsoSuministro(TipoSuministro tipo){
        Tienda tienda=Tienda.getInstancia();
        if(tienda.getMascotas().isEmpty()){
            JOptionPane.showMessageDialog(this,"No hay mascotas");
            return;
        }
        try {
            Suministros suministro= tienda.seleccionarSuministro(tipo);
            Mascotas[] arregloMascotas=tienda.getMascotas().toArray(new Mascotas[0]);
            Mascotas mascotaSeleccionada=(Mascotas) JOptionPane.showInputDialog(this,"Elige mascota "+tipo.name(),"Usar suministro",JOptionPane.QUESTION_MESSAGE,null,arregloMascotas,arregloMascotas[0]);
            if(mascotaSeleccionada!=null){
                if(suministro instanceof Alimento) {
                    Alimento alimento=(Alimento) suministro;
                    if(alimento.getTipoMascota()!=mascotaSeleccionada.getTipo()){
                        JOptionPane.showMessageDialog(this, "Comida incorrecta para: "+mascotaSeleccionada.getTipo(),"Comida Incorrecta", JOptionPane.WARNING_MESSAGE);
                        Jugador jugadorTemporal=new Jugador();
                        jugadorTemporal.setPresupuesto(999999);
                        try{
                            tienda.comprarSuministros(jugadorTemporal, suministro);
                        }
                        catch (PagoInsuficienteException ignored){}
                        return;
                    }
                }
                suministro.usar(mascotaSeleccionada);
                JOptionPane.showMessageDialog(this,mascotaSeleccionada.getTipo()+" ha recibido el suministro");
            }
            else{
                Jugador jugadorTemporal=new Jugador();
                jugadorTemporal.setPresupuesto(999999);
                try {
                    tienda.comprarSuministros(jugadorTemporal,suministro);
                }
                catch (PagoInsuficienteException ignored){}
            }
        }
        catch (DepositoVacioException e){
            JOptionPane.showMessageDialog(this, "No tienes "+tipo.name()+" en tu inventario");
        }
    }
}
