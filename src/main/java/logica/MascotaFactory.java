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
            default:
                throw new IllegalArgumentException("Tipo de mascota desconocido: " + tipo);
        }
    }
}
