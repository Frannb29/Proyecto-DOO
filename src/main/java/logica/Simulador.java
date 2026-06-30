package logica;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Simulador {
    private Jugador jugador;
    private Tienda tienda;
    private List<Mascotas> listaMascotas;
    private Timer reloj;
    private int contadorSegundos;

    public Simulador(Jugador jugador, Tienda tienda){
        this.jugador = jugador;
        this.tienda = tienda;
        this.listaMascotas = new CopyOnWriteArrayList<>();//lista que evita errores con los threads
        this.contadorSegundos = 0;
        this.reloj = new Timer();
    }

    public void iniciarSimulacion(){
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                contadorSegundos++;
                if (contadorSegundos % 5 == 0){
                    pasarTurno();
                }
            }
        };
        this.reloj.scheduleAtFixedRate(tarea, 0, 1000);
    }
    public void comprarMascota(TipoMascota tipo){
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);
        this.listaMascotas.add(nuevaMascota);
    }
    public void pasarTurno(){
        for (Mascotas mascota : listaMascotas){
            mascota.pasarTiempo();
        }
    }
    public void detenerSimulacion(){
        this.reloj.cancel();
    }

    public List<Mascotas> getListaMascotas() {
        return listaMascotas;
    }
}
