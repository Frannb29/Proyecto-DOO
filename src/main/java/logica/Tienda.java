package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tienda{
    private static Tienda instancia;

    private Deposito<Alimento> stockAlimentoPerro;
    private Deposito<Alimento> stockAlimentoGato;
    private Deposito<Alimento> stockAlimentoConejo;
    private Deposito<Alimento> stockAlimentoHamster;
    private Deposito<Alimento> stockAlimentoBulbasaur;
    private Deposito<Alimento> stockAlimentoEevee;
    private Deposito<Alimento> stockAlimentoTortuga;
    private Deposito<Alimento> stockAlimentoPulpo;
    private Deposito<Alimento> stockAlimentoPez;
    private Deposito<Medicina> stockMedicinaPequeña;
    private Deposito<Medicina> stockMedicinaMediana;
    private Deposito<Medicina> stockMedicinaGrande;
    private ArrayList<Mascotas> mascotas;
    private Queue<Cliente> filaClientes;

    private Tienda(){
        stockAlimentoPerro=new Deposito<Alimento>();
        stockAlimentoGato=new Deposito<Alimento>();
        stockAlimentoConejo=new Deposito<Alimento>();
        stockAlimentoHamster=new Deposito<Alimento>();
        stockAlimentoBulbasaur=new Deposito<Alimento>();
        stockAlimentoEevee=new Deposito<Alimento>();
        stockAlimentoTortuga=new Deposito<Alimento>();
        stockAlimentoPulpo=new Deposito<Alimento>();
        stockAlimentoPez=new Deposito<Alimento>();
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
                else if(alimento.getTipoMascota()==TipoMascota.CONEJO){
                    stockAlimentoConejo.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.HAMSTER){
                    stockAlimentoHamster.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.BULBASAUR){
                    stockAlimentoBulbasaur.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.EEVEE){
                    stockAlimentoEevee.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.TORTUGA){
                    stockAlimentoTortuga.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.PULPO){
                    stockAlimentoPulpo.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.PEZ_DORADO){
                    stockAlimentoPez.add(alimento);
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

    public Mascotas comprarMascota(TipoMascota tipo, Jugador jugador)throws PagoInsuficienteException, HabitatLlenoExcepcion, HabitatBloqueadoException{
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);
        Habitat habitatRequerido = nuevaMascota.getHabitat();
        if (!jugador.tieneHabitat(habitatRequerido)) {
            throw new HabitatBloqueadoException("No puedes comprar esta mascota porque no has adquirido el hábitat " + habitatRequerido.getNombre());
        }
        if(jugador.getPresupuesto() < nuevaMascota.getPrecio()){
            throw new PagoInsuficienteException();
        }
        else if (contadorMascotas(nuevaMascota.getHabitat()) >= nuevaMascota.getHabitat().getCapacidad()){
            throw new HabitatLlenoExcepcion(nuevaMascota.getHabitat().getNombre());
        }
        jugador.descontarPresupuesto(nuevaMascota.getPrecio());
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
        else if(tipo==TipoSuministro.ALIMENTO_CONEJO){
            if(stockAlimentoConejo.getSize()==0){
                throw new DepositoVacioException("comida de conejo");
            }
            return stockAlimentoConejo.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_HAMSTER){
            if(stockAlimentoHamster.getSize()==0){
                throw new DepositoVacioException("comida de hamster");
            }
            return stockAlimentoHamster.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_BULBASAUR){
            if(stockAlimentoBulbasaur.getSize()==0){
                throw new DepositoVacioException("comida de bulbasaur");
            }
            return stockAlimentoBulbasaur.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_EEVEE){
            if(stockAlimentoEevee.getSize()==0){
                throw new DepositoVacioException("comida de eevee");
            }
            return stockAlimentoEevee.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_TORTUGA){
            if(stockAlimentoTortuga.getSize()==0){
                throw new DepositoVacioException("comida de tortuga");
            }
            return stockAlimentoTortuga.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_PULPO){
            if(stockAlimentoPulpo.getSize()==0){
                throw new DepositoVacioException("comida de pulpo");
            }
            return stockAlimentoPulpo.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_PEZ){
            if(stockAlimentoPez.getSize()==0){
                throw new DepositoVacioException("comida de pez dorado");
            }
            return stockAlimentoPez.get();
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

    private void venderMascota(Jugador jugador, Mascotas mascota) throws MascotaNoVendibleException {
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
            jugador.registrarHabitat(habitat);
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
    public Suministros sacarMedicina() throws DepositoVacioException {
        if(stockMedicinaPequeña.getSize() > 0){
            return stockMedicinaPequeña.get();
        }
        if(stockMedicinaMediana.getSize() > 0){
            return stockMedicinaMediana.get();
        }
        if(stockMedicinaGrande.getSize() > 0){
            return stockMedicinaGrande.get();
        }
        throw new DepositoVacioException("medicinas");
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
    public int getCantidadAlimentoConejo() {
        return stockAlimentoConejo.getSize();
    }
    public int getCantidadAlimentoHamster() {
        return stockAlimentoHamster.getSize();
    }
    public int getCantidadAlimentoBulbasaur() {
        return stockAlimentoBulbasaur.getSize();
    }
    public int getCantidadAlimentoEevee() {
        return stockAlimentoEevee.getSize();
    }
    public int getCantidadAlimentoTortuga() {
        return stockAlimentoTortuga.getSize();
    }
    public int getCantidadAlimentoPulpo() {
        return stockAlimentoPulpo.getSize();
    }
    public int getCantidadAlimentoPez() {
        return stockAlimentoPez.getSize();
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
    public Queue<Cliente> getFilaClientes() {
        return filaClientes;
    }
}
