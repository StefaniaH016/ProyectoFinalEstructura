package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.time.LocalDate;

public class Reporte {

    private LocalDate fecha;
    private String contenido;
    private Usuario usuarioGenerador;

    public Reporte(LocalDate fecha, String contenido, Usuario usuarioGenerador) {
        this.fecha = fecha;
        this.contenido = contenido;
        this.usuarioGenerador = usuarioGenerador;
    }

    // ===== Métodos Getters y Setters =====
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getUsuarioGenerador() {
        return usuarioGenerador;
    }

    public void setUsuarioGenerador(Usuario usuarioGenerador) {
        this.usuarioGenerador = usuarioGenerador;
    }

    // ===== Método para generar un reporte automático =====
    public void generarReporte(String evento, String detalles) {
        this.contenido = "Evento: " + evento + "\nDetalles: " + detalles +
                "\nGenerado por: " + usuarioGenerador.getNombre() +
                " (" + usuarioGenerador.getRol() + ")\n" +
                "Fecha: " + fecha.toString();
    }

    // ===== Método para mostrar el reporte =====
    public void mostrarReporte() {
        System.out.println("=== REPORTE DE EMERGENCIA ===");
        System.out.println("Fecha: " + fecha);
        System.out.println("Generado por: " + usuarioGenerador.getNombre());
        System.out.println("Contenido:\n" + contenido);
        System.out.println("==============================");
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "fecha=" + fecha +
                ", usuarioGenerador=" + usuarioGenerador.getNombre() +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
