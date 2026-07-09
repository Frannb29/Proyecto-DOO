package logica;

/**
 * Excepcion para cuando se quiere comprar una mascota pero el habitat alcanzó su maxima capacidad.
 */
public class HabitatLlenoExcepcion extends Exception{
    /**
     * Nueva excepcion con un mensaje que indica el habitat con el fallo.
     * @param habitat Habitat en el que intenta comprar.
     */
    public HabitatLlenoExcepcion(String habitat){
        super("El habitat " + habitat + " está en su maxima capacidad");
    }
}
