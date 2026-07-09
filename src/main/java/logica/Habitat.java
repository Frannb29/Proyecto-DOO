package logica;

/**
 * Diferentes tipos de habitats disponibles en el juego.
 * Cada habitat tiene propiedades especificas.
 */
public enum Habitat {
    CASA(100, 4,500,"Casa","/Imagenes/Fondo_Casa.png"),
    POKECASA(100, 4,550,"PokeCasa","/Imagenes/Fondo_Pokecasa.png"),
    ACUATICO(100, 4,600,"Acuario","/Imagenes/Fondo_Acuatico.png");

    private int higiene;
    private int capacidad;
    private int precio;
    private String ruta;
    private String nombre;

    /**
     * Constructor para inicializar las propiedades.
     * @param HigieneInicial Nivel de higiene con que inicia el habitat (100).
     * @param capacidad Capacidad máxima de mascotas que puede haber en un habitat.
     * @param precio Costo de compra del habitat.
     * @param nombre Nombre del habitat.
     * @param ruta Ruta de la imagen de fondo del habitat.
     */
    Habitat(int HigieneInicial, int capacidad,int precio, String nombre, String ruta){
        this.higiene = HigieneInicial;
        this.capacidad = capacidad;
        this.precio = precio;
        this.ruta = ruta;
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del habitat.
     * @return Texto con el nombre.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la capacidad máxima de mascotas permitidas.
     * @return Cantidad de espacios disponibles.
     */
    public int getCapacidad(){
        return this.capacidad;
    }

    /**
     * Obtiene el precio de compra del habitat.
     * @return El valor monetario del habitat.
     */
    public int getPrecio(){
        return this.precio;
    }

    /**
     * Obtiene la ruta del archivo de la imagen de fondo.
     * @return Cadena con la ubicacion.
     */
    public String getRuta(){
        return this.ruta;
    }

    /**
     * Obtiene el nivel actual de higiene del habitat.
     * @return Un valor entero entre 0 y 100.
     */
    public int getHigiene(){
        return higiene;
    }

    /**
     * Disminuye la higiene del habitat con el paso del tiempo.
     * @param mugre Cantidad de puntos a restar.
     */
    public void ensuciar(int mugre){
        this.higiene -= mugre;
        if(this.higiene < 0){
            this.higiene = 0;
        }
    }

    /**
     * Restaura la higiene por completo (100).
     */
    public void limpiarHabitat(){
        this.higiene = 100;
    }
}
