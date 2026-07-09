package logica;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Motor de tiempo principal del juego (Clase Sujeto en el patrón Observer).
 * Gestiona un temporizador asincrono que corre en segundo plano y notifica
 * periodicamente a todos los componentes registrados como observadores.
 */
public class Reloj {
    private List<ObservadorReloj> observadores;
    private ScheduledExecutorService programador;
    private int segundosTranscurridos;

    /**
     * Constructor que inicializa el reloj con un contador en 0.
     * Utiliza {@link CopyOnWriteArrayList} para permitir la adicion o eliminacion de observadores
     * de forma segura si se interactaa desde multiples hilos.
     */
    public Reloj(){
        this.observadores = new CopyOnWriteArrayList<>();
        this.segundosTranscurridos = 0;
    }

    /**
     * Registra un nuevo componente para escuchar los eventos de tiempo del reloj.
     * * @param observador El {@link ObservadorReloj} que se desea añadir a la lista de notificacion.
     */
    public void addObservador(ObservadorReloj observador){
        this.observadores.add(observador);
    }

    /**
     * Elimina a un componente de la lista de alertas, dejando de recibir eventos de tiempo.
     * * @param observador El {@link ObservadorReloj} que se desea remover.
     */
    public void removeObservador(ObservadorReloj observador){
        this.observadores.remove(observador);
    }

    /**
     * Recorre la lista de observadores registrados y ejecuta pasarTiempo.
     */
    private void notificarObservadores(){
        for(ObservadorReloj observador : this.observadores){
            observador.pasarTiempo();
        }
    }

    /**
     * Inicia el contador del reloj en un hilo secundario independiente.
     * Configura una tarea repetitiva que incrementa el contador y notifica a todos los observadores cada 1 segundo.
     */
    public void iniciarReloj(){
        // El programador asigna un hilo en segundo plano dedicado a los ticks del reloj
        this.programador = Executors.newScheduledThreadPool(1);

        Runnable ticksReloj = () -> {
            this.notificarObservadores();
            this.segundosTranscurridos++;
        };

        // Agenda la tarea para ejecutarse inmediatamente (delay 0) y repetirse cada segundo
        this.programador.scheduleAtFixedRate(ticksReloj, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Detiene la ejecucion del temporizador en segundo plano y libera el hilo
     * asignado por el planificador si este se encuentra activo.
     */
    public void detenerReloj(){
        if (this.programador != null && !this.programador.isShutdown()) {
            this.programador.shutdown();
        }
    }

    /**
     * Obtiene la lista actual de todos los observadores que están escuchando al reloj.
     * * @return Lista de tipo {@link ObservadorReloj}.
     */
    public List<ObservadorReloj> getObservadores(){
        return this.observadores;
    }

    /**
     * Obtiene el tiempo total desde que se inició el reloj por ultima vez.
     * * @return Cantidad de segundos transcurridos.
     */
    public int getSegundosTranscurridos(){
        return this.segundosTranscurridos;
    }
}