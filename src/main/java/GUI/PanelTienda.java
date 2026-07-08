package GUI;
import logica.Jugador;
import logica.TipoSuministro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTienda extends JPanel {
    private Jugador jugador;
    private JLabel labelDinero;
    private JPanel panelCentral;
    private JScrollPane scroll;
    public PanelTienda(Jugador jugador){
        this.jugador=jugador;
        this.setLayout(new BorderLayout());
        JPanel panelSuperior=new JPanel(new FlowLayout(FlowLayout.LEFT,20,15));
        panelSuperior.setBackground(new Color(225,170,85));
        labelDinero=new JLabel("$ "+ jugador.getPresupuesto());
        JButton botonAlimento=new JButton("Alimento");
        JButton botonMedicina=new JButton("Medicina");
        panelSuperior.add(labelDinero);
        panelSuperior.add(botonAlimento);
        panelSuperior.add(botonMedicina);
        panelCentral=new JPanel();
        panelCentral.setBackground(new Color(245,240,230));
        scroll=new JScrollPane(panelCentral);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        botonAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mostrarSeccionAlimentos();
            }
        });
        botonMedicina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSeccionMedicinas();
            }
        });
        this.add(panelSuperior,BorderLayout.NORTH);
        this.add(scroll,BorderLayout.CENTER);
        mostrarSeccionAlimentos();
    }
    public void mostrarSeccionAlimentos(){
        panelCentral.removeAll();
        panelCentral.setLayout(new GridLayout(0, 2, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.add(new PanelProducto("Comida Perro", TipoSuministro.ALIMENTO_PERRO,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Comida Gato", TipoSuministro.ALIMENTO_GATO,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Comida Conejo", TipoSuministro.ALIMENTO_CONEJO,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Comida Hamster", TipoSuministro.ALIMENTO_HAMSTER,jugador,labelDinero));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    public void mostrarSeccionMedicinas(){
        panelCentral.removeAll();
        panelCentral.setLayout(new GridLayout(0, 2, 20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.add(new PanelProducto("Medicina Pequeña", TipoSuministro.MEDICINA_PEQUEÑA,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Medicina Mediana", TipoSuministro.MEDICINA_MEDIANA,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Medicina Grande", TipoSuministro.MEDICINA_GRANDE,jugador,labelDinero));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}