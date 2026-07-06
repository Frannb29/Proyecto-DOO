package logica;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reloj {
    private List<ObservadorReloj> observadores;
    private ScheduledExecutorService programador;
    private int segundosTranscurridos;

    public Reloj(){
        observadores=new CopyOnWriteArrayList<>();
        segundosTranscurridos=0;
    }

    public void addObservador(ObservadorReloj observador){
        observadores.add(observador);
    }


    public void removeObservador(ObservadorReloj observador){
        observadores.remove(observador);
    }

    //llama al método pasarTiempo() de cada observador (funciona distinto si es GeneradorCliente o una mascota)
    private void notificarObservadores(){
        for(ObservadorReloj observador : observadores){
            observador.pasarTiempo();
        }
    }

    public void iniciarReloj(){
        //programador es un hilo que se ejecuta en segundo plano y se encarga de ejecutar el metodo notificarObservadores cada 1 seg.
        programador=Executors.newScheduledThreadPool(1);

        Runnable ticksReloj=() -> {
            notificarObservadores();
            segundosTranscurridos++;
        };

        //por cada unidad(en este caso 1 seg.) se ejecuta lo que tiene dentro ticksReloj
        programador.scheduleAtFixedRate(ticksReloj, 0, 1, TimeUnit.SECONDS);
    }

    public void detenerReloj(){
        if (programador!=null && !programador.isShutdown()) {
            programador.shutdown();
        }
    }

    public List<ObservadorReloj> getObservadores(){
        return observadores;
    }

    public int getSegundosTranscurridos(){
        return segundosTranscurridos;
    }
    
}
