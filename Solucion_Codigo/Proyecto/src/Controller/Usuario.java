package Controller;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private int Npa;
    private String nombre;
    private String email;
    private String tipoUsuario;

    public Usuario(int Npa, String nombre, String email, String tipoUsuario) {
        this.Npa = Npa;
        this.nombre = nombre;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public List<Horario> consultaHorarios(Parada parada) {
        return parada.getHorarios();
    }

    public List<Ruta> consultaRutas(Gestion gestion) {
        return gestion.consultaRuta();
    }

    public void guardar(Formatter formatter) {
        formatter.format("%d,%s,%s,%s%n", Npa, nombre, email, tipoUsuario);
    }

    public static Usuario leer(Scanner sc) {
        String[] data = sc.nextLine().split(",");
        return new Usuario(Integer.parseInt(data[0]), data[1], data[2], data[3]);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Numero de la Parada =" + Npa + "\n" +
                ", nombre='" + nombre + "\n" +
                ", email='" + email + "\n" +
                ", tipoUsuario='" + tipoUsuario + 
                '}';
    }

    public void add(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
