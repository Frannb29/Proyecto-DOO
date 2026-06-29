package logica;

public class Tienda{
    private Deposito<Alimento> stockAlimentoPerro;
    private Deposito<Alimento> stockAlimentoGato;
    private Deposito<Medicina> stockMedicinaPequeña;
    private Deposito<Medicina> stockMedicinaMediana;
    private Deposito<Medicina> stockMedicinaGrande;
    public Tienda(){
        stockAlimentoPerro=new Deposito<Alimento>();
        stockAlimentoGato=new Deposito<Alimento>();
        stockMedicinaPequeña=new Deposito<Medicina>();
        stockMedicinaMediana=new Deposito<Medicina>();
        stockMedicinaGrande=new Deposito<Medicina>();
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
                Alimento alimento=(Alimento) objeto;
                if(alimento.getTipoMascota()==TipoMascota.PERRO){
                    stockAlimentoPerro.add(alimento);
                }
                else if(alimento.getTipoMascota()==TipoMascota.GATO){
                    stockAlimentoGato.add(alimento);
                }
            }
            else if(objeto instanceof Medicina){
                Medicina medicina=(Medicina) objeto;
                if(medicina.getPuntosCuracion()==25){
                    stockMedicinaPequeña.add(medicina);
                }
                else if(medicina.getPuntosCuracion()==50){
                    stockMedicinaMediana.add(medicina);
                }
                else if(medicina.getPuntosCuracion()==100){
                    stockMedicinaGrande.add(medicina);
                }
            }
        }
    }
    public Suministros seleccionarSuministro(TipoSuministro tipo){
        if(tipo==TipoSuministro.ALIMENTO_PERRO){
            return stockAlimentoPerro.get();
        }
        else if(tipo==TipoSuministro.ALIMENTO_GATO){
            return stockAlimentoGato.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_PEQUEÑA){
            return stockMedicinaPequeña.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_MEDIANA){
            return stockMedicinaMediana.get();
        }
        else if(tipo==TipoSuministro.MEDICINA_GRANDE){
            return stockMedicinaGrande.get();
        }
        return null;
    }
}
