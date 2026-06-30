package logica;

public class Cliente {
    private TipoMascota pedido;
    private boolean fueVendido=false;
    private Mascotas mascotaVendida;
    
    public Cliente(TipoMascota pedido){
        this.pedido=pedido;
    }

    public void venderMascota(Mascotas mascota){
        this.mascotaVendida=mascota;
        this.fueVendido=true;
    }

    public TipoMascota getPedido(){
        return pedido;
    }

    public Mascotas getMascotaVendida(){
        return mascotaVendida;
    }

    public boolean isVendido(){
        return fueVendido;
    }
}
