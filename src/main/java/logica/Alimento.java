package logica;

/**
 * Clase Alimento que conecta el alimento con el tipo de mascota al que corresponde.
 * Hereda de la clase Suministros.
 */
public class Alimento extends Suministros{
    private TipoMascota mascotaDestino;
    private int comida;

    /**
     * Constructor que inicializa el alimento e incrementa en 80 los puntos de comida.
     * Asigna a la mascota destino segun el tipo de suministro.
     * @param tipo tipo de Suministro que define el alimento.
     */
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

    /**
     * Obtiene el tipo de mascota que recibe el alimento.
     * @return TipoMascota correspondiente.
     */
    public TipoMascota getTipoMascota(){
        return mascotaDestino;
    }

    /**
     * Obtiene los puntos de alimentacion que otorga el alimento.
     * @return Cantidad de puntos de comida (80).
     */
    public int getComida(){
        return comida;
    }

    /**
     * Aplica el uso del alimento en una mascota especifica.
     * @param mascota Mascota que va a consumir el alimento.
     */
    @Override
    public void usar(Mascotas mascota){
        mascota.alimentar(this);
    }
}
