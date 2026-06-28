package logica;

public abstract class Suministros{
    private int valor;
    protected Suministros(int valor){
        this.valor=valor;
    }
    public int getValor(){
        return valor;
    }
    public abstract void usar(Mascotas mascota);
}
