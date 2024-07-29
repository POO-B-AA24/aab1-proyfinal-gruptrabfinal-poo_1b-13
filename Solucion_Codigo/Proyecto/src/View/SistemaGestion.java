package View;
import Controller.*;
import Model.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class SistemaGestion {
    public static Gestion gestion = new Gestion();
    public static final String datos = "src/Model/datos.txt";
    public static void main(String[] args) {
        gestion.cargarDatos(datos);
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("*********************************************************");
            System.out.println("* |Sistema de Gestión de Recorridos de Autobuses UTPL|  *");
            System.out.println("*********************************************************");
            System.out.println("*                 Sistema de Registro                   *");
            System.out.println("*********************************************************");
            System.out.println("*   1. Registrar Parada                                 *");
            System.out.println("*   2. Registrar Horario                                *");
            System.out.println("*   3. Registrar Ruta                                   *");
            System.out.println("*   4. Registrar Usuario                                *");
            System.out.println("*********************************************************");
            System.out.println("*                  Sistema de Consulta                  *");
            System.out.println("*********************************************************");
            System.out.println("*   5. Consultar Paradas                                *");
            System.out.println("*   6. Consultar Rutas                                  *");
            System.out.println("*   7. Consultar Usuarios                               *");
            System.out.println("*********************************************************");
            System.out.println("*   8. Guardar y Salir                                  *");
            System.out.println("*********************************************************");
            System.out.println("Seleccione una opción: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    regParada(sc);
                    break;
                case 2:
                    regHorario(sc);
                    break;
                case 3:
                    regRuta(sc);
                    break;
                case 4:
                    regUsuario(sc);
                    break;
                case 5:
                    consultarParadas();
                    break;
                case 6:
                    consultarRutas();
                    break;
                case 7:
                    consultarUsuarios();
                    break;
                case 8:
                    gestion.guardarDatos(datos);
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }
    public static void regParada(Scanner sc) {
        String nombre,ubicacion;
        int Npa;
        System.out.print("Nombre de la parada: ");
        nombre = sc.nextLine();
        System.out.print("Número de parada: ");
        Npa = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre de la ubicación: ");
        ubicacion = sc.nextLine();
        
        Parada parada = new Parada(nombre, Npa, ubicacion);
        gestion.regParada(parada);
        System.out.println("Guardado Correctamente.");
    }

    public static void regHorario(Scanner sc) {
        int Npa;
        System.out.print("Número de parada: ");
        Npa = sc.nextInt();
        sc.nextLine();
        System.out.print("Hora de salida (Horas:Minutos): ");
        LocalTime horaSalida = LocalTime.parse(sc.nextLine());
        System.out.print("Hora de entrada (Horas:Minutos): ");
        LocalTime horaEntrada = LocalTime.parse(sc.nextLine());
        Horario horario = new Horario(Npa, horaSalida, horaEntrada);
        for (Parada parada : gestion.consultaParada()) {
            if (parada.Npa() == Npa) {
                parada.setHorario(horario);
                break;
            }
        }
        System.out.println("Guardado Correctamente.");
    }
    public static void regRuta(Scanner sc) {
        int Npa;
        String nombre;
        System.out.print("Número de ruta: ");
        Npa = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre de la ruta: ");
        nombre = sc.nextLine();
        Ruta ruta = new Ruta(Npa, nombre);
        gestion.regRuta(ruta);
        System.out.println("Guardado Correctamente.");
    }
    public static void regUsuario(Scanner sc) {
        int Npa;
        String nombre,email,tipoUsuario;
        System.out.print("Número de usuario: ");
        Npa = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Tipo de usuario: ");
        tipoUsuario = sc.nextLine();
        Usuario usuario = new Usuario(Npa, nombre, email, tipoUsuario);
        gestion.regUsuario(usuario);
        System.out.println("Guardado Correctamente.");
    }
    public static void consultarParadas() {
        List<Parada> paradas = gestion.consultaParada();
        if (paradas.isEmpty()) {
            System.out.println("No se encuentran Paradas registradas");
        } else {
            paradas.forEach(System.out::println);
        }
    }
    public static void consultarRutas() {
        List<Ruta> rutas = gestion.consultaRuta();
        if (rutas.isEmpty()) {
            System.out.println("No se encuentran Rutas registradas");
        } else {
            rutas.forEach(System.out::println);
        }
    }
    public static void consultarUsuarios() {
        List<Usuario> usuarios = gestion.consultaUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No se encuentran Usuarios Registrados");
        } else {
            usuarios.forEach(System.out::println);
        }
    }
}
