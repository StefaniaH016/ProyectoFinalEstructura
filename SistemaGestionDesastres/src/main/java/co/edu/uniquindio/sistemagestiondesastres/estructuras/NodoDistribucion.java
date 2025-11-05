package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.ArrayList;
import java.util.List;

public class NodoDistribucion {
    private String zona;
    private String recurso;
    private int cantidad;
    private List<NodoDistribucion> hijos;

    public NodoDistribucion(String zona, String recurso, int cantidad) {
        this.zona = zona;
        this.recurso = recurso;
        this.cantidad = cantidad;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoDistribucion hijo) {
        hijos.add(hijo);
    }

    public String getZona() {
        return zona;
    }

    public String getRecurso() {
        return recurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public List<NodoDistribucion> getHijos() {
        return hijos;
    }

    public void mostrarArbol(String prefijo) {
        System.out.println(prefijo + "- Zona: " + zona + ", Recurso: " + recurso + ", Cantidad: " + cantidad);
        for (NodoDistribucion hijo : hijos) {
            hijo.mostrarArbol(prefijo + "   ");
        }
    }
}
