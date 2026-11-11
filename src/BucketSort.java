import java.util.ArrayList;

public class BucketSort {
    ArrayList<ArrayList<Double> > listasBuckets = new ArrayList<>();
    
    public BucketSort() { //Constructor
    }

    private ArrayList<Double> insertionSort(ArrayList<Double> cubo){
        for(int i = 1 ; i<cubo.size(); i++){
            double clave = cubo.get(i);
            int j = i - 1;
            if(cubo.get(j) > clave){ //Pequeña mejora al algoritmo de insertion sort en caso de que A[j] < key no habria necesidad de ejecutar el while que ordena 
                while (j >= 0 && cubo.get(j) > clave) {
                  cubo.set(j + 1, cubo.get(j));
                  j--;
                }
              cubo.set(j + 1, clave);
            }
        }
        return cubo;   
    }

    private ArrayList<ArrayList<Double>> añadirList(int k){
        for(int i = 0;i < k;i++){
           //Crea un nuevo arrayList apuntando a una nueva referencia
            listasBuckets.add(new ArrayList<>());//Se añade esa lista al arrayList que contiene varios ArrayList
        }
        
        return listasBuckets;//Devuelvo una lista de listas
    }

    public ArrayList<ArrayList<Double>> distribucion(ArrayList<Double> listaPesoPaquetes){

       listasBuckets.clear();//Para evitar duplicados y liberar la memoria del anterior ordenamiento(limpia el arryList)
       
        // verificacion de que si no tienen nada o estan vacios retornen
        if (listaPesoPaquetes == null || listaPesoPaquetes.isEmpty()){
            return listasBuckets;
        }
       
       //Inicializacion de variables 
       double minimo=listaPesoPaquetes.get(0);//Van a obtener el primer valor del array
       double maximo=listaPesoPaquetes.get(0);//Van a obtener el primer valor del array
        int longitudDatos= listaPesoPaquetes.size();
        int k = longitudDatos/2;//para calcular la cantidad de buckets que el algoritmo va utilizar
        //Restriccion
        if (k == 0) k=1;
        int indice;

        for(int i = 1;i < longitudDatos;i++){//Es un ciclo para hayar el minimo y maximo
            if(listaPesoPaquetes.get(i) < minimo){
               minimo=listaPesoPaquetes.get(i);//Va ser igual a un nuevo valor minimo
            }
            if(listaPesoPaquetes.get(i) > maximo){
               maximo=listaPesoPaquetes.get(i);//Va ser igual a un nuevo valor maximo
            }
        }  
        double rango = maximo-minimo;
        
        añadirList(k);//Se crean los nuevos bucket
        //Es una excepcion en caso de division por cero .puede suceder para una lista con valores iguales
        if (rango == 0) {
            for (int i = 0; i < longitudDatos; i++) {
                listasBuckets.get(0).add(listaPesoPaquetes.get(i));
            }
            return listasBuckets;//Devuelve lista de listas(buckets)

        }else{
                                                                                             //(rango)                   
            for(int i = 0;i < longitudDatos;i++){ //indice = (elemento del arreglo - minimo/(maximo-minimo) )*cantidad de buckets(k)
                indice=(int) (((listaPesoPaquetes.get(i)-minimo)/rango)*k);//Realizo casting para que se haga la conversion a entero
                //Para que no se desborde la pila de los buckets
                if(indice >= k){
                    indice = k-1;//Se garantiza que indice no se vaya desbordar es decir que su indice supere los indices que hay en cada bucket
                }
                listasBuckets.get(indice).add(listaPesoPaquetes.get(i));//Añado al bucket contenido en una lista Ej: add(2) ->listasBuckets [ [2] ]
            }
        
            return listasBuckets; //Devuelve lista de listas(buckets)
        }

    }

    public ArrayList<Double> bucketSort(
        ArrayList<Double> listaPesoPaquetes){

        ArrayList<Double> result=new ArrayList<>();  
        distribucion(listaPesoPaquetes);//Distribuye el array en diferentes Buckets contenidos a una lista
       
        for(int i=0; i < listasBuckets.size();i++){
           insertionSort(listasBuckets.get(i));
        }
        
        for(int i=0; i < listasBuckets.size();i++){
           for(double value : listasBuckets.get(i)){
                result.add(value);//Se van añadiendo los elementos de cada bucket en un arraylist de resultado
           }
        }
        
        return result;//Devuelve la lista con el resultado
    }

  
}
/*
Implementacion del bucket sort yonier 

nuestro criterio para seleccionar algoritmo que al final se va quedar en la implementacion.
Algoritmo Yonier vs Algoritmo Andres
en 100 valores
yonier->0,3978 ms
andres->0,1998 ms

yonier->0,2019 ms
andres->0,1012 ms

en 1000 datos
yonier->17,4325 ms
andres->7,9697 ms

en 10 mil datos
yonier->51,5061 ms
andres->9,3565 ms

librerias
import java.util.ArrayList;
import java.util.List;
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

*/
