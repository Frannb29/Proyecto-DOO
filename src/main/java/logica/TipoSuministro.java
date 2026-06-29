package logica;

public enum TipoSuministro{
    ALIMENTO_PERRO(40),
    ALIMENTO_GATO(55),
    MEDICINA_PEQUEÑA(50),
    MEDICINA_MEDIANA(90),
    MEDICINA_GRANDE(150);
    private int precio;
    TipoSuministro(int precio){
        this.precio=precio;
    }
    public int getPrecio(){
        return precio;
    }
}
