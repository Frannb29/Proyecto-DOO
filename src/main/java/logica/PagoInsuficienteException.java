package logica;

public class PagoInsuficienteException extends Exception {
    public PagoInsuficienteException(){
        super("Pago insuficiente");
    }
}
