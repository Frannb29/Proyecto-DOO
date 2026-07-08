package logica;

public class Gato extends Mascotas{

    public Gato(){
        super(Habitat.CASA, 100);
    }
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
    @Override
    public int getDesgasteAlimentacion(){
        return 2;
    }
    @Override
    public int getDesgasteSalud(){
        return 4;
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
        int curacion=medicina.getPuntosCuracion();
        this.setSalud(this.getSalud() + curacion);

        int desgasteMedicina=medicina.getPuntosCuracion()/5; //la mascota le da un poco de hambre al consumir una medicina
        this.setAlimentacion(this.getAlimentacion()-desgasteMedicina);

        if (this.getAlimentacion() > 20 && this.getSalud() > 20) {
            this.setEstado(new EstadoSano());
        }
    }
    @Override
    public TipoMascota getTipo(){
        return TipoMascota.GATO;
    }
    @Override
    public TipoSuministro getTipoAlimento(){
        return TipoSuministro.ALIMENTO_GATO;
    }
}
