package logica;

public class DepositoVacioException extends Exception {
    public DepositoVacioException(String tipo) {
        super("Depostio de "+tipo+" vacio");
    }
}
