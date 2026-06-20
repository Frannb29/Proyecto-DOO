package logica;

public abstract class Mascotas {
    protected int salud = 100;
    protected int higiene = 100;
    protected int felicidad = 100;
    protected int alimentacion = 100;

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

    public abstract void pasarTiempo();
    public abstract void alimentar();
    public abstract void sanar();
    public abstract void limpiar();
    public abstract void jugar();
    public abstract String getTipo();
}
