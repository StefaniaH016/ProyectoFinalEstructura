package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.TipoEquipo;

import java.util.List;

public class EquipoDeRescate {

    String idEquipo;
    TipoEquipo tipoEquipo;
    int miembros;
    String ubicacionActual;
    boolean disponible;
    List<String> especialidades;

    public EquipoDeRescate(String idEquipo, TipoEquipo tipoEquipo, int miembros, String ubicacionActual, boolean disponible, List<String> especialidades) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.miembros = miembros;
        this.ubicacionActual = ubicacionActual;
        this.disponible = disponible;
        this.especialidades = especialidades;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public int getMiembros() {
        return miembros;
    }

    public void setMiembros(int miembros) {
        this.miembros = miembros;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }


}
