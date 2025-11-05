package co.edu.uniquindio.sistemagestiondesastres.estructuras;

import java.util.PriorityQueue;

public class ColaPrioridadEvacuaciones {

    private static class ZonaAfectada implements Comparable<ZonaAfectada> {
        private String nombre;
        private int nivelUrgencia; // 1 = alta, 5 = baja
        private int personas;

        public ZonaAfectada(String nombre, int nivelUrgencia, int personas) {
            this.nombre = nombre;
            this.nivelUrgencia = nivelUrgencia;
            this.personas = personas;
        }

        public String getNombre() {
            return nombre;
        }

        public int getNivelUrgencia() {
            return nivelUrgencia;
        }

        public int getPersonas() {
            return personas;
        }

        @Override
        public int compareTo(ZonaAfectada o) {
            return Integer.compare(this.nivelUrgencia, o.nivelUrgencia);
        }

        @Override
        public String toString() {
            return nombre + " (Urgencia: " + nivelUrgencia + ", Personas: " + personas + ")";
        }
    }

    private PriorityQueue<ZonaAfectada> cola;

    public ColaPrioridadEvacuaciones() {
        cola = new PriorityQueue<>();
    }

    public void agregarZona(String nombre, int urgencia, int personas) {
        cola.add(new ZonaAfectada(nombre, urgencia, personas));
    }

    public ZonaAfectada atenderZona() {
        return cola.poll();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public void mostrarCola() {
        for (ZonaAfectada z : cola) {
            System.out.println(z);
        }
    }
}
