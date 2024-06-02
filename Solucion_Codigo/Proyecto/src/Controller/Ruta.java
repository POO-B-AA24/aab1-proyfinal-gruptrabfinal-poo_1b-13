package Controller;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Ruta {
    private int Npa;
    private String nombre;
    private List<Parada> paradas;

    public Ruta(int Npa, String nombre) {
        this.Npa = Npa;
        this.nombre = nombre;
        this.paradas = new ArrayList<>();
    }

    public void agregarParada(Parada parada) {
        paradas.add(parada);
    }

    public void eliminarParada(Parada parada) {
        paradas.remove(parada);
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public List<Parada> optimizarRuta() {
        // Implementación del algoritmo de optimización de rutas
        return paradas;
    }

    public void guardar(Formatter formatter) {
        formatter.format("%d,%s%n", Npa, nombre);
        for (Parada parada : paradas) {
            parada.guardar(formatter);
        }
    }

    public static Ruta leer(Scanner sc) {
        String[] data = sc.nextLine().split(",");
        return new Ruta(Integer.parseInt(data[0]), data[1]);
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "Numero de la Parada" + "\n" +
                ", nombre='" + nombre + "\n" +
                ", paradas=" + paradas +
                '}';
    }

    public void add(Ruta ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
