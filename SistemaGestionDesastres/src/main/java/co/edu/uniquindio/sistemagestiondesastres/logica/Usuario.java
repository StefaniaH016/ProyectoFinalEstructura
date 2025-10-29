package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.LinkedList;

public abstract class Usuario {

    String rol;
    String id;
    String nombre;
    String passwd;
    String numero;
    LinkedList<Reporte> listaReportes;

    public Usuario(String rol, String id, String nombre, String passwd, String numero) {
        this.rol = rol;
        this.id = id;
        this.nombre = nombre;
        this.passwd = passwd;
        this.numero = numero;
        this.listaReportes = new LinkedList<>();

    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LinkedList<Reporte> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(LinkedList<Reporte> listaReportes) {
        this.listaReportes = listaReportes;
    }


}
