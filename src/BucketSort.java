import java.util.ArrayList;
import java.util.List;

public class BucketSort {
    //constantes 
    final double minimo = 0.0; // kilos
    final double maximo = 30;

    final int numCubos = 6;

    public BucketSort() {
    }

    private int mapeoDelCubo(double peso) {
        // normaliza el peso en un rango de 0.0 a 1.0
        double normalizado = peso / maximo ;

        // calcula el indice del cubo
        int cubo = (int) (numCubos * normalizado);
        
        // si el peso es exactamente 30.0 kg, el índice daría 6. 
        // Math.min asegura que el indice maximo sea 5 (cubos - 1).
        return Math.min(cubo, numCubos - 1);
    }

    private List<Double> insertionSort(List<Double> cubo){
        for(int i = 1 ; i<cubo.size(); i++){
            double clave = cubo.get(i);
            int j = i - 1;
            while (j >= 0 && cubo.get(j) > clave) {
                cubo.set(j + 1, cubo.get(j));
                j--;
            }
            cubo.set(j + 1, clave);
        }
        return cubo;   
    }

    public List<Double> ordenarYonier(List<Double> listaPesoPaquetes){

        // verificacion de que si no tienen nada o estan vacios retornen
        if (listaPesoPaquetes == null || listaPesoPaquetes.isEmpty()){
            return listaPesoPaquetes;
        }

        // declaracion de cubos
        List<List<Double>> cubos = new ArrayList<>(numCubos);

        // iniciacion de cubos vacios
        for (int i = 0; i < numCubos; i++){
            cubos.add(new ArrayList<>());
        }

        for (int i = 0; i < listaPesoPaquetes.size(); i++ ){
            // asignacion de pesos en sus respectivos cubos
            double peso = listaPesoPaquetes.get(i);
            int numCubo = mapeoDelCubo(peso); 
            cubos.get(numCubo).add(peso);

        }

        List<Double> listaOrdenada = new ArrayList<>();

        // se ordena internamente cada cubo y lo de cada cubo se devuelve a la lista original
        for (List<Double> cubo : cubos){
            insertionSort(cubo);
            listaOrdenada.addAll(cubo);
        }

        return listaOrdenada;
    }
}
