package logica;

public abstract class Suministros{
    protected TipoSuministro tipo;

    protected Suministros(TipoSuministro tipo){
        this.tipo=tipo;
    }
    
    public TipoSuministro getTipo(){
        return tipo;
    }
    
    public int getValor(){
        return tipo.getPrecio();
    }
    public abstract void usar(Mascotas mascota);
}
