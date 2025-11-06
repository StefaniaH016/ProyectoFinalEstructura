package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import co.edu.uniquindio.sistemagestiondesastres.logica.Recurso;
import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoRecurso;

import java.util.HashMap;
import java.util.Map;

public class ArbolDistribucion {

    private NodoDistribucion raiz;

    // --- Constructor ---
    public ArbolDistribucion(String idZonaRaiz, String nombreRaiz) {
        this.raiz = new NodoDistribucion(idZonaRaiz, nombreRaiz, new HashMap<>());
    }

    public ArbolDistribucion() {}

    // --- Getters y Setters ---
    public NodoDistribucion getRaiz() {
        return raiz;
    }

    // --- Métodos funcionales ---

    /** Agrega una zona hija a una zona padre existente */
    public boolean agregarZona(String idPadre, String idZona, String nombreZona, Map<String, Integer> recursos) {
        NodoDistribucion padre = buscarZona(idPadre);
        if (padre != null) {
            NodoDistribucion nueva = new NodoDistribucion(idZona, nombreZona, recursos);
            padre.agregarSubZona(nueva);
            return true;
        }
        return false;
    }

    /** Busca una zona en todo el árbol */
    public NodoDistribucion buscarZona(String id) {
        if (raiz == null) return null;
        return raiz.buscarZona(id);
    }

    /** Asigna recursos a una zona específica */
    public boolean asignarRecursos(String idZona, Map<String, Integer> nuevosRecursos) {
        NodoDistribucion zona = buscarZona(idZona);
        if (zona != null) {
            Map<String, Integer> actuales = zona.getRecursosAsignados();
            for (Map.Entry<String, Integer> entry : nuevosRecursos.entrySet()) {
                actuales.put(entry.getKey(), actuales.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
            zona.setRecursosAsignados(actuales);
            return true;
        }
        return false;
    }

    /** Elimina una zona por ID */
    public boolean eliminarZona(String idZona) {
        if (raiz.getIdZona().equalsIgnoreCase(idZona)) {
            System.out.println("No se puede eliminar la raíz del árbol.");
            return false;
        }
        return eliminarZonaRecursivo(raiz, idZona);
    }

    private boolean eliminarZonaRecursivo(NodoDistribucion actual, String idZona) {
        for (NodoDistribucion sub : actual.getSubZonas()) {
            if (sub.getIdZona().equalsIgnoreCase(idZona)) {
                return actual.eliminarSubZona(idZona);
            } else {
                if (eliminarZonaRecursivo(sub, idZona)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Simula una distribución total de recursos hacia todas las subzonas */
    public void simularDistribucion() {
        if (raiz == null) return;
        System.out.println("Simulación de distribución de recursos:");
        raiz.imprimirEstructura("");
    }

    /** Cuenta la cantidad total de zonas (nodos) en el árbol */
    public int contarZonas() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoDistribucion nodo) {
        if (nodo == null) return 0;
        int total = 1; // cuenta la actual
        for (NodoDistribucion sub : nodo.getSubZonas()) {
            total += contarRecursivo(sub);
        }
        return total;
    }



    /**
     * Inserta un recurso directamente en la raíz (uso común desde la clase principal)
     */
    public void insertarRecurso(String nombreRecurso, int cantidad, String id, TipoRecurso tipoRecurso, String ubicacion, int prioridad ) {
        Recurso recurso = new Recurso(id, tipoRecurso,cantidad, ubicacion, prioridad, nombreRecurso);
        insertarRecurso(recurso);
    }

    /**
     * Inserta o actualiza un recurso dentro de la zona principal (raíz)
     * o dentro de una zona específica si se indica su ID.
     */
    public void insertarRecurso(Recurso recurso) {
        if (raiz == null) {
            System.out.println("Error: el árbol de distribución no tiene raíz definida.");
            return;
        }

        // Buscar la zona destino
        NodoDistribucion zonaDestino = raiz.buscarZona(recurso.getId());

        if (zonaDestino == null) {
            System.out.println("No se encontró la zona con ID: " + recurso.getId());
            return;
        }

        // Obtener el mapa de recursos actuales
        Map<String, Integer> recursos = zonaDestino.getRecursosAsignados();

        // Si el recurso ya existe, se suma la cantidad. Si no, se agrega nuevo.
        if (recursos.containsKey(recurso.getNombre())) {
            int nuevaCantidad = recursos.get(recurso.getNombre()) + recurso.getCantidad();
            recursos.put(recurso.getNombre(), nuevaCantidad);
            System.out.println("Cantidad de recurso '" + recurso.getNombre() + "' actualizada en zona " + zonaDestino.getNombreZona());
        } else {
            recursos.put(recurso.getNombre(), recurso.getCantidad());
            System.out.println("Recurso '" + recurso.getNombre() + "' agregado a la zona " + zonaDestino.getNombreZona());
        }
    }

    public void mostrarDistribucion() {
        if (raiz != null) {
            System.out.println("=== ESTRUCTURA DE DISTRIBUCIÓN DE RECURSOS ===");
            raiz.imprimirEstructura("");
        } else {
            System.out.println("No hay estructura de distribución definida.");
        }
    }

    /**
     * Elimina un recurso (por nombre) de todas las zonas del árbol.
     * Retorna true si se eliminó al menos en una zona, false si no se encontró.
     */
    public boolean eliminarRecurso(String nombreRecurso) {
        if (raiz == null || nombreRecurso == null || nombreRecurso.isEmpty()) {
            return false;
        }
        boolean eliminado = eliminarRecursoRecursivo(raiz, nombreRecurso);
        if (eliminado) {
            System.out.println("🗑️ Recurso '" + nombreRecurso + "' eliminado del árbol de distribución (todas las zonas).");
        } else {
            System.out.println("ℹ️ Recurso '" + nombreRecurso + "' no encontrado en el árbol de distribución.");
        }
        return eliminado;
    }

    private boolean eliminarRecursoRecursivo(NodoDistribucion nodo, String nombreRecurso) {
        boolean eliminadoEnEsteNodo = false;
        if (nodo == null) return false;

        Map<String, Integer> recursos = nodo.getRecursosAsignados();
        if (recursos != null && recursos.containsKey(nombreRecurso)) {
            recursos.remove(nombreRecurso);
            eliminadoEnEsteNodo = true;
        }

        // Recurse into children
        for (NodoDistribucion sub : nodo.getSubZonas()) {
            boolean eliminadoEnSub = eliminarRecursoRecursivo(sub, nombreRecurso);
            if (eliminadoEnSub) eliminadoEnEsteNodo = true;
        }

        return eliminadoEnEsteNodo;
    }




}