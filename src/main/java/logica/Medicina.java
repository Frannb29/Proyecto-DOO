package logica;

/**
 * Clase Medicina que asigna los puntos de curacion respectivos a cada tipo de medicina.
 * Hereda de la clase Suministros.
 */
public class Medicina extends Suministros{
    private int puntosCuracion;

    /**
     * Constructor que inicializa los puntos de curacion a cada tipo de medicina.
     * @param tipo Tipo de medicina a instanciar.
     */
    public Medicina(TipoSuministro tipo){
        super(tipo);
        
        switch (tipo) {
            case MEDICINA_PEQUEÑA:
                this.puntosCuracion=25;
                break;
            case MEDICINA_MEDIANA:
                this.puntosCuracion=50;
                break;
            case MEDICINA_GRANDE:
                this.puntosCuracion=100;
                break;
            default:
                this.puntosCuracion=0;
        }
    }

    /**
     * Obtiene los puntos de curacion de la medicina.
     * @return Cantidad de puntos de salud que se restauraran.
     */
    public int getPuntosCuracion(){
        return puntosCuracion;
    }

    /**
     * Implementa el uso de la medicina en una mascota.
     * @param mascota Mascota a sanar.
     */
    @Override
    public void usar(Mascotas mascota){
        mascota.sanar(this);
    }
}
