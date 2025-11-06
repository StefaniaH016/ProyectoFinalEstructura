package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;
import co.edu.uniquindio.sistemagestiondesastres.logica.enums.*;

public class MapaRecursos {

    private Map<String, Map<String, Integer>> inventario;
    private LinkedList<Recurso> listRecursos;

    // --- Constructor ---
    public MapaRecursos() {
        inventario = new HashMap<>();
        listRecursos = new LinkedList<>();
    }

    // --- Métodos de negocio ---

    public void registrarRecurso(Recurso recurso) {
        listRecursos.add(recurso);
        inventario.putIfAbsent(recurso.getUbicacion(), new HashMap<>());

        Map<String, Integer> recursosZona = inventario.get(recurso.getUbicacion());
        String tipo = recurso.getTipo().toString();
        recursosZona.put(tipo, recursosZona.getOrDefault(tipo, 0) + recurso.getCantidad());

        System.out.println("✅ Recurso " + recurso.getId() + " registrado en " + recurso.getUbicacion());
    }

    public List<Recurso> buscarPorZona(String zona) {
        List<Recurso> encontrados = new ArrayList<>();
        for (Recurso r : listRecursos) {
            if (r.getUbicacion().equalsIgnoreCase(zona)) {
                encontrados.add(r);
            }
        }
        return encontrados;
    }

    public List<Recurso> buscarPorTipo(TipoRecurso tipo) {
        List<Recurso> encontrados = new ArrayList<>();
        for (Recurso r : listRecursos) {
            if (r.getTipo() == tipo) {
                encontrados.add(r);
            }
        }
        return encontrados;
    }

    public Recurso obtenerRecursoPrioritario(TipoRecurso tipo) {
        return listRecursos.stream()
                .filter(r -> r.getTipo() == tipo && r.disponible())
                .max(Comparator.naturalOrder()) // usa compareTo (prioridad)
                .orElse(null);
    }

    public void transferirRecurso(String idRecurso, String nuevaZona) {
        for (Recurso r : listRecursos) {
            if (r.getId().equals(idRecurso)) {
                actualizarInventario(r.getUbicacion(), r.getTipo(), -r.getCantidad());
                r.setUbicacion(nuevaZona);
                actualizarInventario(nuevaZona, r.getTipo(), r.getCantidad());
                System.out.println("🚚 Recurso " + idRecurso + " transferido a " + nuevaZona);
                return;
            }
        }
        System.out.println("⚠️ No se encontró el recurso con ID " + idRecurso);
    }

    private void actualizarInventario(String zona, TipoRecurso tipo, int cambio) {
        inventario.putIfAbsent(zona, new HashMap<>());
        Map<String, Integer> recursosZona = inventario.get(zona);
        String tipoStr = tipo.toString();
        recursosZona.put(tipoStr, recursosZona.getOrDefault(tipoStr, 0) + cambio);
        if (recursosZona.get(tipoStr) <= 0) recursosZona.remove(tipoStr);
    }

    public void mostrarInventario() {
        System.out.println("\n=== Inventario General de Recursos ===");
        for (String zona : inventario.keySet()) {
            System.out.println("📍 Zona: " + zona);
            Map<String, Integer> recursosZona = inventario.get(zona);
            for (String tipo : recursosZona.keySet()) {
                System.out.println("   - " + tipo + ": " + recursosZona.get(tipo));
            }
        }
    }

    public void eliminarRecurso(String id) {
        Recurso eliminado = null;
        for (Recurso r : listRecursos) {
            if (r.getId().equals(id)) {
                eliminado = r;
                break;
            }
        }
        if (eliminado != null) {
            listRecursos.remove(eliminado);
            actualizarInventario(eliminado.getUbicacion(), eliminado.getTipo(), -eliminado.getCantidad());
            System.out.println("🗑️ Recurso " + id + " eliminado del sistema.");
        } else {
            System.out.println("⚠️ No se encontró el recurso con ID " + id);
        }
    }

    public LinkedList<Recurso> getListRecursos() {
        return listRecursos;
    }

    public Map<String, Map<String, Integer>> getInventario() {
        return inventario;
    }
}
