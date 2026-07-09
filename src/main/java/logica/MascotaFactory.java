package logica;

/**
 * Fabrica encargada de la instanciacion de los diferentes tipos de mascotas.
 * Se aplica el patrón Factory para centralizar la creacion de mascotas.
 */
public class MascotaFactory {

    /**
     * Crea y devuelve una nueva instancia de una mascota segun el tipo especificado.
     * @param tipo Tipo de mascota que se quiere crear.
     * @return nueva mascota del tipo solicitada o null si el parametro es null.
     * @throws IllegalArgumentException Si el tipo de mascota recibido no coincide con ninguna de las opciones validas.
     */
    public static Mascotas crearMascota(TipoMascota tipo){
        if (tipo == null){
            return null;
        }
        switch (tipo){
            case PERRO:
                return new Perro();
            case GATO:
                return new Gato();
            case CONEJO:
                return new Conejo();
            case HAMSTER:
                return new Hamster();
            case BULBASAUR:
                return new Bulbasaur();
            case EEVEE:
                return new Eevee();
            case TORTUGA:
                return new Tortuga();
            case PULPO:
                return new Pulpo();
            case PEZ_DORADO:
                return new Pez_dorado();
            default:
                throw new IllegalArgumentException("Tipo de mascota desconocido: " + tipo);
        }
    }
}
