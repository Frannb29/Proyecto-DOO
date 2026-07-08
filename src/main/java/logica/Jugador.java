package logica;

import java.util.ArrayList;

public class Jugador{
    private int presupuesto;
    private ArrayList<Habitat> habitatsComprados;
    public Jugador(){
        presupuesto=1000;
        this.habitatsComprados = new ArrayList<>();
        if(Habitat.values().length > 0){
            this.habitatsComprados.add(Habitat.values()[0]);
        }

    }
    public boolean tieneHabitat(Habitat habitat){
        return habitatsComprados.contains(habitat);
    }
    public void registrarHabitat(Habitat habitat){
        if(!tieneHabitat(habitat)){
            this.habitatsComprados.add(habitat);
        }
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
