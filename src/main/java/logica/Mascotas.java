package logica;

public abstract class Mascotas implements ObservadorReloj {
    protected int salud = 100;
    protected int higiene = 100;
    protected int felicidad = 100;
    protected int alimentacion = 100;
    protected int precio;
    protected Habitat habitat;

    public Mascotas(Habitat habitat, int precio){

        this.habitat = habitat;
        this.precio = precio;
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

    public boolean sePuedeVender(){
        return this.alimentacion > 0 && this.felicidad > 0 && this.higiene > 0 && this.salud > 0 && this.habitat.getHigiene() > 0;
    }
    public int getPrecio(){
        return precio;
    }
    //precio base + 50% de ganancias
    public int getPrecioVenta(){
        return (int)(this.precio * 1.5);
    }
    public abstract void pasarTiempo();
    public abstract void alimentar(Alimento alimento);
    public abstract void sanar(Medicina medicina);
    public abstract void limpiar();
    public abstract void jugar();
    public abstract TipoMascota getTipo();
}
