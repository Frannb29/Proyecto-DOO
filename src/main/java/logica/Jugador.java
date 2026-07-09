package logica;

import java.util.ArrayList;


/**
 * Representa al usuario del juego y gestiona su presupuesto.
 */
public class Jugador{
    private int presupuesto;

    /**
     * Constructor que inicializa el presupuesto del jugador en $1000.
     */
    public Jugador(){
        presupuesto=1000;
    }

    /**
     * Obtiene el presupuesto actual del jugador.
     * @return Cantidad monetaria del presupuesto.
     */
    public int getPresupuesto(){
        return presupuesto;
    }

    /**
     * Modifica el presupuesto actual por uno nuevo.
     * @param nuevoPresupuesto Cantidad del nuevo presupuesto.
     */
    public void setPresupuesto(int nuevoPresupuesto){
        presupuesto=nuevoPresupuesto;
    }

    /**
     * Descuenta cierta cantidad al presupuesto.
     * @param cantidad Cantidad a descontar.
     */
    public void descontarPresupuesto(int cantidad){
        presupuesto-=cantidad;
    }

    /**
     * Aumenta cierta cantidad al presupuesto.
     * @param cantidad Cantidad a aumentar.
     */
    public void aumentarPresupuesto(int cantidad){
        presupuesto+=cantidad;
    }
}
