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
        this.add(panelCentral,BorderLayout.CENTER);
        mostrarSeccionAlimentos();
    }
    public void mostrarSeccionAlimentos(){
        panelCentral.removeAll();
        panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        panelCentral.add(new PanelProducto("Comida Perro", TipoSuministro.ALIMENTO_PERRO,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Comida Gato", TipoSuministro.ALIMENTO_GATO,jugador,labelDinero));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    public void mostrarSeccionMedicinas(){
        panelCentral.removeAll();
        panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        panelCentral.add(new PanelProducto("Medicina Pequeña", TipoSuministro.MEDICINA_PEQUEÑA,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Medicina Mediana", TipoSuministro.MEDICINA_MEDIANA,jugador,labelDinero));
        panelCentral.add(new PanelProducto("Medicina Grande", TipoSuministro.MEDICINA_GRANDE,jugador,labelDinero));
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}