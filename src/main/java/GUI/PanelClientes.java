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
    private VentanaHabitats ventanaHabitats;

    /**
     * Arreglo estatico de coordenadas absolutas que define la posicion
     * de los clientes en la fila.
     */
    private final Point[] posicionClientes = {
        new Point(300, 190),  
        new Point(180, 140), 
        new Point(110, 140)
    };

    /**
     * Construye un nuevo panel de control para los clientes.
     * Configura el tamaño preferido, un layout nulo para el control de posiciones manual
     * y carga el recurso grafico del fondo de la tienda.
     * @param jugador El jugador que recibirá el dinero de las ventas.
     */
    public PanelClientes(Jugador jugador, VentanaHabitats ventanaHabitats) {
        this.jugador=jugador;
        this.tienda=Tienda.getInstancia();
        this.ventanaHabitats = ventanaHabitats;

        setLayout(null);
        setPreferredSize(new Dimension(600, 400));

        try {
            URL urlFondo=getClass().getResource("/Imagenes/fondo_tienda.png");
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
     * Limpia el contenedor y vuelve a generar los componentes de la interfaz y
     * renderiza el boton de atencion y mapea hasta un maximo de 3 clientes de la fila.
     */
    public void actualizarLista() {
        removeAll();

        if(!tienda.getFilaClientes().isEmpty()) {
            JButton botonAtender=new JButton("Atender");
            botonAtender.setBounds(400, 270, 75, 20);
            botonAtender.setBackground(Color.RED);
            botonAtender.setForeground(Color.WHITE);
            botonAtender.setFont(new Font("Arial", Font.BOLD, 9));
            botonAtender.addActionListener(e -> {
                Mascotas mascotaVendida=tienda.atenderCliente(this.jugador);
                if (mascotaVendida==null) {
                    JOptionPane.showMessageDialog(this, "No tienes la mascota solicitada o no se puede vender.");
                }
                else {
                    this.ventanaHabitats.despacharMascotaVendida(mascotaVendida);
                    JOptionPane.showMessageDialog(this, "Cliente atendido y mascota entregada");
                }
                actualizarLista();
            });

            add(botonAtender, 0);
        }

        int posicionActual = 0;
        for (Cliente cliente : tienda.getFilaClientes()) {
            if (posicionActual>=posicionClientes.length) break;

            Point p=posicionClientes[posicionActual];

            if (posicionActual==0) {
                String textoPedido="Pide: " + cliente.getPedido().name();
                JLabel labelPedido=new JLabel(textoPedido, SwingConstants.CENTER);
                labelPedido.setOpaque(true);
                labelPedido.setBackground(Color.WHITE);
                labelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                Font fuente=new Font("Arial", Font.BOLD, 11);
                labelPedido.setFont(fuente);

                FontMetrics fm=labelPedido.getFontMetrics(fuente);
                int anchoTexto=fm.stringWidth(textoPedido) + 20; 

                int xGlobo=p.x+(100/2)-(anchoTexto/2);
                labelPedido.setBounds(xGlobo, p.y - 35, anchoTexto, 25);

                add(labelPedido);
            }

            JLabel labelImagen = crearImagenCliente();
            labelImagen.setBounds(p.x, p.y, 100, 250); 

            add(labelImagen);

            posicionActual++;
        }

        revalidate();
        repaint();
    }

    /**
     * Helper encargado de cargar el sprite o avatar del cliente.
     * @return Un objeto {@link JLabel} contenedor con la imagen escalada y centrada.
     */
    private JLabel crearImagenCliente() {
        JLabel label=new JLabel();
        try {
            URL urlImagen=getClass().getResource("/Imagenes/Cliente.png");
            if (urlImagen!=null) {
                ImageIcon icono=new ImageIcon(urlImagen);

                Image imagenAjustada=icono.getImage().getScaledInstance(100, 250, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(imagenAjustada));
            }
        } catch (Exception e) {}

        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}