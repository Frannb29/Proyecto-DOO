package logica;

public class Jugador{
    private int presupuesto;
    public Jugador(){
        presupuesto=500;
    }
    public int getPresupuesto(){
        return presupuesto;
    }
    public void setPresupuesto(int nuevoPresupuesto){
        presupuesto=nuevoPresupuesto;
    }
    public void descontarPresupuesto(int cantidad){
        presupuesto-=cantidad;
    }
    public void aumentarPresupuesto(int cantidad){
        presupuesto+=cantidad;
    }
}
