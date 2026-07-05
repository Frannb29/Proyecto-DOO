package logica;

public enum Habitat {
    CASA(100, 4, "Casa","/Imagenes/Fondo_Casa.png"),
    POKECASA(100, 4,"PokeCasa","/Imagenes/Fondo_Casa.png"),
    ACUATICO(100, 4,"Acuario","/Imagenes/Fondo_Acuatico.png");

    private int higiene;
    private int capacidad;
    private String ruta;
    private String nombre;
    Habitat(int HigieneInicial, int capacidad,String nombre, String ruta){
        this.higiene = HigieneInicial;
        this.capacidad = capacidad;
        this.ruta = ruta;
    }
    public String getNombre(){
        return nombre;
    }
    public int getCapacidad(){
        return this.capacidad;
    }
    public String getRuta(){
        return this.ruta;
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
