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
            jugador.descontarPresupuesto(valor);
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
                if(medicina.getTipo()==TipoSuministro.MEDICINA_PEQUEÑA){
                    stockMedicinaPequeña.add(medicina);
                }
                else if(medicina.getTipo()==TipoSuministro.MEDICINA_MEDIANA){
                    stockMedicinaMediana.add(medicina);
                }
                else if(medicina.getTipo()==TipoSuministro.MEDICINA_GRANDE){
                    stockMedicinaGrande.add(medicina);
                }
            }
        }
    }

    public Mascotas comprarMascota(TipoMascota tipo, Jugador jugador)throws PagoInsuficienteException, HabitatLlenoExcepcion{
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);
        if(jugador.getPresupuesto() < nuevaMascota.getPrecio()){
            throw new PagoInsuficienteException();
        }
        else if (contadorMascotas(nuevaMascota.getHabitat()) >= nuevaMascota.getHabitat().getCapacidad()){
            throw new HabitatLlenoExcepcion(nuevaMascota.getHabitat().getNombre());
        }
        jugador.setPresupuesto(jugador.getPresupuesto() - nuevaMascota.getPrecio());
        addMascota(nuevaMascota);
        return nuevaMascota;
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

    private Mascotas procesarVenta(Cliente cliente, Jugador jugador){
        TipoMascota pedido=cliente.getPedido();
        Mascotas mascotaAEntregar=null;

        for(Mascotas m : mascotas){
            if(m.getTipo()==pedido){
                mascotaAEntregar=m;
                break;
            }
        }

        if(mascotaAEntregar!=null){
            try{
                venderMascota(jugador, mascotaAEntregar);
                cliente.recibirMascota(mascotaAEntregar);
                System.out.println("Mascota vendida");
                return mascotaAEntregar;
            }
            catch (MascotaNoVendibleException e){
                System.out.println("La mascota solicitada no cumple requisitos de venta");
                return null;
            }
        }
        else{
            System.out.println("No esta la mascota solicitada en el inventario");
            return null;
        }
    }

    public Mascotas atenderCliente(Jugador jugador){
        if (filaClientes.isEmpty()){
            System.out.println("La tienda esta tranquila. No hay clientes para atender");
            return null;
        }

        Cliente clienteAtendido=filaClientes.poll();
        System.out.println("Atendiendo al primer cliente de la fila...");
        return procesarVenta(clienteAtendido, jugador);
        
    }

    public void venderMascota(Jugador jugador, Mascotas mascota) throws MascotaNoVendibleException {
        if(!mascota.sePuedeVender()){
            throw new MascotaNoVendibleException();
        }
        jugador.aumentarPresupuesto(mascota.getPrecioVenta());
        this.mascotas.remove(mascota);
    }

    public void comprarHabitat(Habitat habitat, Jugador jugador)throws PagoInsuficienteException{
        int costo = habitat.getPrecio();
        if(costo > jugador.getPresupuesto()){
            throw new PagoInsuficienteException();
        }
        else{
            jugador.descontarPresupuesto(costo);
            System.out.println("Habitat " + habitat.getNombre() + " comprado con exito");
        }
    }

    public void addCliente(Cliente cliente){
        filaClientes.offer(cliente);
        System.out.println("\nNuevo cliente en la fila, quiere un "+cliente.getPedido());
        System.out.println("Hay "+filaClientes.size()+" cliente(s) esperando");
    }

    private int contadorMascotas(Habitat habitat){
        int contador = 0;
        for(Mascotas m :this.mascotas){
            if(m.getHabitat() == habitat){
                contador++;
            }
        }
        return contador;
    }
    public void addMascota(Mascotas mascota){
        mascotas.add(mascota);
    }

    public int getCantidadAlimentoPerro() {
        return stockAlimentoPerro.getSize();
    }
    public int getCantidadAlimentoGato() {
        return stockAlimentoGato.getSize();
    }
    public int getCantidadMedicinaPequeña() {
        return stockMedicinaPequeña.getSize();
    }
    public int getCantidadMedicinaMediana() {
        return stockMedicinaMediana.getSize();
    }
    public int getCantidadMedicinaGrande() {
        return stockMedicinaGrande.getSize();
    }
    public ArrayList<Mascotas> getMascotas(){
        return mascotas;
    }
}
