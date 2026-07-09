package GUI;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Panel grafico encargado de renderizar visualmente la fila de clientes en la tienda.
 * Maneja la perspectiva, globos de dialogo con los pedidos
 * y la interaccion para atender a los compradores mediante la interfaz de usuario.
 */
public class PanelClientes extends JPanel {
    private Tienda tienda;
    private Jugador jugador;
    private Image imagenFondo;

    /**
     * Arreglo estatico de coordenadas absolutas que define la posicion, el tamaño
     * y la escala de los clientes en la fila para simular un efecto de profundidad.
     */
    private final Rectangle[] posicionClientes = {
            new Rectangle(200, 247, 150, 280),
            new Rectangle(225, 247, 120, 220),
            new Rectangle(245, 247, 95,  175),
            new Rectangle(254, 247, 75,  140),
            new Rectangle(265, 247, 60,  110)
    };

    /**
     * Construye un nuevo panel de control para los clientes.
     * Configura el tamaño preferido, un layout nulo para el control de posiciones manual
     * y carga el recurso grafico del fondo de la tienda.
     * @param jugador El jugador que recibirá el dinero de las ventas.
     */
    public PanelClientes(Jugador jugador) {
        this.jugador=jugador;
        this.tienda=Tienda.getInstancia();

        setLayout(null);
        setPreferredSize(new Dimension(600, 400));

        try {
            URL urlFondo=getClass().getResource("/Imagenes/placeholder_fondo.png");
            if (urlFondo!=null) {
                imagenFondo = ImageIO.read(urlFondo);
            } else {
                System.out.println("No se encontró la imagen de fondo.");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el fondo de la tienda.");
        }

        actualizarLista();
    }

    /**
     * Sobrescribe el metodo de dibujo del componente para renderizar el fondo de la tienda.
     * Si la imagen no está disponible, dibuja un fondo gris por defecto.
     * @param g El contexto gráfico {@link Graphics} utilizado para pintar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo!=null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(new Color(220, 210, 200));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Limpia el contenedor y vuelve a generar los componentes de la interfaz.
     * Renderiza el boton de atencion y mapea hasta un maximo de 5 clientes de la fila
     * aplicando el calculo de perspectiva y sus globos de texto con el pedido correspondiente.
     */
    public void actualizarLista() {
        removeAll();

        JButton botonAtender=new JButton("Atender Cliente");
        botonAtender.setBounds(50, 560, 150, 40);
        botonAtender.setBackground(new Color(76, 175, 80));
        botonAtender.setForeground(Color.WHITE);
        botonAtender.setFont(new Font("Arial", Font.BOLD, 14));
        botonAtender.addActionListener(e -> {
            Mascotas mascotaVendida=tienda.atenderCliente(this.jugador);
            if (mascotaVendida==null) {
                JOptionPane.showMessageDialog(this, "No tienes la mascota solicitada o no se puede vender.");
            }
            actualizarLista();
        });

        add(botonAtender, 0);

        int posicionActual = 0;
        for (Cliente cliente : tienda.getFilaClientes()) {
            if (posicionActual>=posicionClientes.length) break;

            Rectangle bounds=posicionClientes[posicionActual];

            JLabel labelPedido=new JLabel("Pide: "+cliente.getPedido().name(), SwingConstants.CENTER);
            labelPedido.setOpaque(true);
            labelPedido.setBackground(Color.WHITE);
            labelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            int fontSize=Math.max(8, 12-(posicionActual*1));
            labelPedido.setFont(new Font("Arial", Font.BOLD, fontSize));

            int globoX=bounds.x + (bounds.width / 2) - 40;
            int globoY=bounds.y - 25;
            labelPedido.setBounds(globoX, globoY, 80, 20);

            JLabel labelImagen=crearImagenCliente(bounds.width, bounds.height);
            labelImagen.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);

            add(labelPedido);
            add(labelImagen);

            posicionActual++;
        }

        revalidate();
        repaint();
    }

    /**
     * Helper encargado de cargar y redimensionar el sprite o avatar del cliente
     * según el tamaño dinamico requerido por su posicion en la fila.
     * @param ancho Ancho destino del sprite en pixeles.
     * @param alto  Alto destino del sprite en pixeles.
     * @return Un objeto {@link JLabel} contenedor con la imagen escalada y centrada.
     */
    private JLabel crearImagenCliente(int ancho, int alto) {
        JLabel label=new JLabel();
        try {
            URL urlImagen=getClass().getResource("/Imagenes/Cliente.png");
            if (urlImagen!=null) {
                ImageIcon icono=new ImageIcon(urlImagen);

                Image imagenAjustada=icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(imagenAjustada));
            }
        } catch (Exception e) {}

        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}