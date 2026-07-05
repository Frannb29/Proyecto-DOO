package logica;

public class Hamster extends Mascotas{
    public Hamster(){
        super(Habitat.CASA, 130);
    }
    @Override
    public void alimentar(Alimento alimento){
        if(alimento.getTipoMascota()==TipoMascota.HAMSTER){
            this.setAlimentacion(this.getAlimentacion() + alimento.getComida());
        }
        if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
            this.setEstado(new EstadoSano());
        }
    }
    @Override
    public int getDesgasteAlimentacion(){
        return 2;
    }
    @Override
    public int getDesgasteSalud(){
        return 5;
    }
    @Override
    public int getDesgasteHigiene(){
        return 2;
    }
    @Override
    public int getDesgasteFelicidad(){
        return 3;
    }
    @Override
    public void sanar(Medicina medicina){
        this.setSalud(this.getSalud() + medicina.getPuntosCuracion());
        if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
            this.setEstado(new EstadoSano());
        }
    }
    @Override
    public TipoMascota getTipo(){
        return TipoMascota.HAMSTER;
    }
}
