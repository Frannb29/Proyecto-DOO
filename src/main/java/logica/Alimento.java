package logica;

public class Alimento extends Suministros{
    private TipoMascota mascotaDestino;
    public Alimento(int valor, TipoMascota mascotaDestino){
        super(valor);
        this.mascotaDestino=mascotaDestino;
    }
    public TipoMascota getTipoMascota(){
        return mascotaDestino;
    }
    @Override
    public void usar(Mascotas mascota){
        mascota.alimentar();
    }
}
