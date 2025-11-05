package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.estructuras.ArbolDistribucion;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.ColaPrioridadEvacuaciones;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.GrafoDirigido;
import impl.org.controlsfx.collections.MappingChange.Map;
import co.edu.uniquindio.estructuras.nodos.NodoDistribucion;

import java.util.LinkedList;
import java.util.*;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public class SistemaGestionDesastres {

    // 🔹 Patrón Singleton
    private static SistemaGestionDesastres instance;

    // 🔹 Estructuras principales
    private GrafoDirigido grafoRutas;
    private ArbolDistribucion arbolRecursos;
    private ColaPrioridadEvacuaciones colaEvacuaciones;

    // 🔹 Almacenes de datos
    private Map<String, ZonaAfectada> zonasAfectadas;
    private Map<String, Recurso> recursosDisponibles;
    private List<String> reportes;

    // -------------------------------
    // CONSTRUCTOR PRIVADO
    // -------------------------------
    private SistemaGestionDesastres() {
        this.grafoRutas = new GrafoDirigido();
        this.colaEvacuaciones = new ColaPrioridadEvacuaciones();
        this.zonasAfectadas = new HashMap<>();
        this.recursosDisponibles = new HashMap<>();
        this.reportes = new ArrayList<>();

        // Nodo raíz del árbol de distribución
        NodoDistribucion raiz = new NodoDistribucion("Centro Nacional", "Recursos generales", 0);
        this.arbolRecursos = new ArbolDistribucion(raiz);
    }

    // -------------------------------
    // SINGLETON
    // -------------------------------
    public static SistemaGestionDesastres getInstance() {
        if (instance == null) {
            instance = new SistemaGestionDesastres();
        }
        return instance;
    }

    // -------------------------------
    // MÉTODOS DE GESTIÓN DE ZONAS
    // -------------------------------
    public void registrarZona(String nombre, int nivelRiesgo, int personasAfectadas) {
        if (!zonasAfectadas.containsKey(nombre)) {
            ZonaAfectada zona = new ZonaAfectada(nombre, nivelRiesgo, personasAfectadas);
            zonasAfectadas.put(nombre, zona);
            grafoRutas.agregarNodo(nombre);
            generarReporte("Zona registrada: " + nombre);
        }
    }

    public void actualizarEstadoZona(String nombre, int nuevoNivelRiesgo, int personasAfectadas) {
        ZonaAfectada zona = zonasAfectadas.get(nombre);
        if (zona != null) {
            zona.setNivelRiesgo(nuevoNivelRiesgo);
            zona.setPersonasAfectadas(personasAfectadas);
            generarReporte("Zona actualizada: " + nombre + " (riesgo " + nuevoNivelRiesgo + ")");
        }
    }

    public ZonaAfectada obtenerZona(String nombre) {
        return zonasAfectadas.get(nombre);
    }

    public Collection<ZonaAfectada> listarZonas() {
        return zonasAfectadas.values();
    }

    // -------------------------------
    // MÉTODOS DE GESTIÓN DE RECURSOS
    // -------------------------------
    public void registrarRecurso(String nombre, String tipo, int cantidad) {
        Recurso recurso = new Recurso(nombre, tipo, cantidad);
        recursosDisponibles.put(nombre, recurso);
        generarReporte("Recurso registrado: " + nombre + " (" + tipo + ")");
    }

    public void asignarRecursoAZona(String nombreZona, String nombreRecurso, int cantidad) {
        ZonaAfectada zona = zonasAfectadas.get(nombreZona);
        Recurso recurso = recursosDisponibles.get(nombreRecurso);

        if (zona != null && recurso != null && recurso.getCantidad() >= cantidad) {
            recurso.setCantidad(recurso.getCantidad() - cantidad);
            zona.agregarRecursoAsignado(new Recurso(nombreRecurso, recurso.getTipo(), cantidad));

            NodoDistribucion nodo = new NodoDistribucion(nombreZona, nombreRecurso, cantidad);
            arbolRecursos.agregarNodo(arbolRecursos.getRaiz(), nodo);

            generarReporte("Recurso asignado: " + cantidad + " de " + nombreRecurso + " a " + nombreZona);
        } else {
            generarReporte("Error asignando recurso: " + nombreRecurso + " a " + nombreZona);
        }
    }

    public Collection<Recurso> listarRecursos() {
        return recursosDisponibles.values();
    }

    // -------------------------------
    // GESTIÓN DE RUTAS
    // -------------------------------
    public void definirRuta(String origen, String destino, int distancia) {
        grafoRutas.agregarArista(origen, destino, distancia);
        generarReporte("Ruta definida: " + origen + " → " + destino + " (" + distancia + " km)");
    }

    public List<String> calcularRutaMasCorta(String origen, String destino) {
        return grafoRutas.obtenerRutaMasCorta(origen, destino);
    }

    // -------------------------------
    // EVACUACIONES
    // -------------------------------
    public void registrarEvacuacion(String zona, int prioridad, int personas) {
        colaEvacuaciones.agregarZona(zona, prioridad, personas);
        generarReporte("Evacuación registrada para: " + zona);
    }

    public void procesarEvacuaciones() {
        while (!colaEvacuaciones.estaVacia()) {
            String evacuada = colaEvacuaciones.atenderZona();
            generarReporte("Evacuación completada en: " + evacuada);
        }
    }

    // -------------------------------
    // REPORTES Y CONSULTAS
    // -------------------------------
    public void generarReporte(String mensaje) {
        String fecha = new Date().toString();
        reportes.add("[" + fecha + "] " + mensaje);
    }

    public void mostrarReportes() {
        System.out.println("📋 HISTORIAL DE REPORTES:");
        for (String r : reportes) {
            System.out.println(" - " + r);
        }
    }

    public void mostrarEstadoGeneral() {
        System.out.println("\n🌍 ESTADO GENERAL DEL SISTEMA:");
        System.out.println("Zonas registradas: " + zonasAfectadas.size());
        System.out.println("Recursos disponibles: " + recursosDisponibles.size());
        System.out.println("Evacuaciones pendientes: " + (colaEvacuaciones.estaVacia() ? 0 : "Sí"));
        System.out.println("Rutas totales: " + grafoRutas.obtenerNumeroDeAristas());
    }

    // -------------------------------
    // GETTERS
    // -------------------------------
    public GrafoDirigido getGrafoRutas() { return grafoRutas; }
    public ArbolDistribucion getArbolRecursos() { return arbolRecursos; }
    public ColaPrioridadEvacuaciones getColaEvacuaciones() { return colaEvacuaciones; }
    public Map<String, ZonaAfectada> getZonasAfectadas() { return zonasAfectadas; }
    public Map<String, Recurso> getRecursosDisponibles() { return recursosDisponibles; }
}
