package logica;

/**
 * Interfaz que define el comportamiento de cada estado.
 * Aplica el patron de diseño State para encapsular el comportamiento de la mascota.
 */
public interface EstadoMascota {

    /**
     * Procesa el desgaste de los atributos de la mascota de manera distinta durante un tick (tiempo).
     * @param mascota Mascota a la que se le aplicaran los cambios.
     */
    void procesarTick(Mascotas mascota);

    /**
     * Determina si una mascota es vendible segun su estado.
     * @return true si se puede vender, false en caso contrario.
     */
    boolean sePuedeVender();

    /**
     * Dependiendo del estado, esta accion se puede bloquear mediante una excepcion.
     * @param mascotas Mascota con la que se intenta jugar.
     * @throws EstadoMascotaInvalidoException Si el estado impide que la mascota juegue.
     */
    void jugar(Mascotas mascotas)throws EstadoMascotaInvalidoException;

    /**
     * Ejecuta la accion de limpiar a la mascota.
     * @param mascotas Mascota que se intenta limpiar.
     * @throws EstadoMascotaInvalidoException no aplica en esta accion.
     */
    void limpiar(Mascotas mascotas)throws EstadoMascotaInvalidoException;
}
