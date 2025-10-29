package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.LinkedList;

public class ZonaAfectada {

    String idZonaAfectada;
    String nombreZonaAfectada;
    int poblacion;
    int nivelDeRiesgo;
    Ruta ruta;
    LinkedList<Recurso> listaRecursos;
    LinkedList<EquipoDeRescate> listaEquipoDeRescates;
    LinkedList<Evacuacion> evacuaciones;

    public ZonaAfectada(String idZonaAfectada, String nombreZonaAfectada, int poblacion, int nivelRiesgo) {
        this.idZonaAfectada = idZonaAfectada;
        this.nombreZonaAfectada = nombreZonaAfectada;
        this.poblacion = poblacion;
        this.nivelDeRiesgo = nivelRiesgo;
        this.ruta = new Ruta();
        this.listaRecursos = new LinkedList<>();
        this.listaEquipoDeRescates = new LinkedList<>();
        this.evacuaciones = new LinkedList<>();

    }

    public String getIdZonaAfectada() {
        return idZonaAfectada;
    }

    public void setIdZonaAfectada(String idZonaAfectada) {
        this.idZonaAfectada = idZonaAfectada;
    }

    public String getNombreZonaAfectada() {
        return nombreZonaAfectada;
    }

    public void setNombreZonaAfectada(String nombreZonaAfectada) {
        this.nombreZonaAfectada = nombreZonaAfectada;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getNivelDeRiesgo() {
        return nivelDeRiesgo;
    }

    public void setNivelDeRiesgo(int nivelDeRiesgo) {
        this.nivelDeRiesgo = nivelDeRiesgo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public LinkedList<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(LinkedList<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public LinkedList<EquipoDeRescate> getListaEquipoDeRescates() {
        return listaEquipoDeRescates;
    }

    public LinkedList<Evacuacion> getEvacuaciones() {
        return evacuaciones;
    }

    public void setEvacuaciones(LinkedList<Evacuacion> listaEvacuaciones) {
        this.evacuaciones = listaEvacuaciones;
    }

    public void setListaEquipoDeRescates(LinkedList<EquipoDeRescate> listaEquipoDeRescates) {
        this.listaEquipoDeRescates = listaEquipoDeRescates;
    }

}
