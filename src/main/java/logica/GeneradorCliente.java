package logica;

import java.util.Random;

/**
 * Genera un cliente cada 10 ticks con una probabilidad del 60%.
 * Implementa {@link ObservadorReloj} para activarse con cada ciclo de tiempo (tick).
 */
public class GeneradorCliente implements ObservadorReloj{
    private int contadorTicks=0;
    Tienda tienda=Tienda.getInstancia();
    private static final int INTERVALO_GENERACION=10; //cada 10 ticks hay una probabilidad de generar un cliente
    private final Random rand=new Random();

    /**
     * Incrementa el contador de forma periodica.
     * cuando el contador llega a 10 este se reinicia y se evalua con una probabilidad del 60% si se genera un nuevo cliente..
     */
    @Override
    public void pasarTiempo(){
        contadorTicks++;

        if(contadorTicks>=INTERVALO_GENERACION){
            contadorTicks=0;

            //probabilidad de 60% de generar al cliente
            if(rand.nextDouble()<0.6){
                generarCliente();
            }
        }
    }

    /**
     * Se le asigna un pedido al azar al nuevo cliente generado y lo agrega a la lista de espera de la tienda.
     */
    private void generarCliente(){
        //selecciona el pedido un tipo de mascota al azar 
        TipoMascota pedido=TipoMascota.values()[rand.nextInt(TipoMascota.values().length)];
        Cliente cliente=new Cliente(pedido);
        tienda.addCliente(cliente);
    }

}
