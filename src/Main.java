import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Paquete> paquetes = new ArrayList<>();

    public static void main(String[] args) {
        /*Paquete p1 = new Paquete("123","Laptop",2.4);
        p1.mostrarPaquete();
        BucketSort a1 = new BucketSort();
        List<Double> pesosDesordenados = List.of(
            24.5,  3.1,  18.7, 12.4, 29.2, 
            7.8,   15.3, 21.0, 0.6,  26.4, 
            11.9,  9.5,  28.1, 14.2, 5.7
        );
        List<Double> pesosOrdenados = a1.ordenarYonier(pesosDesordenados);

        System.out.println(pesosOrdenados);*/

        Scanner scanner = new Scanner(System.in);

        System.out.println("Sistema de clasificación de paquetes por peso");
        while (true) {
            System.out.println("Opciones disponibles: ");
            System.out.println("1. Ingresar paquetes de forma manual");
            System.out.println("2. Generar N paquetes con distribucion uniforme (equitativo entre 0.1 kg y 30.0 kg)");
            System.out.println("3. Ejecutar Bucket sort, midiendo el tiempo de ejecucion y imprimiendo la lista ordenada");
            System.out.println("4. Mostrar paquete");
            System.out.println("5. Mostrar todos los paquete");
            System.out.println("6. Restablecer el sistema");
            System.out.println("7. salir");
            System.out.println("¿Cual opcion desea escoger? ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del paquete: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese el peso del paquete: ");
                    double peso = scanner.nextDouble();
                    crearPaquete(nombre, peso);
                    break;
                case 2:
                    System.out.print("¿Cuantos paquetes desea generar con distribucion uniforme?: ");
                    int nUniforme = scanner.nextInt();
                    java.util.Random randomU = new java.util.Random();
                    
                    for (int i = 1; i <= nUniforme; i++) {
                        // Genera peso aleatorio uniformemente entre 0.1 y 30.0
                        double pesoGenerado = 0.1 + (30.0 - 0.1) * randomU.nextDouble();
                        
                        // Redondear a 2 decimales para mejor legibilidad (opcional)
                        pesoGenerado = Math.round(pesoGenerado * 100.0) / 100.0;
                        
                        crearPaquete("Paquete-U" + i, pesoGenerado);
                    }
                    System.out.println("Se generaron " + nUniforme + " paquetes con distribucion uniforme correctamente.");
                    break;
                case 3:
                    if (paquetes.isEmpty()) {
                        System.out.println("No hay paquetes registrados en el sistema para ordenar.");
                        break;
                    }

                    System.out.println("Ejecutando Bucket Sort para " + paquetes.size() + " paquetes...");

                    // Extraer solo los pesos para pasarlos a BucketSort
                    List<Double> pesos = new ArrayList<>();
                    for (Paquete p : paquetes) {
                        pesos.add(p.getPeso());
                    }
                    //Para que funcione en mi implementacion
                    ArrayList<Double> pesos_ = new ArrayList<>();
                    for (Paquete p : paquetes) {
                        pesos_.add(p.getPeso());
                    }


                    BucketSort sorter = new BucketSort();

                    // Medición de tiempo de ejecución
                    long inicio = System.nanoTime();
                    List<Double> pesosOrdenados = sorter.ordenarYonier(pesos);
                    long fin = System.nanoTime();
                    double tiempoMilisegundos = (fin - inicio) / 1_000_000.0;

                    //Andres
                    long inicio2 = System.nanoTime();
                    ArrayList<Double> pesosOrdenados2 = sorter.bucketSort(pesos_);
                    long fin2 = System.nanoTime();
                    double tiempoMilisegundos2 = (fin2 - inicio2) / 1_000_000.0;


                    System.out.println("\n--- RESULTADO DEL ORDENAMIENTO ---");
                    System.out.println("Pesos ordenados: " + pesosOrdenados);
                    System.out.printf("Tiempo de ejecución: %.4f ms\n", tiempoMilisegundos);
                    //Mio
                    System.out.println("Pesos ordenados: " + pesosOrdenados2);
                    System.out.printf("Tiempo de ejecución: %.4f ms\n", tiempoMilisegundos2);
                    break;
                case 4:
                    System.out.print("Ingrese el id del paquete: ");
                    String id = scanner.next();
                    
                    Boolean bandera = false;

                    for (Paquete p : paquetes){
                        if (p.getId().equals(id)){
                            p.mostrarPaquete();
                            bandera = true;
                            break;
                        }
                    }

                    if (!bandera) {
                        System.out.println("No se encontró ningún paquete con el ID: " + id);
                    }
                    break;
                case 5:
                    if (paquetes.isEmpty()) {
                        System.out.println("No hay paquetes registrados en el sistema.");
                    } else {
                        System.out.println("--- LISTA COMPLETA DE PAQUETES (" + paquetes.size() + ") ---");
                        for (Paquete p : paquetes) {
                            p.mostrarPaquete();
                        }
                    }
                    break;
                case 6:
                    paquetes.clear();
                    System.out.println("El sistema ha sido restablecido. Se eliminaron todos los paquetes.");
                    break;
                case 7:
                    System.out.println("Saliendo del sistema Logtech. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;
                        
                default:
                    break;
            }
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine(); 
            scanner.nextLine(); 
            limpiarPantalla();
        }
    }

    public static void crearPaquete(String nombre, double peso){
        Paquete p = new Paquete(nombre, peso);
        paquetes.add(p);
        p.mostrarPaquete();
    }

    public static void limpiarPantalla() {
    try {
        // Evalúa si el sistema operativo es Windows
        if (System.getProperty("os.name").contains("Windows")) {
            // Ejecuta el comando 'cls' de la terminal de Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            // Ejecuta el comando 'clear' en Linux y Mac o usa códigos ANSI
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (Exception e) {
        // Si todo lo anterior falla, imprime líneas en blanco como alternativa
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

}
