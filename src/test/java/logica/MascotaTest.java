package logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {
    private Mascotas perro;

    @BeforeEach
    public void setUp() {
        perro = MascotaFactory.crearMascota(TipoMascota.PERRO);
    }

    @Test
    public void testCrearMascota() {
        assertNotNull(perro);
        assertEquals(TipoMascota.PERRO, perro.getTipo()); 
        assertEquals(Habitat.CASA, perro.getHabitat());
        assertEquals(100, perro.getSalud()); 
    }

    @Test
    public void testBajadeAtributosConElTiempo() {
        for(int i = 0; i < 5; i++){
            perro.pasarTiempo();
        }
    
        assertEquals(96, perro.getAlimentacion()); // 100 - 4
        assertEquals(96, perro.getFelicidad());    // 100 - 4
        assertEquals(95, perro.getHigiene());      // 100 - 5
        assertEquals(97, perro.getSalud());        // 100 - 3
    }

    @Test
    public void testAlimentarMascotaAumentaEstadisticas() {
        perro.setAlimentacion(50);
        perro.setSalud(50);
        Alimento comidaPerro = new Alimento(TipoSuministro.ALIMENTO_PERRO);
        
        perro.alimentar(comidaPerro);
        
        assertEquals(100, perro.getAlimentacion());
        assertEquals(70, perro.getSalud()); 
    }

    @Test
    public void testSanarMascotaEnferma() {
        perro.setSalud(20);
        Medicina medicina = new Medicina(TipoSuministro.MEDICINA_MEDIANA); 
        perro.sanar(medicina);
        
        assertEquals(70, perro.getSalud());
        assertTrue(perro.getAlimentacion() < 100); 
    }

    @Test
    public void testTransicionEstadoYExcepcionJugar() {
  
        perro.setAlimentacion(10); 
        

        for(int i=0; i<5; i++) { 
            perro.pasarTiempo(); 
        }
        
        assertThrows(EstadoMascotaInvalidoException.class, () -> {
            perro.jugar();
        });
    }
    
    @Test
    public void testLimpiarMascotaYaLimpiaLanzaExcepcion() {
        assertThrows(EstadoMascotaInvalidoException.class, () -> {
            perro.limpiar();
        });
    }
}