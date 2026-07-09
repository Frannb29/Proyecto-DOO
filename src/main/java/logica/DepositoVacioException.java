package logica;

/**
 * Excepcion para cuando se intenta extraer un elemento de un deposito vacio.
 */
public class DepositoVacioException extends Exception {
    /**
     * Especifica el tipo de deposito que está vacio.
     * @param tipo Tipo de deposito.
     */
    public DepositoVacioException(String tipo) {
        super("Depostio de "+tipo+" vacio");
    }
}
