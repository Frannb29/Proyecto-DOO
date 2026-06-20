package logica;

public class Perro extends Mascotas {
    @Override
    public void pasarTiempo(){
        this.alimentacion -= 20;
        this.felicidad -= 15;
        this.higiene -= 20;
        this.salud -= 10;
    }
    @Override
    public void alimentar(){}

    @Override
    public void sanar(){}

    @Override
    public void limpiar(){}

    @Override
    public void jugar(){}
    @Override
    public String getTipo(){
        return "Perro";
    }
}
