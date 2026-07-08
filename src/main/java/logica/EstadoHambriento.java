package logica;

public class EstadoHambriento implements EstadoMascota{
    @Override
    public void procesarTick (Mascotas mascota){
        mascota.setSalud(mascota.getSalud()-(2*mascota.getDesgasteSalud()));
        mascota.setFelicidad(mascota.getFelicidad()-(2*mascota.getDesgasteFelicidad()));
        mascota.setHigiene(mascota.getHigiene()-(2*mascota.getDesgasteHigiene()));
        mascota.setAlimentacion(mascota.getAlimentacion()-mascota.getDesgasteAlimentacion());
        if(mascota.getSalud() <= 20){
            mascota.setEstado(new EstadoEnfermo());
            System.out.println(mascota.getTipo() + " se ha enfermado por hambre");
        }
    }
    @Override
    public void jugar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        throw new EstadoMascotaInvalidoException(mascota.getTipo() + " está muy hambriento para jugar :(");
    }
    @Override
    public void limpiar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setHigiene(100);
        mascota.setSalud(mascota.getSalud()+10);
        mascota.setFelicidad(mascota.getFelicidad()+5);
    }
    @Override
    public boolean sePuedeVender(){
        return false;
    }
}
