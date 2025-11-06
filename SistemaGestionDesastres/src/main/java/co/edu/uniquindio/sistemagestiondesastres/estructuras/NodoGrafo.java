package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.*;

public class NodoGrafo {
    private String nombre; // Nombre de la ubicación
    private Map<NodoGrafo, Double> adyacentes; // Nodo destino y peso (distancia)
    private int nivelEmergencia; // Nivel de urgencia (1: bajo, 5: crítico)
    private int personasAfectadas; // Número de personas en la zona
    private Map<String, Integer> recursosNecesarios; // Recursos requeridos (alimentos, medicinas, etc.)

    public NodoGrafo(String nombre, int nivelEmergencia, int personasAfectadas) {
        this.nombre = nombre;
        this.nivelEmergencia = nivelEmergencia;
        this.personasAfectadas = personasAfectadas;
        this.adyacentes = new HashMap<>();
        this.recursosNecesarios = new HashMap<>();
    }

    // ----- Métodos básicos -----

    public String getNombre() {
        return nombre;
    }

    public int getNivelEmergencia() {
        return nivelEmergencia;
    }

    public void setNivelEmergencia(int nivelEmergencia) {
        this.nivelEmergencia = nivelEmergencia;
    }

    public int getPersonasAfectadas() {
        return personasAfectadas;
    }

    public void setPersonasAfectadas(int personasAfectadas) {
        this.personasAfectadas = personasAfectadas;
    }

    public Map<NodoGrafo, Double> getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(NodoGrafo destino, double distancia) {
        adyacentes.put(destino, distancia);
    }

    public void eliminarAdyacente(NodoGrafo destino) {
        adyacentes.remove(destino);
    }

    public Map<String, Integer> getRecursosNecesarios() {
        return recursosNecesarios;
    }

    public void agregarRecursoNecesario(String recurso, int cantidad) {
        recursosNecesarios.put(recurso, cantidad);
    }

    public void actualizarRecurso(String recurso, int cantidad) {
        recursosNecesarios.replace(recurso, cantidad);
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "nombre='" + nombre + '\'' +
                ", nivelEmergencia=" + nivelEmergencia +
                ", personasAfectadas=" + personasAfectadas +
                ", adyacentes=" + adyacentes.keySet().stream().map(NodoGrafo::getNombre).toList() +
                '}';
    }
}