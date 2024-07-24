package Controller;
import java.util.Formatter;
import java.util.Scanner;

public class Ruta {
    private int idRuta;
    private String descripcion;

    public Ruta(int idRuta, String descripcion) {
        this.idRuta = idRuta;
        this.descripcion = descripcion;
    }

    public void guardar(Formatter formatter) {
        formatter.format("%d,%s%n", idRuta, descripcion);
    }

    public static Ruta leer(Scanner sc) {
        String[] data = sc.nextLine().split(",");
        return new Ruta(Integer.parseInt(data[0]), data[1]);
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "ID Ruta=" + idRuta + "\n" +
                ", Descripción='" + descripcion + 
                '}';
    }
}
