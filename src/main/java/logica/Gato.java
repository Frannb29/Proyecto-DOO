package logica;

/**
 * Clase de la mascota tipo Gato.
 * Asigna y gestiona atributos personaolizados y comportamiento de la mascota.
 */
public class Gato extends Mascotas{

    /**
     * Constructor que inicializa el habitat en CASA y su precio correspondiente.
     */
    public Gato(){
        super(Habitat.CASA, 100);
    }

    /**
     * Alimenta a la mascota si el alimeto es correcto.
     * Aumenta la alimentacion y un 25% del valor en salud.
     * @param alimento Alimento a utilizar.
     */
    @Override
    public void alimentar(Alimento alimento){
        if(alimento.getTipoMascota()==TipoMascota.GATO){
            int puntosComida=alimento.getComida();
            this.setAlimentacion(this.getAlimentacion() + puntosComida);

            int curacionComida=puntosComida/4; //la mascota gana 25% del valor nutricional del alimento(80)
            this.setSalud(this.getSalud()+curacionComida);

            if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
                this.setEstado(new EstadoSano());
            }
        }
    }

    /**
     * Obtiene la tasa de desgaste de la alimentacion.
     * @return Puntos que pierda la mascota en alimentacion.
     */
    @Override
    public int getDesgasteAlimentacion(){
        return 2;
    }

    /**
     * Obtiene la tasa de desgaste de la salud.
     * @return Puntos que pierda la mascota en salud.
     */
    @Override
    public int getDesgasteSalud(){
        return 4;
    }

    /**
     * Obtiene la tasa de desgaste de la higiene.
     * @return Puntos que pierda la mascota en higiene.
     */
    @Override
    public int getDesgasteHigiene(){
        return 2;
    }
    @Override

    /**
     * Obtiene la tasa de desgaste de la felicidad.
     * @return Puntos que pierda la mascota en felicidad.
     */
    public int getDesgasteFelicidad(){
        return 3;
    }

    /**
     * Sana a la mascota segun los puntos de curacion de la medicina.
     * Efecto secundario, baja un poco la alimentacion.
     * @param medicina Medicina a utilizar.
     */
    @Override
    public void sanar(Medicina medicina){
        int curacion=medicina.getPuntosCuracion();
        this.setSalud(this.getSalud() + curacion);

        int desgasteMedicina=medicina.getPuntosCuracion()/5; //la mascota le da un poco de hambre al consumir una medicina
        this.setAlimentacion(this.getAlimentacion()-desgasteMedicina);

        if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
            this.setEstado(new EstadoSano());
        }
    }

    /**
     * Obtiene el tipo de mascota de esta clase.
     * @return TipoMascota correspondiente.
     */
    @Override
    public TipoMascota getTipo(){
        return TipoMascota.GATO;
    }

    /**
     * Obtiene el tipo de alimento que necesita la mascota.
     * @return TipoSuministro asignado a esta clase.
     */
    @Override
    public TipoSuministro getTipoAlimento(){
        return TipoSuministro.ALIMENTO_GATO;
    }
}
