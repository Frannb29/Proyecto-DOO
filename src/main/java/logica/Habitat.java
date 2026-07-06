package logica;

public enum Habitat {
    CASA(100, 4,500,"Casa","/Imagenes/Fondo_Casa.png"),
    POKECASA(100, 4,600,"PokeCasa","/Imagenes/Fondo_Pokecasa.png"),
    ACUATICO(100, 4,700,"Acuario","/Imagenes/Fondo_Acuatico.png");

    private int higiene;
    private int capacidad;
    private int precio;
    private String ruta;
    private String nombre;
    Habitat(int HigieneInicial, int capacidad,int precio, String nombre, String ruta){
        this.higiene = HigieneInicial;
        this.capacidad = capacidad;
        this.precio = precio;
        this.ruta = ruta;
    }
    public String getNombre(){
        return nombre;
    }
    public int getCapacidad(){
        return this.capacidad;
    }
    public int getPrecio(){
        return this.precio;
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
