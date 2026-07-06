package logica;

public class Alimento extends Suministros{
    private TipoMascota mascotaDestino;
    private int comida;

    public Alimento(TipoSuministro tipo){
        super(tipo);
        this.comida=80;
        
        switch (tipo) {
            case ALIMENTO_PERRO:
                this.mascotaDestino=TipoMascota.PERRO;
                break;
            case ALIMENTO_GATO:
                this.mascotaDestino=TipoMascota.GATO;
            default:
                break;
        }
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
