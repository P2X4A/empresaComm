package co.edu.unbosque.view;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    private final Scanner consola = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("""
                ===========================
                ====== Menú PRINCIPAL =====
                ===========================
                1. Configurar líneas (números y minutos del plan)
                2. Registrar llamada
                3. Mostrar resumen de líneas
                4. Mostrar detalles de una línea
                0. Salir
                """);
        return Integer.parseInt(consola.nextLine());
    }

    public int pedirNumeroLinea() {
        System.out.println("Ingrese el número de la línea (ej. 3101234567):");
        return Integer.parseInt(consola.nextLine());
    }

    public int pedirMinutosPlan() {
        System.out.println("Ingrese los minutos del plan contratado (entero):");
        return Integer.parseInt(consola.nextLine());
    }

    public int pedirOpcionLineaExistente() {
        System.out.println("Seleccione la línea (1 = Local, 2 = Nacional, 3 = Corporativa):");
        return Integer.parseInt(consola.nextLine());
    }

    public int mostrarMenuTipoLlamada() {
        System.out.println("""
                Seleccione tipo de llamada:
                1. LOCAL
                2. LARGA_DISTANCIA
                3. CELULAR
                """);
        return Integer.parseInt(consola.nextLine());
    }

    public int pedirDuracionEnMinutos() {
        System.out.println("Ingrese la duración de la llamada en minutos (entero):");
        return Integer.parseInt(consola.nextLine());
    }

    public Instant pedirInstante(String textoInstruccion) {
        System.out.println(textoInstruccion + " (formato ISO-8601, ej. 2023-08-31T14:30:00Z, o 'now'):");
        while (true) {
            String linea = consola.nextLine().trim();
            if (linea.equalsIgnoreCase("now")) {
                return Instant.now();
            }
            try {
                return Instant.parse(linea);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Ingrese ISO-8601 o 'now':");
            }
        }
    }

    public void mostrarResumenLineas(String resumen) {
        System.out.println("=== Resumen de líneas ===");
        System.out.println(resumen);
    }

    public void mostrarDetalleLinea(String detalle) {
        System.out.println("=== Detalle línea ===");
        System.out.println(detalle);
    }

    public void mostrarRegistroLlamada(String resumen) {
        System.out.println("=== Registro llamada ===");
        System.out.println(resumen);
    }

    public String pedirCadena(String mensaje) {
        System.out.println(mensaje);
        return consola.nextLine();
    }

    public int pedirEntero(String mensaje) {
        System.out.println(mensaje);
        return Integer.parseInt(consola.nextLine());
    }

    public boolean pedirBoolean(String mensaje) {
        System.out.println(mensaje + " (true/false):");
        return Boolean.parseBoolean(consola.nextLine());
    }
}
