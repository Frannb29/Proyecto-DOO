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
        Mascotas nuevoPerro = MascotaFactory.crearMascota(TipoMascota.PERRO);
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

    @Test
    public void testBajadeAtributosconelTiempo(){
        Jugador jugador = new Jugador();
        Tienda tienda = new Tienda();
        Simulador sim = new Simulador(jugador, tienda);
        sim.comprarMascota(TipoMascota.PERRO);
        Mascotas perro = sim.getListaMascotas().get(0);
        sim.pasarTurno();
        assertEquals(95, perro.getAlimentacion());
        assertEquals(97, perro.getFelicidad());
        assertEquals(95, perro.getHigiene());
        assertEquals(98, perro.getSalud());

    }
}
