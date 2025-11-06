package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoRecurso;

public class Recurso implements Comparable<Recurso> {

    private String id;
    private String nombre;
    private TipoRecurso tipo;
    private int cantidad;
    private String ubicacion;
    private int prioridad; // 1 = baja, 5 = alta

    // --- Constructor ---
    public Recurso(String id, TipoRecurso tipo, int cantidad, String ubicacion, int prioridad, String nombre) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    // --- Métodos de negocio ---
    public void consumir(int cantidadUsada) {
        if (cantidadUsada <= cantidad && cantidadUsada > 0) {
            cantidad -= cantidadUsada;
            System.out.println("💧 Se usaron " + cantidadUsada + " unidades del recurso " + id +
                    ". Restan " + cantidad + ".");
        } else {
            System.out.println("⚠️ No hay suficiente cantidad para consumir en el recurso " + id);
        }
    }

    public void reabastecer(int cantidadAgregada) {
        if (cantidadAgregada > 0) {
            cantidad += cantidadAgregada;
            System.out.println("📦 Recurso " + id + " reabastecido con " + cantidadAgregada +
                    " unidades. Total: " + cantidad);
        }
    }

    public boolean disponible() {
        return cantidad > 0;
    }

    public void mostrarDetalles() {
        System.out.println("\n=== Recurso " + id + " ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Prioridad: " + prioridad);
    }

    // --- Getters y Setters ---
    public String getId() { return id; }
    public TipoRecurso getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public String getUbicacion() { return ubicacion; }
    public int getPrioridad() { return prioridad; }

    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    @Override
    public int compareTo(Recurso otro) {
        // Mayor prioridad primero
        return Integer.compare(otro.prioridad, this.prioridad);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", cantidad=" + cantidad +
                ", ubicacion='" + ubicacion + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}
