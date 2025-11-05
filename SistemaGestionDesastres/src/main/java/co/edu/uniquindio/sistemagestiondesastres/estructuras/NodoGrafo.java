package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.HashMap;
import java.util.Map;

public class NodoGrafo {
    private String nombre;
    private Map<NodoGrafo, Integer> adyacentes;

    public NodoGrafo(String nombre) {
        this.nombre = nombre;
        this.adyacentes = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Map<NodoGrafo, Integer> getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(NodoGrafo destino, int peso) {
        adyacentes.put(destino, peso);
    }

    @Override
    public String toString() {
        return nombre;
    }
}