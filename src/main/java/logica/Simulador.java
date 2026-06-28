package logica;

import java.util.ArrayList;
import java.util.List;

public class Simulador {
    private Jugador jugador;
    private Tienda tienda;
    private List<Mascotas> listaMascotas;

    public Simulador(Jugador jugador, Tienda tienda){
        this.jugador = jugador;
        this.tienda = tienda;
        this.listaMascotas = new ArrayList<>();
    }
    public void comprarMascota(String tipo){
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);
        this.listaMascotas.add(nuevaMascota);
    }
    public void pasarTurno(){
        for (Mascotas mascota : listaMascotas){
            mascota.pasarTiempo();
        }
    }
}
