package logica;

public class EstadoSano implements EstadoMascota{
    @Override
    public void procesarTick(Mascotas mascota){
        mascota.setSalud(mascota.getSalud()-mascota.getDesgasteSalud());
        mascota.setAlimentacion(mascota.getAlimentacion()-mascota.getDesgasteAlimentacion());

        if(mascota.getSalud() <= 0){
            mascota.setSalud(0);
            mascota.setEstado(new EstadoEnfermo());
            System.out.println(mascota.getTipo() + " está enfermo");
        } else if (mascota.getAlimentacion()<=20) {
            mascota.setEstado(new EstadoHambriento());
            System.out.println(mascota.getTipo() + " está hambriento");
        }
    }
    @Override
    public void jugar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setFelicidad(mascota.getFelicidad()+20);
        mascota.setAlimentacion(mascota.getAlimentacion()-10);
        if(mascota.getAlimentacion() <= 20){
            mascota.setEstado(new EstadoHambriento());
            System.out.println(mascota.getTipo() + " está hambriento");
        }
    }
    @Override
    public void limpiar(Mascotas mascota)throws EstadoMascotaInvalidoException{
        mascota.setHigiene(100);
        mascota.setSalud(mascota.getSalud()+10);
        mascota.setFelicidad(mascota.getFelicidad()+10);
    }
    @Override
    public boolean sePuedeVender(){
        return true;
    }
}
