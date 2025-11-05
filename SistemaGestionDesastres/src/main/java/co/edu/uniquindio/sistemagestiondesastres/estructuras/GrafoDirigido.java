package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.*;

public class GrafoDirigido {
    private Map<String, NodoGrafo> nodos;

    public GrafoDirigido() {
        this.nodos = new HashMap<>();
    }

    public void agregarNodo(String nombre) {
        if (!nodos.containsKey(nombre)) {
            nodos.put(nombre, new NodoGrafo(nombre));
        }
    }

    public void agregarArista(String origen, String destino, int peso) {
        NodoGrafo nodoOrigen = nodos.get(origen);
        NodoGrafo nodoDestino = nodos.get(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarAdyacente(nodoDestino, peso);
        }
    }

    public List<String> obtenerRutaMasCorta(String inicio, String fin) {
        Map<NodoGrafo, Integer> distancias = new HashMap<>();
        Map<NodoGrafo, NodoGrafo> previos = new HashMap<>();
        PriorityQueue<NodoGrafo> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        NodoGrafo nodoInicio = nodos.get(inicio);
        NodoGrafo nodoFin = nodos.get(fin);

        for (NodoGrafo n : nodos.values()) {
            distancias.put(n, Integer.MAX_VALUE);
        }
        distancias.put(nodoInicio, 0);
        cola.add(nodoInicio);

        while (!cola.isEmpty()) {
            NodoGrafo actual = cola.poll();

            if (actual == nodoFin) break;

            for (Map.Entry<NodoGrafo, Integer> entry : actual.getAdyacentes().entrySet()) {
                NodoGrafo vecino = entry.getKey();
                int nuevoDist = distancias.get(actual) + entry.getValue();

                if (nuevoDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevoDist);
                    previos.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        List<String> ruta = new LinkedList<>();
        for (NodoGrafo at = nodoFin; at != null; at = previos.get(at)) {
            ruta.add(0, at.getNombre());
        }
        return ruta;
    }

    public void mostrarGrafo() {
        for (NodoGrafo nodo : nodos.values()) {
            System.out.print(nodo.getNombre() + " -> ");
            for (NodoGrafo ady : nodo.getAdyacentes().keySet()) {
                System.out.print(ady.getNombre() + "(" + nodo.getAdyacentes().get(ady) + ") ");
            }
            System.out.println();
        }
    }
}
