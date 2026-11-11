error id: file:///C:/Users/pipec/Desktop/Java/Sortify-Logtech/src/BucketSort.java:java/util/List#
file:///C:/Users/pipec/Desktop/Java/Sortify-Logtech/src/BucketSort.java
empty definition using pc, found symbol in pc: java/util/List#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1182
uri: file:///C:/Users/pipec/Desktop/Java/Sortify-Logtech/src/BucketSort.java
text:
```scala
import java.util.ArrayList;
import java.util.List;

public class BucketSort {
    //constantes 
    final double minimo = 0.0; // kilos
    final double maximo = 30;
    ArrayList<ArrayList<Double> > listasBuckets = new ArrayList<>();
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

    public List<Double> ordenarYonier(@@List<Double> listaPesoPaquetes){

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
 //;;;;;
//          Implementacion Andres
//;;;;;;
    private ArrayList<ArrayList<Double>> añadirList(int k){
        for(int i = 0;i < k;i++){
           //Crea un nuevo arrayList apuntando a una nueva referencia
            listasBuckets.add(new ArrayList<>());//Se añade esa lista al arrayList que contiene varios ArrayList
        }
        
        return listasBuckets;//Devuelvo una lista de listas
    }

    public ArrayList<ArrayList<Double>> distribucion(double [] A){
       listasBuckets.clear();//Para evitar duplicados y liberar la memoria del anterior ordenamiento(limpia el arryList)
       
       double minimo=A[0];//Van a obtener el primer valor del array
       double maximo=A[0];//Van a obtener el primer valor del array
        int longitudDatos=A.length;
        
        int k = longitudDatos/2;//Para organizar los buckets
        int rangoInsertB = longitudDatos/k;//Para saber cuantos valores meter en cada bucket
        
        for(int i = 1;i < A.length;i++){//Es un ciclo para hayar el minimo y maximo
            if(A[i] < minimo){
               minimo=A[i];//Va ser igual a un nuevo valor minimo
            }
            if(A[i] > maximo){
               maximo=A[i];//Va ser igual a un nuevo valor maximo
            }
        }  //indice = (elemento del arreglo(x) - minimo/(maximo-minimo) )*cantidad de buckets(k)
        int indice;
        
        añadirList(k);//Se crean los nuevos bucket
        
        for(int i = 0;i < A.length;i++){
           
           indice=(int) (((A[i]-minimo)/(maximo-minimo))*k);//Realizo casting para que se haga la conversion a entero
           //Para que no se desborde la pila de los buckets
           if(indice >= k){
              indice = k-1;//Se garantiza que indice no se vaya desbordar es decir que su indice supere los indices que hay en cada bucket
           }
           listasBuckets.get(indice).add(A[i]);//Añado al bucket contenido en una lista Ej: add(2) ->listasBuckets [ [2] ]
        }
        
        return listasBuckets; //Devuelve lista de listas(buckets)
    }

    public ArrayList<Double> bucketSort(
        double [] A){

        ArrayList<Double> result=new ArrayList<>();  
        distribucion(A);//Distribuye el array en diferentes Buckets contenidos a una lista
       
        for(int i=0; i < listasBuckets.size();i++){
           insertionSort(listasBuckets.get(i));
        }
        
        for(int i=0; i < listasBuckets.size();i++){
           for(double value : listasBuckets.get(i)){
                result.add(value);//Se van añadiendo los elementos de cada bucket en un arraylist de resultado
           }
        }
        
        return result;
    }



}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/List#