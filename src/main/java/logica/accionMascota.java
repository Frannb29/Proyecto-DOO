package logica;

public enum accionMascota {
    ALIMENTAR("Alimentar"),
    SANAR("Sanar"),
    JUGAR("Jugar"),
    LIMPIAR("Limpiar");
    private final String nombre;
    accionMascota(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }


}
