package logica;

public class Medicina extends Suministros{
    private int puntosCuracion;
    public Medicina(int valor,int puntosCuracion){
        super(valor);
        this.puntosCuracion=puntosCuracion;
    }
    public int getPuntosCuracion(){
        return puntosCuracion;
    }
    @Override
    public void usar(Mascotas mascota){
        mascota.sanar(this);
    }
}
