package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodoDistribucion {
    private String idZona;
    private String nombreZona;
    private Map<String, Integer> recursosAsignados; // clave: tipo de recurso, valor: cantidad
    private List<NodoDistribucion> subZonas;

    // --- Constructor ---
    public NodoDistribucion(String idZona, String nombreZona, Map<String, Integer> recursosAsignados) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.recursosAsignados = recursosAsignados;
        this.subZonas = new ArrayList<>();
    }

    // --- Getters y Setters ---
    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public Map<String, Integer> getRecursosAsignados() {
        return recursosAsignados;
    }

    public void setRecursosAsignados(Map<String, Integer> recursosAsignados) {
        this.recursosAsignados = recursosAsignados;
    }

    public List<NodoDistribucion> getSubZonas() {
        return subZonas;
    }

    // --- Métodos funcionales ---

    /** Agrega una subzona (hijo) a la zona actual */
    public void agregarSubZona(NodoDistribucion subZona) {
        subZonas.add(subZona);
    }

    /** Elimina una subzona según su ID */
    public boolean eliminarSubZona(String id) {
        return subZonas.removeIf(subZona -> subZona.getIdZona().equalsIgnoreCase(id));
    }

    /** Busca una zona por ID dentro del árbol (búsqueda recursiva) */
    public NodoDistribucion buscarZona(String id) {
        if (this.idZona.equalsIgnoreCase(id)) {
            return this;
        }
        for (NodoDistribucion sub : subZonas) {
            NodoDistribucion encontrada = sub.buscarZona(id);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }

    /** Muestra los recursos asignados en esta zona */
    public void mostrarRecursos() {
        System.out.println("Zona: " + nombreZona);
        for (Map.Entry<String, Integer> entry : recursosAsignados.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }
    }

    /** Imprime toda la estructura jerárquica de la distribución (recursivamente) */
    public void imprimirEstructura(String prefijo) {
        System.out.println(prefijo + "Zona: " + nombreZona);
        for (Map.Entry<String, Integer> entry : recursosAsignados.entrySet()) {
            System.out.println(prefijo + "  Recurso: " + entry.getKey() + " -> " + entry.getValue());
        }
        for (NodoDistribucion sub : subZonas) {
            sub.imprimirEstructura(prefijo + "  ");
        }
    }
}
