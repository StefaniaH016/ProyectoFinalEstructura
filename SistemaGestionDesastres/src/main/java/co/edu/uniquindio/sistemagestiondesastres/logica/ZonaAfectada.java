package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.ArrayList;
import java.util.List;

public class ZonaAfectada {

    private String nombre;
    private int nivelRiesgo;          // Escala 1–10
    private int personasAfectadas;
    private List<Recurso> recursosAsignados;
    private String estado;            // Ej: “En riesgo”, “Evacuando”, “Estable”

    public ZonaAfectada(String nombre, int nivelRiesgo, int personasAfectadas, String estado) {
        this.nombre = nombre;
        this.nivelRiesgo = nivelRiesgo;
        this.personasAfectadas = personasAfectadas;
        this.estado = estado;
        this.recursosAsignados = new ArrayList<>();
    }

    // ======== Getters y Setters ========

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        if (nivelRiesgo >= 1 && nivelRiesgo <= 10) {
            this.nivelRiesgo = nivelRiesgo;
        } else {
            System.out.println("⚠️ El nivel de riesgo debe estar entre 1 y 10.");
        }
    }

    public int getPersonasAfectadas() {
        return personasAfectadas;
    }

    public void setPersonasAfectadas(int personasAfectadas) {
        this.personasAfectadas = personasAfectadas;
    }

    public List<Recurso> getRecursosAsignados() {
        return recursosAsignados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ======== Métodos funcionales ========

    /** Asigna un recurso a la zona **/
    public void asignarRecurso(Recurso recurso) {
        if (!recursosAsignados.contains(recurso)) {
            recursosAsignados.add(recurso);
            System.out.println("✅ Recurso " + recurso.getId() + " asignado a la zona " + nombre);
        } else {
            System.out.println("⚠️ El recurso " + recurso.getId() + " ya está asignado a esta zona.");
        }
    }

    /** Elimina un recurso de la zona **/
    public void eliminarRecurso(Recurso recurso) {
        recursosAsignados.remove(recurso);
        System.out.println("🗑️ Recurso " + recurso.getId() + " removido de la zona " + nombre);
    }

    /** Calcula el nivel de prioridad de atención basado en riesgo y personas afectadas **/
    public int calcularPrioridad() {
        int prioridad = (nivelRiesgo * 2) + (personasAfectadas / 50);
        return Math.min(prioridad, 10); // Se limita a un máximo de 10
    }

    /** Evalúa si la zona necesita evacuación inmediata **/
    public boolean necesitaEvacuacion() {
        return nivelRiesgo >= 7 || personasAfectadas > 1000;
    }

    /** Actualiza el estado automáticamente según el nivel de riesgo **/
    public void actualizarEstado() {
        if (nivelRiesgo >= 8) {
            estado = "Evacuando";
        } else if (nivelRiesgo >= 5) {
            estado = "En riesgo";
        } else {
            estado = "Estable";
        }
    }

    /** Muestra información detallada de la zona **/
    public void mostrarInfoZona() {
        System.out.println("=== Información de Zona Afectada ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Nivel de Riesgo: " + nivelRiesgo);
        System.out.println("Personas Afectadas: " + personasAfectadas);
        System.out.println("Estado: " + estado);
        System.out.println("Recursos Asignados: " + recursosAsignados.size());
        System.out.println("Prioridad: " + calcularPrioridad());
        System.out.println("====================================");
    }

    @Override
    public String toString() {
        return "ZonaAfectada{" +
                "nombre='" + nombre + '\'' +
                ", nivelRiesgo=" + nivelRiesgo +
                ", personasAfectadas=" + personasAfectadas +
                ", estado='" + estado + '\'' +
                ", recursosAsignados=" + recursosAsignados.size() +
                '}';
    }
}
