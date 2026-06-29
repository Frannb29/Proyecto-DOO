package logica;

public class Alimento extends Suministros{
    private TipoMascota mascotaDestino;
    private int comida;
    public Alimento(int valor, TipoMascota mascotaDestino){
        super(valor);
        this.mascotaDestino=mascotaDestino;
        comida=80;
    }
    public TipoMascota getTipoMascota(){
        return mascotaDestino;
    }
    public int getComida(){
        return comida;
    }
    @Override
    public void usar(Mascotas mascota){
        mascota.alimentar(this);
    }
}
