package logica;

public class HabitatLlenoExcepcion extends Exception{
    public HabitatLlenoExcepcion(String habitat){
        super("El habitat " + habitat + " está en su maxima capacidad");
    }
}
