package GUI;

import logica.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PanelHabitat extends JPanel{
    private Image imagenFondo;
    private JPanel panelMascotas;
    private ArrayList<JPanel> espaciosOcupados = new ArrayList<>();

    public PanelHabitat (String ruta){

        setLayout(new BorderLayout());
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
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error cargando la imagen " + ruta);
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(imagenFondo != null){
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public boolean dibujarMascota(String rutaImagen, Mascotas mascota){

        if (espaciosOcupados.size() >= 4) {
            System.out.println("El habitat está lleno");
            return false;
        }
        java.net.URL urlMascota = getClass().getResource(rutaImagen);
        JLabel labelMascota;
        if(urlMascota != null){
            ImageIcon iconoOriginal = new ImageIcon(urlMascota);
            Image imagenAjustada = iconoOriginal.getImage().getScaledInstance(110, 140, Image.SCALE_SMOOTH);
            labelMascota = new JLabel(new ImageIcon(imagenAjustada));
        }
        else {
            System.out.println("Error, no se encontro la imagen en la ruta "+ rutaImagen);
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

        labelMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedorMascota.add(labelMascota);
        contenedorMascota.add(Box.createVerticalStrut(8));

        contenedorMascota.setPreferredSize(new Dimension(120, 270));

        contenedorMascota.putClientProperty("mascota", mascota);
        contenedorMascota.putClientProperty("barraAlimento", barraAlimento);
        contenedorMascota.putClientProperty("barraFelicidad", barraFelicidad);
        contenedorMascota.putClientProperty("barraSalud", barraSalud);
        contenedorMascota.putClientProperty("barraHigiene", barraHigiene);

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

    public void actualizarBarrasMascotas(){
        for (JPanel contenedor : espaciosOcupados) {
            Mascotas mLogica = (Mascotas) contenedor.getClientProperty("mascota");
            JProgressBar bAlimento = (JProgressBar) contenedor.getClientProperty("barraAlimento");
            JProgressBar bFelicidad = (JProgressBar) contenedor.getClientProperty("barraFelicidad");
            JProgressBar bSalud = (JProgressBar) contenedor.getClientProperty("barraSalud");
            JProgressBar bHigiene = (JProgressBar) contenedor.getClientProperty("barraHigiene");

            if(mLogica != null){
                if (bAlimento != null) bAlimento.setValue(mLogica.getAlimentacion());
                if (bFelicidad != null) bFelicidad.setValue(mLogica.getFelicidad());
                if (bSalud != null) bSalud.setValue(mLogica.getSalud());
                if (bHigiene != null) bHigiene.setValue(mLogica.getHigiene());

            }
        }
        revalidate();
        repaint();
    }

    public void removerMascota(Component mascotaVendida){
        if(espaciosOcupados.contains(mascotaVendida)){
            panelMascotas.remove(mascotaVendida);
            espaciosOcupados.remove(mascotaVendida);

            panelMascotas.revalidate();
            panelMascotas.repaint();
            this.revalidate();
            this.repaint();
        }
    }
    public ArrayList<JPanel> getEspaciosOcupados() {
        return this.espaciosOcupados;
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Habitat con Mascotas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            PanelHabitat habitat = new PanelHabitat("/Imagenes/Fondo_Casa.png");
            JPanel panelControl = new JPanel();
            JButton botonComprar = new JButton("Comprar Mascota");
            JButton botonVender = new JButton("Vender Mascota");
            panelControl.add(botonComprar);
            panelControl.add(botonVender);

            botonComprar.addActionListener(e -> {
                Mascotas mascotaPrueba = MascotaFactory.crearMascota(TipoMascota.PERRO);
                boolean compraExitosa = habitat.dibujarMascota("/Imagenes/Perro.png", mascotaPrueba);
                if(!compraExitosa){
                    JOptionPane.showMessageDialog(frame, "¡No puedes comprar más! El habitat está lleno");
                }
            });

            botonVender.addActionListener(e -> {
                ArrayList<JPanel> listaMascotas = habitat.getEspaciosOcupados();
                if(!listaMascotas.isEmpty()){
                    JPanel ultimaMascota = listaMascotas.get(listaMascotas.size() - 1);
                    habitat.removerMascota(ultimaMascota);
                } else {
                    JOptionPane.showMessageDialog(frame, "No hay mascotas para vender");
                }
            });
            frame.setLayout(new BorderLayout());
            frame.add(habitat, BorderLayout.CENTER);
            frame.add(panelControl, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}
