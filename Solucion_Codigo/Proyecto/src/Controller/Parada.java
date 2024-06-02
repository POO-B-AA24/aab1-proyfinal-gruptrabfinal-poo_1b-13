package Controller;
import View.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Parada {
    private String nombre;
    private int Npa;
    private String ubicacion;
    private List<Horario> horarios;

    public Parada(String nombre, int Npa, String ubicacion) {
        this.nombre = nombre;
        this.Npa = Npa;
        this.ubicacion = ubicacion;
        this.horarios = new ArrayList<>();
    }

    public int Npa() {
        return Npa;
    }

    public void setHorario(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void guardar(Formatter formatter) {
        formatter.format("%s,%d,%s%n", nombre, Npa, ubicacion);
        for (Horario horario : horarios) {
            horario.guardar(formatter);
        }
    }

    public static Parada leer(Scanner sc) {
        String[] data = sc.nextLine().split(",");
        Parada parada = new Parada(data[0], Integer.parseInt(data[1]), data[2]);
        return parada;
    }
    
    @Override
    public String toString() {
        return "Parada{" +
                ", Numero de la Parada=" + Npa + "\n" +
                ", Nombre de la Parada =" + nombre + '\n' +
                ", Ubicacion de la Parada =" + ubicacion + '\'' +
                '}';
    }  
    public void add(Parada parada) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}