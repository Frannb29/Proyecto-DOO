package logica;

public enum Habitat {
    CASA(100),
    POKECASA(100),
    ACUATICO(100);
    private int higiene;
    Habitat(int HigieneInicial){
        this.higiene = HigieneInicial;
    }
    public int getHigiene(){
        return higiene;
    }
    public void ensuciar(int mugre){
        this.higiene -= mugre;
        if(this.higiene < 0){
            this.higiene = 0;
        }
    }
    public void limpiarHabitat(){
        this.higiene = 100;
    }
}
