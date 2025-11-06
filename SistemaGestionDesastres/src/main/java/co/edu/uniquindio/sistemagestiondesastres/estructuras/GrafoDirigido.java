package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.*;

public class GrafoDirigido {
    private Map<String, NodoGrafo> nodos;

    public GrafoDirigido() {
        this.nodos = new HashMap<>();
    }

    // ----- Gestión básica -----

    public void agregarNodo(String nombre, int nivelEmergencia, int personasAfectadas) {
        if (!nodos.containsKey(nombre)) {
            nodos.put(nombre, new NodoGrafo(nombre, nivelEmergencia, personasAfectadas));
        }
    }

    public void eliminarNodo(String nombre) {
        NodoGrafo nodo = nodos.remove(nombre);
        if (nodo != null) {
            for (NodoGrafo n : nodos.values()) {
                n.eliminarAdyacente(nodo);
            }
        }
    }

    public void agregarRuta(String origen, String destino, double distancia) {
        NodoGrafo nodoOrigen = nodos.get(origen);
        NodoGrafo nodoDestino = nodos.get(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarAdyacente(nodoDestino, distancia);
        }
    }

    public NodoGrafo obtenerNodo(String nombre) {
        return nodos.get(nombre);
    }

    public Collection<NodoGrafo> obtenerNodos() {
        return nodos.values();
    }

    public void mostrarGrafo() {
        System.out.println("=== Grafo de Rutas ===");
        for (NodoGrafo nodo : nodos.values()) {
            System.out.println(nodo);
        }
    }

    // ----- Algoritmo de Dijkstra -----

    public Map<NodoGrafo, Double> calcularRutasMasCortas(String origenNombre) {
        NodoGrafo origen = nodos.get(origenNombre);
        if (origen == null) return Collections.emptyMap();

        Map<NodoGrafo, Double> distancias = new HashMap<>();
        Set<NodoGrafo> visitados = new HashSet<>();
        PriorityQueue<NodoGrafo> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        for (NodoGrafo nodo : nodos.values()) {
            distancias.put(nodo, Double.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0.0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            NodoGrafo actual = cola.poll();
            if (!visitados.add(actual)) continue;

            for (Map.Entry<NodoGrafo, Double> ady : actual.getAdyacentes().entrySet()) {
                NodoGrafo vecino = ady.getKey();
                double nuevaDist = distancias.get(actual) + ady.getValue();
                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    cola.add(vecino);
                }
            }
        }
        return distancias;
    }

    public List<NodoGrafo> obtenerRutaMasCorta(String origen, String destino) {
        Map<NodoGrafo, Double> distancias = new HashMap<>();
        Map<NodoGrafo, NodoGrafo> predecesores = new HashMap<>();
        PriorityQueue<NodoGrafo> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<NodoGrafo> visitados = new HashSet<>();

        NodoGrafo nodoOrigen = nodos.get(origen);
        NodoGrafo nodoDestino = nodos.get(destino);

        if (nodoOrigen == null || nodoDestino == null) return Collections.emptyList();

        for (NodoGrafo n : nodos.values()) {
            distancias.put(n, Double.POSITIVE_INFINITY);
        }
        distancias.put(nodoOrigen, 0.0);
        cola.add(nodoOrigen);

        while (!cola.isEmpty()) {
            NodoGrafo actual = cola.poll();
            if (!visitados.add(actual)) continue;

            for (Map.Entry<NodoGrafo, Double> ady : actual.getAdyacentes().entrySet()) {
                NodoGrafo vecino = ady.getKey();
                double nuevaDist = distancias.get(actual) + ady.getValue();
                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    predecesores.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        List<NodoGrafo> ruta = new LinkedList<>();
        NodoGrafo actual = nodoDestino;
        while (actual != null) {
            ruta.add(0, actual);
            actual = predecesores.get(actual);
        }
        return ruta;
    }
}
