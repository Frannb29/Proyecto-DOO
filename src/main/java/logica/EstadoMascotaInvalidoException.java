package logica;

/**
 * Excepcion para cuando se intenta hacer una accion y el estado de la mascota no lo permite.
 */
public class EstadoMascotaInvalidoException extends Exception{
    /**
     * Nueva excepcion con un mensaje que detalla el motivo de la falla.
     * @param mensaje Texto que describe el motivo por el cual la accion no es valida.
     */
    public EstadoMascotaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
