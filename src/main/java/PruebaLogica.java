import logica.*;
import java.util.Scanner;

//main temporal de prueba lógica 
public class PruebaLogica {
    public static void main(String[] args) {
        System.out.println("Inicializando el Simulador de Tienda de Mascotas... ");
        
        Jugador jugador=new Jugador();
        Simulador simulador=new Simulador(jugador);
        simulador.iniciarSimulacion();
        Scanner scanner=new Scanner(System.in);
        boolean jugando=true;

        System.out.println(" ¡Tienda abierta!");

        while (jugando) {
            
            System.out.println("\n===================================");
            System.out.println(" Presupuesto actual: $" + jugador.getPresupuesto());
            System.out.println("===================================");
            System.out.println("1. Atender al siguiente cliente en la fila");
            System.out.println("2. Comprar un PERRO para la tienda");
            System.out.println("3. Comprar un GATO para la tienda");
            System.out.println("4. Salir y cerrar la tienda");
            System.out.print(" Escribe tu opción: ");

           //el reloj sigue corriendo aqui 
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    simulador.venderMascotaACliente();
                    break;
                    
                case "2":
                    try {
                        simulador.comprarMascota(TipoMascota.PERRO);
                    } catch (PagoInsuficienteException e) {
                        System.out.println("¡Error! No tienes suficiente dinero para comprar un perro");
                    }
                    break;
                    
                case "3":
                    try {
                        simulador.comprarMascota(TipoMascota.PERRO);
                    } catch (PagoInsuficienteException e) {
                        System.out.println("¡Error! No tienes suficiente dinero para comprar un gato");
                    }
                    break;
                    
                case "4":
                    System.out.println("Cerrando la tienda... ¡Hasta la próxima!");
                    jugando = false;
                    simulador.detenerSimulacion(); 
                    break;
                    
                default:
                    System.out.println(" Opción no válida. Intenta de nuevo.");
            }
        }
        
        scanner.close();
        
        System.exit(0); 
    }
}
