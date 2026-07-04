package logica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiendaTest{
    private Tienda tienda;
    private Jugador jugador;
    @BeforeEach
    public void preparar(){
        tienda= Tienda.getInstancia();
        jugador=new Jugador();
    }
    @Test
    public void testComprarSuministros(){
        Suministros alimento=new Alimento(40, TipoMascota.PERRO);
        int presupuestoInicial=jugador.getPresupuesto();
        try{
            tienda.comprarSuministros(jugador,alimento);
            assertEquals(presupuestoInicial-40,jugador.getPresupuesto());
        }
        catch(PagoInsuficienteException e){
            fail("fallo la compra");
        }
    }
    @Test
    public void testComprarConPresupuestoInsuficiente(){
        jugador.setPresupuesto(10);
        Suministros medicina=new Medicina(150,100);
        int presupuestoInicial=jugador.getPresupuesto();
        assertThrows(PagoInsuficienteException.class, ()->{tienda.comprarSuministros(jugador,medicina);});
    }
    @Test
    public void testDepositoVacio(){
        assertThrows(DepositoVacioException.class,()->{tienda.seleccionarSuministro(TipoSuministro.ALIMENTO_GATO);});
    }
}

