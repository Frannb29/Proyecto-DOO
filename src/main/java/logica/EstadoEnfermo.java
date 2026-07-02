package logica;

public class EstadoEnfermo implements EstadoMascota{
    @Override
    public void procesarTick(Mascotas mascota){
        mascota.setSalud(mascota.getSalud()-mascota.getDesgasteSalud());
        mascota.setFelicidad(mascota.getFelicidad()-(2*mascota.getDesgasteFelicidad()));
    }
    @Override
    public void jugar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        throw new EstadoMascotaInvalidoException(mascota.getTipo() + " está muy enfermo para jugar :(");
    }
    @Override
    public void limpiar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setHigiene(100);
        mascota.setSalud(mascota.getSalud()+5);
        mascota.setFelicidad(mascota.getFelicidad()+5);
    }
    @Override
    public boolean sePuedeVender(){
        return false;
    }
}
