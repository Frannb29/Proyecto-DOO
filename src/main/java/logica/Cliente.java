package logica;

public class Cliente {
    private TipoMascota pedido;
    private boolean fueRecibido=false;
    private Mascotas mascotaVendida;
    
    public Cliente(TipoMascota pedido){
        this.pedido=pedido;
    }

    public void recibirMascota(Mascotas mascota){
        this.mascotaVendida=mascota;
        this.fueRecibido=true;
    }

    public TipoMascota getPedido(){
        return pedido;
    }

    public Mascotas getMascotaVendida(){
        return mascotaVendida;
    }

    public boolean isRecibido(){
        return fueRecibido;
    }

    public void setFueRecibido(boolean vendido){
        this.fueRecibido=vendido;
    }

    public void setMascotaVendida(Mascotas mascota){
        this.mascotaVendida=mascota;
    }

    public void setPedido(TipoMascota pedido){
        this.pedido=pedido;
    }
}
