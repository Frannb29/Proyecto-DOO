package logica;

public class Tienda{
    private Deposito<Alimento> stockAlimento;
    private Deposito<Medicina> stockMedicina;
    public Tienda(){
        stockAlimento=new Deposito<Alimento>();
        stockMedicina=new Deposito<Medicina>();
    }
    public void comprarSuministros(Jugador jugador, Suministros objeto){
        int valor=objeto.getValor();
        int presupuesto=jugador.getPresupuesto();
        if(valor>presupuesto){
            System.out.println("Pago insuficiente");
        }
        else{
            presupuesto-=valor;
            jugador.setPresupuesto(presupuesto);
            if(objeto instanceof Alimento){
                stockAlimento.add((Alimento) objeto);
            }
            else if(objeto instanceof Medicina){
                stockMedicina.add((Medicina) objeto);
            }
        }
    }
    public Suministros seleccionarSuministro(TipoSuministro tipo){
        if(tipo==TipoSuministro.ALIMENTO){
            return stockAlimento.get();
        }
        else if(tipo==TipoSuministro.MEDICINA){
            return stockMedicina.get();
        }
        return null;
    }
}
