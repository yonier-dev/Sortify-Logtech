public class Paquete {
    //atributos
    private String id;
    private String nombre;
    private double peso;

    //contructor
    public Paquete(String id, String nombre, double peso) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
    }

    //metodos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void mostrarPaquete(){
        System.out.println("| id:" + this.getId() + " | Nombre: " + this.getNombre() + " | Peso: " + this.getPeso() + " |");
    }
}
