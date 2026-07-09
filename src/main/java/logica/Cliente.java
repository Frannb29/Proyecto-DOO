package logica;

/**
 * Cliente que pide un tipo de mascota especifico.
 * Verifica que la mascota fue recibida.
 */
public class Cliente {
    private TipoMascota pedido;
    private boolean fueRecibido=false;
    private Mascotas mascotaVendida;

    /**
     * Crea un nuevo cliente con un pedido de mascota especifico.
     * @param pedido Tipo de mascota que el cliente solicita.
     */
    public Cliente(TipoMascota pedido){
        this.pedido=pedido;
    }

    /**
     * Entrega la mascota al cliente y la registra como vendida.
     * @param mascota La mascota que se le vende al cliente.
     */
    public void recibirMascota(Mascotas mascota){
        this.mascotaVendida=mascota;
        this.fueRecibido=true;
    }

    /**
     * Obtiene el tipo de mascota solicitado por el cliente.
     * @return TipoMascota solicitada.
     */
    public TipoMascota getPedido(){
        return pedido;
    }

    /**
     * Obtiene la Mascota vendida.
     * @return Mascota vendida.
     */
    public Mascotas getMascotaVendida(){
        return mascotaVendida;
    }

    /**
     * Verifica si la mascota fue recibida.
     * @return true o false.
     */
    public boolean isRecibido(){
        return fueRecibido;
    }
}
