package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Gestiona el inventario del juego y el stock de suministros.
 * Aplica el patrón Singleton para asegurar una unica instancia global.
 */
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

    /**
     * Constructor privado que inicializa de forma las colecciones, depositos y estructuras de control de la tienda.
     */
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

    /**
     * Obtiene la unica instancia activa de la tienda y si no ha sido creada la instancia.
     * @return Instancia unica de la tienda.
     */
    public static Tienda getInstancia(){
        if(instancia==null){
            instancia=new Tienda();
        }

        return instancia;
    }

    /**
     * Procesa la compra de un suministro y lo añade a su respectivo deposito segun su tipo y especie correspondiente.
     * @param jugador El jugador que realiza la compra.
     * @param objeto  El suministro que se desea adquirir.
     * @throws PagoInsuficienteException Si el presupuesto del jugador es menor al valor del suministro.
     */
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

    /**
     * Permite al jugador comprar una nueva mascota.
     * Realiza verificaciones de presupuesto y limites del habitat asignado.
     * @param tipo Tipo de mascota que el jugador quiere comprar.
     * @param jugador El jugador que realiza la compra.
     * @return La nueva instancia de la mascota añadida.
     * @throws PagoInsuficienteException Si el jugador no cuenta con el dinero necesario.
     * @throws HabitatLlenoExcepcion Si el habitat correspondiente está en su capacidad maxima.
     */
    public Mascotas comprarMascota(TipoMascota tipo, Jugador jugador)throws PagoInsuficienteException, HabitatLlenoExcepcion{
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);

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

    /**
     * Extrae y devuelve un suministro especifico desde sus depositos.
     * @param tipo Tipo de suministro que se quiere extraer.
     * @return El suministro removido del stock.
     * @throws DepositoVacioException Si el deposito seleccionado no cuenta con unidades disponibles.
     */
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

    /**
     * Ejecuta la conexion entre la peticion de un cliente y el inventario disponible.
     * Valida que la mascota sea vendibole.
     * @param cliente El cliente que desea comprar.
     * @param jugador El jugador que recibirá las ganancias.
     * @return La instancia de la mascota vendida o null si la venta falla.
     */
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

    /**
     * Remueve de la fila al primer cliente en espera y procesa su solicitud de compra.
     * @param jugador El jugador a cargo.
     * @return Mascota entregada o null si la fila está vacia.
     */
    public Mascotas atenderCliente(Jugador jugador){
        if (filaClientes.isEmpty()){
            System.out.println("La tienda esta tranquila. No hay clientes para atender");
            return null;
        }

        Cliente clienteAtendido=filaClientes.poll();
        System.out.println("Atendiendo al primer cliente de la fila...");
        return procesarVenta(clienteAtendido, jugador);

    }

    /**
     * Finaliza la venta de una mascota removiendola de la tienda y aumentando el presupuesto
     * del jugador con el precio de venta.
     * @param jugador El jugador beneficiario de la venta.
     * @param mascota La mascota que va a ser transferida.
     * @throws MascotaNoVendibleException Si las estadísticas o estado de la mascota prohiben su venta.
     */
    public void venderMascota(Jugador jugador, Mascotas mascota) throws MascotaNoVendibleException {
        if(!mascota.sePuedeVender()){
            throw new MascotaNoVendibleException();
        }
        jugador.aumentarPresupuesto(mascota.getPrecioVenta());
        this.mascotas.remove(mascota);
    }

    /**
     * Permite al jugador comprar un nuevo habitat descontando su precio del presupuesto.
     * @param habitat El habitat seleccionado para la compra.
     * @param jugador El jugador comprador.
     * @throws PagoInsuficienteException Si el presupuesto no alcanza para la compra.
     */
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

    /**
     * Registra el ingreso de un nuevo cliente a la fila de atencion de la tienda.
     * @param cliente El cliente a añadir.
     */
    public void addCliente(Cliente cliente){
        filaClientes.offer(cliente);
        System.out.println("\nNuevo cliente en la fila, quiere un "+cliente.getPedido());
        System.out.println("Hay "+filaClientes.size()+" cliente(s) esperando");
    }

    /**
     * Recorre el inventario para calcular cuantas mascotas hay en un habitat.
     * @param habitat El habitat que se desea consultar.
     * @return Numero total de mascotas en ese habitat.
     */
    private int contadorMascotas(Habitat habitat){
        int contador = 0;
        for(Mascotas m :this.mascotas){
            if(m.getHabitat() == habitat){
                contador++;
            }
        }
        return contador;
    }

    /**
     * Busca y remueve la primera medicina disponible en stock de la pequeña a la grande.
     * @return La medicina a aplicar.
     * @throws DepositoVacioException Si no queda ninguna unidad de medicina en la tienda.
     */
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

    /**
     * Añade una mascota a la lista de registros de la tienda.
     * @param mascota La mascota a almacenar.
     */
    public void addMascota(Mascotas mascota){
        mascotas.add(mascota);
    }

    /** @return Cantidad actual en stock de alimentos de perro. */
    public int getCantidadAlimentoPerro() { return stockAlimentoPerro.getSize(); }
    /** @return Cantidad actual en stock de alimentos de gato. */
    public int getCantidadAlimentoGato() { return stockAlimentoGato.getSize(); }
    /** @return Cantidad actual en stock de alimentos de conejo. */
    public int getCantidadAlimentoConejo() { return stockAlimentoConejo.getSize(); }
    /** @return Cantidad actual en stock de alimentos de hamster. */
    public int getCantidadAlimentoHamster() { return stockAlimentoHamster.getSize(); }
    /** @return Cantidad actual en stock de alimentos de bulbasaur. */
    public int getCantidadAlimentoBulbasaur() { return stockAlimentoBulbasaur.getSize(); }
    /** @return Cantidad actual en stock de alimentos de eevee. */
    public int getCantidadAlimentoEevee() { return stockAlimentoEevee.getSize(); }
    /** @return Cantidad actual en stock de alimentos de tortuga. */
    public int getCantidadAlimentoTortuga() { return stockAlimentoTortuga.getSize(); }
    /** @return Cantidad actual en stock de alimentos de pulpo. */
    public int getCantidadAlimentoPulpo() { return stockAlimentoPulpo.getSize(); }
    /** @return Cantidad actual en stock de alimentos de pez dorado. */
    public int getCantidadAlimentoPez() { return stockAlimentoPez.getSize(); }
    /** @return Cantidad actual en stock de medicinas de tamaño pequeño. */
    public int getCantidadMedicinaPequeña() { return stockMedicinaPequeña.getSize(); }
    /** @return Cantidad actual en stock de medicinas de tamaño mediano. */
    public int getCantidadMedicinaMediana() { return stockMedicinaMediana.getSize(); }
    /** @return Cantidad actual en stock de medicinas de tamaño grande. */
    public int getCantidadMedicinaGrande() { return stockMedicinaGrande.getSize(); }

    /**
     * Obtiene la lista de mascotas en la tienda.
     * @return Un Arraylist con las mascotas actuales.
     */
    public ArrayList<Mascotas> getMascotas(){ return mascotas; }

    /**
     * Obtiene la fila de clientes que esperan ser atendidos.
     * @return Una estructura Queue ordenada con los clientes en fila.
     */
    public Queue<Cliente> getFilaClientes() { return filaClientes; }
}