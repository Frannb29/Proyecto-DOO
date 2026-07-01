package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tienda{
    private static Tienda instancia;

    private Deposito<Alimento> stockAlimentoPerro;
    private Deposito<Alimento> stockAlimentoGato;
    private Deposito<Medicina> stockMedicinaPequeña;
    private Deposito<Medicina> stockMedicinaMediana;
    private Deposito<Medicina> stockMedicinaGrande;
    private ArrayList<Mascotas> mascotas;
    private Queue<Cliente> filaClientes;

    private Tienda(){
        stockAlimentoPerro=new Deposito<Alimento>();
        stockAlimentoGato=new Deposito<Alimento>();
        stockMedicinaPequeña=new Deposito<Medicina>();
        stockMedicinaMediana=new Deposito<Medicina>();
        stockMedicinaGrande=new Deposito<Medicina>();

        mascotas=new ArrayList<>();
        filaClientes=new LinkedList<>();
    }

    public static Tienda getInstancia(){
        if(instancia==null){
            instancia=new Tienda();
        }
        
        return instancia;
    }

    public void comprarSuministros(Jugador jugador, Suministros objeto) throws PagoInsuficienteException{
        int valor=objeto.getValor();
        int presupuesto=jugador.getPresupuesto();
        if(valor>presupuesto){
            throw new PagoInsuficienteException();
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
    public Suministros seleccionarSuministro(TipoSuministro tipo) throws DepositoVacioException{
        if(tipo==TipoSuministro.ALIMENTO_PERRO){
            if(stockAlimentoPerro.getSize()==0){
                throw new DepositoVacioException("comida de perro");
            }
            return stockAlimentoPerro.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_GATO){
            if(stockAlimentoGato.getSize()==0){
                throw new DepositoVacioException("comida de gato");
            }
            return stockAlimentoGato.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_PEQUEÑA){
            if(stockMedicinaPequeña.getSize()==0){
                throw new DepositoVacioException("medicina pequeña");
            }
            return stockMedicinaPequeña.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_MEDIANA){
            if(stockMedicinaMediana.getSize()==0){
                throw new DepositoVacioException("medicina mediana");
            }
            return stockMedicinaMediana.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_GRANDE){
            if(stockMedicinaGrande.getSize()==0){
                throw new DepositoVacioException("medicina grande");
            }
            return stockMedicinaGrande.get();
        }
        return null;
    }

    public void procesarVenta(Cliente cliente, Jugador jugador){
        TipoMascota pedido=cliente.getPedido();
        Mascotas mascotaAEntregar=null;

        for(Mascotas m : mascotas){
            if(m.getTipo()==pedido){
                mascotaAEntregar=m;
                break;
            }
        }

        if(mascotaAEntregar!=null){
            cliente.recibirMascota(mascotaAEntregar);
            mascotas.remove(mascotaAEntregar);
            jugador.setPresupuesto(jugador.getPresupuesto()+500); /*por ahora dejo un valor fijo, 
            luego cuando le asignemos un precio a cada mascota se lo cambiamos*/
            System.out.println("Mascota vendida");
        }
        else{
            System.out.println("No esta la mascota solicitada en el inventario");
        }
    }

    public void atenderCliente(Jugador jugador){
        if (filaClientes.isEmpty()){
            System.out.println("La tienda esta tranquila. No hay clientes para atender");
            return;
        }

        Cliente clienteAtendido=filaClientes.poll();
        System.out.println("Atendiendo al primer cliente de la fila...");
        procesarVenta(clienteAtendido, jugador);
        
    }    

    public void addCliente(Cliente cliente){
        filaClientes.offer(cliente);
        System.out.println("Nuevo cliente en la fila. Hay "+filaClientes.size()+" clientes esperando");
    }

    public void addMascota(Mascotas mascota){
        mascotas.add(mascota);
    }

}
