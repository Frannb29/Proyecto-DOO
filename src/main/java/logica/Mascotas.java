package logica;

public abstract class Mascotas implements ObservadorReloj {
    protected int salud = 100;
    protected int higiene = 100;
    protected int felicidad = 100;
    protected int alimentacion = 100;
    protected Habitat habitat;

    public Mascotas(Habitat habitat){
        this.habitat = habitat;
    }

    public int getSalud(){
        return salud;
    }
    public int getFelicidad() {
        return felicidad;
    }
    public int getAlimentacion() {
        return alimentacion;
    }
    public int getHigiene() {
        return higiene;
    }
    public Habitat getHabitat(){
        return habitat;
    }

    public abstract void pasarTiempo();
    public abstract void alimentar(Alimento alimento);
    public abstract void sanar(Medicina medicina);
    public abstract void limpiar();
    public abstract void jugar();
    public abstract TipoMascota getTipo();
}
