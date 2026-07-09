package logica;

/**
 * Enums de las interacciones de mascotas para los botones.
 */
public enum accionMascota {
    ALIMENTAR("Alimentar"),
    SANAR("Sanar"),
    JUGAR("Jugar"),
    LIMPIAR("Limpiar");
    private final String nombre;

    /**
     * @param nombre nombre de la accion.
     */
    accionMascota(String nombre){
        this.nombre = nombre;
    }

    /**
     * metodo getter para obtener el nombre.
     * @return nombre de la interaccion.
     */
    public String getNombre(){
        return this.nombre;
    }


}
