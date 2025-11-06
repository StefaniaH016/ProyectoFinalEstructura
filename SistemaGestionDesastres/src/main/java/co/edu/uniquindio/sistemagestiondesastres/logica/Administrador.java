package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.List;
import java.util.Map;

import co.edu.uniquindio.sistemagestiondesastres.estructuras.ArbolDistribucion;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.GrafoDirigido;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.NodoGrafo;
import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoRecurso;

public class Administrador extends Usuario {
    private GrafoDirigido grafo;
    private ArbolDistribucion arbolRecursos;

    public Administrador(String id, String nombre, String usuario, String contrasena, GrafoDirigido grafo, ArbolDistribucion arbol) {
        super(id, nombre, usuario, contrasena, "Administrador");
        this.grafo = grafo;
        this.arbolRecursos = arbol;
    }

    // ----- Métodos principales -----

    public void registrarUbicacion(String nombre, int nivelEmergencia, int personasAfectadas) {
        grafo.agregarNodo(nombre, nivelEmergencia, personasAfectadas);
        System.out.println("Ubicación registrada: " + nombre);
    }

    public void definirRuta(String origen, String destino, double distancia) {
        grafo.agregarRuta(origen, destino, distancia);
        System.out.println("Ruta definida: " + origen + " → " + destino + " (" + distancia + " km)");
    }

    public void gestionarRecurso(String nombreRecurso, int cantidad, String id, TipoRecurso tipoRecurso, String ubicacion, int prioridad) {
        arbolRecursos.insertarRecurso(nombreRecurso, cantidad, id, tipoRecurso, ubicacion, prioridad);
        System.out.println("Recurso agregado al sistema: " + nombreRecurso + " (" + cantidad + ")");
    }

    public void asignarRecursoAZona(String nombreRecurso, String zonaDestino, int cantidad) {
        NodoGrafo nodo = grafo.obtenerNodo(zonaDestino);
        if (nodo != null) {
            nodo.agregarRecursoNecesario(nombreRecurso, cantidad);
            System.out.println("Recurso " + nombreRecurso + " asignado a " + zonaDestino);
        } else {
            System.out.println("Zona no encontrada: " + zonaDestino);
        }
    }

    public void generarInformeEstado() {
        System.out.println("\n=== INFORME GENERAL DE ZONAS ===");
        for (NodoGrafo n : grafo.obtenerNodos()) {
            System.out.println("Zona: " + n.getNombre() +
                    " | Nivel emergencia: " + n.getNivelEmergencia() +
                    " | Personas afectadas: " + n.getPersonasAfectadas());
        }
        System.out.println("\n=== RECURSOS DISPONIBLES ===");
        arbolRecursos.mostrarDistribucion();
    }

    public void mostrarMenuPrincipal() {
        System.out.println("Bienvenido Administrador " + nombre);
        System.out.println("Opciones:");
        System.out.println("1. Registrar ubicación");
        System.out.println("2. Definir ruta");
        System.out.println("3. Gestionar recursos");
        System.out.println("4. Asignar recurso a zona");
        System.out.println("5. Generar informe");
    }
}
