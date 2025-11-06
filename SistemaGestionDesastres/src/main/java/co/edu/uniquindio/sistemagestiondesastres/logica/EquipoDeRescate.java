package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoEquipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoDeRescate {

    private String idEquipo;
    private TipoEquipo tipoEquipo;
    private int miembros;
    private String ubicacionActual;
    private boolean disponible;
    private List<String> especialidades;

    // --- Constructor ---
    public EquipoDeRescate(String idEquipo, TipoEquipo tipoEquipo, int miembros,
                           String ubicacionActual, boolean disponible, List<String> especialidades) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.miembros = miembros;
        this.ubicacionActual = ubicacionActual;
        this.disponible = disponible;
        this.especialidades = (especialidades != null) ? especialidades : new ArrayList<>();
    }

    // --- Métodos de negocio ---

    public void asignarMision(String nuevaUbicacion) {
        if (disponible) {
            this.ubicacionActual = nuevaUbicacion;
            this.disponible = false;
            System.out.println("✅ Equipo " + idEquipo + " asignado a misión en " + nuevaUbicacion);
        } else {
            System.out.println("⚠️ Equipo " + idEquipo + " no disponible actualmente.");
        }
    }

    public void finalizarMision() {
        if (!disponible) {
            this.disponible = true;
            System.out.println("✅ Equipo " + idEquipo + " ha completado su misión y está disponible nuevamente.");
        } else {
            System.out.println("ℹ️ El equipo " + idEquipo + " ya estaba disponible.");
        }
    }

    public void moverA(String nuevaUbicacion) {
        this.ubicacionActual = nuevaUbicacion;
        System.out.println("🚚 Equipo " + idEquipo + " movido a " + nuevaUbicacion);
    }

    public void agregarEspecialidad(String especialidad) {
        if (!especialidades.contains(especialidad)) {
            especialidades.add(especialidad);
            System.out.println("🔧 Especialidad '" + especialidad + "' agregada al equipo " + idEquipo);
        }
    }

    public boolean tieneEspecialidad(String especialidad) {
        return especialidades.contains(especialidad);
    }

    public void mostrarDetalles() {
        System.out.println("\n=== Equipo de Rescate " + idEquipo + " ===");
        System.out.println("Tipo: " + tipoEquipo);
        System.out.println("Miembros: " + miembros);
        System.out.println("Ubicación actual: " + ubicacionActual);
        System.out.println("Disponible: " + (disponible ? "Sí" : "No"));
        System.out.println("Especialidades: " + String.join(", ", especialidades));
    }

    // --- Getters y Setters ---
    public String getIdEquipo() { return idEquipo; }
    public TipoEquipo getTipoEquipo() { return tipoEquipo; }
    public int getMiembros() { return miembros; }
    public String getUbicacionActual() { return ubicacionActual; }
    public boolean isDisponible() { return disponible; }
    public List<String> getEspecialidades() { return especialidades; }

    public void setDisponibilidad(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Equipo{" +
                "id='" + idEquipo + '\'' +
                ", tipo=" + tipoEquipo +
                ", miembros=" + miembros +
                ", ubicacion='" + ubicacionActual + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
