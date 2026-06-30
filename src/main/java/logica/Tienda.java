package logica;

import java.util.ArrayList;

public class Tienda{
    private static Tienda instancia;

    private Deposito<Alimento> stockAlimentoPerro;
    private Deposito<Alimento> stockAlimentoGato;
    private Deposito<Medicina> stockMedicinaPequeña;
    private Deposito<Medicina> stockMedicinaMediana;
    private Deposito<Medicina> stockMedicinaGrande;
    private ArrayList<Mascotas> mascotas;
    private ArrayList<Cliente> filaClientes;

    private Tienda(){
        stockAlimentoPerro=new Deposito<Alimento>();
        stockAlimentoGato=new Deposito<Alimento>();
        stockMedicinaPequeña=new Deposito<Medicina>();
        stockMedicinaMediana=new Deposito<Medicina>();
        stockMedicinaGrande=new Deposito<Medicina>();

        mascotas=new ArrayList<>();
        filaClientes=new ArrayList<>();
    }

    public static Tienda getInstancia(){
        if(instancia==null){
            instancia=new Tienda();
        }
        
        return instancia;
    }

    public void comprarSuministros(Jugador jugador, Suministros objeto){
        int valor=objeto.getValor();
        int presupuesto=jugador.getPresupuesto();
        if(valor>presupuesto){
            System.out.println("Pago insuficiente");
        }
        else{
            presupuesto-=valor;
            jugador.setPresupuesto(presupuesto);
            if(objeto instanceof Alimento){
                Alimento alimento=(Alimento) objeto;
                if(alimento.getTipoMascota()==TipoMascota.PERRO){
                    stockAlimentoPerro.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.GATO){
                    stockAlimentoGato.add(alimento);
                }
            }
            else if(objeto instanceof Medicina){
                Medicina medicina=(Medicina) objeto;
                if(medicina.getPuntosCuracion()==25){
                    stockMedicinaPequeña.add(medicina);
                }
                else if(medicina.getPuntosCuracion()==50){
                    stockMedicinaMediana.add(medicina);
                }
                else if(medicina.getPuntosCuracion()==100){
                    stockMedicinaGrande.add(medicina);
                }
            }
        }
    }
    public Suministros seleccionarSuministro(TipoSuministro tipo){
        if(tipo==TipoSuministro.ALIMENTO_PERRO){
            return stockAlimentoPerro.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_GATO){
            return stockAlimentoGato.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_PEQUEÑA){
            return stockMedicinaPequeña.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_MEDIANA){
            return stockMedicinaMediana.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_GRANDE){
            return stockMedicinaGrande.get();
        }
        return null;
    }

    public void addCliente(Cliente cliente){
        filaClientes.add(cliente);
    }
}
