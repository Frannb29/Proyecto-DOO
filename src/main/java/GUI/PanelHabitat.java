package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelHabitat extends JPanel{
    private Image imagenFondo;
    private final Point[] posicionesFijas = {
            new Point(130, 430),
            new Point(310, 390),
            new Point(540, 390),
            new Point(750, 430)
    };
    //cambiar a private despues
    public final Component[] espaciosOcupados = new Component[posicionesFijas.length];
    public PanelHabitat (String ruta){
        setLayout(null);
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

    public boolean dibujarMascota(String rutaImagen){
        int indiceLibre = -1;
        for(int i = 0; i < espaciosOcupados.length; i++){
            if (espaciosOcupados[i] == null){
                indiceLibre = i;
                break;
            }
        }
        if(indiceLibre == -1){
            System.out.println("El habitat está lleno");
            return false;
        }
        java.net.URL urlMascota = getClass().getResource(rutaImagen);
        if(urlMascota != null){
            ImageIcon iconoOriginal = new ImageIcon(urlMascota);
            Image imagenAjustada = iconoOriginal.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
            JLabel labelMascota = new JLabel(new ImageIcon(imagenAjustada));

            Point punto = posicionesFijas[indiceLibre];
            espaciosOcupados[indiceLibre] = labelMascota;
            añadirMascotas(labelMascota, punto.x, punto.y);
            return true;
        }
        return false;
    }

    public void añadirMascotas(Component mascota, int x, int y){
        mascota.setBounds(x, y, 100, 110);
        add(mascota);
        revalidate();
        repaint();
    }

    public void removerMascota(Component mascotaVendida){
        for(int i = 0; i < espaciosOcupados.length; i++){
            if (espaciosOcupados[i] == mascotaVendida){
                remove(mascotaVendida);
                espaciosOcupados[i] = null;
                revalidate();
                repaint();
                break;
            }
        }
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
                boolean compraExitosa = habitat.dibujarMascota("/Imagenes/Perro.png");
                if(!compraExitosa){
                    JOptionPane.showMessageDialog(frame, "¡No puedes comprar más! El habitat está lleno");
                }
            });

            botonVender.addActionListener(e -> {
                Component mascotaVendida = null;
                for (int i = habitat.espaciosOcupados.length-1; i>= 0; i--){
                    if(habitat.espaciosOcupados[i] != null){
                        mascotaVendida = habitat.espaciosOcupados[i];
                        break;
                    }
                }
                if (mascotaVendida != null){
                    habitat.removerMascota(mascotaVendida);
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
