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
            System.out.println("Error: No se pudieron guardar los datos. " + e.getMessage());
        }
    }

    public void cargarDatos(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        System.out.println("Intentando cargar datos desde: " + archivo.getAbsolutePath());

        if (!archivo.exists()) {
            System.out.println("Error: El archivo no existe en la ruta especificada.");
            return;
        }

        try (Scanner sc = new Scanner(archivo)) {
            Parada currentParada = null;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue; // Salta líneas vacías
                }
                String[] data = linea.split(",");
                if (data.length == 0) {
                    continue; // Salta líneas vacías
                }
                try {
                    switch (data.length) {
                        case 3:
                            // Formato para Parada
                            currentParada = Parada.leer(new Scanner(linea));
                            regParada(currentParada); // Cambiado a regParada para agregar a la lista
                            break;
                        case 4:
                            // Formato para Usuario
                            Usuario usuario = Usuario.leer(new Scanner(linea));
                            regUsuario(usuario); // Cambiado a regUsuario para agregar a la lista
                            break;
                        case 2:
                            // Formato para Ruta
                            Ruta ruta = Ruta.leer(new Scanner(linea));
                            regRuta(ruta); // Cambiado a regRuta para agregar a la lista
                            break;
                        case 5:
                            // Formato para Horario, relacionado con una parada existente
                            if (currentParada != null) {
                                Horario horario = Horario.leer(new Scanner(linea));
                                currentParada.setHorario(horario);
                            } else {
                                System.out.println("Error: No hay una parada actual para asignar el horario.");
                            }
                            break;
                        default:
                            System.out.println("Formato de línea desconocido: " + linea);
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato de número en la línea: " + linea + ". " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error al procesar la línea: " + linea + ". " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo.");
            e.printStackTrace();
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
