package logica;

public class Perro extends Mascotas {
    @Override
    public void pasarTiempo(){
        this.alimentacion -= 5;
        this.felicidad -= 3;
        this.higiene -= 5;
        this.salud -= 2;
    }

    public Perro(){
        super(Habitat.CASA);
    }
    @Override
    public void alimentar(Alimento alimento){
        int recuperacion = alimento.getComida();
        if(alimento.getTipoMascota()==TipoMascota.PERRO){
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
        return TipoMascota.PERRO;
    }
}
