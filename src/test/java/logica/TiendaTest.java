package logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiendaTest{
    private Tienda tienda;
    private Jugador jugador;

    @BeforeEach
    public void preparar(){
        tienda = Tienda.getInstancia();
       
        tienda.getMascotas().clear();
        tienda.getFilaClientes().clear();
        jugador = new Jugador(); // Inicia con $1000
    }

    @Test
    public void testComprarSuministrosExitoso(){
        Suministros alimento = new Alimento(TipoSuministro.ALIMENTO_PERRO); 
        int presupuestoInicial = jugador.getPresupuesto();
        try{
            tienda.comprarSuministros(jugador, alimento);
            assertEquals(presupuestoInicial - 40, jugador.getPresupuesto());
        } catch(PagoInsuficienteException e){
            fail("La compra debió ser exitosa y no lanzar excepción.");
        }
    }

    @Test
    public void testComprarSuministrosConPresupuestoInsuficiente(){
        jugador.setPresupuesto(10); 
        Suministros medicina = new Medicina(TipoSuministro.MEDICINA_GRANDE); 
        
        assertThrows(PagoInsuficienteException.class, () -> {
            tienda.comprarSuministros(jugador, medicina);
        });
    }

    @Test
    public void testComprarMascotaHabitatLlenoExcepcion() throws Exception {
        jugador.setPresupuesto(10000);
       
        tienda.comprarMascota(TipoMascota.PERRO, jugador);
        tienda.comprarMascota(TipoMascota.GATO, jugador);
        tienda.comprarMascota(TipoMascota.CONEJO, jugador);
        tienda.comprarMascota(TipoMascota.HAMSTER, jugador);

        assertThrows(HabitatLlenoExcepcion.class, () -> {
            tienda.comprarMascota(TipoMascota.PERRO, jugador);
        });
    }

    @Test
    public void testFlujoVentaClienteExitoso() throws Exception {
        Cliente cliente = new Cliente(TipoMascota.GATO);
        tienda.addCliente(cliente);
        assertEquals(1, tienda.getFilaClientes().size());

        Mascotas miGato = tienda.comprarMascota(TipoMascota.GATO, jugador);
        int dineroAntesDeVenta = jugador.getPresupuesto();

        Mascotas mascotaEntregada = tienda.atenderCliente(jugador);

        assertNotNull(mascotaEntregada);
        assertEquals(TipoMascota.GATO, mascotaEntregada.getTipo());
        assertEquals(0, tienda.getFilaClientes().size()); 
        assertTrue(cliente.isRecibido()); 
        
        assertEquals(dineroAntesDeVenta + miGato.getPrecioVenta(), jugador.getPresupuesto());
    }

    @Test
    public void testIntentarVenderMascotaEnferma() throws Exception {
       
        Mascotas perro = tienda.comprarMascota(TipoMascota.PERRO, jugador);
        
        perro.setSalud(0); 
        for(int i=0; i<5; i++){ perro.pasarTiempo(); } 
        
        Cliente cliente = new Cliente(TipoMascota.PERRO);
        tienda.addCliente(cliente);

        Mascotas mascotaEntregada = tienda.atenderCliente(jugador);
        
        assertNull(mascotaEntregada);
        assertFalse(cliente.isRecibido());
    }
}