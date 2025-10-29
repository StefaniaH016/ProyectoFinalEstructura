package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.logica.enums.EstadoEvacuacion;

public class Evacuacion {

    String id;
    String idZona;
    int prioridad;
    EstadoEvacuacion estado;
    int personasEvacuadas;
    int personasTotales;
    String rutaId;
    Ruta ruta;

    public Evacuacion(String id, String idZona, int prioridad, EstadoEvacuacion estado, int personasEvacuadas, int personasTotales, String rutaId, Ruta ruta) {
        this.id = id;
        this.idZona = idZona;
        this.prioridad = prioridad;
        this.estado = estado;
        this.personasEvacuadas = personasEvacuadas;
        this.personasTotales = personasTotales;
        this.rutaId = rutaId;
        this.ruta = ruta;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoEvacuacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoEvacuacion estado) {
        this.estado = estado;
    }

    public int getPersonasEvacuadas() {
        return personasEvacuadas;
    }

    public void setPersonasEvacuadas(int personasEvacuadas) {
        this.personasEvacuadas = personasEvacuadas;
    }

    public int getPersonasTotales() {
        return personasTotales;
    }

    public void setPersonasTotales(int personasTotales) {
        this.personasTotales = personasTotales;
    }

    public String getRutaId() {
        return rutaId;
    }

    public void setRutaId(String rutaId) {
        this.rutaId = rutaId;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }




}
