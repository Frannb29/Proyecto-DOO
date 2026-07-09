package GUI;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Panel grafico representativo de un habitat especifico del juego.
 * Gestiona la renderizacion del fondo del habitat, la barra de higiene global,
 * y la disposicion visual de hasta 4 mascotas con sus respectivos paneles de control e indicadores de estado.
 */
public class PanelHabitat extends JPanel {
    private Image imagenFondo;
    private JPanel panelMascotas;
    private ArrayList<JPanel> espaciosOcupados = new ArrayList<>();
    private JProgressBar barraHigieneHabitat;
    private Habitat habitat;

    /**
     * Construye un nuevo contenedor para el habitat.
     * Configura los paneles de limpieza superiores, los indicadores de progreso y lee el recurso de imagen.
     * @param habitat El habitat asociado a este componente visual.
     */
    public PanelHabitat(Habitat habitat) {
        this.habitat = habitat;
        String ruta = habitat.getRuta();
        setLayout(new BorderLayout());

        JPanel panelLimpieza = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelLimpieza.setBackground(new Color(230, 240, 250));
        JLabel txtHigieneGlobal = new JLabel("Higiene del Habitat: ");
        txtHigieneGlobal.setFont(new Font("Arial", Font.BOLD, 12));

        barraHigieneHabitat = new JProgressBar(0, 100);
        barraHigieneHabitat.setValue(habitat.getHigiene());
        barraHigieneHabitat.setForeground(new Color(0, 180, 216));
        barraHigieneHabitat.setStringPainted(true);

        JButton botonLimpiarHabitat = new JButton("Limpiar Habitat");
        botonLimpiarHabitat.setFont(new Font("Arial", Font.BOLD, 11));
        botonLimpiarHabitat.addActionListener(e -> {
            this.habitat.limpiarHabitat();
            barraHigieneHabitat.setValue(100);
            JOptionPane.showMessageDialog(this, "El habitat " + habitat.getNombre() + " ha sido limpiado");
        });
        panelLimpieza.add(txtHigieneGlobal);
        panelLimpieza.add(barraHigieneHabitat);
        panelLimpieza.add(botonLimpiarHabitat);
        add(panelLimpieza, BorderLayout.NORTH);

        panelMascotas = new JPanel(new GridBagLayout());
        panelMascotas.setOpaque(false);
        add(panelMascotas, BorderLayout.CENTER);

        try {
            java.net.URL urlImagen = getClass().getResource(ruta);
            if (urlImagen != null) {
                imagenFondo = ImageIO.read(urlImagen);
            } else {
                System.err.println("No se encontró el recurso en la ruta: " + ruta);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error cargando la imagen " + ruta);
        }
    }

    /**
     * Dibuja el componente renderizando la imagen de fondo de este habitat
     * adaptandola a las dimensiones reales de la ventana de forma elastica.
     * @param g El contexto gráfico {@link Graphics} provisto por la JVM.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Genera y agrega un nuevo panel de mascota dentro del habitat.
     * Construye las barras individuales (Salud, Alimento, Felicidad, Higiene), los botones de accion
     * y la imagen de la mascota utilizando restricciones {@link GridBagConstraints}.
     * @param rutaImagen La ruta del recurso grafico de la mascota.
     * @param mascota La instancia de la mascota a representar.
     * @return true si la mascota pudo ser añadida visualmente, false si el habitat está lleno o el recurso falló.
     */
    public boolean dibujarMascota(String rutaImagen, Mascotas mascota) {

        if (espaciosOcupados.size() >= 4) {
            System.out.println("El habitat está lleno");
            return false;
        }
        java.net.URL urlMascota = getClass().getResource(rutaImagen);
        JLabel labelMascota;
        if (urlMascota != null) {
            ImageIcon iconoOriginal = new ImageIcon(urlMascota);
            Image imagenAjustada = iconoOriginal.getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH);
            labelMascota = new JLabel(new ImageIcon(imagenAjustada));
        } else {
            System.out.println("Error, no se encontro la imagen en la ruta " + rutaImagen);
            return false;
        }

        Dimension dimBarra = new Dimension(110, 10);
        Dimension dimTexto = new Dimension(110, 15);

        JLabel txtAlimento = new JLabel("Alimento");
        txtAlimento.setFont(new Font("Arial", Font.BOLD, 11));
        txtAlimento.setForeground(Color.BLACK);
        txtAlimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtAlimento.setMaximumSize(dimTexto);

        JProgressBar barraAlimento = new JProgressBar(0, 100);
        barraAlimento.setValue(mascota.getAlimentacion());
        barraAlimento.setMaximumSize(dimBarra);
        barraAlimento.setForeground(Color.GREEN);
        barraAlimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel txtFelicidad = new JLabel("Felicidad");
        txtFelicidad.setFont(new Font("Arial", Font.BOLD, 11));
        txtFelicidad.setForeground(Color.BLACK);
        txtFelicidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtFelicidad.setMaximumSize(dimTexto);

        JProgressBar barraFelicidad = new JProgressBar(0, 100);
        barraFelicidad.setValue(mascota.getFelicidad());
        barraFelicidad.setMaximumSize(dimBarra);
        barraFelicidad.setForeground(Color.GREEN);
        barraFelicidad.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel txtSalud = new JLabel("Salud");
        txtSalud.setFont(new Font("Arial", Font.BOLD, 11));
        txtSalud.setForeground(Color.BLACK);
        txtSalud.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtSalud.setMaximumSize(dimTexto);

        JProgressBar barraSalud = new JProgressBar(0, 100);
        barraSalud.setValue(mascota.getSalud());
        barraSalud.setMaximumSize(dimBarra);
        barraSalud.setForeground(Color.GREEN);
        barraSalud.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel txtHigiene = new JLabel("Higiene");
        txtHigiene.setFont(new Font("Arial", Font.BOLD, 11));
        txtHigiene.setForeground(Color.BLACK);
        txtHigiene.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtHigiene.setMaximumSize(dimTexto);

        JProgressBar barraHigiene = new JProgressBar(0, 100);
        barraHigiene.setValue(mascota.getHigiene());
        barraHigiene.setMaximumSize(dimBarra);
        barraHigiene.setForeground(Color.GREEN);
        barraHigiene.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel contenedorMascota = new JPanel();
        contenedorMascota.setLayout(new BoxLayout(contenedorMascota, BoxLayout.Y_AXIS));
        contenedorMascota.setOpaque(false);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 4, 4));
        panelBotones.setOpaque(false);

        JButton botonSuministro = new JButton("Dar Item");
        botonSuministro.setFont(new Font("Arial", Font.PLAIN, 10));
        botonSuministro.addActionListener(e -> darSuministro(mascota));

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setFont(new Font("Arial", Font.PLAIN, 10));
        botonLimpiar.addActionListener(e -> {
            try {
                mascota.limpiar();
                JOptionPane.showMessageDialog(this, "Mascota limpiada");
                actualizarBarras();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        JButton botonJugar = new JButton("Jugar");
        botonJugar.setFont(new Font("Arial", Font.PLAIN, 10));
        botonJugar.addActionListener(e -> {
            try {
                mascota.jugar();
                JOptionPane.showMessageDialog(this, "Jugaste con la mascota, su felicidad aumentó");
                actualizarBarras();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        panelBotones.add(botonSuministro);
        panelBotones.add(botonLimpiar);
        panelBotones.add(botonJugar);

        contenedorMascota.add(panelBotones);
        contenedorMascota.add(Box.createVerticalStrut(10));

        contenedorMascota.add(txtAlimento);
        contenedorMascota.add(barraAlimento);
        contenedorMascota.add(Box.createVerticalStrut(4));

        contenedorMascota.add(txtFelicidad);
        contenedorMascota.add(barraFelicidad);
        contenedorMascota.add(Box.createVerticalStrut(4));

        contenedorMascota.add(txtSalud);
        contenedorMascota.add(barraSalud);
        contenedorMascota.add(Box.createVerticalStrut(4));

        contenedorMascota.add(txtHigiene);
        contenedorMascota.add(barraHigiene);

        contenedorMascota.add(Box.createVerticalGlue());

        labelMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedorMascota.add(labelMascota);
        contenedorMascota.add(Box.createVerticalStrut(8));

        contenedorMascota.putClientProperty("mascota", mascota);
        contenedorMascota.putClientProperty("barraAlimento", barraAlimento);
        contenedorMascota.putClientProperty("barraFelicidad", barraFelicidad);
        contenedorMascota.putClientProperty("barraSalud", barraSalud);
        contenedorMascota.putClientProperty("barraHigiene", barraHigiene);

        contenedorMascota.setPreferredSize(new Dimension(130, 360));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = espaciosOcupados.size();
        c.gridy = 0;
        c.weightx = 0.20;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(0, 10, 20, 10);

        espaciosOcupados.add(contenedorMascota);
        panelMascotas.add(contenedorMascota, c);

        panelMascotas.revalidate();
        panelMascotas.repaint();
        this.revalidate();
        this.repaint();
        return true;
    }

    /**
     * Crea un cuadro de dialogo interactivo que lee el stock de suministros disponibles en la tienda
     * y le permite al jugador seleccionar un suministro para aplicar sobre la mascota.
     * @param mascota La mascota que recibe el suministro.
     */
    private void darSuministro(Mascotas mascota) {
        ArrayList<TipoSuministro> disponibles = obtenerSuministrosDisponibles();

        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tu inventario de suministros está vacío.", "Inventario", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TipoSuministro elegido = (TipoSuministro) JOptionPane.showInputDialog(
                this,
                "Selecciona el suministro a dar:",
                "Usar Suministro",
                JOptionPane.QUESTION_MESSAGE,
                null,
                disponibles.toArray(),
                disponibles.get(0)
        );

        if (elegido != null) {
            try {
                Suministros suministro = Tienda.getInstancia().seleccionarSuministro(elegido);
                suministro.usar(mascota);
                JOptionPane.showMessageDialog(this, "Suministro aplicado con éxito.");
                actualizarBarras();
            } catch (DepositoVacioException e) {
                JOptionPane.showMessageDialog(this, "No quedan suministros de ese tipo.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    /**
     * Evalaa las cantidades del inventario global de la tienda.
     * Filtra solo las categorias que cuentan con al menos una unidad.
     * @return Un listado de Tipos de suministros con stock activo.
     */
    private ArrayList<TipoSuministro> obtenerSuministrosDisponibles() {
        Tienda tienda = Tienda.getInstancia();
        ArrayList<TipoSuministro> disponibles = new ArrayList<>();

        if (tienda.getCantidadAlimentoPerro() > 0) disponibles.add(TipoSuministro.ALIMENTO_PERRO);
        if (tienda.getCantidadAlimentoGato() > 0) disponibles.add(TipoSuministro.ALIMENTO_GATO);
        if (tienda.getCantidadAlimentoConejo() > 0) disponibles.add(TipoSuministro.ALIMENTO_CONEJO);
        if (tienda.getCantidadAlimentoHamster() > 0) disponibles.add(TipoSuministro.ALIMENTO_HAMSTER);
        if (tienda.getCantidadAlimentoEevee() > 0) disponibles.add(TipoSuministro.ALIMENTO_EEVEE);
        if (tienda.getCantidadAlimentoBulbasaur() > 0) disponibles.add(TipoSuministro.ALIMENTO_BULBASAUR);
        if (tienda.getCantidadAlimentoPez() > 0) disponibles.add(TipoSuministro.ALIMENTO_PEZ);
        if (tienda.getCantidadAlimentoPulpo() > 0) disponibles.add(TipoSuministro.ALIMENTO_PULPO);
        if (tienda.getCantidadAlimentoTortuga() > 0) disponibles.add(TipoSuministro.ALIMENTO_TORTUGA);

        if (tienda.getCantidadMedicinaPequeña() > 0) disponibles.add(TipoSuministro.MEDICINA_PEQUEÑA);
        if (tienda.getCantidadMedicinaMediana() > 0) disponibles.add(TipoSuministro.MEDICINA_MEDIANA);
        if (tienda.getCantidadMedicinaGrande() > 0) disponibles.add(TipoSuministro.MEDICINA_GRANDE);

        return disponibles;
    }

    /**
     * Sincroniza y refresca los valores de todas las {@link JProgressBar}
     * leyendo los atributos en tiempo real de la parte logica.
     */
    public void actualizarBarras() {
        if (habitat != null && barraHigieneHabitat != null) {
            barraHigieneHabitat.setValue(habitat.getHigiene());
        }
        for (JPanel contenedor : espaciosOcupados) {
            Mascotas mLogica = (Mascotas) contenedor.getClientProperty("mascota");
            JProgressBar bAlimento = (JProgressBar) contenedor.getClientProperty("barraAlimento");
            JProgressBar bFelicidad = (JProgressBar) contenedor.getClientProperty("barraFelicidad");
            JProgressBar bSalud = (JProgressBar) contenedor.getClientProperty("barraSalud");
            JProgressBar bHigiene = (JProgressBar) contenedor.getClientProperty("barraHigiene");

            if (mLogica != null) {
                if (bAlimento != null) bAlimento.setValue(mLogica.getAlimentacion());
                if (bFelicidad != null) bFelicidad.setValue(mLogica.getFelicidad());
                if (bSalud != null) bSalud.setValue(mLogica.getSalud());
                if (bHigiene != null) bHigiene.setValue(mLogica.getHigiene());
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Elimina el contenedor visual de una mascota y reordena las coordenadas X de las mascotas restantes
     * en el GridBagLayout para no dejar vacios visuales.
     * @param mascotaVendida El panel {@link JPanel} de la mascota que fue vendida.
     */
    public void removerMascota(JPanel mascotaVendida) {
        if (espaciosOcupados.contains(mascotaVendida)) {
            panelMascotas.remove(mascotaVendida);
            espaciosOcupados.remove(mascotaVendida);
            panelMascotas.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;
            c.weightx = 0.20;
            c.weighty = 1.0;
            c.anchor = GridBagConstraints.SOUTH;
            c.insets = new Insets(0, 10, 20, 10);

            for (int i = 0; i < espaciosOcupados.size(); i++) {
                c.gridx = i;
                panelMascotas.add(espaciosOcupados.get(i), c);
            }

            panelMascotas.revalidate();
            panelMascotas.repaint();
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Obtiene el listado de paneles de las mascotas presentes en el habitat.
     * @return Una {@link ArrayList} de subpaneles {@link JPanel}.
     */
    public ArrayList<JPanel> getEspaciosOcupados() {
        return this.espaciosOcupados;
    }
}