package logica;

public class MascotaFactory {
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
                return new eevee();
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
