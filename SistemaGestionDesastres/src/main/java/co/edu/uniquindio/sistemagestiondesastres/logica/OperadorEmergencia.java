package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.estructuras.NodoGrafo;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.*;

import java.util.List;

public class OperadorEmergencia extends Usuario {
    private ColaPrioridadEvacuaciones colaEvacuaciones;
    private GrafoDirigido grafo;

    public OperadorEmergencia(String id, String nombre, String usuario, String contrasena,
                              GrafoDirigido grafo, ColaPrioridadEvacuaciones colaEvacuaciones) {
        super(id, nombre, usuario, contrasena, "Operador de Emergencia");
        this.grafo = grafo;
        this.colaEvacuaciones = colaEvacuaciones;
    }

    // ----- Métodos principales -----

    public void registrarZonaEvacuacion(String nombreZona) {
        NodoGrafo nodo = grafo.obtenerNodo(nombreZona);
        if (nodo != null) {
            colaEvacuaciones.agregarZonaEvacuacion(nodo);
            System.out.println("Zona agregada a la cola de evacuación: " + nombreZona);
        } else {
            System.out.println("Zona no encontrada: " + nombreZona);
        }
    }

    public void procesarEvacuaciones() {
        System.out.println("\n=== Procesando Evacuaciones ===");
        while (!colaEvacuaciones.estaVacia()) {
            ZonaEvacuacion siguiente = colaEvacuaciones.verSiguienteZona();
            System.out.println("Evacuando zona: " + siguiente.getNombreZona() +
                    " | Nivel emergencia: " + siguiente.getNivelEmergencia());
        }
    }

    public void mostrarRutasDisponibles() {
        System.out.println("\n=== Rutas disponibles ===");
        grafo.mostrarGrafo();
    }

    public void mostrarRutaMasCorta(String origen, String destino) {
        List<NodoGrafo> ruta = grafo.obtenerRutaMasCorta(origen, destino);
        System.out.println("Ruta más corta " + origen + " → " + destino + ":");
        for (NodoGrafo n : ruta) {
            System.out.print(n.getNombre() + " -> ");
        }
        System.out.println();
    }
    public void mostrarMenuPrincipal() {
        System.out.println("Bienvenido Operador de Emergencia " + nombre);
        System.out.println("Opciones:");
        System.out.println("1. Registrar zona para evacuación");
        System.out.println("2. Procesar evacuaciones");
        System.out.println("3. Ver rutas disponibles");
        System.out.println("4. Calcular ruta más corta");
    }
}
