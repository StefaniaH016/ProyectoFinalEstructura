package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.PriorityQueue;
import java.util.Iterator;
import co.edu.uniquindio.sistemagestiondesastres.logica.ZonaEvacuacion;

public class ColaPrioridadEvacuaciones {

    private PriorityQueue<ZonaEvacuacion> colaEvacuaciones;

    // --- Constructor ---
    public ColaPrioridadEvacuaciones() {
        colaEvacuaciones = new PriorityQueue<>();
    }

    // --- Métodos principales ---

    /** Permite agregar una zona directamente. */
    public void agregarZonaEvacuacion(ZonaEvacuacion z) {
        if (z == null) return;
        colaEvacuaciones.add(z);
        System.out.println("Zona agregada: " + z.getNombreZona());
    }

    /** Permite agregar una zona a partir de un nodo del grafo. */
    public void agregarZonaEvacuacion(NodoGrafo nodo) {
        if (nodo == null) return;
        ZonaEvacuacion z = crearZonaEvacuacionDesdeNodo(nodo);
        agregarZonaEvacuacion(z); // ✅ ahora este método existe
    }

    /** Convierte un NodoGrafo en una ZonaEvacuacion (adaptador). */
    private ZonaEvacuacion crearZonaEvacuacionDesdeNodo(NodoGrafo nodo) {
        // Ajusta según tu constructor de ZonaEvacuacion
        return new ZonaEvacuacion(
                nodo.getNombre(),          // idZona
                nodo.getNombre(),          // nombreZona
                nodo.getPersonasAfectadas(), // personasAevacuar
                nodo.getNivelEmergencia()    // nivelUrgencia
        );
    }

    /** Obtiene la siguiente zona con mayor prioridad de evacuación (sin eliminarla) */
    public ZonaEvacuacion verSiguienteZona() {
        return colaEvacuaciones.peek();
    }

    /** Extrae y devuelve la zona con mayor prioridad (la que se evacúa a continuación) */
    public ZonaEvacuacion evacuarZonaPrioritaria() {
        ZonaEvacuacion zona = colaEvacuaciones.poll();
        if (zona != null) {
            System.out.println("🚨 Evacuando zona prioritaria: " + zona.getNombreZona());
        } else {
            System.out.println("⚠️ No hay zonas pendientes de evacuación.");
        }
        return zona;
    }

    /** Actualiza el nivel de urgencia de una zona ya en la cola */
    public boolean actualizarUrgencia(String idZona, int nuevoNivel) {
        PriorityQueue<ZonaEvacuacion> temporal = new PriorityQueue<>();
        boolean encontrada = false;

        while (!colaEvacuaciones.isEmpty()) {
            ZonaEvacuacion actual = colaEvacuaciones.poll();
            if (actual.getIdZona().equalsIgnoreCase(idZona)) {
                actual.setNivelUrgencia(nuevoNivel);
                encontrada = true;
            }
            temporal.offer(actual);
        }

        colaEvacuaciones = temporal;
        return encontrada;
    }

    /** Muestra todas las zonas pendientes en la cola */
    public void mostrarCola() {
        if (colaEvacuaciones.isEmpty()) {
            System.out.println("⚠️ No hay zonas en espera de evacuación.");
            return;
        }

        System.out.println("🧭 Zonas pendientes de evacuación (por prioridad):");
        Iterator<ZonaEvacuacion> it = colaEvacuaciones.iterator();
        while (it.hasNext()) {
            System.out.println(" - " + it.next());
        }
    }

    /** Elimina una zona específica de la cola */
    public boolean eliminarZona(String idZona) {
        Iterator<ZonaEvacuacion> it = colaEvacuaciones.iterator();
        while (it.hasNext()) {
            ZonaEvacuacion zona = it.next();
            if (zona.getIdZona().equalsIgnoreCase(idZona)) {
                it.remove();
                System.out.println("🗑️ Zona eliminada de la cola: " + zona.getNombreZona());
                return true;
            }
        }
        return false;
    }

    /** Devuelve el número total de zonas pendientes de evacuación */
    public int contarZonasPendientes() {
        return colaEvacuaciones.size();
    }

    /** Verifica si la cola está vacía */
    public boolean estaVacia() {
        return colaEvacuaciones.isEmpty();
    }

    /** Limpia toda la cola (reinicia el sistema de evacuación) */
    public void limpiarCola() {
        colaEvacuaciones.clear();
        System.out.println("🧹 Cola de evacuaciones reiniciada.");
    }

    public ZonaEvacuacion obtenerZonaPrioritaria() {
        return colaEvacuaciones.poll(); // puede devolver null si está vacía
    }
}
