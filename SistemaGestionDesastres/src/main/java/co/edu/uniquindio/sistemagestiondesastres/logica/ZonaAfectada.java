package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoRecurso;

import java.util.ArrayList;
import java.util.List;

public class ZonaAfectada {

    // 🔹 Atributos principales
    private String nombre;
    private int nivelRiesgo;          // Escala 1–10
    private int personasAfectadas;
    private List<Recurso> recursosAsignados;
    private String estado;            // Ej: “En riesgo”, “Evacuando”, “Estable”

    // -------------------------------
    // CONSTRUCTOR
    // -------------------------------
    public ZonaAfectada(String nombre, int nivelRiesgo, int personasAfectadas) {
        this.nombre = nombre;
        this.nivelRiesgo = nivelRiesgo;
        this.personasAfectadas = personasAfectadas;
        this.recursosAsignados = new ArrayList<>();
        this.estado = determinarEstado();
    }

    // -------------------------------
    // MÉTODOS DE LÓGICA
    // -------------------------------

    // Actualiza el estado según el nivel de riesgo
    private String determinarEstado() {
        if (nivelRiesgo >= 8) return "Crítico";
        else if (nivelRiesgo >= 5) return "En riesgo";
        else if (nivelRiesgo >= 3) return "Vigilancia";
        else return "Estable";
    }

    public void actualizarEstado() {
        this.estado = determinarEstado();
    }

    // Asigna un recurso a la zona
    public void agregarRecursoAsignado(Recurso recurso) {
        recursosAsignados.add(recurso);
    }

    // Suma la cantidad total de recursos asignados
    public int obtenerTotalRecursos() {
        int total = 0;
        for (Recurso r : recursosAsignados) {
            total += r.getCantidad();
        }
        return total;
    }

    // -------------------------------
    // GETTERS Y SETTERS
    // -------------------------------
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNivelRiesgo() { return nivelRiesgo; }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
        actualizarEstado();
    }

    public int getPersonasAfectadas() { return personasAfectadas; }

    public void setPersonasAfectadas(int personasAfectadas) {
        this.personasAfectadas = personasAfectadas;
    }

    public List<Recurso> getRecursosAsignados() { return recursosAsignados; }

    public void setRecursosAsignados(List<Recurso> recursosAsignados) {
        this.recursosAsignados = recursosAsignados;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    // -------------------------------
    // REPRESENTACIÓN EN TEXTO
    // -------------------------------
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
