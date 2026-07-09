package logica;

/**
 * Controlador del juego que actua como coordinador del sistema.
 * Conecta e interactua con el jugador, la tienda, el generador de clientes y el reloj.
 * Centraliza la ejecucion de la simulacion de las mascotas.
 */
public class Simulador{
    private Jugador jugador;
    private Tienda tienda;
    private Reloj reloj;
    private GeneradorCliente generaClientes;

    /**
     * Constructor que inicializa el entorno de simulacion, crea el reloj principal y al generador de clientes,
     * suscribiendolo como un observador del tiempo.
     * @param jugador Instancia del jugador que participa en el juego.
     */
    public Simulador(Jugador jugador){
        this.jugador = jugador;
        this.tienda = Tienda.getInstancia();
        this.reloj = new Reloj();
        this.generaClientes = new GeneradorCliente();

        this.reloj.addObservador(generaClientes);
    }

    /**
     * Inicia la ejecucion de la simulacion activando el motor del reloj.
     */
    public void iniciarSimulacion(){
        this.reloj.iniciarReloj();
    }

    /**
     * Detiene la simulacion y libera el hilo secundario del reloj.
     */
    public void detenerSimulacion(){
        this.reloj.detenerReloj();
    }

    /**
     * Agrega a una mascota ya creada al inventario de la tienda
     * y a la lista de observadores del reloj para que reciba ticks.
     * @param mascota Instancia de lamascota a introducir en el sistema.
     */
    public void addMascotaAlSistema(Mascotas mascota){
        this.reloj.addObservador(mascota);
        this.tienda.addMascota(mascota);
    }

    /**
     * Gestiona el proceso de compra de una nueva mascota.
     * Si la comprea es exitosa la mascota se suscribe al reloj del juego.
     * @param tipo Tipo de mascota que el jugador quiere comprar.
     * @return Instancia de la mascota comprada y vinculada al reloj.
     * @throws PagoInsuficienteException Si el dinero actual del jugador es menor al precio de la mascota.
     * @throws HabitatLlenoExcepcion Si el habitat correspondiente a esa mascota ya no tiene espacio.
     */
    public Mascotas comprarMascota(TipoMascota tipo) throws PagoInsuficienteException, HabitatLlenoExcepcion{
        Mascotas nuevaMascota = this.tienda.comprarMascota(tipo, this.jugador);
        this.reloj.addObservador(nuevaMascota);
        return nuevaMascota;
    }

    /**
     * Intenta vender una mascota a un cliente en espera.
     * Si la venta se realiza con exito la mascota es retirada de los observadores del reloj.
     */
    public void venderMascotaACliente(){
        Mascotas mascotaVendida = this.tienda.atenderCliente(this.jugador);

        if(mascotaVendida != null){
            this.reloj.removeObservador(mascotaVendida);
        }
    }

    /**
     * Obtiene la instancia del reloj utilizada en la simulacion.
     * * @return El objeto Reloj del sistema.
     */
    public Reloj getReloj(){
        return this.reloj;
    }
}