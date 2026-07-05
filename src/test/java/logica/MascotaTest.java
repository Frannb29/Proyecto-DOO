package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {

    @Test
    public void testCrearMascota() {
        Mascotas nuevoPerro = MascotaFactory.crearMascota(TipoMascota.PERRO);
        assertNotNull(nuevoPerro);
        
        assertEquals(TipoMascota.PERRO, nuevoPerro.getTipo()); 
        assertEquals(Habitat.CASA, nuevoPerro.getHabitat());
    }

    @Test
    public void testPasarTiempoBajaAlimentacion() {
        Mascotas otroPerro = new Perro();
        int alimentacionInicial = otroPerro.getAlimentacion(); 
        
        for(int i=0; i<5; i++){
            otroPerro.pasarTiempo();
        }
        assertEquals(alimentacionInicial - 5, otroPerro.getAlimentacion());
    }

    @Test
    public void testBajadeAtributosconelTiempo() {
        Mascotas perro = new Perro(); // Inicialmente todo en 100
        
        for(int i = 0; i < 5; i++){
            perro.pasarTiempo();
        }
        
        assertEquals(95, perro.getAlimentacion());
        assertEquals(97, perro.getFelicidad());
        assertEquals(95, perro.getHigiene());
        assertEquals(98, perro.getSalud());
    }

}