package logica;

public class Gato extends Mascotas{
    private int contadorTicks;

    @Override
    public void pasarTiempo(){
        contadorTicks++;
        if(contadorTicks>=5){
            contadorTicks=0;
            this.alimentacion -= 3;
            this.felicidad -= 2;
            this.higiene -= 2;
            this.salud -= 3;
            super.habitat.ensuciar(5);
        }
    }
    public Gato(){
        super(Habitat.CASA);
    }
    @Override
    public void alimentar(Alimento alimento){
        int recuperacion = alimento.getComida();
        if(alimento.getTipoMascota()==TipoMascota.GATO){
            this.alimentacion += recuperacion;

            if(alimentacion > 100){
                alimentacion = 100;
            }
        }
    }
    @Override
    public void sanar(Medicina medicina){
        int recuperacion = medicina.getPuntosCuracion();
        this.salud += recuperacion;

        if(this.salud > 100){
            this.salud = 100;
        }
    }
    @Override
    public void limpiar(){
        this.higiene=100;
    }

    @Override
    public void jugar(){
        this.felicidad=100;
    }
    @Override
    public TipoMascota getTipo(){
        return TipoMascota.GATO;
    }
}
