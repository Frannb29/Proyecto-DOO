package logica;

/**
 * Tipo de alimento y medicina disponibles en el juego.
 */
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

    /**
     * Constructor que inicializa el precio de los suministros.
     * @param precio Precio del suministro correspondiente.
     */
    TipoSuministro(int precio){
        this.precio=precio;
    }

    /**
     * Obtiene el precio del suministro correspondiente.
     * @return Precio del suministro.
     */
    public int getPrecio(){
        return precio;
    }
}
