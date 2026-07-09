package logica;

/**
 * Clase abstracta con metodos base para Medicinas y Alimentos
 */
public abstract class Suministros{
    protected TipoSuministro tipo;

    /**
     * Constructor protegido que inicializa el tipo de suministro.
     * @param tipo Tipo de suministro.
     */
    protected Suministros(TipoSuministro tipo){
        this.tipo=tipo;
    }

    /**
     * Obtiene el tipo de suministro
     * @return Tipo de Suministro.
     */
    public TipoSuministro getTipo(){
        return tipo;
    }

    /**
     * Obtiene el precio de compra del suministro.
     * @return Precio del suministro.
     */
    public int getValor(){
        return tipo.getPrecio();
    }

    /**
     * Define el uso del suministro para una mascota.
     * Este metodo debe ser implementado por las subclases.
     * @param mascota Mascota a la que se le aplica el suministro.
     */
    public abstract void usar(Mascotas mascota);
}
