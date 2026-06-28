package logica;

public class Medicina extends Suministros{
    public Medicina(int valor){
        super(valor);
    }
    @Override
    public void usar(Mascotas mascota){
        mascota.sanar(this);
    }
}
