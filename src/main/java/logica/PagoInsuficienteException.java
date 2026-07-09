package logica;

/**
 * Excepcion para cuando se intente comprar un elemento con un presupuesto menor a su precio.
 */
public class PagoInsuficienteException extends Exception {
    /**
     * Nueva excepcion con un texto exolicativo.
     */
    public PagoInsuficienteException(){
        super("Pago insuficiente");
    }
}
