package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.estructuras.ArbolDistribucion;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.ColaPrioridadEvacuaciones;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.GrafoDirigido;

import java.util.LinkedList;

public class SistemaGestionDesastres {

    String nombre;
    MapaRecursos mapaRecursos;
    GrafoDirigido grafoDirigido;
    ArbolDistribucion arbolDistribucion;
    LinkedList<Usuario> listaUsuarios;
    Administrador administrador;
    ColaPrioridadEvacuaciones colaPrioridadEvacuaciones;

    public SistemaGestionDesastres(String nombre) {
        this.nombre = nombre;
        this.mapaRecursos = new MapaRecursos();
        this.grafoDirigido = new GrafoDirigido();
        this.arbolDistribucion = new ArbolDistribucion();
        this.listaUsuarios = new LinkedList<>();
        this.administrador = null;
        this.colaPrioridadEvacuaciones = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MapaRecursos getMapaRecursos() {
        return mapaRecursos;
    }

    public void setMapaRecursos(MapaRecursos mapaRecursos) {
        this.mapaRecursos = mapaRecursos;
    }

    public GrafoDirigido getGrafoDirigido() {
        return grafoDirigido;
    }

    public void setGrafoDirigido(GrafoDirigido grafoDirigido) {
        this.grafoDirigido = grafoDirigido;
    }

    public ArbolDistribucion getArbolDistribucion() {
        return arbolDistribucion;
    }

    public void setArbolDistribucion(ArbolDistribucion arbolDistribucion) {
        this.arbolDistribucion = arbolDistribucion;
    }

    public LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ColaPrioridadEvacuaciones getColaPrioridadEvacuaciones() {
        return colaPrioridadEvacuaciones;
    }

    public void setColaPrioridadEvacuaciones(ColaPrioridadEvacuaciones colaPrioridadEvacuaciones) {
        this.colaPrioridadEvacuaciones = colaPrioridadEvacuaciones;
    }

}
