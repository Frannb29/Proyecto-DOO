package logica;

import logica.Mascotas;
import logica.Perro;
import logica.Alimento;
import logica.TipoMascota;
import logica.MascotaFactory;
import logica.Habitat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {
    @Test
    public void testCrearMascota(){
        Mascotas nuevoPerro = MascotaFactory.crearMascota("Perro");
        assertNotNull(nuevoPerro);
        assertEquals("Perro", nuevoPerro.getTipo());
        assertEquals(Habitat.CASA, nuevoPerro.getHabitat());
    }

    @Test
    public void testPasarTiempoBajaAlimentacion(){
        Mascotas otroPerro = new Perro();
        int alimentacionInicial = otroPerro.getAlimentacion();
        otroPerro.pasarTiempo();
        assertEquals(alimentacionInicial - 20, otroPerro.getAlimentacion());
    }
}
