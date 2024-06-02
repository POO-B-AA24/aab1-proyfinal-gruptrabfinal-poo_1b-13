package Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private List<Parada> paradas;
    private List<Ruta> rutas;
    private List<Usuario> usuarios;

    public Gestion() {
        paradas = new ArrayList<>();
        rutas = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void regParada(Parada parada) {
        paradas.add(parada);
    }

    public void regRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public void regUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Parada> consultaParada() {
        return paradas;
    }

    public List<Ruta> consultaRuta() {
        return rutas;
    }

    public List<Usuario> consultaUsuarios() {
        return usuarios;
    }

    public void guardarDatos(String nombreArchivo) {
        try (Formatter formatter = new Formatter(nombreArchivo)) {
            for (Parada parada : paradas) {
                parada.guardar(formatter);
            }
            for (Ruta ruta : rutas) {
                ruta.guardar(formatter);
            }
            for (Usuario usuario : usuarios) {
                usuario.guardar(formatter);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error No se Guardaron los Datos " + e.getMessage());
        }
    }

    public void cargarDatos(String nombreArchivo) {
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] data = linea.split(",");
                switch (data.length) {
                    case 3:
                        Parada parada = new Parada(data[0], Integer.parseInt(data[1]), data[2]);
                        paradas.add(parada);
                        break;
                    case 4:
                        Usuario usuario = new Usuario(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                        usuarios.add(usuario);
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error No se encuentran los Datos: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Gestion{" + 
                "paradas=" + paradas + "\n" +
                ", rutas=" + rutas + "\n" +
                ", usuarios=" + usuarios + 
                '}';
    }
}
