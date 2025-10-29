package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.time.LocalDate;

public class Reporte {

    LocalDate fecha;
    String contenido;
    Usuario usuarioGenerador;

    public Reporte(LocalDate fecha, String contenido, Usuario usuarioGenerador) {
        this.fecha = fecha;
        this.contenido = contenido;
        this.usuarioGenerador = usuarioGenerador;
    }

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


}
