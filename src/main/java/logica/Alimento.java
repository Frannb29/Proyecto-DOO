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
                break;
            case ALIMENTO_CONEJO:
                this.mascotaDestino=TipoMascota.CONEJO;
                break;
            case ALIMENTO_HAMSTER:
                this.mascotaDestino=TipoMascota.HAMSTER;
                break;
            case ALIMENTO_EEVEE:
                this.mascotaDestino=TipoMascota.EEVEE;
                break;
            case ALIMENTO_BULBASAUR:
                this.mascotaDestino=TipoMascota.BULBASAUR;
                break;
            case ALIMENTO_PEZ:
                this.mascotaDestino=TipoMascota.PEZ_DORADO;
                break;
            case ALIMENTO_PULPO:
                this.mascotaDestino=TipoMascota.PULPO;
                break;
            case ALIMENTO_TORTUGA:
                this.mascotaDestino=TipoMascota.TORTUGA;
                break;
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
