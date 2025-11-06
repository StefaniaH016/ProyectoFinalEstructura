package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.EstadoEvacuacion;
public class Evacuacion implements Comparable<Evacuacion> {

    private String id;
    private String idZona;
    private int prioridad; // entre mayor el valor, más urgente
    private EstadoEvacuacion estado;
    private int personasEvacuadas;
    private int personasTotales;
    private String rutaId;
    private Ruta ruta;

    // --- Constructor ---
    public Evacuacion(String id, String idZona, int prioridad, EstadoEvacuacion estado,
                      int personasEvacuadas, int personasTotales, String rutaId, Ruta ruta) {
        this.id = id;
        this.idZona = idZona;
        this.prioridad = prioridad;
        this.estado = estado;
        this.personasEvacuadas = personasEvacuadas;
        this.personasTotales = personasTotales;
        this.rutaId = rutaId;
        this.ruta = ruta;
    }

    // --- Métodos de negocio ---

    public void iniciarEvacuacion() {
        if (estado == EstadoEvacuacion.PENDIENTE) {
            estado = EstadoEvacuacion.EN_PROCESO;
            System.out.println("🚨 Evacuación " + id + " iniciada en zona " + idZona);
        } else {
            System.out.println("⚠️ Evacuación " + id + " no puede iniciarse (estado actual: " + estado + ")");
        }
    }

    public void registrarAvance(int personasEvacuadasAhora) {
        if (estado == EstadoEvacuacion.EN_PROCESO) {
            personasEvacuadas += personasEvacuadasAhora;
            if (personasEvacuadas >= personasTotales) {
                personasEvacuadas = personasTotales;
                completarEvacuacion();
            } else {
                System.out.println("👥 Avance registrado en evacuación " + id +
                        ": " + personasEvacuadas + "/" + personasTotales + " evacuadas.");
            }
        } else {
            System.out.println("⚠️ No se puede registrar avance, la evacuación no está en proceso.");
        }
    }

    public void completarEvacuacion() {
        estado = EstadoEvacuacion.COMPLETADA;
        System.out.println("✅ Evacuación " + id + " completada exitosamente. Personas evacuadas: " + personasTotales);
    }

    public void cancelarEvacuacion() {
        estado = EstadoEvacuacion.CANCELADA;
        System.out.println("❌ Evacuación " + id + " cancelada.");
    }

    public double calcularProgreso() {
        return (personasTotales > 0)
                ? (personasEvacuadas * 100.0 / personasTotales)
                : 0;
    }

    public void mostrarDetalles() {
        System.out.println("\n=== Evacuación " + id + " ===");
        System.out.println("Zona: " + idZona);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Estado: " + estado);
        System.out.println("Personas evacuadas: " + personasEvacuadas + "/" + personasTotales);
        System.out.println("Ruta asignada: " + (ruta != null ? ruta.toString() : "No asignada"));
    }

    // --- Getters y Setters ---
    public String getId() { return id; }
    public String getIdZona() { return idZona; }
    public int getPrioridad() { return prioridad; }
    public EstadoEvacuacion getEstado() { return estado; }
    public int getPersonasEvacuadas() { return personasEvacuadas; }
    public int getPersonasTotales() { return personasTotales; }
    public String getRutaId() { return rutaId; }
    public Ruta getRuta() { return ruta; }

    @Override
    public int compareTo(Evacuacion otra) {
        // La prioridad más alta debe salir primero en la cola
        return Integer.compare(otra.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        return "Evacuacion{" +
                "id='" + id + '\'' +
                ", zona='" + idZona + '\'' +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", progreso=" + String.format("%.1f", calcularProgreso()) + "%" +
                '}';
    }
}
