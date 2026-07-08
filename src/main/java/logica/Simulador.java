package logica;

public class Simulador{
    private Jugador jugador;
    private Tienda tienda;
    private Reloj reloj;
    private GeneradorCliente generaClientes;

    public Simulador(Jugador jugador){
        this.jugador = jugador;
        this.tienda = Tienda.getInstancia();
        reloj = new Reloj();
        generaClientes=new GeneradorCliente();

        reloj.addObservador(generaClientes);
    }

    public void iniciarSimulacion(){
        reloj.iniciarReloj();
    }

    public void detenerSimulacion(){
        reloj.detenerReloj();
    }

    public void addMascotaAlSistema(Mascotas mascota){
        reloj.addObservador(mascota);
        tienda.addMascota(mascota);
    }

    public void comprarMascota(TipoMascota tipo) throws PagoInsuficienteException, HabitatLlenoExcepcion, HabitatBloqueadoException{
        Mascotas nuevaMascota = tienda.comprarMascota(tipo, jugador);
        reloj.addObservador(nuevaMascota);
    }
    public void venderMascotaACliente(){
        Mascotas mascotaVendida=tienda.atenderCliente(jugador);

        if(mascotaVendida!=null){
            reloj.removeObservador(mascotaVendida);
        }
    }

    public Reloj getReloj(){
        return reloj;
    }
}
