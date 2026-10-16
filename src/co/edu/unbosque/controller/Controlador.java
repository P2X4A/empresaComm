package co.edu.unbosque.controller;

import co.edu.unbosque.view.Consola;
import co.edu.unbosque.model.LogiComm;

public class Controlador {

    private Consola consola;
    private LogiComm logi;

    public Controlador() {
        this.consola = new Consola();
        this.logi = new LogiComm();
    }

    public void ejecutar() {
        boolean continuar = true;
        while (continuar) {
            int opcion = consola.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> configurarLineas();
                case 2 -> registrarLlamada();
                case 3 -> mostrarResumen();
                case 4 -> mostrarDetalleLinea();
                case 0 -> {
                    consola.mostrarMensaje("Saliendo...");
                    continuar = false;
                }
                default -> consola.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void configurarLineas() {
        int numeroLocal = consola.pedirEntero("Número de línea LOCAL (ej. 3101234567):");
        int minutosLocal = consola.pedirEntero("Minutos del plan (LOCAL):");

        int numeroNacional = consola.pedirEntero("Número de línea NACIONAL (ej. 3201234567):");
        int minutosNacional = consola.pedirEntero("Minutos del plan (NACIONAL):");

        int numeroCorp = consola.pedirEntero("Número de línea CORPORATIVA (ej. 3301234567):");
        int minutosCorp = consola.pedirEntero("Minutos del plan (CORPORATIVA):");

        logi.configurarLineas(numeroLocal, minutosLocal, numeroNacional, minutosNacional, numeroCorp, minutosCorp);
        consola.mostrarMensaje("Líneas configuradas.");
    }

    private void registrarLlamada() {
        int opcionLinea = consola.pedirEntero("Seleccione la línea (1 = Local, 2 = Nacional, 3 = Corporativa):");
        int tipo = consola.mostrarMenuTipoLlamada();
        int duracion = consola.pedirDuracionEnMinutos();

        String resultado = logi.registrarLlamadaPorDuracion(opcionLinea, tipo, duracion);
        if (resultado == null || resultado.isEmpty()) {
            consola.mostrarMensaje("Registro fallido.");
        } else {
            consola.mostrarRegistroLlamada(resultado); // muestra datos en crudo devueltos por LogiComm
        }
    }

    private void mostrarResumen() {
        String resumen = logi.resumenLineas();
        if (resumen == null || resumen.isEmpty()) {
            consola.mostrarMensaje("No hay líneas configuradas.");
        } else {
            consola.mostrarResumenLineas(resumen);
        }
    }

    private void mostrarDetalleLinea() {
        int opcionLinea = consola.pedirEntero("Seleccione la línea para ver detalle (1 = Local, 2 = Nacional, 3 = Corporativa):");
        String detalle = logi.detalleLineaPorOpcion(opcionLinea);
        if (detalle == null || detalle.isEmpty()) {
            consola.mostrarMensaje("Detalle no disponible para la opción seleccionada.");
        } else {
            consola.mostrarDetalleLinea(detalle); // muestra datos en crudo devueltos por LogiComm
        }
    }
}
