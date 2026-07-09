package logica;

/**
 * Clase abstracta base que define los atributos, estadisticas y comportamientos
 * de las mascotas en el juego.
 * Implementa {@link ObservadorReloj} para actualizar sus estados.
 */
public abstract class Mascotas implements ObservadorReloj {
    protected int salud;
    protected int higiene;
    protected int felicidad;
    protected int alimentacion;
    protected int precio;
    protected Habitat habitat;
    private EstadoMascota estadoActual;
    private int contadorTicks = 0;

    /**
     * Constructor que inicializa una nueva mascota con todas sus estadisticas en 100
     * y en el estado sano.
     * * @param habitat Habitat asignado para cada mascota.
     * @param precio El precio de la mascota en la tienda.
     */
    public Mascotas(Habitat habitat, int precio){
        this.salud = 100;
        this.higiene = 100;
        this.felicidad = 100;
        this.alimentacion = 100;
        this.habitat = habitat;
        this.precio = precio;
        this.estadoActual = new EstadoSano();
    }

    /**
     * Obtiene los puntos de desgaste de la alimentacion de la mascota.
     * @return Tasa de desgaste de alimentacion.
     */
    public abstract int getDesgasteAlimentacion();

    /**
     * Obtiene los puntos de desgaste de la salud de la mascota.
     * @return Tasa de desgaste de salud.
     */
    public abstract int getDesgasteSalud();

    /**
     * Obtiene los puntos de desgaste de la higiene de la mascota.
     * @return Tasa de desgaste de higiene.
     */
    public abstract int getDesgasteHigiene();

    /**
     * Obtiene los puntos de desgaste de la felicidad de la mascota.
     * @return Tasa de desgaste de felicidad.
     */
    public abstract int getDesgasteFelicidad();

    /**
     * Modifica el estado actual de la mascota.
     * @param estadoNuevo Nuevo {@link EstadoMascota} a cambiar.
     */
    public void setEstado(EstadoMascota estadoNuevo) {
        this.estadoActual = estadoNuevo;
    }

    /**
     * Obtiene el nivel actual de salud de la mascota.
     * @return Valor entero entre 0 y 100.
     */
    public int getSalud(){
        return salud;
    }

    /**
     * Modifica el nivel de salud de la mascota entre 0 y 100.
     * @param salud Los nuevos puntos de salud a modificar.
     */
    public void setSalud(int salud){
        this.salud = Math.max(0, Math.min(salud,100));
    }

    /**
     * Obtiene el nivel actual de felicidad de la mascota.
     * @return Valor entero entre 0 y 100.
     */
    public int getFelicidad() {
        return felicidad;
    }

    /**
     * Modifica el nivel de felicidad de la mascota entre 0 y 100.
     * @param felicidad Los nuevos puntos de felicidad a modificar.
     */
    public void setFelicidad(int felicidad) {
        this.felicidad = Math.max(0, Math.min(felicidad, 100));
    }

    /**
     * Obtiene el nivel actual de alimentacion de la mascota.
     * @return Valor entero entre 0 y 100.
     */
    public int getAlimentacion() {
        return alimentacion;
    }

    /**
     * Modifica el nivel de alimentacion de la mascota entre 0 y 100.
     * @param alimentacion Los nuevos puntos de alimentacion a modificar.
     */
    public void setAlimentacion(int alimentacion) {
        this.alimentacion = Math.max(0, Math.min(alimentacion,100));
    }

    /**
     * Obtiene el nivel actual de higiene de la mascota.
     * @return Valor entero entre 0 y 100.
     */
    public int getHigiene() {
        return higiene;
    }

    /**
     * Modifica el nivel de higiene de la mascota entre 0 y 100.
     * @param higiene Los nuevos puntos de higiene a modificar.
     */
    public void setHigiene(int higiene) {
        this.higiene = Math.max(0, Math.min(higiene, 100));
    }

    /**
     * Obtiene el habitat asociado en el que vive la mascota.
     * @return El habitat correspondiente.
     */
    public Habitat getHabitat(){
        return habitat;
    }

    /**
     * Determina si la mascota cumple con los requisitos para ser vendida.
     * Depende de su estado actual y de que el habitat no esté completamente sucio.
     * * @return true si se permite la venta, false en caso contrario.
     */
    public boolean sePuedeVender(){
        return estadoActual.sePuedeVender() && this.habitat.getHigiene() > 0;
    }

    /**
     * Obtiene el precio de compra de la mascota.
     * @return Precio.
     */
    public int getPrecio(){
        return precio;
    }

    /**
     * Calcula el precio de venta de la mascota al cliente,
     * con un 70% de ganancia sobre el precio base.
     * * @return Valor total de venta como un entero.
     */
    public int getPrecioVenta(){
        return (int)(this.precio * 1.7);
    }

    /**
     * Gestiona el paso del tiempo.
     * Cada 5 ticks se realiza el proceso de desgaste del estado actual y se ensucia el habitat.
     */
    public void pasarTiempo(){
        this.contadorTicks++;
        if(contadorTicks >= 5){
            this.contadorTicks = 0;
            estadoActual.procesarTick(this);
            this.habitat.ensuciar(2);
        }
    }

    /**
     * Obtiene el tipo de alimento especifico que consume la mascota.
     * @return Tipo de suministro requerido.
     */
    public abstract TipoSuministro getTipoAlimento();

    /**
     * Aplica los puntos de alimentacion de un alimento sobre los atributos de la mascota.
     * @param alimento El alimento que va a consumir.
     */
    public abstract void alimentar(Alimento alimento);

    /**
     * Aplica los puntos de curacion de una medicina sobre los atributos de la mascota.
     * @param medicina La medicina que va a consumir.
     */
    public abstract void sanar(Medicina medicina);

    /**
     * Realiza la accion de limpiar a la mascota.
     * Delega la logica al estado actual a menos que la mascota ya se encuentre limpia.
     * @throws EstadoMascotaInvalidoException Si la mascota ya tiene una higiene igual o mayor a 60.
     */
    public void limpiar() throws EstadoMascotaInvalidoException {
        if(this.higiene >= 60){
            throw new EstadoMascotaInvalidoException("¡Ya está limpio! no lo puedes limpiar");
        }
        estadoActual.limpiar(this);
    }

    /**
     * Intenta realizar la accion de jugar con la mascota.
     * El resultado depende del estado actual de la mascota.
     * @throws EstadoMascotaInvalidoException Si el estado de la mascota no le permite jugar.
     */
    public void jugar() throws EstadoMascotaInvalidoException {
        estadoActual.jugar(this);
    }

    /**
     * Devuelve un texto de la mascota y sus atributos.
     * @return Cadena con el tipo, salud y alimentacion.
     */
    @Override
    public String toString() {
        return this.getTipo()+"(Salud: "+this.salud+", Alimento: "+this.alimentacion+")";
    }

    /**
     * Obtiene el tipo de la mascota.
     * @return Tipo de mascota.
     */
    public abstract TipoMascota getTipo();
}