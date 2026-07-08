package GUI;
import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PanelProducto extends JPanel {
    public PanelProducto(String nombre,TipoSuministro tipo,Jugador jugador,JLabel labelDinero){
        this.setLayout(new BorderLayout(0, 15));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(160, 250));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(210,210,210)), BorderFactory.createEmptyBorder(10,10,10,10)));
        JLabel labelNombre=new JLabel(nombre,SwingConstants.CENTER);
        JLabel labelImagen=new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        try{
            URL urlImagen=getClass().getResource("/Imagenes/"+tipo.name()+".png");
            if(urlImagen!=null){
                ImageIcon icono=new ImageIcon(urlImagen);
                Image imagen=icono.getImage();
                Image imagenAjustada=imagen.getScaledInstance(110,-1,Image.SCALE_SMOOTH);
                labelImagen.setIcon(new ImageIcon(imagenAjustada));
            }
            else{
                labelImagen.setText("Imagen no encontrada");
            }
        }
        catch (Exception e){
            labelImagen.setText("Error");
        }
        JButton botonCompra=new JButton("$"+tipo.getPrecio());
        botonCompra.setBackground(new Color(139,196,74));
        botonCompra.setForeground(Color.WHITE);
        botonCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Suministros s=null;
                    if (tipo==TipoSuministro.ALIMENTO_PERRO || tipo==TipoSuministro.ALIMENTO_GATO || tipo==TipoSuministro.ALIMENTO_CONEJO || tipo==TipoSuministro.ALIMENTO_HAMSTER || tipo==TipoSuministro.ALIMENTO_EEVEE || tipo==TipoSuministro.ALIMENTO_BULBASAUR || tipo==TipoSuministro.ALIMENTO_PEZ || tipo==TipoSuministro.ALIMENTO_PULPO || tipo==TipoSuministro.ALIMENTO_TORTUGA){
                        s=new Alimento(tipo);
                    }
                    else{
                        s=new Medicina(tipo);
                    }
                    Tienda.getInstancia().comprarSuministros(jugador,s);
                    labelDinero.setText("$ "+jugador.getPresupuesto());
                    JOptionPane.showMessageDialog(null,"Articulo comprado");
                }
                catch (PagoInsuficienteException ex){
                    JOptionPane.showMessageDialog(null,"Dinero insuficiente");
                }
            }
        });
        this.add(labelNombre,BorderLayout.NORTH);
        this.add(labelImagen,BorderLayout.CENTER);
        this.add(botonCompra,BorderLayout.SOUTH);
    }
}