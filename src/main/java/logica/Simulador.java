package logica;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Simulador implements ObservadorReloj{
    private Jugador jugador;
    private Tienda tienda;
    private List<Mascotas> listaMascotas;
    private int contadorSegundos;
    private Reloj reloj;

    public Simulador(Jugador jugador, Tienda tienda){
        this.jugador = jugador;
        this.tienda = tienda;
        this.listaMascotas = new CopyOnWriteArrayList<>();//lista que evita errores con los threads
        this.contadorSegundos = 0;
        reloj = new Reloj();
        reloj.addObservador(this);
    }

    @Override
    public void pasarTiempo(){
        contadorSegundos++;
        if(contadorSegundos % 5 == 0){
            pasarTurno();
        }
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

    public void iniciarSimulacion(){
        reloj.iniciarReloj();
    }

    public void detenerSimulacion(){
        reloj.detenerReloj();
    }

    public List<Mascotas> getListaMascotas() {
        return listaMascotas;
    }
}
