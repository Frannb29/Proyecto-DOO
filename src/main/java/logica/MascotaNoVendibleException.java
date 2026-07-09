package logica;

/**
 * Excepcion para cuando se quiere vender una mascota con al menos uno de sus atributos en 0.
 */
public class MascotaNoVendibleException extends Exception{
    /**
     * Nueva excepcion con un texto que detalla el motivo de la falla.
     */
    public MascotaNoVendibleException(){
        super("La mascota tiene al menos un atributo en 0 que no permite su venta");
    }
}
