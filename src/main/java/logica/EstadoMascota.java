package logica;

public interface EstadoMascota {
    void procesarTick(Mascotas mascota);
    boolean sePuedeVender();
    void jugar(Mascotas mascotas)throws EstadoMascotaInvalidoException;
    void limpiar(Mascotas mascotas)throws EstadoMascotaInvalidoException;
}
