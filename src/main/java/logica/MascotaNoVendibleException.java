package logica;

public class MascotaNoVendibleException extends Exception{
    public MascotaNoVendibleException(){
        super("La Mascota tiene al menos un atributo en 0 que no permite su venta");
    }
}
