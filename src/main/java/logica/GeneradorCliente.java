package logica;

import java.util.Random;

public class GeneradorCliente implements ObservadorReloj{
    private int contadorTicks=0;
    private static final int INTERVALO_GENERACION=10; //cada 10 ticks hay una probabilidad de generar un cliente
    private final Random rand=new Random();

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

    private void generarCliente(){
        //selecciona el pedido un tipo de mascota al azar 
        TipoMascota pedido=TipoMascota.values()[rand.nextInt(TipoMascota.values().length)];
        Cliente cliente=new Cliente(pedido);
        //aquí haría falta meter al cliente a la fila de la tienda pero falta el método
    }

}
