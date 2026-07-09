package logica;

/**
 * Estado enfermo cuando la salud de la mascota es muy bajo.
 * Duplica el desgaste de felicidad e higiene.
 */
public class EstadoEnfermo implements EstadoMascota{

    /**
     * Reduce los atributos de la mascota y procesa un tick (tiempo).
     * @param mascota Mascota que se encuentra en este estado.
     */
    @Override
    public void procesarTick(Mascotas mascota){
        mascota.setSalud(mascota.getSalud()-mascota.getDesgasteSalud());
        mascota.setFelicidad(mascota.getFelicidad()-(2*mascota.getDesgasteFelicidad()));
        mascota.setHigiene(mascota.getHigiene()-(2*mascota.getDesgasteHigiene()));
        mascota.setAlimentacion(mascota.getAlimentacion()-mascota.getDesgasteAlimentacion());
    }

    /**
     * Bloquea la accion de jugar, una mascota enferma no tiene animo para jugar.
     * @param mascota Mascota que se encuentra en este estado.
     * @throws EstadoMascotaInvalidoException Si se intenta jugar con la mascota que está en estado Enfermo.
     */
    @Override
    public void jugar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        throw new EstadoMascotaInvalidoException(mascota.getTipo() + " está muy enfermo para jugar :(");
    }

    /**
     * Accion de limpiar funciona normal y recupera puntos de salud y felicidad.
     * @param mascota Mascota que se encuentra en este estado.
     * @throws EstadoMascotaInvalidoException No afecta en este caso.
     */
    @Override
    public void limpiar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setHigiene(100);
        mascota.setSalud(mascota.getSalud()+5);
        mascota.setFelicidad(mascota.getFelicidad()+5);
    }

    /**
     * Indica si la mascota se puede vender en este estado.
     * @return false, no es vendible una mascota enferma.
     */
    @Override
    public boolean sePuedeVender(){
        return false;
    }
}
