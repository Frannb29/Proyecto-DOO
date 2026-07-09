package logica;

/**
 * Interfaz que define el comportamiento de un observador del tiempo en el juego.
 * Actua como la parte "Suscrita" en el patrón Observer, permitiendo la sincronizacion de los objetos.
 */
public interface ObservadorReloj {

    /**
     * Metodo que se ejecuta en cada tick.
     * Las clases que lo implementen deben definir su logica.
     */
    void pasarTiempo();
} 
