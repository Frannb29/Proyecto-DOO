package logica;

public abstract class Mascotas implements ObservadorReloj {
    protected int salud;
    protected int higiene;
    protected int felicidad;
    protected int alimentacion;
    protected int precio;
    protected Habitat habitat;
    private EstadoMascota estadoActual;
    private int contadorTicks = 0;

    public Mascotas(Habitat habitat, int precio){
        this.salud = 100;
        this.higiene = 100;
        this.felicidad = 100;
        this.alimentacion = 100;
        this.habitat = habitat;
        this.precio = precio;
        this.estadoActual = new EstadoSano();
    }

    public abstract int getDesgasteAlimentacion();
    public abstract int getDesgasteSalud();
    public abstract int getDesgasteHigiene();
    public abstract int getDesgasteFelicidad();

    public void setEstado(EstadoMascota estadoNuevo) {
        this.estadoActual = estadoNuevo;
    }

    public int getSalud(){
        return salud;
    }
    public void setSalud(int salud){
        this.salud = Math.max(0, Math.min(salud,100));
    }
    public int getFelicidad() {
        return felicidad;
    }
    public void setFelicidad(int felicidad) {
        this.felicidad = Math.max(0, Math.min(felicidad, 100));
    }

    public int getAlimentacion() {
        return alimentacion;
    }
    public void setAlimentacion(int alimentacion) {
        this.alimentacion = Math.max(0, Math.min(alimentacion,100));
    }

    public int getHigiene() {
        return higiene;
    }
    public void setHigiene(int higiene) {
        this.higiene = Math.max(0, Math.min(higiene, 100));
    }

    public Habitat getHabitat(){
        return habitat;
    }

    public boolean sePuedeVender(){
        return estadoActual.sePuedeVender() && this.habitat.getHigiene() > 0;
    }
    public int getPrecio(){
        return precio;
    }
    //precio base + 50% de ganancias
    public int getPrecioVenta(){
        return (int)(this.precio * 1.5);
    }
    public void pasarTiempo(){
        if(contadorTicks >= 5){
            this.contadorTicks = 0;
            estadoActual.procesarTick(this);
            this.habitat.ensuciar(2);
        }
    }
    public abstract void alimentar(Alimento alimento);
    public abstract void sanar(Medicina medicina);
    public void limpiar()throws EstadoMascotaInvalidoException{
        if(this.higiene >= 60){
            throw new EstadoMascotaInvalidoException("¡Ya está limpio! no lo puedes limpiar");
        }
        estadoActual.limpiar(this);
    }
    public void jugar() throws EstadoMascotaInvalidoException{
        estadoActual.jugar(this);
    }
    public abstract TipoMascota getTipo();
}
