package logica;

/**
 * Estado Sano por defecto.
 * Los atributos y acciones se realizan de forma normal.
 */
public class EstadoSano implements EstadoMascota{

    /**
     * Procesa un tick y desgasta los atributos con normalidad.
     * Si la alimentacion llega a 20 pasa a estado hambriento.
     * Si la salud llega a 20 pasa a estado enfermo.
     * @param mascota Mascota a la que se le aplicaran los cambios.
     */
    @Override
    public void procesarTick(Mascotas mascota){
        mascota.setSalud(mascota.getSalud()-mascota.getDesgasteSalud());
        mascota.setAlimentacion(mascota.getAlimentacion()-mascota.getDesgasteAlimentacion());
        mascota.setHigiene(mascota.getHigiene()-mascota.getDesgasteHigiene());
        mascota.setFelicidad(mascota.getFelicidad()-mascota.getDesgasteFelicidad());

        if(mascota.getSalud() <= 0){
            mascota.setSalud(0);
            mascota.setEstado(new EstadoEnfermo());
            System.out.println(mascota.getTipo() + " está enfermo");
        } else if (mascota.getAlimentacion()<=20) {
            mascota.setEstado(new EstadoHambriento());
            System.out.println(mascota.getTipo() + " está hambriento");
        }
    }

    /**
     * Jugar aumenta la felicidad pero baja la alimentacion (para jugar se necesita energia).
     * Si la alimentacion llega a 20 pasa a estado hambriento.
     * @param mascota Mascota con la que se intenta jugar.
     * @throws EstadoMascotaInvalidoException no afecta en este caso.
     */
    @Override
    public void jugar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setFelicidad(mascota.getFelicidad()+20);
        mascota.setAlimentacion(mascota.getAlimentacion()-10);
        if(mascota.getAlimentacion() <= 20){
            mascota.setEstado(new EstadoHambriento());
            System.out.println(mascota.getTipo() + " está hambriento");
        }
    }

    /**
     * Accion de limpiar con normalidad.
     * @param mascota Mascota que se intenta limpiar.
     * @throws EstadoMascotaInvalidoException no afecta en este caso.
     */
    @Override
    public void limpiar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setHigiene(100);
        mascota.setSalud(mascota.getSalud()+10);
        mascota.setFelicidad(mascota.getFelicidad()+10);
    }

    /**
     * Indica si la mascota se puede vender en este estado.
     * @return true, cumple requisitos para ser vendida.
     */
    @Override
    public boolean sePuedeVender(){
        return true;
    }
}
