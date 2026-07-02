package logica;

public class Perro extends Mascotas {
    private int contadorTicks=0;

    @Override
    public void pasarTiempo(){
        contadorTicks++;
        if(contadorTicks>=5){
            contadorTicks=0;
            this.alimentacion -= 5;
            this.felicidad -= 3;
            this.higiene -= 5;
            this.salud -= 2;
            super.habitat.ensuciar(5);
        }
    }

    public Perro(){
        super(Habitat.CASA, 120);
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
