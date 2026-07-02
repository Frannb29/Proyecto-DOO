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
        addMascotaAlSistema(MascotaFactory.crearMascota(TipoMascota.PERRO));
        addMascotaAlSistema(MascotaFactory.crearMascota(TipoMascota.GATO));
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

    public void comprarMascota(TipoMascota tipo)throws PagoInsuficienteException{
        Mascotas nuevaMascota = MascotaFactory.crearMascota(tipo);
        if(jugador.getPresupuesto() < nuevaMascota.getPrecio()){
            throw new PagoInsuficienteException();
        }
        int nuevoPresupuesto = jugador.getPresupuesto() - nuevaMascota.getPrecio();
        jugador.setPresupuesto(nuevoPresupuesto);
        addMascotaAlSistema(nuevaMascota);
    }
    
    public void venderMascotaACliente(){
        Mascotas mascotaVendida=tienda.atenderCliente(jugador);

        if(mascotaVendida!=null){
            reloj.removeObservador(mascotaVendida);
        }
    }
}
