package logica;

public class Perro extends Mascotas {
    public Perro(){
        super(Habitat.CASA, 120);
    }
    @Override
    public void alimentar(Alimento alimento){
        if(alimento.getTipoMascota()==TipoMascota.PERRO){
            this.setAlimentacion(this.getAlimentacion() + alimento.getComida());
        }
        if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
            this.setEstado(new EstadoSano());
        }
    }
    @Override
    public int getDesgasteAlimentacion(){
        return 4;
    }
    @Override
    public int getDesgasteSalud(){
        return 3;
    }
    @Override
    public int getDesgasteHigiene(){
        return 5;
    }
    @Override
    public int getDesgasteFelicidad(){
        return 4;
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
        return TipoMascota.PERRO;
    }
}
