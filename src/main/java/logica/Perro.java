package logica;

public class Perro extends Mascotas {
    @Override
    public void pasarTiempo(){
        this.alimentacion -= 20;
        this.felicidad -= 15;
        this.higiene -= 20;
        this.salud -= 10;
    }

    public Perro(){
        super(Habitat.CASA);
    }
    @Override
    public void alimentar(Alimento alimento){
        int recuperacion = alimento.getValor();
        this.alimentacion += recuperacion;

        if(alimentacion > 100){
            alimentacion = 100;
        }
    }

    @Override
    public void sanar(Medicina medicina){
        int recuperacion = medicina.getValor();
        this.salud += recuperacion;

        if(this.salud > 100){
            this.salud = 100;
        }
    }

    @Override
    public void limpiar(){}

    @Override
    public void jugar(){}
    @Override
    public String getTipo(){
        return "Perro";
    }
}
