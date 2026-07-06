package logica;

public class Medicina extends Suministros{
    private int puntosCuracion;
    public Medicina(TipoSuministro tipo){
        super(tipo);
        
        switch (tipo) {
            case MEDICINA_PEQUEÑA:
                this.puntosCuracion=25;
                break;
            case MEDICINA_MEDIANA:
                this.puntosCuracion=50;
                break;
            case MEDICINA_GRANDE:
                this.puntosCuracion=100;
                break;
            default:
                this.puntosCuracion=0;
        }
    }
    public int getPuntosCuracion(){
        return puntosCuracion;
    }
    @Override
    public void usar(Mascotas mascota){
        mascota.sanar(this);
    }
}
