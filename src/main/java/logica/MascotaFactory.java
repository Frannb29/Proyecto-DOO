package logica;

public class MascotaFactory {
    public static Mascotas crearMascota(String tipo){
        if (tipo == null){
            return null;
        }
        //convierte el string en minusculas para evitar errores de escritura
        switch (tipo.toLowerCase()){
            case "perro":
                return new Perro();
            default:
                throw new IllegalArgumentException("Tipo de mascota desconocido: " + tipo);
        }
    }
}
