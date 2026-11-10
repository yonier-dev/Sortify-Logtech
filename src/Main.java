import java.util.List;

public class Main {
    public static void main(String[] args) {
        Paquete p1 = new Paquete("123","Laptop",2.4);
        p1.mostrarPaquete();
        BucketSort a1 = new BucketSort();
        List<Double> pesosDesordenados = List.of(
            24.5,  3.1,  18.7, 12.4, 29.2, 
            7.8,   15.3, 21.0, 0.6,  26.4, 
            11.9,  9.5,  28.1, 14.2, 5.7
        );
        List<Double> pesosOrdenados = a1.ordenarYonier(pesosDesordenados);

        System.out.println(pesosOrdenados);
    }
}
