package Model;
import Controller.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
public class cargarDatos {
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
                            Parada parada = Parada.leer(new Scanner(linea));
                            currentParada = parada;
                            // Añade la parada a la lista si la instancia es válida
                            // Cambia esta línea si tienes una instancia de Gestion para agregar paradas
                            // gestion.regParada(parada);
                            break;
                        case 4:
                            // Formato para Usuario
                            Usuario usuario = Usuario.leer(new Scanner(linea));
                            // Añade el usuario a la lista si la instancia es válida
                            // Cambia esta línea si tienes una instancia de Gestion para agregar usuarios
                            // gestion.regUsuario(usuario);
                            break;
                        case 2:
                            // Formato para Ruta
                            Ruta ruta = Ruta.leer(new Scanner(linea));
                            // Añade la ruta a la lista si la instancia es válida
                            // Cambia esta línea si tienes una instancia de Gestion para agregar rutas
                            // gestion.regRuta(ruta);
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
}
