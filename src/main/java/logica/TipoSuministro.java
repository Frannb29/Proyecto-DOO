package logica;

public enum TipoSuministro{
    ALIMENTO_PERRO(40),
    ALIMENTO_GATO(55),
    ALIMENTO_CONEJO(60),
    ALIMENTO_HAMSTER(60),
    ALIMENTO_BULBASAUR(100),
    ALIMENTO_EEVEE(110),
    ALIMENTO_TORTUGA(130),
    ALIMENTO_PULPO(150),
    ALIMENTO_PEZ(100),
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
